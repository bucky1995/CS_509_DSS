package shapes.controller;

import shapes.model.*;
import shapes.view.*;
import junit.framework.TestCase;

public class TestSelectItemController extends TestCase {
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
		SelectItemController select = new SelectItemController(model, panel);
		
		assertTrue (model.getSelected() == null);
		
		// select 1st shape.
		select.process(1, true);
		
		assertTrue (model.getSelected() == shape2);
		
		select.process(1, false);
		assertTrue (model.getSelected() == null);
	}
}
