package dos;

import org.newdawn.slick.state.StateBasedGame;

import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.screen.ScreenController;

public class GameMenuController implements ScreenController {
	private StateBasedGame sbg;
	
	public void bind(Nifty arg0, de.lessvoid.nifty.screen.Screen arg1) { }
	  	public void onStartScreen() { }
	   	public void onEndScreen() {}
	    
	   public void next(int x, int y) {
		   
		   System.out.println(x + " " + y);
	   }

}