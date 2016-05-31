package saveload;

import saveload.GameState;
import saveload.FileLoaderWindow;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A listener for loading the game.
 * 
 * @author Alec James
 */
public class LoadListener implements ActionListener
{
    /** the game state. Set after the action completes. */
    private GameState state;
    
    /** the FileLoaderWindow. */
    private FileLoaderWindow window;
    
    /**
     * Constructor. Sets the Game state.
     * 
     */
    public LoadListener(String user, String pass)
    {
        this.state = state;
        window = new FileLoaderWindow(user, pass);
    }
    
    /**
     * goes through a load operation. After this is called the loaded game state
     * is accessible through getLoadedState().
     * @param evt unused
     */
    @Override
    public void actionPerformed(ActionEvent evt)
    {
        window.load();
    }
    
    /**
     * Accesses the game state after a load.
     * @return the game state
     */
    public GameState getLoadedState()
    {
        return state;
    }
}
