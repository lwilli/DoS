
package unittesting;

import java.util.logging.Level;
import java.util.logging.Logger;
import saveload.GameStateSerializer;
import saveload.GameState;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Game State Serializer unit Test.
 * 
 * @author Alec James
 */
public class GameStateSerializerTest 
{
    /** logger. */
    private Logger log = 
        Logger.getLogger(GameStateSerializerTest.class.getName());
    
    private static class TestGS extends GameState
    {
        public final String str = "Hello";
    }
    
    @Test
    public void TestSerializeGeneral()
    {
        try
        {
            GameState g = new TestGS();
            GameStateSerializer gs = new GameStateSerializer(g);
            byte[] ser = gs.serialize();
            gs.deserialize(ser);
            TestGS g2 = (TestGS) gs.getGameState();
            assertEquals("Hello", g2.str);
        }
        catch(Exception ex)
        {
            log.log(Level.SEVERE, "serialization exception!", ex);
            fail("Serialization/Deserialization threw an exception.");
        }
        
    }
}
