package saveload;

/* Holds the game state variables. */

import java.io.Serializable;

public class GameState implements java.io.Serializable {
   /** Max wave round number. */
   private static final int MAX_WAVE_NUM = 10;

   private int waveNum;
   private boolean active;

   /** Default constructor. */
   public GameState() {
      waveNum = 1;
      active = false;
   }

   /**
    * Returns the current wave number.
    * @return The current wave number.
    */
   public int getWaveNum() {
      return waveNum;
   }

   /**
    * Changes this GameState's wave number.
    * @param newWaveNum The new wave number.
    */
   public void setWaveNum(int newWaveNum) {
      if (newWaveNum >= 1 && newWaveNum <= MAX_WAVE_NUM) {
         waveNum = newWaveNum;
      }
      else {
         throw new IllegalArgumentException(
               "New wave number must be between 1-10 (inclusive). Given: " + newWaveNum);
      }
   }

   /**
    * Sets the game as paused or unpaused.
    * @param active If true, the game will be set to active.
    */
   public void setGameActive(boolean active) {
      this.active = active;
   }

   /**
    * Determines if the game is in an active or paused state.
    * @return True if the game is active; false if the game is paused.
    */
   public boolean isActive() {
      return this.active;
   }

}
