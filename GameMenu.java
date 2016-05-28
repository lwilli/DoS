package DoS;


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
import de.lessvoid.nifty.slick2d.NiftyBasicGame;
import de.lessvoid.nifty.slick2d.NiftyOverlayBasicGameState;
import de.lessvoid.nifty.slick2d.NiftyOverlayGameState;
import de.lessvoid.nifty.slick2d.input.PlainSlickInputSystem;
import de.lessvoid.nifty.slick2d.input.SlickInputSystem;
import de.lessvoid.nifty.slick2d.render.SlickRenderDevice;
import de.lessvoid.nifty.spi.time.impl.AccurateTimeProvider;

import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.fills.GradientFill;
import org.newdawn.slick.opengl.SlickCallable;

import java.awt.Font;

public class GameMenu extends BasicGameState  {
	public String mouse ="No input yet";

	public Image staley;
	private Nifty nifty;
	public int staleyX = 200;
	public int staleyY = 150;
	public UnicodeFont font;
	private boolean quit_game = false;
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
	
	@NiftyEventSubscriber(id="resume_game")
	public void onClick(final String topic,final ButtonClickedEvent event) {
		System.out.println("element with id");
	}
	    
	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics gr) throws SlickException {
		SlickCallable.enterSafeBlock();
        nifty.render(true);
        SlickCallable.leaveSafeBlock();
       
		/*For reference only just checks mouse position */
		gr.drawString(mouse, 50, 50);
		//gr.drawRect(50, 100, 60, 120); //x y , width, height
	
		//gr.drawString("Do you want to defeat me?", 80, 80);
		
		//gr.fill(playButton, healthFill);
		
	}
	
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		nifty.update();

		
		Input input = gc.getInput();
		
		int xpos = Mouse.getX();
		int ypos = Mouse.getY();
		healthFill.setStartColor(Color.red);
		
		mouse = "Mouse position x: " + xpos + " y : " + ypos;
		
		//System.out.println(mouse);
		/*if (input.isKeyDown(Input.KEY_DOWN)) {
			staleyY += 1;
		}
		if (input.isKeyDown(Input.KEY_UP)) {
			staleyY -= 1;
		}
		if (input.isKeyDown(Input.KEY_LEFT)) {
			staleyX -= 1;
		}
		if (input.isKeyDown(Input.KEY_RIGHT)) {
			staleyX += 1;
		}*/
		/* Play Game */
		if((xpos  > 566 && xpos < 715) && (ypos < 475 && ypos > 425)) {
			
			if (input.isMouseButtonDown(0)) {
				
				sbg.enterState(1);
				
			}
				
		}
		
		/* Load Game*/ 
		if((xpos  > 566 && xpos < 713) && (ypos < 415 && ypos > 370)) {
			
			if (input.isMouseButtonDown(0)) {
				System.out.println("Load Game");
			}		
		}
		
		/* Show Tutorial */
		if((xpos  > 566 && xpos < 713) && (ypos < 360 && ypos > 315)) {
			
			if (input.isMouseButtonDown(0)) {
				System.out.println("Tutorial Game");
			}		
		}

		
		/* Quit Game Functionality 
		 Just exits the game 
		 */
		if((xpos  > 566 && xpos < 713) && (ypos < 305 && ypos > 260)) {
	
			if (input.isMouseButtonDown(0)) {
				System.out.println("Quit Game");
				nifty.showPopup(nifty.getCurrentScreen(), exit.getId(), null);
				quit_game = true;
			}		
		}
	
		if( input.isMouseButtonDown(0) && quit_game == true 
				&& (xpos  > 279 && xpos < 381) && (ypos < 305 && ypos > 275)) {
			System.out.println("Yes");
			nifty.closePopup(exit.getId());
			gc.exit();	
		}
		
		if( input.isMouseButtonDown(0) && quit_game == true && (xpos  > 390 && xpos < 491) && (ypos < 305 && ypos > 275))
				 {
			System.out.println("No");
			nifty.closePopup(exit.getId());
			quit_game = false;
		}
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
