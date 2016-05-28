package unittesting;

import saveload.ByteCrypto;
import org.junit.Test;
import saveload.ByteCrypto;
import static org.junit.Assert.*;

/**
 * ByteCrypto unit test.
 * 
 * @author Alec James
 */
public class ByteCryptoTest {
    
    @Test
    public void TestEncryptGeneral()
    {
        try
        {
            ByteCrypto b = new ByteCrypto("I am a hamster");
            String data = "Hello I am data";
            byte[] pre = data.getBytes();
            
            byte[] post = b.decrypt(b.encrypt(pre));
            
            for(int i = 0; i < pre.length; i++)
            {
                assertEquals(pre[i], post[i]);
            }
        }
        catch(Exception ex)
        {
            fail("Threw an exception - check crypto code");
        }
    }
}
