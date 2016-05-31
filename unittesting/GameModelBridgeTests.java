package unittesting;

import static org.junit.Assert.*;

import org.junit.Test;

import dos.GameModelBridge;

/**
 * Tests GameModelBridge so and that the
 * the functions in GameModelBridge works properly
 * 
 * 
 * @author Bill Mak
 *
 */
public class GameModelBridgeTests {

   @Test
   public void addWaveUnitsTest() {
      GameModelBridge gmb = new GameModelBridge(800);
      gmb.addWaveUnits(20);
      assertEquals(20, gmb.returnAttackers().size());
   }
   
   @Test
   public void addDefenderTest() {
      GameModelBridge gmb = new GameModelBridge(800);
      gmb.addDefenderUnit(300, 400);
      assertEquals(300, gmb.returnDefenders().get(0).getPosition()[0]);
      assertEquals(400, gmb.returnDefenders().get(0).getPosition()[1]);
   }
   
   @Test
   public void gameLoopMoveAttackerTest() {
      GameModelBridge gmb = new GameModelBridge(800);
      gmb.addWaveUnits(1);
      gmb.gameLoop();
      assertEquals(1, gmb.returnAttackers().get(0).getPosition()[0]);
   }
   
   @Test
   public void gameLoopMoveAttackerEndTest() {
      int resX = 40;
      GameModelBridge gmb = new GameModelBridge(resX);
      gmb.addWaveUnits(1);
      for (int ndx = 0; ndx <= resX; ndx++) {
         gmb.gameLoop();
      }
      assertEquals(0, gmb.returnAttackers().size());
   }
   
   @Test
   public void gameLoopMoveAttackerDeadTest() {
      int resX = 40;
      GameModelBridge gmb = new GameModelBridge(resX);
      gmb.addWaveUnits(1);
      gmb.returnAttackers().get(0).takeDamage(100);
      gmb.gameLoop();
      assertEquals(0, gmb.returnAttackers().size());
   }

}
