package DoS;

import org.bushe.swing.event.EventTopicSubscriber;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.opengl.SlickCallable;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.controls.ButtonClickedEvent;
import de.lessvoid.nifty.nulldevice.NullSoundDevice;
import de.lessvoid.nifty.slick2d.input.PlainSlickInputSystem;
import de.lessvoid.nifty.slick2d.input.SlickInputSystem;
import de.lessvoid.nifty.slick2d.render.SlickRenderDevice;
import de.lessvoid.nifty.spi.time.impl.AccurateTimeProvider;

public class GameOver extends BasicGameState{

	private Nifty nifty;
	private Sound gameOverSound;
	private boolean gameStart = false;
	private EventTopicSubscriber<ButtonClickedEvent> eventHandler;
	public GameOver( int gameOver) {
		
	}
	public void init(GameContainer container, StateBasedGame arg1) throws SlickException {
		/*We initialize nifty GUI first*/  
		SlickInputSystem inputSystem = new PlainSlickInputSystem();
	     Input input = container.getInput();
	    
	     inputSystem.setInput(input);
	     input.addListener(inputSystem);
	     
	     
	     nifty = new Nifty(new SlickRenderDevice(container), new NullSoundDevice(), inputSystem, new AccurateTimeProvider());

	     nifty.loadStyleFile("nifty-default-styles.xml");
	     nifty.loadControlFile("nifty-default-controls.xml");
	     
	     nifty.fromXml("res/game_over.xml", "game_over");
	     
	     gameOverSound = new Sound("res/Heh Heh Heh Heh.wav");
	}

	public void render(GameContainer arg0, StateBasedGame arg1, Graphics arg2) throws SlickException {
		SlickCallable.enterSafeBlock();
        nifty.render(true);
        SlickCallable.leaveSafeBlock();
        nifty.update();
	}

	public void update(final GameContainer gc, final StateBasedGame sbg, int arg2) throws SlickException {
		if (!gameStart) {
			gameOverSound.play();
			eventHandler = new EventTopicSubscriber<ButtonClickedEvent>() {
				
				
				public void onEvent(String topic, ButtonClickedEvent arg1) {
			
					if (topic.equals("go_menu")) {
						gameStart = false;
						sbg.enterState(0);
						
					}
					if (topic.equals("go_quit")) {
						gc.exit();
					}
					
				}
		      };
		    nifty.subscribe(nifty.getCurrentScreen(), "go_menu", ButtonClickedEvent.class, eventHandler);
		    nifty.subscribe(nifty.getCurrentScreen(), "go_quit", ButtonClickedEvent.class, eventHandler);
		    gameStart = true;
		}
		
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 2;
	}

}
