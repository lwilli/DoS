package DoS;
public class DefendUnit extends Unit {

   /** Default DefendUnit constructor. */
   public DefendUnit() {
      super();
   }

   /** Creates an DefendUnit with the given fields. */
   public DefendUnit(int id, int posX, int posY, double maxHealth, 
    double attackStrength, double defenseStrength, double attackSpeed, 
    double attackRange) {

      super(id, posX, posY, Unit.UnitType.Defend, maxHealth, attackStrength,
       defenseStrength, attackSpeed, attackRange); 
   }
   
}
