package logic;

public class AttackUnit extends Unit {

   private double moveSpeed;

   /** Default AttackUnit constructor. */
   public AttackUnit() {
      super();
      moveSpeed = 1;
   }

   /** Creates an AttackUnit with the given fields. */
   public AttackUnit(int id, int posX, int posY, double maxHealth, 
    double attackStrength, double defenseStrength, double attackSpeed, 
    double attackRange, double moveSpeed) {

      super(id, posX, posY, Unit.UnitType.Attack, maxHealth, attackStrength,
       defenseStrength, attackSpeed, attackRange); 
      this.moveSpeed = moveSpeed;
   }

   /**
    * Gets this AttackUnit's move speed.
    * @return The AttackUnit's move speed.
    */
   public double getMoveSpeed() {
      return moveSpeed;
   }
   
   /**
    * Moves this AttackUnit towards the opponent's base according to the
    * path and the unit's speed.
    */
    public void advanceOnTower() {
       // Not implemented
    }

    /**
     * Moves the unit by the given x and y coordinate offsets.
     *
     * @param xOffset The number to add to this unit's x coordinate position.
     * @param yOffset The number to add to this unit's y coordinate position.
     */
    public void move(int xOffset, int yOffset) {
      int newPos[] = {xOffset, yOffset};
      this.setPosition(newPos);
    }


}
