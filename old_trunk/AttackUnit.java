

public class AttackUnit extends Unit {

   private double moveSpeed;

   /** Default AttackUnit constructor. */
   public AttackUnit() {
      super();
      moveSpeed = 1;
   }

   /** Creates an AttackUnit with the given fields. 
    * @param x
    * @param y
    * @param moveSpeed */
   public AttackUnit(int id, int posX, int posY, double moveSpeed) {

      super(id, posX, posY, Unit.UnitType.Attack); 
      this.moveSpeed = moveSpeed;
   }

   /**
    * Gets this AttackUnit's move speed.
    * @return The AttackUnit's move speed.
    */
   public double getMoveSpeed() {
      return moveSpeed;
   }
   
}