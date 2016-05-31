package looptesting;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

import Model.AttackUnit;
import Model.DefendUnit;
import Model.GameState;
import Model.Player;
import Model.Unit;
import Model.Unit.UnitType;

/**
 * Tests the loop in GameState's runAllAttacks() method.
 * 
 * @author Logan Williams
 */
public class TestLoopGameState {

	@Test
	/**
	 * Tests for just the default Player defender.
	 */
	public void TestRunAllAttacksOneLoop() {
		GameState state = new GameState();
		
		state.runAllAttacks();
		
		assertEquals(state, new GameState());
	}

	@Test
	/** 
	 * Tests for one attacker and one defender (default Player).
	 */
	public void TestRunAllAttacksTwoLoops() {
		GameState state = new GameState();
		AttackUnit attacker = new AttackUnit(1, new int[] {5, 5}, 10.0, 3.0, 1.0, 1.0, 1.0);
		if (state.addActiveUnit(attacker) != true) {
			System.out.println("ERROR: failed to add active unit of type Attack.");
		}
		
		state.runAllAttacks();

		ArrayList<Unit> expectedAttackList = new ArrayList<Unit>();
		expectedAttackList.add(attacker);
		assertTrue(Arrays.deepEquals(expectedAttackList.toArray(), 
				   state.getActiveUnits(UnitType.ATTACK).toArray()));
	}
	
	@Test
	/**
	 * Tests for multiple attackers and multiple defenders.
	 */
	public void TestRunAllAttacksSevenLoops() {
		GameState state = new GameState();
		AttackUnit attacker1 = new AttackUnit(1, new int[] {25, 45}, 10.0, 3.0, 1.0, 1.0, 0.1);
		AttackUnit attacker2 = new AttackUnit(2, new int[] {51, 55}, 10.0, 3.0, 1.0, 1.0, 0.1);
		AttackUnit attacker3 = new AttackUnit(3, new int[] {35, 45}, 10.0, 3.0, 1.0, 1.0, 0.1);
		
		state.addActiveUnit(attacker1);
		state.addActiveUnit(attacker2);
		state.addActiveUnit(attacker3);

		DefendUnit defender1 = new DefendUnit(4, new int[] {15, 28}, 10.0, 2.0, 4.0, 0.1);
		DefendUnit defender2 = new DefendUnit(5, new int[] {15, 18}, 10.0, 2.0, 4.0, 0.1);
		DefendUnit defender3 = new DefendUnit(6, new int[] {52, 83}, 10.0, 2.0, 4.0, 0.1);
		
		state.addActiveUnit(defender1);
		state.addActiveUnit(defender2);
		state.addActiveUnit(defender3);
		
		state.runAllAttacks();
		
		ArrayList<Unit> expectedAttackers = new ArrayList<Unit>();
		expectedAttackers.add(attacker1);
		expectedAttackers.add(attacker2);
		expectedAttackers.add(attacker3);
		System.out.println("attackers: " + expectedAttackers);
		System.out.println("results:   " + state.getActiveUnits(UnitType.ATTACK));
		assertTrue(Arrays.deepEquals(expectedAttackers.toArray(), 
				   state.getActiveUnits(UnitType.ATTACK).toArray()));
		
		ArrayList<Unit> expectedDefenders = new ArrayList<Unit>();
		expectedDefenders.add(new Player());
		expectedDefenders.add(defender1);
		expectedDefenders.add(defender2);
		expectedDefenders.add(defender3);
			assertTrue(Arrays.deepEquals(expectedDefenders.toArray(), 
				   state.getActiveUnits(UnitType.DEFEND).toArray()));	
	}
}
