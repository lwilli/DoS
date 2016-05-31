package Model;
public class AttackUnit extends Unit {

   /** Default AttackUnit constructor. */
   public AttackUnit() {
      super();
   }

   /** Creates an AttackUnit with the given fields. */
   public AttackUnit(int id, int[] pos, double maxHealth, 
    double attackStrength, double defenseStrength, 
    double attackRange) {
      super(id, new int[] {pos[0], pos[1]}, Unit.UnitType.ATTACK, maxHealth, attackStrength,
       defenseStrength, attackRange); 
   }
}
