package saveload;

import saveload.GameState;
import saveload.FileLoaderWindow;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A listener for saving the game.
 * 
 * @author Alec James
 */
public class SaveListener implements ActionListener
{
    /** the game state. */
    private GameState state;
    
    /** the FileLoaderWindow. */
    private FileLoaderWindow window;
    
    /**
     * Constructor. Sets the Game state.
     * @param user the username
     * @param pass the password
     * @param state the game state that will be saved
     */
    public SaveListener(String user, String pass, GameState state)
    {
        this.state = state;
        window = new FileLoaderWindow(user, pass);
    }
    
    /**
     * goes through a save operation.
     * @param evt unused
     */
    @Override
    public void actionPerformed(ActionEvent evt)
    {
        window.save(state);
    }
}
