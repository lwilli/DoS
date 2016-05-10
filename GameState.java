/* Holds the game state variables. */

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

public class GameState implements java.io.Serializable {

   /** Max health to start the player at. */
   private static final int MAX_HEALTH = 100;

   /** Max wave round number. */
   private static final int MAX_WAVE_NUM = 10;

   private int waveNum;
   private Timer roundTimeLeft;
   private List<Unit> activeUnits;
   private int playerHealth;
   private int difficulty;
   public ClickCounter clickCounter;

   /** Default constructor. */
   public GameState() {
      waveNum = 1;
      roundTimeLeft = new Timer(); 
      activeUnits = new ArrayList<Unit>();
      playerHealth = MAX_HEALTH;
      difficulty = 0;
      clickCounter = new ClickCounter();
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
    * Gets the list of active units.
    * @return The list of active units.
    */
   public List<Unit> getActiveUnits() {
      return activeUnits;
   }

   /**
    * Replaces the GameState's list of active units with the given list.
    * @param newUnits The list of new active units.
    */
   public void setActiveUnits(List<Unit> newUnits) {
      activeUnits = newUnits;
   }

   /**
    * Removes the given Unit from the active list of units.
    * @param unitToRemove The unit to remove from the list.
    * @return True if the unit was sucessfully removed.
    */
   public boolean removeActiveUnit(Unit unitToRemove) {
      return activeUnits.remove(unitToRemove);
   }

   /**
    * Adds the given Unit to the list of active units.
    * @param unitToAdd The unit to add to the list.
    * @return True if the Unit was successfully added.
    */
   public boolean addActiveUnit(Unit unitToAdd) {
      return activeUnits.add(unitToAdd);
   }

   /**
    * Returns the player's health.
    * @return The player's health.
    */
   public int getPlayerHealth() {
      return playerHealth;
   }

   /**
    * Sets the player's health to the given value.
    * @param newHealth The new health value.
    */
   public void setPlayerHealth(int newHealth) {
      if (newHealth > 0 && newHealth < MAX_HEALTH) {
         playerHealth = newHealth;
      }
      else {
         throw new IllegalArgumentException(
          "New health must be between 0 and " + MAX_HEALTH + 
          " (inclusive). Given: " + newHealth);
      }
   }
   
   public void 
   
   /**
   * Currency is encapsulated within it's own class so that it is more easily
   * managable.
   */
   public class Currency {
      private int defenderPlayerCurrency = 0;
      private int attackerPlayerCurrency = 0;
      
      /**
      * Constructor with the defender's currency
      * @param initial starting currency
      */
      public Currency (int initialAmount) {
         defenderPlayerCurrency = initialAmount;
      }
      
      /**
      * Constructor with the defender's and attacker's currency
      * @param defender's currency
      * @param attacker's currency
      */
      public Currency (int initialAmountDefender, int initialAmountAttacker) {
         defenderPlayerCurrency = initialAmountDefender;
         attackerPlayerCurrency = initialAmountAttacker;
      }
      
      /**
      * Changes the defender's currency
      * @param adds this value to the defender's currency, can use negatives to subtract
      */
      public void changeDefenderCurrency(int change) {
         defenderPlayerCurrency += change;
      }
      
      /**
      * Changes the attacker's currency
      * @param adds this value to the attacker's currency, can use negatives to subtract
      */
      public void changeAttackerCurrency(int change) {
         attackerPlayerCurrency += change;
      }
      
      /**
      * Gets the current amount of currency the defender has
      * @return defender's currency
      */
      public int getDefenderCurrency() {
         return defenderPlayerCurrency;
      }
      
	   /**
      * Gets the current amount of currency the attacker has
      * @return attacker's currency
	  */
      public int getAttackerCurrency() {
         return attackerPlayerCurrency;
      }
   }

}
