package logic;

/* Holds the game state variables. */

import java.io.Serializable;
import logic.ClickCounter;
import java.util.ArrayList;
import java.util.List;

public class GameState implements java.io.Serializable {
   /** Max wave round number. */
   private static final int MAX_WAVE_NUM = 10;

   private int waveNum;
   private List<Unit> activeDefendUnits;
   private List<Unit> activeAttackUnits;
   private Player player;
   private boolean active;
   public ClickCounter clickCounter;
   public int roundTimeLeft; //**** Needs to be updated to proper timer class. ****

   /** Default constructor. */
   public GameState() {
      waveNum = 1;
      roundTimeLeft = 0; // ******************************** 
      activeDefendUnits = new ArrayList<Unit>();
      activeAttackUnits = new ArrayList<Unit>();
      player = new Player();
      this.addActiveUnit(player);
      active = false;
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
    * Gets the list of active units of the given type.
    * @param type The type of units to get (defend or attack).
    * @return The list of active units.
    */
   public List<Unit> getActiveUnits(Unit.UnitType type) {
      if (type == Unit.UnitType.Defend || type == Unit.UnitType.Player) {
         return activeDefendUnits;
      }
      else {
         return activeAttackUnits;
      }
   }

   /**
    * Replaces the GameState's list of active units of the same type
    * with the given list.
    * @param newUnits The (nonempty) list of new active units.
    */
   public void setActiveUnits(List<Unit> newUnits) {
	  Unit.UnitType uType = newUnits.get(0).getType();
      if (uType == Unit.UnitType.Defend || uType == Unit.UnitType.Player) {
         activeDefendUnits = newUnits;
      }
      else {
         activeAttackUnits = newUnits;
      }
   }

   /**
    * Removes the given Unit from the active list. You cannot remove
    * the Player unit.
    * @param unitToRemove The unit to remove from the list.
    * @return True if the unit was sucessfully removed.
    */
   public boolean removeActiveUnit(Unit unitToRemove) {
	  if (unitToRemove.getType() == Unit.UnitType.Player) {
		  return false;
	  }
      else if (unitToRemove.getType() == Unit.UnitType.Defend) {
         return activeDefendUnits.remove(unitToRemove);
      }
      else {
         return activeAttackUnits.remove(unitToRemove);
      }
   }

   /**
    * Adds the given Unit to the type-appropriate list of active units.
    * @param unitToAdd The unit to add to the list.
    * @return True if the Unit was successfully added.
    */
   public boolean addActiveUnit(Unit unitToAdd) {
	  Unit.UnitType uType = unitToAdd.getType();
      if (uType == Unit.UnitType.Defend || uType == Unit.UnitType.Player) {
         return activeDefendUnits.add(unitToAdd);
      }
      else {
         return activeAttackUnits.add(unitToAdd);
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

   /**
    * Runs every unit's attack in the given list of active units
    * and removes the unit if its health is reduced to 0.
    * @param units The list of units to run attacks against their opponents.
    * @param unitType The type of unit the given list is made of.
    * @return True if a unit was destroyed; false if not.
    */
   private boolean runAttacks(List<Unit> units, Unit.UnitType unitType) {
      boolean unitWasDestroyed = false;

      for (Unit unit : units) {
         Unit nearest = null;
         if (unitType == Unit.UnitType.Attack) {
            nearest = unit.nearestOpponentUnit(activeDefendUnits);
         }  
         else {
            nearest = unit.nearestOpponentUnit(activeAttackUnits);
         }
         if (nearest != null) {
            unit.dealDamage(nearest);
            if (nearest.getUnitHealthLeft() <= 0) {
               removeActiveUnit(nearest);
               unitWasDestroyed = true;
            }
         }
      }

      return unitWasDestroyed;
   }

   /**
    * Runs all the active units of both types attacks against the nearest
    * in-range opponent.
    * @return True if a unit was destroyed; false if not.
    */
   public boolean runAllAttacks() {
      boolean attackUnitDied, defendUnitDied;
      
      attackUnitDied = runAttacks(activeDefendUnits, Unit.UnitType.Defend);
	  defendUnitDied = runAttacks(activeAttackUnits, Unit.UnitType.Attack);
	  
	  return attackUnitDied || defendUnitDied;
   }

}
