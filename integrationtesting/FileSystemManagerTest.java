/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package integrationtesting;

import saveload.GameState;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Test;
import saveload.FileSystemManager;
import static org.junit.Assert.*;

/**
 * FileSystemManager Integration test.
 * 
 * @author Alec James
 */
public class FileSystemManagerTest {
    
    /** Test logger. */
    private static final Logger log = 
        Logger.getLogger(FileSystemManagerTest.class.getName());
    
    @Test
    public void TestSaveToFileFileExistence()
    {
        FileSystemManager m = new FileSystemManager("Falessi", "Basketball");
        m.saveToFile("myTestSave", new GameState());
        File f = new File(System.getProperty("user.dir") + "/saves/Falessi/myTestSave.save");
        assertTrue(f.exists());
    }
    
    @Test
    public void TestLoadFileGeneral()
    {
        try
        {
            GameState g = new GameState();
            g.setGameActive(true);
            FileSystemManager m = new FileSystemManager("Falessi", "Kobe");
            m.saveToFile("mygame", g);
            g = m.loadFile("mygame");
            assertTrue(g.isActive());
        }
        catch(Exception ex)
        {
            log.log(Level.SEVERE, "Test failure", ex);
            fail();
        }
    }
}
