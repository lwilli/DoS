package Model;

import java.util.List;

public abstract class Unit implements java.io.Serializable {

   /** Enum to represent the two types of Units: DEFEND and ATTACK units. */
   public enum UnitType {DEFEND, ATTACK, PLAYER};

   private final static int DEFAULT_ATTACK_RANGE = 1000;
   
   private int id;
   private int positionX;
   private int positionY;
   private UnitType type;
   private double unitMaxHealth;
   private double unitHealthLeft;
   private double attackStrength;
   private double defenseStrength;
   private double attackRange;
   
   /** Default Unit constructor (probably shouldn't use). */
   public Unit() {
      id = -1;
      positionX = 0;
      positionY = 0;
      type = null;
      unitMaxHealth = 100.0;
      unitHealthLeft = unitMaxHealth;
      attackStrength = 1.0;
      defenseStrength = 1.0;
      attackRange = DEFAULT_ATTACK_RANGE;
   }

   /** Creates a Unit with the given fields. */
   public Unit(int id, int[] pos, UnitType type, double maxHealth, 
    double attackStrength, double defenseStrength, double attackRange) {
      this.id = id;
      this.positionX = pos[0];
      this.positionY = pos[1];
      this.type = type;
      this.unitMaxHealth = maxHealth;
      this.unitHealthLeft = this.unitMaxHealth;
      this.attackStrength = attackStrength;
      this.defenseStrength = defenseStrength;
      this.attackRange = attackRange;
   }
   
   /** 
    * Returns the unique id for this Unit.
    * @return The unique id for this Unit or -1 if not set.
    */
   public int getId() {
      return id;  
   }

   /**
    * Gets the Unit's position.
    * @return Two-element array containing the x and y positions, respectively.
    */
   public int[] getPosition() {
      return new int[] {positionX, positionY};
   }

   /**
    * Gets the Unit's type.
    * @return The Unit's type (ATTACK or DEFEND).
    */
   public UnitType getType() {
      return type;
   }

   /**
    * Gets the Unit's remaining health.
    * @return The Unit's remaining health.
    */
   public double getUnitHealthLeft() {
      return unitHealthLeft;
   }

   /**
    * Gets the Unit's maximum health.
    * @return The Unit's maximum health.
    */
   public double getUnitMaxHealth() {
      return unitMaxHealth;
   }

   /**
    * Sets the unit's health to the given value.
    * @param newHealth The new health value.
    */
   public void setUnitHealth(double newHealth) {
      if (newHealth > 0.0 && newHealth <= unitMaxHealth) {
         this.unitHealthLeft = newHealth;
      }
      else {
         throw new IllegalArgumentException(
               "New health must be between 0 and " + unitMaxHealth + 
               " (inclusive). Given: " + newHealth);
      }
   }
   
   /**
    * Gets the Unit's attack strength.
    * @return The Unit's attack strength.
    */
   public double getAttackStrength() {
      return attackStrength;
   }

   /**
    * Gets the Unit's defense strength.
    * @return The Unit's defense strength.
    */
   public double getDefenseStrength() {
      return defenseStrength;
   }

   /**
    * Deals damage to the Unit's health.
    * @param damage The amount by which to reduce the Unit's health.
    * @return The Unit's new health amount (also can be seen with a 
    * call to getUnitHealthLeft() after the takeDamage() call. 
    * Must be greater than 0 and less than or equal to Integer.MAX_VALUE.
    */
   public double takeDamage(double damage) {
      if (damage < 0 || damage > Integer.MAX_VALUE) {
         throw new IllegalArgumentException("Damage amount must be greater than zero.");
      }
      else {
         if (unitHealthLeft > 0) {
            unitHealthLeft -= damage;
            if (unitHealthLeft < 0) {
               unitHealthLeft = 0;
            }
         }
         return unitHealthLeft;
      }
   }


   /**
    * Deals damage from this Unit to the given Unit using the following algorithm:
    * Damage dealt = attacking unit's attackStrength / defending unit's defenseStrength
    * @param Unit The Unit to deal damage to.
    * @return The target unit's remaining health after the dealt damage.
    */
   public double dealDamage(Unit target) {
	  return target.takeDamage(this.attackStrength / target.defenseStrength);
   }


   /**
    * Computes the Euclidean distance from this to the given unit.
    * @param unit The unit to compute distance to.
    * @return The distance to the unit as a double.
    */
   private double distanceToUnit(Unit unit) {
      int[] pos = unit.getPosition();
      return Math.sqrt(Math.pow((double)this.positionX - pos[0], 2) + 
       (Math.pow(this.positionY - pos[1], 2)));
   }


   /**
    * Find the unit nearest opponent unit to this, unless this unit is an 
    * attacker and the Player is in range, then return Player.
    * @param units The list of active opponent units.
    * @return The nearest opposing unit or null if none.
    */
   public Unit nearestOpponentUnit(List<Unit> units) {
      if (units.size() == 0) {
         return null;
      }

      double closestDist = Integer.MAX_VALUE;
      Unit closestUnit = null;

      for (Unit unit : units) {
         double thisDist = distanceToUnit(unit);
         if (unit.type == UnitType.PLAYER && thisDist <= this.attackRange) {
            return unit;
         }
         if (thisDist <= this.attackRange && thisDist < closestDist) {
            closestUnit = unit;
            closestDist = thisDist;
         }
      }

      return closestUnit;
   }


   /** 
    * Deals damage to the nearest opposing unit if there is one in range.
    * @param units The list of all active opponent units.
    * @return True if this unit was able to attack another unit; false otherwise.
    */
   public boolean attackNearest(List<Unit> units) {
      Unit nearestUnit = nearestOpponentUnit(units); 
      if (nearestUnit == null) {
         return false;
      }
      else {
         this.dealDamage(nearestUnit);
         return true;
      }
   }

   /**
    * Moves this Unit to the given position if the coordinates are valid.
    * @param posXY A two-element int array containing the x and y coordinates for
    *  the new position, respectively.
    */
   public boolean setPosition(int posXY[]) {
      int xPos = posXY[0];
      int yPos = posXY[1];

      if (xPos < 0 || yPos < 0) {
         throw new IllegalArgumentException("Position coordinates must be non-negative.");
      }
      else {
         positionX = xPos;
         positionY = yPos;
         return true;
      }
   }
   
   @Override
   public boolean equals(Object other) {
	  if (other == null || !(other instanceof Unit)) {
         return false;
	  }
	  
	  Unit otherU = (Unit)other;
	  boolean first = otherU.id == this.id &&
			  otherU.positionX == this.positionX &&
	          otherU.positionY == this.positionY;
	  boolean second = otherU.type == this.type  &&
   		  otherU.unitMaxHealth == this.unitMaxHealth &&
		  otherU.unitHealthLeft == this.unitHealthLeft;
	  boolean third = otherU.attackStrength == this.attackStrength &&
		  otherU.defenseStrength == this.defenseStrength &&
		  otherU.attackRange == this.attackRange;
	  
	  return first && second && third;
   }
   
   @Override
   public int hashCode() {
	return 0;
	   
   }
   
   @Override
   public String toString() {
	   return "id: " + this.id + ", position: [" + this.positionX + "," + this.positionY +
			   "], type: " + this.type + ", maxHealth: " + this.unitMaxHealth + ", healthLeft: " + 
			   this.unitHealthLeft + ", attackStrength: " + this.attackStrength + ", defenseStrength: " +
			   this.defenseStrength + ", attackRange: " + this.attackRange;
   }
   
}
