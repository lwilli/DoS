package DoS;

import java.util.ArrayList;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.opengl.SlickCallable;
import org.newdawn.slick.state.*;

import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.builder.PanelBuilder;
import de.lessvoid.nifty.builder.PopupBuilder;
import de.lessvoid.nifty.controls.button.builder.ButtonBuilder;
import de.lessvoid.nifty.elements.Element;
import de.lessvoid.nifty.nulldevice.NullSoundDevice;
import de.lessvoid.nifty.slick2d.input.PlainSlickInputSystem;
import de.lessvoid.nifty.slick2d.input.SlickInputSystem;
import de.lessvoid.nifty.slick2d.render.SlickRenderDevice;
import de.lessvoid.nifty.spi.time.impl.AccurateTimeProvider;
public class PlayGame extends BasicGameState{

	/*I don't know if we should implement a class that keeps track
	 * of the number of towers and another for the Health
	 * It will be easier to test stuff  */
	private boolean gameStart = false;
	private Image map;
	private Image tower;
	private Image attackUnit;
	
	/* Position of every tower or staley face*/
	private ArrayList<Integer> ypos = new ArrayList<Integer>();
	private ArrayList<Integer> xpos = new ArrayList<Integer>();
	
	private boolean drawImage = false;
	private boolean gamePause = false;
	private Element pause_game;
	private Sound wait_min;
	private Sound pause_sound;
	private Sound resume_sound;
	private Nifty nifty;
	
	private int seconds;
	/* Number of rounds */
	private int numRounds;
	private String health = "A";
	private int numTowers = 0;
	public PlayGame(int state) {
		
	}
	
	
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
	     SlickInputSystem inputSystem = new PlainSlickInputSystem();
	     Input input = container.getInput();
	    
	     inputSystem.setInput(input);
	     input.addListener(inputSystem);

	     nifty = new Nifty(new SlickRenderDevice(container), new NullSoundDevice(), inputSystem, new AccurateTimeProvider());

	     nifty.loadStyleFile("nifty-default-styles.xml");
	     nifty.loadControlFile("nifty-default-controls.xml");
	     nifty.fromXml("res/play_game.xml", "play_game");

	   
	     new PopupBuilder("pause") {{
	    	 childLayoutCenter();
	         backgroundColor("#000a");
	            panel(new PanelBuilder() {{
	                width("130px");                        
	                height("160px");
	                childLayoutVertical(); 
	                style("nifty-panel-bright");                                                     
	                control(new ButtonBuilder("resume_game", "Resume Game") {{
	                	childLayoutCenter();
	                	width("120px");                        
		                height("50px");
	                }});
	                control(new ButtonBuilder("save_game", "Save Game") {{
	                	childLayoutCenter();
	                	width("120px");                        
		                height("50px");
	                }});
	                control(new ButtonBuilder("quit_game", "Quit Game") {{
	                	childLayoutCenter();
	                	width("120px");                        
		                height("50px");
	                }});
	            }});
               
	    }}.registerPopup(nifty);
	    
	    pause_game =  nifty.createPopup("pause");
	    
	    tower = new Image("res/Defend_Tower.png");
		attackUnit = new Image("res/Attack_Unit.png");
		wait_min = new Sound("res/Wait a Minute.wav");
		pause_sound = new Sound("res/Pause, Think About That.wav");
		resume_sound = new Sound("res/Coming Back from a Pause.wav");
	}
	public void render(GameContainer gc, StateBasedGame sbg, Graphics gr) throws SlickException {
		SlickCallable.enterSafeBlock();
        nifty.render(true);
        SlickCallable.leaveSafeBlock();
		Input input = gc.getInput();
		
		
       
		if (gameStart == true) {
			numTowers = xpos.size();
	        String format = String.format("%02d", (seconds /1000 ) % 60);
	        gr.drawString("Round: " + String.valueOf(numRounds), 300, 20);
	        gr.drawString("Time: " + (seconds / 1000) / 60 + ":" + format , 300, 40);
	        gr.drawString("Health: " + health, 650, 20);
	        gr.drawString("Number of Towers: " + numTowers, 450, 20);
			if (gamePause == false) {
				
				gr.drawString("Time to play!", 100, 100);
				
				
				for (int i = 0; i < xpos.size(); i ++)
					gr.drawImage(tower, xpos.get(i), 600 - ypos.get(i));
			}
			
		}
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
		nifty.update();
		Input input = gc.getInput();
		
		if (gameStart == true) {
		
			if (input.isMousePressed(0) && gamePause == false) {
				xpos.add(Mouse.getX());
		        ypos.add(Mouse.getY());
		        wait_min.play();
			}
			
			if(gamePause == false) { 
				seconds += delta;
			}
			if(gc.getInput().isKeyPressed(Input.KEY_P) 
					&& gamePause == false){
			      gamePause = true;
			      pause_sound.play();
			      nifty.showPopup(nifty.getCurrentScreen(), pause_game.getId(), null);
			      System.out.println("id popup " + pause_game.getId());
		     
			 }
			
			else if(gc.getInput().isKeyPressed(Input.KEY_R) 
					&& gamePause == true){
				  resume_sound.play();
				  nifty.closePopup(pause_game.getId());
			      gamePause = false;
			      System.out.println("no pause");
		
			 }
		}
		
		else {
			try {
				Thread.sleep(1000);
				gameStart = true;
			
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public int getID() {
		return 1;
	}
}
