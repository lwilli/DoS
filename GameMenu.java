package DoS;


import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.XRandR.Screen;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

import DoS.Game;
import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.NiftyEventSubscriber;
import de.lessvoid.nifty.builder.PanelBuilder;
import de.lessvoid.nifty.builder.ScreenBuilder;
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
import de.lessvoid.nifty.tools.TimeProvider;

import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.fills.GradientFill;
import org.newdawn.slick.gui.TextField;
import org.newdawn.slick.opengl.SlickCallable;

import java.awt.Font;
public class GameMenu extends BasicGameState  {
	public String mouse ="No input yet";

	public Image staley;
	private Nifty nifty;
	public int staleyX = 200;
	public int staleyY = 150;
	public UnicodeFont font;

	private Rectangle playButton = new Rectangle(275, 350, 100 ,50);
	private GradientFill healthFill = new GradientFill(0, playButton.getHeight(), Color.red, 
			playButton.getWidth(), playButton.getHeight(), Color.red, true);
	
	public GameMenu(int state) {
		
	}
	
	
	@NiftyEventSubscriber(id="game_play")
    public void onClick(String id, NiftyMousePrimaryClickedEvent event) {
     System.out.println("element with id [" + id + "] clicked at [" + event.getMouseX() +
     ", " + event.getMouseY() + "]");
    }
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
	     SlickInputSystem inputSystem = new PlainSlickInputSystem();
	     Input input = container.getInput();
	    
	     inputSystem.setInput(input);
	     input.addListener(inputSystem);

	     nifty = new Nifty(new SlickRenderDevice(container), new NullSoundDevice(), inputSystem, new AccurateTimeProvider());

	     nifty.loadStyleFile("nifty-default-styles.xml");
	     nifty.loadControlFile("nifty-default-controls.xml");
	     nifty.fromXml("res/game_menu.xml", "game_menu");

	     
		/*staley = new Image("res/staley.jpg");
		font = getNewFont("Arial" , 16);
		text = new TextField(gc, font, 50, 100, 120, 60);*/
	 	
	}
	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics gr) throws SlickException {
		SlickCallable.enterSafeBlock();
        nifty.render(false);
        SlickCallable.leaveSafeBlock();
		/*gr.setBackground(Color.blue);
		gr.drawString(mouse, 50, 50);
		//gr.drawRect(50, 100, 60, 120); //x y , width, height
	
		gr.drawString("Do you want to defeat me?", 80, 80);
		staley.draw(staleyX, staleyY, 0.25f);
		
		gr.fill(playButton, healthFill);
		text.setBackgroundColor(Color.white);
		text.setTextColor(Color.black);
		text.setAcceptingInput(true);
		text.setText("nothing");
		text.render(gc, gr);*/
	}
	
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		nifty.update();
		
	
		Input input = gc.getInput();
		
		int xpos = Mouse.getX();
		int ypos = Mouse.getY();
		healthFill.setStartColor(Color.red);
		
		mouse = "Mouse position x: " + xpos + " y : " + ypos;
		
		System.out.println(mouse);
		if (input.isKeyDown(Input.KEY_DOWN)) {
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
		}
		
		if((xpos  > 567 && xpos < 713) && (ypos < 475 && ypos > 425)) {
			healthFill.setStartColor(Color.yellow);
			if (input.isMouseButtonDown(0)) {
				System.out.println("I am here");
				sbg.enterState(1);
				
			}
				
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
