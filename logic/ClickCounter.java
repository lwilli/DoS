/* Class to count all clicks made.
 *
 * To use, just add a new mouse listener with the ClickCounter as the mouse 
 * listener to the Component you want to count clicks on, like this: 
 *    ClickCounter ctr = new ClickCounter();
 *    JPanel jp = new JPanel();
 *    jp.addMouseListener(ctr);
 *    JButton jb = new JButton();
 *    jb.addMouseListener(ctr);
 * 
 * Then it will count clicks on the JPanel and the JButton you can get the 
 * number of total clicks with ctr.getTotalClickCount().
 */

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ClickCounter extends MouseAdapter {

   private int numClicks;

   /** Default constructor. */
   public ClickCounter() {
      numClicks = 0;
   }

   /**
    * Increments the click counter each time the user presses and releases
    * the mouse.
    */
   public void mouseClicked(MouseEvent e) {
      numClicks++;
      System.out.println("numClicks: " + numClicks);
   }

   /** 
    * Returns the number of total clicks the user has made.
    * @return The number of clicks.
    */
   public int getTotalClickCount() {
      return numClicks;
   }
}
