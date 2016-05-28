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
    * 
    */
    public void advanceOnTower() {

    }
}