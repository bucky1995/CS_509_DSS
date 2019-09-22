package shapes.controller;

import shapes.model.*;
import shapes.view.*;
import junit.framework.TestCase;

public class TestMoveShapeController extends TestCase {
	Application app;
	Model model;
	Shape shape;
	
	protected void setUp() {
		Board b = new Board();
		shape = new WordShape (10, 10, new WordDrawer(), "sample");
		b.add(shape);
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
	
	public void testMove() {
		ApplicationPanel panel = app.getWordPanel();
		MoveShapeController msc = new MoveShapeController(model, app);
		msc.register();
		
		assertEquals(10, shape.getX());
		assertEquals(10, shape.getY());
		
		msc.select(20, 20);
		msc.drag(30, 30);
		msc.release(30, 30);
		
		assertEquals(20, shape.getX());
		assertEquals(20, shape.getY());
		
		// throw in undo, why not?
		UndoController undo = new UndoController(model, panel);
		assertTrue (undo.process());
		
		assertEquals (10, shape.getX());
		assertEquals (10, shape.getY());
	}
	
}
