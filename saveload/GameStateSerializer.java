package saveload;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * GameStateSerializer handles serialization and deserialization of game state
 * objects by providing convenience methods that do such tasks.
 * 
 * @author Alec James
 */
public class GameStateSerializer
{
    /** Internal Game State. */
    private GameState state;
    
    /**
     * Constructor. Sets the internal game state.
     * 
     * @param state the state
     */
    public GameStateSerializer(GameState state)
    {
        this.state = state;
    }
    
    /**
     * serializes the game state.
     * 
     * @return serialized game state
     * @throw IllegalArgumentException shouldn't
     */
    public byte[] serialize() throws IllegalArgumentException
    {
        try
        {
            ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
            ObjectOutputStream stateStream = new ObjectOutputStream(byteStream);
            stateStream.writeObject(state);
            return byteStream.toByteArray();
        }
        catch(Exception ex)
        {
            throw new IllegalArgumentException();
        }
    }
    
    /**
     * Deserializes the passed in byte array back into the internal game state.
     * 
     * @param stateBytes the serialized game state
     * @throws IllegalArgumentException shouldn't
     */
    public void deserialize(byte[] stateBytes) throws IllegalArgumentException
    {
        try
        {
            ByteArrayInputStream byteReader = new ByteArrayInputStream(stateBytes);
            ObjectInputStream gameStateReader = new ObjectInputStream(byteReader);
            state = (GameState) gameStateReader.readObject();
        }
        catch(Exception ex)
        {
            throw new IllegalArgumentException();
        }
    }
    
    /**
     * Accesses the internal game state.
     * 
     * @return the internal game state
     */
    public GameState getGameState()
    {
        return state;
    }
}
