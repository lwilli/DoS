package unittesting;

/*Test by Juan Carlos Ferrel */
import Model.HealthView;
import static org.junit.Assert.*;
import org.junit.Test;

public class HelathViewTest {

	

	@Test
	public void decreaseHealth() {
		HealthView health = new HealthView();
		health.decreaseHealth();
		String myGrade = health.getGrade();
		assertEquals(health.getGrade(), myGrade,  "B");
	}
	
	@Test
	public void increaseHealth() {
		HealthView health = new HealthView();
		health.setGrade("C");
		String myGrade = "B";
		
		health.increaseHealth();
		
		assertEquals(health.getGrade(), myGrade, "B");
	}
	
	@Test
	public void increaseGradeA() {
		HealthView health = new HealthView();
		health.increaseHealth();
		health.increaseHealth();
		health.increaseHealth();
		health.increaseHealth();
		health.increaseHealth();
		String myGrade = "A";
		assertEquals(health.getGrade(), myGrade, "A");
	}
	
	public void decreaseGradeF() {
		HealthView health = new HealthView();
		health.decreaseHealth();
		health.decreaseHealth();
		health.decreaseHealth();
		health.decreaseHealth();
		health.decreaseHealth();
		health.decreaseHealth();
		health.decreaseHealth();
		health.decreaseHealth();
		health.decreaseHealth();
		String myGrade = "F";
		assertEquals(health.getGrade(), myGrade, "F");
	}
	
	@Test 
	public void decreaseIncrease() {
		HealthView health = new HealthView();
		
		health.decreaseHealth();
		health.decreaseHealth();
		health.decreaseHealth();
		health.increaseHealth();
		health.increaseHealth();
		String myGrade = "B";
		assertEquals(health.getGrade(), myGrade, "B");
	}
}
