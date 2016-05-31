package DoS;

import Model.AttackUnit;
import Model.DefendUnit;
import Model.GameState;
import Model.Unit;

import java.util.*;

public class GameModelBridge {
	
	private GameState gs;
	private int defCount = 0;
	private int atkCount = 0;

	public GameModelBridge() {
		gs = new GameState();
	}

	/**
	 * Contains the primary logic of the game, such as movement and attacks
	 */
	public void gameLoop() {
	   Set<Unit> deadUnits = new HashSet<Unit>();
		gs.runAllAttacks();
		List<Unit> atkUnits = gs.getActiveUnits(Unit.UnitType.Attack);
		for (Unit atk: atkUnits) {
			if (atk.getUnitHealthLeft() <= 0) {
			   deadUnits.add(atk);
			   //PlaySountHere
			} else {
			   int[] newPos = atk.getPosition();
			   newPos[0] += 1;
			   atk.setPosition(newPos);
			}
			
		}
		
		atkUnits.removeAll(deadUnits);
		
	}

	/**
	 * Add new attackers per the number of units specified.
	 * 
	 * @param numUnits - number of attackers, default stats for now.
	 */
	public void addWaveUnits(int numUnits) {
		for (int ndx = 0; ndx < numUnits; ndx++) {
			Unit newAttacker = new AttackUnit(atkCount, 0, 300, 50.0, 1, 1, 1, 1, 1);
			gs.addActiveUnit(newAttacker);
		}
	}

	/**
	 * Adds defenders at the given x and y
	 * 
	 * @param xPos - should be mouse at X
	 * @param yPos - should be mouse at Y
	 */
	public void addDefenderUnit(int xPos, int yPos) {
		Unit newDefender = new DefendUnit(defCount, xPos, yPos, 100, 1, 1, 1, 50);
		defCount++;
		gs.addActiveUnit(newDefender);
	}

	/**
	 * Returns a list of defenders so that PlayGame can render them
	 * 
	 * @return List of Active Defenders
	 */
	public List<Unit> returnDefenders() {
	   return gs.getActiveUnits(Unit.UnitType.Defend);
	}
	
	/**
	 * Returns a list of attackers so that PlayGame can render them
	 * 
	 * @return List of Active Attackers
	 */
	public List<Unit> returnAttackers() {
      return gs.getActiveUnits(Unit.UnitType.Attack);
   }
	
}
