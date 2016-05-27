package DoS;

import java.util.ArrayList;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
public class PlayGame extends BasicGameState{

	private Image map;
	private Image tower;
	private Image attackUnit;
	private ArrayList<Integer> ypos = new ArrayList<Integer>();
	private ArrayList<Integer> xpos = new ArrayList<Integer>();
	private boolean drawImage = false;
	private Sound wait_min;
	
	public PlayGame(int state) {
		
	}
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		map = new Image("res/DoS_Map.jpg");
		tower = new Image("res/Defend_Tower.png");
		attackUnit = new Image("res/Attack_Unit.png");
		wait_min = new Sound("res/Wait a Minute.wav");
	}
	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics gr) throws SlickException {
		Input input = gc.getInput();
		
		gr.drawImage(map, 0 , 0);
		gr.drawString("Time to play!", 100, 100);
		
		
		for (int i = 0; i < xpos.size(); i ++)
			gr.drawImage(attackUnit, xpos.get(i), 600 - ypos.get(i));

	}
	

	   /*@Override
	   public void mouseReleased(int button, int x, int y) {
	      if (button == Input.MOUSE_LEFT_BUTTON) {
	         System.out.println("click");
	         xpos.add(x);
	         ypos.add(y);
	      }
	   }*/
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		Input input = gc.getInput();
		
		if (input.isMousePressed(0)) {
			xpos.add(Mouse.getX());
	        ypos.add(Mouse.getY());
	        wait_min.play();
		}
	}
	
	public int getID() {
		return 1;
	}
}
