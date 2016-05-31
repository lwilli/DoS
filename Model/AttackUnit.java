package Model;
public class AttackUnit extends Unit {

   private double moveSpeed;

   /** Default AttackUnit constructor. */
   public AttackUnit() {
      super();
      moveSpeed = 1;
   }

   /** Creates an AttackUnit with the given fields. */
   public AttackUnit(int id, int[] pos, double maxHealth, 
    double attackStrength, double defenseStrength, 
    double attackRange, double moveSpeed) {

      super(id, new int[] {pos[0], pos[1]}, Unit.UnitType.ATTACK, maxHealth, attackStrength,
       defenseStrength, attackRange); 
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
