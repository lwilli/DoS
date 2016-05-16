package DoS;


import org.lwjgl.input.Mouse;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;


import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.fills.GradientFill;
import org.newdawn.slick.gui.TextField;
import java.awt.Font;
public class GameMenu extends BasicGameState{
	public String mouse ="No input yet";

	public Image staley;
	public int staleyX = 200;
	public int staleyY = 150;
	public UnicodeFont font;
	private TextField text;
	private Rectangle playButton = new Rectangle(275, 350, 100 ,50);
	private GradientFill healthFill = new GradientFill(0, playButton.getHeight(), Color.red, 
			playButton.getWidth(), playButton.getHeight(), Color.red, true);
	
	public GameMenu(int state) {
		
	}
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		staley = new Image("res/staley.jpg");
		font = getNewFont("Arial" , 16);
		text = new TextField(gc, font, 50, 100, 120, 60);
	}
	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics gr) throws SlickException {
		
		gr.setBackground(Color.blue);
		gr.drawString(mouse, 50, 50);
		//gr.drawRect(50, 100, 60, 120); //x y , width, height
	
		gr.drawString("Do you want to defeat me?", 80, 80);
		staley.draw(staleyX, staleyY, 0.25f);
		
		gr.fill(playButton, healthFill);
		text.setBackgroundColor(Color.white);
		text.setTextColor(Color.black);
		text.setAcceptingInput(true);
		text.setText("nothing");
		text.render(gc, gr);
	}
	
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		Input input = gc.getInput();
		
		int xpos = Mouse.getX();
		int ypos = Mouse.getY();
		healthFill.setStartColor(Color.red);
		
		mouse = "Mouse position x: " + xpos + " y : " + ypos;
		
		
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
		
		if((xpos  > 275 && xpos < 375) && (ypos < 250 && ypos > 200)) {
			healthFill.setStartColor(Color.yellow);
			if (input.isMouseButtonDown(0)) {
				
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
