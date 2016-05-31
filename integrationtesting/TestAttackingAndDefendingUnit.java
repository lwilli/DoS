package integrationtesting;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import Model.Unit;
import Model.AttackUnit;
import Model.DefendUnit;

/**
 * Attack Unit and Defend Unit integration tests
 * 
 * @author Angelo Scattini
 */
public class TestAttackingAndDefendingUnit {

	@Test
	public void testAttackNearestDefendUnit() {
		AttackUnit attack1 = new AttackUnit();
		DefendUnit defend1 = new DefendUnit();
		
		ArrayList<Unit> unitList = new ArrayList<Unit>();
		unitList.add(defend1);
		
		boolean retVal = attack1.attackNearest(unitList);
		assertEquals(true, retVal);
	}
	
	@Test
	public void testAttackNearestAttackUnit() {
		AttackUnit attack1 = new AttackUnit();
		DefendUnit defend1 = new DefendUnit();
		
		ArrayList<Unit> unitList = new ArrayList<Unit>();
		unitList.add(attack1);
		
		boolean retVal = defend1.attackNearest(unitList);
		assertEquals(true, retVal);
	}

}
