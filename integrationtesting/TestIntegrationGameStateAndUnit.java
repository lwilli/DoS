package integrationtesting;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;
import Model.GameState;
import Model.AttackUnit;
import Model.DefendUnit;
import Model.Unit.UnitType;
import Model.Unit;
import Model.Player;

/** 
 * Integration tests for GameState and Unit.
 * @author Logan Williams 
 */
public class TestIntegrationGameStateAndUnit {

	@Test
	public void testAddUnitsGeneral() {
		GameState state = new GameState();
		AttackUnit attacker = new AttackUnit(1, new int[] {5, 5}, 10.0, 3.0, 1.0, 5.0);
		DefendUnit defender = new DefendUnit(2, new int[] {5, 9}, 10.0, 2.0, 4.0, 4.0);
		if (!state.addActiveUnit(attacker)) {
			fail("ERROR: failed to add active unit of type Attack.");
		}
		if (!state.addActiveUnit(defender)) {
			fail("ERROR: failed to add active unit of type Defend.");
		}

		ArrayList<Unit> expectedDefendList = new ArrayList<>();
		expectedDefendList.add(new Player());
		expectedDefendList.add(defender);
		assertTrue(Arrays.deepEquals(expectedDefendList.toArray(), 
				   state.getActiveUnits(UnitType.DEFEND).toArray()));

		ArrayList<Unit> expectedAttackList = new ArrayList<>();
		expectedAttackList.add(attacker);
		assertTrue(Arrays.deepEquals(expectedAttackList.toArray(), 
				   state.getActiveUnits(UnitType.ATTACK).toArray()));
	}
	
	@Test
	public void testRunAllAttacksTwoStillAlive() {
		GameState state = new GameState();
		AttackUnit attacker = new AttackUnit(1, new int[] {5, 5}, 10.0, 3.0, 1.0, 3.5);
		DefendUnit defender = new DefendUnit(2, new int[] {5, 8}, 10.0, 2.0, 4.0, 4.0);
		state.addActiveUnit(attacker);
		state.addActiveUnit(defender);
		
		state.runAllAttacks();
		
		ArrayList<Unit> expectedAttackers = new ArrayList<>();
		AttackUnit expectedAUnit = new AttackUnit(1, new int[] {5, 5}, 10.0, 3.0, 1.0, 3.5);
		expectedAUnit.setUnitHealth(8.0);
		expectedAttackers.add(expectedAUnit);
		assertTrue(Arrays.deepEquals(expectedAttackers.toArray(), 
				   state.getActiveUnits(UnitType.ATTACK).toArray()));
		
		ArrayList<Unit> expectedDefenders = new ArrayList<>();
		DefendUnit expectedDUnit = new DefendUnit(2, new int[] {5, 8}, 10.0, 2.0, 4.0, 4.0);
		expectedDUnit.setUnitHealth(9.25);
		expectedDefenders.add(new Player());
		expectedDefenders.add(expectedDUnit);
			assertTrue(Arrays.deepEquals(expectedDefenders.toArray(), 
				   state.getActiveUnits(UnitType.DEFEND).toArray()));	
	}

}
