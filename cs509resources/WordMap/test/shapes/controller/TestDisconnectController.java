package shapes.controller;

import shapes.model.*;
import shapes.view.*;
import junit.framework.TestCase;

public class TestDisconnectController extends TestCase {
	Application app;
	Model model;
	Shape shape1, shape2;
	Shape newPoem;
	
	protected void setUp() {
		Board b = new Board();
		shape1 = new WordShape (10, 10, new WordDrawer(), "sample");
		shape2 = new WordShape (30, 30, new WordDrawer(), "other");
		
		newPoem = shape1.verticalConnect(shape2, 15, 15);
		b.add(newPoem);
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
	
	public void testConnect() {
		ApplicationPanel panel = app.getWordPanel();
		DisconnectController dc = new DisconnectController(model, app);
		dc.register();
		
		dc.select(60, 50);   // in the second line
		dc.drag(90, 90);
		dc.release(90, 90);
		
		// Two poems present
		for (Shape s : model.getBoard()) {
			assertTrue ("other\n".equals(s.toString()) || "sample\n".equals(s.toString()));
		}
		
		// throw in undo, why not?
		UndoController undo = new UndoController(model, panel);
		assertTrue (undo.process());
		
		for (Shape s : model.getBoard()) {
			assertEquals ("other\nsample\n", s.toString());
		}
		
		// redo NOT YET WORKING!!!
		RedoController redo = new RedoController(model, panel);
		assertTrue (redo.process());
		
		// single poem now appears.
		for (Shape s : model.getBoard()) {
			assertTrue ("other\n".equals(s.toString()) || "sample\n".equals(s.toString()));
		}
	}
	
}
