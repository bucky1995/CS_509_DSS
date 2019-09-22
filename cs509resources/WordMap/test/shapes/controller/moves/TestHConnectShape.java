package shapes.controller.moves;

import shapes.model.Board;
import shapes.model.Model;
import shapes.model.Shape;
import shapes.model.WordShape;
import shapes.view.Application;
import shapes.view.WordDrawer;
import junit.framework.TestCase;

public class TestHConnectShape extends TestCase {
	Shape s1;
	Model model;
	Shape s2;
	Application app;
	
	protected void setUp() {
		Board b = new Board();
		s1 = new WordShape (10, 10, new WordDrawer(), "sample");
		b.add(s1);
		s2 = new WordShape (30, 30, new WordDrawer(), "other");
		b.add(s2);
		model = new Model(b);
		app = new Application(model);

		// launch everything and go!
		app.setVisible (true);
	}
	
	protected void tearDown() {
		if (app != null) {
			app.dispose();
		}
	}
	
	public void testV() {
		Board b = model.getBoard();
		HConnectShape move = new HConnectShape(model, s1, s1.getX(), s1.getY(),
				s2, 35, 35);
		
		assertTrue (move.execute());
		
		// only shape on board is this one.
		for (Shape s : b) {
			assertEquals ("sample other\n", s.toString());
		}
		
		move.undo();
		
		for (Shape s : b) {
			if (s != s1 && s != s2) {
				fail("unknown shape: " + s);
			}
		}
				
	}
}
