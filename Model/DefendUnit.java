package Model;
public class DefendUnit extends Unit {

   /** Default DefendUnit constructor. */
   public DefendUnit() {
      super();
   }

   /** Creates an DefendUnit with the given fields. */
   public DefendUnit(int id, int[] pos, double maxHealth, 
    double attackStrength, double defenseStrength, 
    double attackRange) {

      super(id, new int[] {pos[0], pos[1]}, Unit.UnitType.DEFEND, maxHealth, attackStrength,
       defenseStrength, attackRange); 
   }
   
}
