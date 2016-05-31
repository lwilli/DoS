package DoS;

import java.util.ArrayList;
import java.util.Random;

import org.bushe.swing.event.EventTopicSubscriber;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.opengl.SlickCallable;
import org.newdawn.slick.state.*;

import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.builder.PanelBuilder;
import de.lessvoid.nifty.builder.PopupBuilder;
import de.lessvoid.nifty.controls.ButtonClickedEvent;
import de.lessvoid.nifty.controls.button.builder.ButtonBuilder;
import de.lessvoid.nifty.controls.label.builder.LabelBuilder;
import de.lessvoid.nifty.elements.Element;
import de.lessvoid.nifty.nulldevice.NullSoundDevice;
import de.lessvoid.nifty.slick2d.input.PlainSlickInputSystem;
import de.lessvoid.nifty.slick2d.input.SlickInputSystem;
import de.lessvoid.nifty.slick2d.render.SlickRenderDevice;
import de.lessvoid.nifty.spi.time.impl.AccurateTimeProvider;
public class PlayGame extends BasicGameState{
	
	private static int STALEY_DELAY = 3000;
	private int delay = 0;
	private int delayStaley = 0;

	private Random random = new Random();
	private ArrayList<Boolean> chances;
	private boolean gameStart = false;

	private Image defenseUnit;
	private Image attackUnit;
	private Image explosion;
	private Image playerUnit;
	/* Position of every defense unit */
	private ArrayList<Integer> ypos = new ArrayList<Integer>();
	private ArrayList<Integer> xpos = new ArrayList<Integer>();
	/* Position of every Staley face*/
	private ArrayList<Integer> yStaley = new ArrayList<Integer>();
	private ArrayList<Integer> xStaley = new ArrayList<Integer>();
	
	/*Position and time for the explosion */
	private ArrayList<Integer> yExplode = new ArrayList<Integer>();
	private ArrayList<Integer> xExplode = new ArrayList<Integer>();
	private ArrayList<Integer> timerExplode = new ArrayList<Integer>();
	
	private boolean drawImage = false;
	private boolean gamePause = false;
	private Element pause_game;
	private Element exit;
	private Element hit_me;
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

	     chances = new ArrayList<Boolean>();
	     for (int i = 0; i < 100; i ++) {
	    	 chances.add(random.nextBoolean());
	     }
	     
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
		playerUnit = new Image("res/Player_Unit.png");
		wait_min = new Sound("res/Wait a Minute.wav");
		pause_sound = new Sound("res/Pause, Think About That.wav");
		resume_sound = new Sound("res/Coming Back from a Pause.wav");
		oh_yes = new Sound("res/Oh Yes.wav");
		dammit = new Sound("res/Dammit.wav");
	}
	public void render(GameContainer gc, StateBasedGame sbg, Graphics gr) throws SlickException {
		SlickCallable.enterSafeBlock();
        nifty.render(true);
        SlickCallable.leaveSafeBlock();
		Input input = gc.getInput();
		
		
		
		if (gameStart == true) {
			gr.drawString(Mouse.getX() + ":"+ Mouse.getY(), 50 ,50);
			hit_me.setVisible(false);
			numTowers = xpos.size();
	        String format = String.format("%02d", (seconds /1000 ) % 60);
	        gr.drawImage(playerUnit, 750, 250);
	        gr.drawString("Round: " + String.valueOf(numRounds), 300, 20);
	        gr.drawString("Time: " + (seconds / 1000) / 60 + ":" + format , 300, 40);
	        gr.drawString("Health: " + health, 650, 20);
	        gr.drawString("Number of Towers: " + numTowers, 450, 20);
			int yTower = 0;
			int xTower = 0;
	        	for (int i = 0; i < xpos.size(); i ++) {
	        		yTower = ypos.get(i);
	        		xTower = xpos.get(i);
	        		for (int j = 0; j < xStaley.size(); j++) {
	        			if ( collision(xStaley.get(j) - 60, xStaley.get(j), yStaley.get(j) - 62 ,yStaley.get(j),
	        					xTower - 60, xTower, yTower - 63, yTower)) {
	        				ypos.remove(i);
	        				xpos.remove(i);
	        				yStaley.remove(j);
	        				xStaley.remove(j);
        		
	        				xExplode.add(xTower);
	        				yExplode.add(yTower);
	        				timerExplode.add(800);
	        				dammit.play();
	        				
	        						
	        			}
		
	        		}
	        	}
				for (int i = 0; i < xpos.size(); i ++) {
					gr.drawImage(defenseUnit, xpos.get(i), 600 - ypos.get(i));
				}
				
				for (int i = 0; i < xStaley.size(); i ++) {
					
					if (xStaley.get(i) >= 750 ||
							collision(xStaley.get(i) - 60, xStaley.get(i), yStaley.get(i) - 62 ,yStaley.get(i),
									750, 782, 285, 350)) {
						xStaley.remove(i);
						yStaley.remove(i);
						oh_yes.play();
						hit_me.setVisible(true);
					}
					else {
						gr.drawImage(attackUnit, xStaley.get(i), 600 - yStaley.get(i));
					}
				}
				
				
				for (int i = 0; i < timerExplode.size(); i++)  {
					if (timerExplode.get(i) <= 0) {
						timerExplode.remove(i);
						xExplode.remove(i);
						yExplode.remove(i);
					}
					else
						gr.drawImage(explosion, xExplode.get(i), 600 - yExplode.get(i));
				}
		}
	}
	


	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		nifty.update();
		Input input = gc.getInput();
		if (gameStart == true) {
			delay  -= delta;
			delayStaley -= delta;
			
			for (int i = 0; i < timerExplode.size(); i++) 
				timerExplode.set(i, timerExplode.get(i) - delta);
			
			int shouldAdd = random.nextInt(100);
			if (gamePause == false) {
				if (delay <= 0 && chances.get(shouldAdd)) {
	
					int yAdd = random.nextInt((500 - 120) + 1) + 120;
						if (xStaley.size() <= 10) {
							xStaley.add(0);
							yStaley.add(yAdd);
						}
						for (int i = 0; i < xStaley.size(); i ++) 
							xStaley.set(i, xStaley.get(i) + 5);
						
						delay = STALEY_DELAY;
				}
				
				if (delayStaley <= 0 ) {
					for (int i = 0; i < xStaley.size(); i ++) {
						xStaley.set(i, xStaley.get(i) + 5);
					}
					delayStaley = 50;
				}
			
			
				if (input.isMousePressed(0) && gamePause == false) {
					if (600 - Mouse.getY() > 65 &&
							Mouse.getY() > 100) {
						xpos.add(Mouse.getX());
			        	ypos.add(Mouse.getY());
			        	wait_min.play();
					}
				}
			}
			if(gamePause == false) { 
				seconds += delta;
			}

			if(input.isKeyDown(input.KEY_P) && !gamePause) {
				gamePause = !gamePause;
				//nifty.showPopup(nifty.getCurrentScreen(), pause_game.getId(), null);
				pause_sound.play();
			} else if(input.isKeyDown(input.KEY_R) && gamePause) {
				//nifty.closePopup(pause_game.getId());
				gamePause = !gamePause;
				resume_sound.play();
				
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
	
	public boolean collision(int x1min, int x1max, int y1min, int y1max,
			int x2min, int x2max, int y2min, int y2max) {
		if (x1min < x2max &&
				   x1max > x2min &&
				   y1min < y2max &&
				   y1max > y2min) {
				   return true;
				}
		return false;
	}
}
