package saveload;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import saveload.ByteCrypto;
import saveload.GameStateSerializer;

/**
 * FileSystemManager saves Game states to files, and loads Game states from
 * files. Also encrypts/decrypts files.
 * 
 * @author Alec James
 */
public class FileSystemManager
{
    /** Encryption/Decryption engine. */
    private ByteCrypto crypto;
    
    /** Save file root directory. */
    private String saveFileDir;
    
    /** Extension for save files. */
    private static final String EXT = ".save";
    
    /**
     * Makes a new file system manager. The username makes a part of the
     * save file directory structure, while the password is given to the crypto
     * engine. Passwords must be the same to properly load saved files.
     * 
     * @param user the user's username
     * @param pass the user's password
     */
    public FileSystemManager(String user, String pass)
    {
        //don't allow empty Strings
        if("".equals(user) || "".equals(pass))
        {
            throw new IllegalArgumentException();
        }
        
        //set up directories
        saveFileDir = System.getProperty("user.dir") + "/saves/"
                + user + "/";
        
        //set up crypto
        try
        {
            crypto = new ByteCrypto(pass);
        }
        catch(Exception ex)
        {
            throw new IllegalArgumentException(ex);
        }
        
        //create save file directories
        new File(saveFileDir).mkdirs();
    }
    
    /**
     * Saves the passed in game state to the specified external file.
     * 
     * @param saveFileName name of the save file
     * @param state the game state to save
     * @return false if file was created for the 1st time, true if file was
     * overwritten
     * @throws IllegalArgumentException shouldn't
     */
    public boolean saveToFile(String saveFileName, GameState state)
    {
        boolean overwritten = false;
        
        try
        {
            byte[] game = new GameStateSerializer(state).serialize();
        
            //encrypt the serialized game
            String toSave = crypto.encrypt(game);
        
            //create the save file
            File saveFile = new File(saveFileDir + saveFileName + EXT);
            
            //did we overwrite?
            if(saveFile.exists())
            {
                saveFile.delete();
                overwritten = true;
            }
            
            saveFile.createNewFile();
        
            //save the encrypted game to the specified file
            PrintWriter wtr = new PrintWriter(saveFile);
            wtr.print(toSave);
            wtr.close();
        }
        catch(Exception ex)
        {
            throw new IllegalArgumentException(ex);
        }
        
        return overwritten;
    }
    
    /**
     * Loads the specified save game file.
     * 
     * @param toLoad name of save file to load
     * @return the loaded game state
     * @throws FileNotFoundException if the specified file does not exist
     */
    public GameState loadFile(String toLoad) throws FileNotFoundException
    {
        //get the file to load
        File saveToLoad = new File(saveFileDir + toLoad + EXT);
        
        //make a scanner from the file
        Scanner in = new Scanner(saveToLoad);
        String encSavedData = "";
        
        //read it in
        while(in.hasNext())
        {
            encSavedData += in.next() + " ";
        }
        
        in.close();
        
        encSavedData = encSavedData.trim();
        
        //decrypt it
        byte[] decSavedData = crypto.decrypt(encSavedData);
        
        //deserialize it & return
        GameStateSerializer ser = new GameStateSerializer(null);
        ser.deserialize(decSavedData);
        return ser.getGameState();
    }
    
    /**
     * Returns the save file directory.
     * @return the save file directory
     */
    public String getSaveFileDir()
    {
        return saveFileDir;
    }
}