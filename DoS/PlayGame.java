package dos;

import java.util.logging.Logger;

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
	
	private boolean drawImage = false;
	private boolean gamePause = false;
	private GameModelBridge gmb;
	private Element pause_game;
   private Sound wait_min;
   private Sound pause_sound;
   private Sound resume_sound;
   private Sound oh_yes;
   private Sound dammit;
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
	   	oh_yes = new Sound("res/Oh Yes.wav");
	   	dammit = new Sound("res/Dammit.wav");
		gmb = new GameModelBridge(800);
	}
	public void render(GameContainer gc, StateBasedGame sbg, Graphics gr) throws SlickException {
		SlickCallable.enterSafeBlock();
        nifty.render(true);
        SlickCallable.leaveSafeBlock();
		Input input = gc.getInput();
		
		
       
		if (gameStart == true) {
			numTowers = gmb.returnDefenders().size();
	        String format = String.format("%02d", (seconds /1000 ) % 60);
	        gr.drawString("Round: " + String.valueOf(numRounds), 300, 20);
	        gr.drawString("Time: " + (seconds / 1000) / 60 + ":" + format , 300, 40);
	        gr.drawString("Health: " + health, 650, 20);
	        gr.drawString("Number of Towers: " + numTowers, 450, 20);
			if (gamePause == false) {
				
				gr.drawString("Time to play!", 100, 100);
				
				
				for (int i = 0; i < gmb.returnDefenders().size(); i++)
					gr.drawImage(tower, gmb.returnDefenders().get(i).getPosition()[0], 600 - gmb.returnDefenders().get(i).getPosition()[1]);
				
				for (int i = 0; i < gmb.returnAttackers().size(); i++)
				   gr.drawImage(attackUnit, gmb.returnAttackers().get(i).getPosition()[0], 600 - gmb.returnAttackers().get(i).getPosition()[1]);
			}
			
		}
	}
	
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		nifty.update();
		Input input = gc.getInput();
		int status = 0;
		
		if (gameStart == true) {
		
			if (input.isMousePressed(0) && gamePause == false) {
			   gmb.addDefenderUnit(Mouse.getX(), Mouse.getY());
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
			
			if(gc.getInput().isKeyPressed(Input.KEY_X)) {
			   gmb.addWaveUnits(1);			   
			}
			
			status = gmb.gameLoop();
			if((status & 1) > 0) {
			   dammit.play();
			}
			if((status & 2) > 0) {
            oh_yes.play();
         }
			status = 0;
			
		}
		
		else {
			try {
				Thread.sleep(1000);
				gameStart = true;
			
			} catch (InterruptedException e) {
				throw new SlickException("Game failed to start");
			}
		}
	}
	
	public int getID() {
		return 1;
	}
}

