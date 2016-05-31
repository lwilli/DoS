package looptesting;

import static org.junit.Assert.*;
import org.junit.Test;

import java.util.ArrayList;
import Model.*;

public class TestLoopUnit {

	@Test
	public void TestNearestOpponentUnitZeroLoops() {
		ArrayList<Unit> emptyList = new ArrayList<>();
		AttackUnit aUnit = new AttackUnit();
		Unit nearest = aUnit.nearestOpponentUnit(emptyList);
		assertEquals(nearest, null);
	}
	
	@Test 
	public void TestNearestOpponentUnitOneLoop() {
		ArrayList<Unit> aList = new ArrayList<>();
		ArrayList<Unit> dList = new ArrayList<>();
		AttackUnit attacker = new AttackUnit(1, new int[] {5, 5}, 10.0, 3.0, 1.0, 3.5);
		DefendUnit defender = new DefendUnit(2, new int[] {5, 8}, 10.0, 2.0, 4.0, 4.0);
		aList.add(attacker);
		dList.add(defender);
		
		Unit nearest = attacker.nearestOpponentUnit(dList);
		assertEquals(defender, nearest);
	}
	
	@Test 
	public void TestNearestOpponentUnitMultipleLoopsNull() {
		ArrayList<Unit> aList = new ArrayList<>();
		ArrayList<Unit> dList = new ArrayList<>();
		AttackUnit attacker1 = new AttackUnit(1, new int[] {511, 15}, 10.0, 3.0, 1.0, 3.5);
		DefendUnit defender1 = new DefendUnit(2, new int[] {15, 38}, 10.0, 2.0, 4.0, 4.0);
		AttackUnit attacker2 = new AttackUnit(3, new int[] {3, 55}, 10.0, 3.0, 1.0, 5.0);
		DefendUnit defender2 = new DefendUnit(4, new int[] {44, 29}, 10.0, 2.0, 4.0, 4.0);
		aList.add(attacker1);
		dList.add(defender1);
		aList.add(attacker2);
		dList.add(defender2);
		
		Unit nearest = attacker1.nearestOpponentUnit(dList);
		assertEquals(null, nearest);
	}
	
	@Test 
	public void TestNearestOpponentUnitMultipleLoops() {
		ArrayList<Unit> aList = new ArrayList<>();
		ArrayList<Unit> dList = new ArrayList<>();
		AttackUnit attacker1 = new AttackUnit(1, new int[] {51, 15}, 10.0, 3.0, 1.0, 344.5);
		DefendUnit defender1 = new DefendUnit(2, new int[] {15, 38}, 10.0, 2.0, 4.0, 4.0);
		AttackUnit attacker2 = new AttackUnit(3, new int[] {3, 55}, 10.0, 3.0, 1.0, 5.0);
		DefendUnit defender2 = new DefendUnit(4, new int[] {44, 29}, 10.0, 2.0, 4.0, 4.0);
		aList.add(attacker1);
		dList.add(defender1);
		aList.add(attacker2);
		dList.add(defender2);
		
		Unit nearest = attacker1.nearestOpponentUnit(dList);
		assertEquals(defender2, nearest);
	}

}
