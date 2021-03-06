package Model;
/* Holds the game state variables. */

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import Model.Unit.UnitType;

public class GameState implements java.io.Serializable {
   /** Max wave round number. */
   private static final int MAX_WAVE_NUM = 10;

   private int waveNum;
   private List<Unit> activeDefendUnits;
   private List<Unit> activeAttackUnits;
   private Player player;
   private boolean active;

   /** Default constructor. */
   public GameState() {
      waveNum = 1;
      activeDefendUnits = new ArrayList<Unit>();
      activeAttackUnits = new ArrayList<Unit>();
      player = new Player();
      this.addActiveUnit(player);
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
    * Gets the list of active units of the given type.
    * @param type The type of units to get (defend or attack).
    * @return The list of active units.
    */
   public List<Unit> getActiveUnits(Unit.UnitType type) {
      if (type == Unit.UnitType.DEFEND || type == Unit.UnitType.PLAYER) {
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
      if (uType == Unit.UnitType.DEFEND || uType == Unit.UnitType.PLAYER) {
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
    * @return True if the unit was successfully removed.
    */
   public boolean removeActiveUnit(Unit unitToRemove) {
	  if (unitToRemove.getType() == Unit.UnitType.PLAYER) {
		  return false;
	  }
      else if (unitToRemove.getType() == Unit.UnitType.DEFEND) {
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
      if (uType == Unit.UnitType.DEFEND || uType == Unit.UnitType.PLAYER) {
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
    * Runs every unit's attackNearest.
    * @return True if running the attacks succeeded. 
    */
   public void runAllAttacks() {
      for (Unit d : activeDefendUnits) {
         d.attackNearest(activeAttackUnits);
      }
      for (Unit a : activeAttackUnits) {
         a.attackNearest(activeDefendUnits);
      }
   }

   @Override
   public boolean equals(Object other) {
	   if (other == null || !(other instanceof GameState)) {
	         return false;
	   }
	   GameState otherGS = (GameState)other;
	   
	   if (this.waveNum == otherGS.getWaveNum() && 
		       this.active == otherGS.isActive()) {
		   if (Arrays.deepEquals(this.activeAttackUnits.toArray(), 
				   otherGS.getActiveUnits(UnitType.ATTACK).toArray()) &&
		       Arrays.deepEquals(this.activeDefendUnits.toArray(), 
				   otherGS.getActiveUnits(UnitType.DEFEND).toArray())) {
			   return true;
		   }
	   }
	   
	   return false;
   }
   
   @Override
   public int hashCode() {
	return 0;
	   
   }
}
