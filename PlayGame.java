package DoS;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
public class PlayGame extends BasicGameState{

	public PlayGame(int state) {
		
	}
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		
	}
	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics gr) throws SlickException {
		gr.drawString("Time to play!", 100, 100);
	}
	
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
	
	}
	
	public int getID() {
		return 1;
	}
}
