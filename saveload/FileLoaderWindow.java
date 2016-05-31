package saveload;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileSystemView;

/**
 * FileLoaderWindow is a GUI wrapper for FileSystemManager that handles saving
 * and loading exceptions.
 * 
 * @author Alec James
 */
public class FileLoaderWindow
{
    /** File System Manager. */
    private FileSystemManager fileSys;
    
    /** Error logger. */
    private static final Logger log = 
        Logger.getLogger(FileLoaderWindow.class.getName());
    
    /**
     * Provides the username and password to the internal FileSystemManager.
     * @param user the username
     * @param pass the password
     */
    public FileLoaderWindow(String user, String pass)
    {
        fileSys = new FileSystemManager(user, pass);
    }
    
    /**
     * Saves a file. The user specifies the save file name.
     * @param state the GameState to save
     */
    public void save(GameState state)
    {
        JFrame msgFrame = new JFrame();
        
        //need to catch exceptions
        try
        {
            //display a warning message that says overwriting is possible
            JOptionPane.showMessageDialog(msgFrame,
                "Warning: overwriting save files is possible!",
                "Note",
                JOptionPane.WARNING_MESSAGE);
        
            String saveName = "";
            
            //get the save file name
            while("".equals(saveName))
            {
                saveName = JOptionPane.showInputDialog(msgFrame,
                    "Enter the name for this save: ",
                    "Enter save name",
                    JOptionPane.QUESTION_MESSAGE);
            
                //clicking cancel nullifies saveName
                if(saveName == null)
                {
                    saveName = "";
                }
            }
        
            //pass the name and state to save in the FSM
            boolean overwritten = fileSys.saveToFile(saveName, state);
        
            //display another window if a save file was overwritten
            if(overwritten)
            {
                JOptionPane.showMessageDialog(msgFrame,
                    "Overwrote save " +  saveName,
                    "Notice",
                    JOptionPane.INFORMATION_MESSAGE);
            }
        
            msgFrame.dispose();
        }
        //on a caught exception, display an error
        catch(Exception ex)
        {
            log.log(Level.SEVERE, "Crypto error!");
            JOptionPane.showMessageDialog(msgFrame,
                "Error: cannot save file",
                "Error",
                JOptionPane.ERROR_MESSAGE);
        }
        
        msgFrame.dispose();
    }
    
    /**
     * Loads a file of the user's choice.
     * @return loaded Game state
     */
    public GameState load()
    {
        GameState out = null;
        
        try
        {
            //open a file chooser locked in the save file directory
            FileSystemView view = new SingleRootFileSystemView(
                new File(fileSys.getSaveFileDir()));
            JFileChooser loadChooser = 
                new JFileChooser(view);
        
            boolean selectedAFile = false;
            String fileName = "";
            
            //get the user's choice
            while(!selectedAFile)
            {
                //user picked a file
                if(loadChooser.showOpenDialog(null) == 
                    JFileChooser.APPROVE_OPTION)
                {
                    File fileToLoad = loadChooser.getSelectedFile();
                    fileName = fileToLoad.getName();
                    selectedAFile = true;
                }
            }
            
            //load it
            out = fileSys.loadFile(fileName);
        }
        catch(Exception ex)
        {
            log.log(Level.SEVERE, "Crypto Error!");
            JFrame frame = new JFrame();
            JOptionPane.showMessageDialog(frame,
                "Error: cannot load file",
                "Error",
                JOptionPane.ERROR_MESSAGE);
            frame.dispose();
        }
        
        return out;
    }
}