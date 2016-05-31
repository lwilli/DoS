package unittesting;

import static org.junit.Assert.*;

import org.junit.Test;

import Model.AttackUnit;
import Model.Player;

/**
 * Player class unit tests
 * 
 * @author Angelo Scattini
 */
public class TestPlayer {

	@Test
	public void testDealDamageEmpty() {
		Player play1 = new Player();
		Player play2 = new Player();
		double damage = play1.dealDamage(play2);
		assertEquals(0, damage, 0);
	}
	
	@Test
	public void testSetPositionZero() {
		Player play1 = new Player();
		int[] location = new int[] {0, 0};
		boolean retVal = play1.setPosition(location);
		assertEquals(false, retVal);
	}
	
	@Test
	public void testSetPositionSecondConstructor() {
		Player play1 = new Player(5, 5);
		int[] location = new int[] {2, 2};
		boolean retVal = play1.setPosition(location);
		assertEquals(false, retVal);
	}
	
	@Test
	public void testDealDamageSecondConstructor() {
		Player play1 = new Player(5, 5);
		Player play2 = new Player(1, 1);
		double damage = play1.dealDamage(play2);
		assertEquals(0, damage, 0);
	}
	
	@Test
	public void testDealDamageDefenseUnit() {
		Player play1 = new Player(5, 5);
		AttackUnit attack1 = new AttackUnit();
		double damage = play1.dealDamage(attack1);
		assertEquals(0, damage, 0);
	}

}
