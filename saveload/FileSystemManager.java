package saveload;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * FileSystemManager saves Game states to files, and loads Game states from
 * files. Also encrypts/decrypts files.
 * 
 * @author Alec James
 */
class FileSystemManager
{
    /** Encryption/Decryption engine. */
    private ByteCrypto crypto;
    
    /** Save file root directory. */
    private String saveFileDir;
    
    /** Extension for save files. */
    private final static String EXT = ".save";
    
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
        //set up directories
        saveFileDir = System.getProperty("user.dir") + "/saves/"
                + user + "/";
        
        //set up crypto
        crypto = new ByteCrypto(pass);
        
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
        throws IllegalArgumentException
    {
        boolean overwritten = false;
        
        try
        {
            //make a serializer and serialize the game
            byte[] game = new GameStateSerializer(state).serialize();
        
            //encrypt the serialized game
            String toSave = crypto.encrypt(game);
        
            //create the save file
            File saveFile = new File(saveFileDir + saveFileName);
            
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
            throw new IllegalArgumentException();
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
        File saveToLoad = new File(saveFileDir + toLoad);
        
        //make a scanner from the file
        Scanner in = new Scanner(saveToLoad);
        String encSavedData = "";
        
        //read it in
        while(in.hasNext())
        {
            encSavedData += in.next() + " ";
        }
        
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