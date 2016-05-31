import java.util.List;

public abstract class Unit implements java.io.Serializable {

   /** Enum to represent the two types of Units: Defend and Attack units. */
   public enum UnitType {Defend, Attack, Player};

   private int id;
   private int positionX;
   private int positionY;
   private UnitType type;

   /** Default Unit constructor (probably shouldn't use). */
   public Unit() {
      id = -1;
      positionX = 0;
      positionY = 0;
      type = null;
   }

   /** Creates a Unit with the given fields. */
   public Unit(int id, int posX, int posY, UnitType type) {
      this.id = id;
      this.positionX = posX;
      this.positionY = posY;
      this.type = type;
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

   public boolean setPosition(int x, int y) {

      if (x< 0 || y < 0) {
         throw new IllegalArgumentException("Position coordinates must be non-negative.");
      }
      else {
         positionX = x;
         positionY = y;
         return true;
      }
   }
}
