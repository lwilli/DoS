
package unittesting;

import saveload.FileSystemManager;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Test;
import static org.junit.Assert.*;
import saveload.GameState;

/**
 * FileSystemManager unit Test.
 * 
 * @author Alec James
 */
public class FileSystemManagerTest {
    
    /** Logger. */
    private static final Logger log = 
        Logger.getLogger(FileSystemManagerTest.class.getName());
    
    @Test
    public void TestGetSaveFileDirHasUsername()
    {
        String user = "Charles";
        FileSystemManager m = new FileSystemManager(user, "chocolate");
        assertTrue(m.getSaveFileDir().contains(user));
    }
    
    @Test
    public void TestGetSaveFileDirGeneral()
    {
        FileSystemManager m = new FileSystemManager("Alec", "aPassword");
        String expSaveFileDir = System.getProperty("user.dir") + "/saves/Alec/";
        assertEquals(expSaveFileDir, m.getSaveFileDir());
    }
    
    @Test
    public void TestConstructorNoUsername()
    {
        try
        {
            FileSystemManager m = new FileSystemManager("", "best password ever");
            //and now, to avoid sonarqube's complaints
            m.saveToFile("MySave", new GameState());
            fail();
        }
        catch(Exception ex)
        {
            log.log(Level.INFO, "passed test", ex);
        }
    }
}
