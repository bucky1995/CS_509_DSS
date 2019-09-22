package shapes.controller;

import shapes.model.*;
import shapes.view.*;
import junit.framework.TestCase;

public class TestHConnectController extends TestCase {
	Application app;
	Model model;
	Shape shape1, shape2;
	
	protected void setUp() {
		Board b = new Board();
		shape1 = new WordShape (10, 10, new WordDrawer(), "sample");
		shape2 = new WordShape (30, 30, new WordDrawer(), "other");
		b.add(shape1);
		b.add(shape2);;
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
		HorizontalConnectShapeController hcsc = new HorizontalConnectShapeController(model, app);
		hcsc.register();
		
		hcsc.select(35, 35);
		hcsc.drag(15, 15);
		hcsc.release(15, 15);
		
		// single poem now appears.
		for (Shape s : model.getBoard()) {
			assertEquals ("other sample\n", s.toString());
			assertEquals (10, s.getX());
			assertEquals (10, s.getY());
		}
		
		// throw in undo, why not?
		UndoController undo = new UndoController(model, panel);
		assertTrue (undo.process());
		
		for (Shape s : model.getBoard()) {
			if (s.toString().equals(shape1.toString())) {
				assertEquals (10, shape1.getX());
				assertEquals (10, shape1.getY());
			}
			
			if (s.toString().equals(shape2.toString())) {
				assertEquals (30, shape2.getX());
				assertEquals (30, shape2.getY());
			}
		}
		
		// redo NOT YET WORKING!!!
		RedoController redo = new RedoController(model, panel);
		assertTrue (redo.process());
		
		// single poem now appears.
		for (Shape s : model.getBoard()) {
			assertEquals ("other sample\n", s.toString());
			assertEquals (10, s.getX());
			assertEquals (10, s.getY());
		}
		
	}
	
}
