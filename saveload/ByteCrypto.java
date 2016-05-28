package saveload;

import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * ByteCrypto encrypts byte arrays.
 * 
 * @author Alec James
 */
public class ByteCrypto
{
    /** Encryption standard used. */
    private final static String CRYPTO = "PBEwithMD5AndDES";
    
    /** Encryption Cipher. */
    private Cipher encryptor;
    
    /** Decryption Cipher. */
    private Cipher decryptor;
    
    /** Salt. */
    private final static byte[] salt = { (byte)0xDE, (byte)0xAD, (byte)0xBE, 
        (byte)0xEF, (byte)0x23, (byte)0x19, (byte)0x67, (byte)0x54 };
    
    /** number of iterations. */
    private static final int iters = 4;
    
    /**
     * Constructor. Generates a key based on the user's password.
     * 
     * @param pass the user's password
     * @throws IllegalArgumentException shouldn't
     */
    public ByteCrypto(String pass) throws IllegalArgumentException
    {
        try
        {
            //hash the password a few times
             char[] hashPass = hashedPass(pass);
        
            //generate a key from my password
            SecretKey key = SecretKeyFactory.getInstance(CRYPTO)
                .generateSecret(new PBEKeySpec(hashPass));
        
            //set paramters
            PBEParameterSpec params = new PBEParameterSpec(salt, iters);
        
            //set up encryption/decryption engines
            encryptor = Cipher.getInstance(CRYPTO);
            encryptor.init(Cipher.ENCRYPT_MODE, key, params);
            decryptor = Cipher.getInstance(CRYPTO);
            decryptor.init(Cipher.DECRYPT_MODE, key, params);
        }
        catch(Exception ex)
        {
            throw new IllegalArgumentException();
        }
    }
    
    /**
     * Hashes the password a few times. Mainly just extra muddling of the
     * password for added security.
     * 
     * @param password the password
     * @return char array of the hashed password
     */
    private char[] hashedPass(String pass)
    {
        //hash the password
        int hashedPass = pass.hashCode();
        
        //hash it a few more times
        for(int hash = 0; hash < (pass.length() + 1) * 2; hash++)
        {
            hashedPass = new Integer(hashedPass).hashCode();
        }
        
        //return char[] representation
        return Integer.toString(hashedPass).toCharArray();
    }
    
    /**
     * Encrypts the passed in byte array.
     * 
     * @param toEncrypt the byte array to encrypt
     * @return the byte array encrypted
     * @throws IllegalArgumentException shouldn't
     */
    public String encrypt(byte[] toEncrypt) throws IllegalArgumentException
    {
        try
        {
            return Arrays.toString(encryptor.doFinal(toEncrypt));
        }
        catch(Exception ex)
        {
            throw new IllegalArgumentException();
        }
    }
    
    /**
     * Decrypts the passed in String.
     * 
     * @param toDecrypt the string to decrypt (a byte array string)
     * @return decrypted byte array
     * @throws IllegalArgumentException shouldn't
     */
    public byte[] decrypt(String toDecrypt) throws IllegalArgumentException
    {
        try
        {
            return decryptor.doFinal(backToBytes(toDecrypt));
        }
        catch(Exception ex)
        {
            throw new IllegalArgumentException();
        }
    }
    
    /**
     * Converts the string representation of a byte array back to a byte array.
     * 
     * @param byteString the byte array as a string
     * @return byteString as a byte array
     */
    private byte[] backToBytes(String byteString)
    {
        String[] byteStrings = byteString.substring(1, byteString.length() - 1)
                .split(",");
        byte[] bytes = new byte[byteStrings.length];
        
        //convert the strings to bytes
        for(int byt = 0; byt < bytes.length; byt++)
        {
            bytes[byt] = Byte.parseByte(byteStrings[byt].trim());
        }
        
        return bytes;
    }
}