import javax.swing.Timer;
import java.time.Duration;

/**
 * 
 * @author Bill Mak
 * 
 * The ModelTimer class keeps track of events that occur in the game and
 * the current time that the round is running.
 */
public class ModelTimer {
   
   private Timer gameTimer;
   private Duration roundTime;
   private final int THIRTY_FPS_REFRESHRATE = 33;
   private final int SIXTY_FPS_REFRESHRATE = 16;
   
   public ModelTimer() {
      if (gameTimer == null) {
         gameTimer = new Timer(THIRTY_FPS_REFRESHRATE, null);
      }
      if (roundTime == null) {
         roundTime = Duration.ofMinutes(5);
      }
      
   }
   
   public javax.swing.Timer getGameTimer() {
      return gameTimer;
   }
   
   public synchronized void pauseGame() throws InterruptedException {
      roundTime.wait();
      gameTimer.stop();
   }
   
   public synchronized void unpauseGame() {
      roundTime.notify();
      gameTimer.start();
   }
   
}
