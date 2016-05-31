package integrationtesting;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import DoS.GameModelBridge;
import Model.AttackUnit;
import Model.Unit;

/**
 * Integration Test for GameModelBridge
 * 
 * @author Bill Mak
 *
 */
public class TestIntegrationGameModelBridge {

   @Test
   public void addUnitsTest() {
      GameModelBridge gmb = new GameModelBridge(200);
      gmb.addWaveUnits(10);
      List<Unit> attackUnits = new ArrayList<Unit>();
      
      for (int ndx = 0; ndx < 10; ndx++) {
         attackUnits.add(new AttackUnit(0, 0, 300, 50.0, 1, 1, 1, 1, 1));
      }
      
      for (int ndx = 0; ndx < 10; ndx++) {
         assertEquals(attackUnits.get(ndx).getId(), gmb.returnAttackers().get(ndx).getId());
      }
   }
   
   @Test
   public void checkUnitsAttack() {
      GameModelBridge gmb = new GameModelBridge(800);
      int previousHealth = 0;
      
      gmb.addWaveUnits(1);
      gmb.addDefenderUnit(5, 300);
      previousHealth = (int) gmb.returnAttackers().get(0).getUnitHealthLeft();
      gmb.gameLoop();
      assertEquals(previousHealth - 1, (int) gmb.returnAttackers().get(0).getUnitHealthLeft());
   }

}
