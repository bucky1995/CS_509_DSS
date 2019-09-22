package oval.view;

import java.awt.Point;

import oval.model.Model;
import oval.model.Oval;
import junit.framework.TestCase;

public class TestOvalPanel extends TestCase {

	Model model;
	Application app;
	
	@Override
	protected void setUp() {
		model = new Model();
		app = new Application(model);
		app.setVisible(true);
	}
	
	@Override
	protected void tearDown() {
		app.setVisible(false);
	}
	
	// not much you can really do here other than ensure doesn't crash.
	public void testDrawingWorks() {
		Point p = new Point (30, 60);
		Oval o = new Oval(p, 10, 20);
		
		model.addOval(o);
		app.panel.repaint();
		System.out.println("Drawn");
	}
}
