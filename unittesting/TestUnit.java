package unittesting;

import static org.junit.Assert.*;
import logic.Unit;
import logic.Unit.UnitType;

import org.junit.Test;

/**
 * Tests for basic use of the Unit class. 
 *
 * @author Logan Williams 
 */
public class TestUnit {

	public class ImplementedUnit extends Unit{
		private static final long serialVersionUID = 1L;

		public ImplementedUnit(int id, int posX, int posY, UnitType type, int maxHealth, 
			    int attackStrength, int defenseStrength, double attackSpeed, int range) {
			super(id, posX, posY, type, maxHealth, attackStrength, defenseStrength, attackSpeed, range);
		}
	}
	
	@Test
	public void TestGetPositionGeneral() {
		Unit u1 = new ImplementedUnit(1, 10, 32, UnitType.Attack, 100, 1, 1, 1.0, 50);
		int pos[] = u1.getPosition();
		int expected[] = {10, 32};
		
		assertEquals(pos[0], expected[0]);
		assertEquals(pos[1], expected[1]);
	}
	
	@Test
	public void TestTakeDamagePositive() {
		Unit u1 = new ImplementedUnit(1, 10, 32, UnitType.Attack, 50, 3, 12, 1.0, 100);
		double newHealth = u1.takeDamage(10);
		
		assertEquals(40, newHealth, 0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void TestTakeDamageNegative() {
		Unit u1 = new ImplementedUnit(1, 10, 32, UnitType.Defend, 100, 3, 12, 1.0, 50);
		u1.takeDamage(-10);
		
		fail("No Exception Throwns");
	}
	
	@Test
	public void TestTakeDamagePastZero() {
		Unit u1 = new ImplementedUnit(1, 10, 32, UnitType.Defend, 100, 3, 12, 1.0, 50);
		double newHealth = u1.takeDamage(101);
		
		assertEquals(0, newHealth, 0);
	}
	
	@Test
	public void TestDealDamageGeneral() {
		Unit defender = new ImplementedUnit(1, 10, 32, UnitType.Defend, 100, 3, 12, 1.0, 50);
		Unit attacker = new ImplementedUnit(1, 10, 32, UnitType.Attack, 100, 27, 5, 1.0, 50);
		attacker.dealDamage(defender);
		
		assertEquals(97.75, defender.getUnitHealthLeft(), 0.001);
	}

}
