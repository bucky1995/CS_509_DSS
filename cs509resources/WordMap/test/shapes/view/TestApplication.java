package shapes.view;

import shapes.model.Board;
import shapes.model.Model;
import junit.framework.TestCase;

// this one test case by itself merits 65% coverage of 
// shapes.view package
public class TestApplication extends TestCase {
	Application pr;
	
	protected void setUp() {
		Board b = new Board();
		Model model = new Model(b);
		Application pr = new Application(model);

		// launch everything and go!
		pr.setVisible (true);
	}
	
	protected void tearDown() {
		if (pr != null) {
			pr.dispose();
		}
	}
	
	public void testApp() {
		System.out.println("Sample GUI");
	}
}
