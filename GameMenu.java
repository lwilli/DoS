package DoS;


import org.bushe.swing.event.EventTopicSubscriber;
import org.lwjgl.input.Mouse;

import org.lwjgl.opengl.XRandR.Screen;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

import DoS.Game;
import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.NiftyEventSubscriber;
import de.lessvoid.nifty.builder.LayerBuilder;
import de.lessvoid.nifty.builder.PanelBuilder;
import de.lessvoid.nifty.builder.PopupBuilder;
import de.lessvoid.nifty.builder.ScreenBuilder;
import de.lessvoid.nifty.controls.ButtonClickedEvent;
import de.lessvoid.nifty.controls.button.builder.ButtonBuilder;
import de.lessvoid.nifty.controls.label.builder.LabelBuilder;
import de.lessvoid.nifty.elements.Element;
import de.lessvoid.nifty.elements.events.NiftyMousePrimaryClickedEvent;
import de.lessvoid.nifty.nulldevice.NullSoundDevice;
import de.lessvoid.nifty.renderer.lwjgl.input.LwjglInputSystem;
import de.lessvoid.nifty.renderer.lwjgl.render.LwjglRenderDevice;
import de.lessvoid.nifty.screen.ScreenController;
import de.lessvoid.nifty.slick2d.input.PlainSlickInputSystem;
import de.lessvoid.nifty.slick2d.input.SlickInputSystem;
import de.lessvoid.nifty.slick2d.render.SlickRenderDevice;
import de.lessvoid.nifty.spi.time.impl.AccurateTimeProvider;

import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.fills.GradientFill;
import org.newdawn.slick.opengl.SlickCallable;

import java.awt.Font;
import java.beans.EventHandler;

public class GameMenu extends BasicGameState  {
	public String mouse ="No input yet";

	private EventTopicSubscriber<ButtonClickedEvent> eventHandler;
	private boolean quit_game = false;
	private boolean startGame = false;
	public Image staley;
	private Nifty nifty;
	public int staleyX = 200;
	public int staleyY = 150;
	public UnicodeFont font;
	private Rectangle playButton = new Rectangle(275, 350, 100 ,50);
	private Element exit;
	private GradientFill healthFill = new GradientFill(0, playButton.getHeight(), Color.red, 
			playButton.getWidth(), playButton.getHeight(), Color.red, true);
	
	public GameMenu(int state) {
		
	}
	
	
	
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
	    /*We initialize nifty GUI first*/  
		SlickInputSystem inputSystem = new PlainSlickInputSystem();
	     Input input = container.getInput();
	    
	     inputSystem.setInput(input);
	     input.addListener(inputSystem);
	     
	     
	     nifty = new Nifty(new SlickRenderDevice(container), new NullSoundDevice(), inputSystem, new AccurateTimeProvider());

	     nifty.loadStyleFile("nifty-default-styles.xml");
	     nifty.loadControlFile("nifty-default-controls.xml");
	     /*Load the game menu. See the xml file for more details.*/
	     nifty.fromXml("res/game_menu.xml", "game_menu");

	     /*Create a pop up exit menu*/
	     new PopupBuilder("exit_game") {{
	    	 childLayoutCenter();
	         backgroundColor("#000a");
	         panel(new PanelBuilder() {{
	        	 width("250px");                        
	             height("65px");
	             style("nifty-panel-bright");
	             childLayoutVertical(); 
	             control(new LabelBuilder() {{
	            	 width("100px");                        
		             height("25px");
		             label("Are you sure?");
		             color("#B0171F");
	             }});
	             panel(new PanelBuilder() {{
		                childLayoutHorizontal();                                                       
		                control(new ButtonBuilder("yes_quit", "YES") {{
		                	childLayoutCenter();
		                	width("100px");                        
			                height("25px");
		                }});
		                panel(new PanelBuilder() {{ width("10px"); }});
		                control(new ButtonBuilder("no_quit", "NO") {{
		                	childLayoutCenter();
		                	width("100px");                        
			                height("25px");
		                }});
		            }});
	         }});
 
	    }}.registerPopup(nifty);
    
	    
	    exit = nifty.createPopup("exit_game");
	    
		//staley = new Image("res/staley.jpg");
		//font = getNewFont("Arial" , 16);
		//text = new TextField(gc, font, 50, 100, 120, 60);
	    
	    
	    //System.out.println("is visible " + exit.isVisibleToMouseEvents());
	}
	


	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics gr) throws SlickException {
		SlickCallable.enterSafeBlock();
        nifty.render(true);
        SlickCallable.leaveSafeBlock();
       
		gr.drawString(mouse, 50, 50);
	
	}
	
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		nifty.update();
		Input input = gc.getInput();
		if(quit_game == true && (Mouse.getX() > 390  && Mouse.getX() < 490)
				&& (Mouse.getY() > 275 && Mouse.getY() < 300) 
			&& input.isMousePressed(0)){		
			nifty.closePopup(exit.getId());
			quit_game = false;
		}
		
		if(quit_game == true && (Mouse.getX() > 280 && Mouse.getX() < 380)
				&& (Mouse.getY() > 275 && Mouse.getY() < 300) 
			&& input.isMousePressed(0)){		
			gc.exit();
		}
		if (!startGame ) {
			 eventHandler = new EventTopicSubscriber<ButtonClickedEvent>() {
			
				@Override
				public void onEvent(String topic, ButtonClickedEvent arg1) {
					System.out.println(topic);
					if (topic.equals("play_game")) {
						sbg.enterState(1);
					}
					if (topic.equals("load_game")) {
						System.out.println("Load Game");
					}
					if( topic.equals("tutorial_game")) {
						System.out.println("Tutorial Game");
					}
					if ( topic.equals("exit_game")) {
						nifty.showPopup(nifty.getCurrentScreen(), exit.getId(), null);
						quit_game = true;
					}

				}
		      };
		    nifty.subscribe(nifty.getCurrentScreen(), "play_game", ButtonClickedEvent.class, eventHandler);
		    nifty.subscribe(nifty.getCurrentScreen(), "load_game", ButtonClickedEvent.class, eventHandler);
		    nifty.subscribe(nifty.getCurrentScreen(), "tutorial_game", ButtonClickedEvent.class, eventHandler);
		    nifty.subscribe(nifty.getCurrentScreen(), "exit_game", ButtonClickedEvent.class, eventHandler);
		    startGame = true;
		}

		int xpos = Mouse.getX();
		int ypos = Mouse.getY();
		mouse = "Mouse position x: " + xpos + " y : " + ypos;    
	}
		
	public int getID() {
		return 0;
	}
	
    public UnicodeFont getNewFont(String fontName , int fontSize)
    {
        font = new UnicodeFont(new Font(fontName , Font.PLAIN , fontSize));
        font.addGlyphs("@");
        //font.getEffects().add(new ColorEffect(java.awt.Color.white));
        return (font);
    }


}
