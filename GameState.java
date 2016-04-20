/* Class to hold the game state. */

import java.io.Serializable;
import java.util.ArrayList;

public class GameState implements java.io.Serializable {

   private static final int MAX_HEALTH = 100;

   private int waveNum;
   private Timer roundTimeLeft;
   private List<Unit> activeUnits;
   private int health;
   private int difficulty;

   /** Default constructor. */
   public GameState() {
      waveNum = 1;
      roundTimeLeft = 0;
      activeUnits = new ArrayList<Unit>();
      health = MAX_HEALTH;
      difficulty = 0;
   }

   public int getWaveNum() {
      return waveNum;
   }

   public void setWaveNum(int newWaveNum) {
      waveNum = newWaveNum;
   }

   public List<Unit> getActiveUnits() {

   }

   public void setActiveUnits(List<Unit> newUnits) {

   }

   public int getHealth() {
      return heatlh;
   }

   public void setHealth(int newHealth) {
      health = newHealth;
   }


}
