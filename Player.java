/** Represents the player/base tower. */

public class Player extends Unit {

   private static final int MAX_HP = 1000;

   public Player(int posX, int posY) {
      super(0, posX, posY, UnitType.Player, MAX_HP, 0, 1, 0, 0);
   }

   @Override
   public int dealDamage(Unit unit) {
      return 0;
   }

   @Override
   public boolean setPosition(int pos[]) {
      return false;
   }


}
