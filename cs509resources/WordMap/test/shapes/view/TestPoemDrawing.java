package shapes.view;

import shapes.model.*;
import junit.framework.TestCase;

// Create model with at least one poem
public class TestPoemDrawing extends TestCase {
	Application pr;
	Model model;
	
	protected void setUp() {
		Board b = new Board();
		WordShape ws1 = new WordShape(20, 20, new WordDrawer(), "Sample");
		WordShape ws2 = new WordShape(40, 40, new WordDrawer(), "Other");
		
		Shape ps = ws2.verticalConnect(ws1, 42, 42);
		b.add(ps);
		
		model = new Model(b);
		Application pr = new Application(model);

		pr.setVisible (true);
	}
	
	protected void tearDown() {
		if (pr != null) { pr.dispose();	}
	}
	
	public void testApp() {
		System.out.println("Sample GUI");
		for (Shape s : model.getBoard()) {
			assertEquals ("Sample\nOther\n", s.toString());
		}
	}
}
