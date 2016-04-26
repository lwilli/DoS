

/** Enum to represent the two types of Units: Defend and Attack units. */
enum UnitType {Defend, Attack};

public abstract class Unit {
   
   private int id;
   private int positionX;
   private int positionY;
   private UnitType type;
   private int unitMaxHealth;
   private int unitHealthLeft;
   private int attackStrength;
   private int defenseStrength;
   private double attackSpeed;   
   
   /** Default Unit constructor (probably shouldn't use). */
   public Unit() {
      id = -1;
      positionX = 0;
      positionY = 0;
      type = null;
      unitMaxHealth = 100;
      unitHealthLeft = unitMaxHealth;
      attackStrength = 1;
      defenseStrength = 1;
      attackSpeed = 1;
   }

   /** Creates a Unit with the given fields. */
   public Unit(int id, int posX, int posY, UnitType type, int maxHealth, 
    int attackStrength, int defenseStrength, double attackSpeed) {
      this.id = id;
      this.positionX = posX;
      this.positionY = posY;
      this.type = type;
      this.unitMaxHealth = maxHealth;
      this.unitHealthLeft = this.unitMaxHealth;
      this.attackStrength = attackStrength;
      this.defenseStrength = defenseStrength;
      this.attackSpeed = attackSpeed;
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
      int pos[] = {positionX, positionY};
      return pos;
   }

   /**
    * Gets the Unit's type.
    * @return The Unit's type (Attack or Defend).
    */
   public UnitType getType() {
      return type;
   }

   /**
    * Gets the Unit's remaining health.
    * @return The Unit's remaining health.
    */
   public int getUnitHealthLeft() {
      return unitHealthLeft;
   }

   /**
    * Gets the Unit's maximum health.
    * @return The Unit's maximum health.
    */
   public int getUnitMaxHealth() {
      return unitMaxHealth;
   }
   
   /**
    * Gets the Unit's attack strength.
    * @return The Unit's attack strength.
    */
   public int getAttackStrength() {
      return attackStrength;
   }

   /**
    * Gets the Unit's defense strength.
    * @return The Unit's defense strength.
    */
   public int getDefenseStrength() {
      return defenseStrength;
   }

   /**
    * Gets the Unit's attack speed.
    * @return The Unit's attack speed.
    */
   public double getAttackSpeed() {
      return attackSpeed;
   }
   
   /**
    * Deals damage to the Unit's health.
    * @param damage The amount by which to reduce the Unit's health.
    * @return The Unit's new health amount (also can be seen with a 
    * call to getUnitHealthLeft() after the takeDamage() call. 
    * Must be greater than 0 and less than or equal to Integer.MAX_VALUE.
    */
   public int takeDamage(int damage) {
      if (damage < 0 || damage > Integer.MAX_VALUE) {
         throw new IllegalArgumentException("Damage amount must be greater than zero.");
      }
      else {
         if (unitHealthLeft != 0) {
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
   public int dealDamage(Unit target) {
      return target.takeDamage(this.attackStrength / target.defenseStrength);
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
}
