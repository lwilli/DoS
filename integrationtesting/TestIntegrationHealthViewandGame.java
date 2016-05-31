package DoSTest;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Game;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import DoS.HealthView;
import DoS.PlayGame;
import de.lessvoid.nifty.slick2d.NiftyStateBasedGame;

public class TestIntegrationHealthViewandGame {
	public static int x;
	public static  int y;
	public static int count;
	
	@Test 
	public void testGameOver() {

		HealthView health = new HealthView();
		try {
			AppGameContainer app = new AppGameContainer(new BasicGame("Test") {
                @Override
                public void render(GameContainer container, Graphics g)
                        throws SlickException {
                    final Image image = new Image("res/Attack_Unit.png");
                    g.drawImage(image, x += 1, y);
                    
                }

                @Override
                public void update(GameContainer container, int delta)
                        throws SlickException {
                	
                	if (x >= 750) {
                		health.decreaseHealth();
                		x = 0;
                		count++;
                		//container.exit();
                	}
                	
                	/*Game Over*/
                	if (count == 4) {
                		System.out.println("I am here");
                		assertEquals(health.getGrade(), "F");
                		container.exit();
                	}
                }

                @Override
                public void init(GameContainer container) throws SlickException {
                	x = 0;
                	y = 250; 
                	count = 0;
                }
            });

			app.start();

        } catch (SlickException e) {
            System.out.println("Error");
        }
	}
	
	@Test 
	public void decreaseHealth() {			
			HealthView health = new HealthView();
			try {
				AppGameContainer app = new AppGameContainer(new BasicGame("Test") {
	                @Override
	                public void render(GameContainer container, Graphics g)
	                        throws SlickException {
	                    final Image image = new Image("res/Attack_Unit.png");
	                    g.drawImage(image, x += 1, y);
	                    
	                }

	                @Override
	                public void update(GameContainer container, int delta)
	                        throws SlickException {
	                	
	                	if (x >= 750) {
	                		health.decreaseHealth();
	                		assertEquals(health.getGrade(), "C");
	                		container.exit();
	                		
	                	}
	                }

	                @Override
	                public void init(GameContainer container) throws SlickException {
	                	x = 0;
	                	y = 250; 
	                }
	            });

				app.start();

	        } catch (SlickException e) {
	            System.out.println("Error");
	        }
	}
	
}
