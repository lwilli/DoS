
package unittesting;

import java.io.File;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import saveload.FileSystemManager;
import saveload.GameState;
import static org.junit.Assert.*;

/**
 * FileSystemManager unit Test.
 * 
 * @author Alec James
 */
public class FileSystemManagerTest {
    
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
            fail();
        }
        catch(Exception ex)
        {
            //pass
        }
    }
}
