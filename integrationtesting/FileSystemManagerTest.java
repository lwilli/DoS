/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package integrationtesting;

import java.io.File;
import org.junit.Test;
import saveload.FileSystemManager;
import saveload.GameState;
import static org.junit.Assert.*;

/**
 * FileSystemManager Integration test.
 * 
 * @author Alec James
 */
public class FileSystemManagerTest {
    
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
            g.setPlayerHealth(99);
            FileSystemManager m = new FileSystemManager("Falessi", "Kobe");
            m.saveToFile("mygame", g);
            g = m.loadFile("mygame");
            assertEquals(g.getPlayerHealth(), 99);
        }
        catch(Exception ex)
        {
            fail();
        }
    }
}
