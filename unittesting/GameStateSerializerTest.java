
package unittesting;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import saveload.GameState;
import saveload.GameStateSerializer;
import static org.junit.Assert.*;

/**
 * Game State Serializer unit Test.
 * 
 * @author Alec James
 */
public class GameStateSerializerTest 
{
    
    private static class TestGS extends GameState
    {
        public String str = "Hello";
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
            fail("Serialization/Deserialization threw an exception.");
        }
        
    }
}
