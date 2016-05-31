package dos;


import org.lwjgl.input.Mouse;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.NiftyEventSubscriber;
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

import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.fills.GradientFill;
import org.newdawn.slick.opengl.SlickCallable;

import java.awt.Font;

public class GameMenu extends BasicGameState  {
	public static final String MOUSE ="No input yet";

	public Image staley;
	private Nifty nifty;
	public static final int STALEYX = 200;
	public static final int STALEYY = 150;
   public static final String LABELWIDTH = "100px";
   public static final String LABELHEIGHT = "25px";
	private boolean quitGame = false;
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
	            	 width(LABELWIDTH);                        
		             height(LABELHEIGHT);
		             label("Are you sure?");
		             color("#B0171F");
	             }});
	             panel(new PanelBuilder() {{
		                childLayoutHorizontal();                                                       
		                control(new ButtonBuilder("yes_quit", "YES") {{
		                	childLayoutCenter();
		                	width(LABELWIDTH);                        
			                height(LABELHEIGHT);
		                }});
		                panel(new PanelBuilder() {{ width("10px"); }});
		                control(new ButtonBuilder("no_quit", "NO") {{
		                	childLayoutCenter();
		                	width(LABELHEIGHT);                        
			                height(LABELHEIGHT);
		                }});
		            }});
	         }});
 
	    }}.registerPopup(nifty);
	   
	  
	    exit = nifty.createPopup("exit_game");
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
		gr.drawString(MOUSE, 50, 50);
		
	}
	
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		nifty.update();

		
		Input input = gc.getInput();
		
		int xpos = Mouse.getX();
		int ypos = Mouse.getY();
		healthFill.setStartColor(Color.red);
      
		/* Play Game */
		if((xpos  > 566 && xpos < 715) && (ypos < 475 && ypos > 425) && input.isMouseButtonDown(0)) {	
				sbg.enterState(1);
		}
		
		/* Load Game*/ 
		if((xpos  > 566 && xpos < 713) && (ypos < 415 && ypos > 370) && input.isMouseButtonDown(0) {
				System.out.println("Load Game");
		}
		
		/* Show Tutorial */
		if((xpos  > 566 && xpos < 713) && (ypos < 360 && ypos > 315) && input.isMouseButtonDown(0)) {
				System.out.println("Tutorial Game");
		}

		
		/* Quit Game Functionality 
		 Just exits the game 
		 */
		if((xpos  > 566 && xpos < 713) && (ypos < 305 && ypos > 260) && input.isMouseButtonDown(0)) {
				System.out.println("Quit Game");
				nifty.showPopup(nifty.getCurrentScreen(), exit.getId(), null);
				quitGame = true;
		}
	
		if(input.isMouseButtonDown(0) && quitGame 
				&& (xpos  > 279 && xpos < 381) && (ypos < 305 && ypos > 275)) {
			System.out.println("Yes");
			nifty.closePopup(exit.getId());
			gc.exit();	
		}
		
		if(input.isMouseButtonDown(0) && quitGame && (xpos  > 390 && xpos < 491) && (ypos < 305 && ypos > 275))
				 {
			System.out.println("No");
			nifty.closePopup(exit.getId());
			quitGame = false;
		}
	}
		
	public int getID() {
		return 0;
	}
	
    public UnicodeFont getNewFont(String fontName , int fontSize)
    {
        UnicodeFont font = new UnicodeFont(new Font(fontName , Font.PLAIN , fontSize));
        font.addGlyphs("@");
        return font;
    }


}
