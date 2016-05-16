package DoS;

import java.net.MalformedURLException;
import java.net.URL;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
public class Game extends StateBasedGame{

	public static final String gameName = "DoS Dehense of Staley";
	public static final int mainMenu = 0;
	public static final int playScreen = 1;
	
	public Game(String gameName) {
		super(gameName);
		this.addState(new GameMenu(mainMenu));
		this.addState(new PlayGame(playScreen));
	}
	
	public void initStatesList(GameContainer gc) throws SlickException {
		this.getState(mainMenu).init(gc, this);
		this.getState(playScreen).init(gc, this);
		this.enterState(0);
	}
	public static void main(String[] args) throws SlickException {
		// TODO Auto-generated method stub
		AppGameContainer appgc;
		
		appgc = new AppGameContainer(new Game (gameName));
		appgc.setDisplayMode(800, 600, false);
		appgc.start();
	
	}

}
