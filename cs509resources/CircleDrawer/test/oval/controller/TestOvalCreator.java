package oval.controller;

import oval.model.Model;
import oval.view.*;

public class TestOvalCreator extends generic.MouseEventTestCase {
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
	
	public void testCreateFirst() {
		OvalCreator controller = new OvalCreator(model, app.getPanel());
		OvalPanel panel = app.getPanel();
		
		assertTrue (model.getActive() == null);
		controller.mousePressed(this.createPressed(panel, 30, 80));
		controller.mouseDragged(this.createDragged(panel, 100, 180));
		
		assertTrue (model.getActive() != null);
		
		controller.mouseReleased(this.createReleased(panel, 100, 180));
		
		// one oval exists and none are active.
		assertEquals (1, model.getOvals().size());
		assertTrue (model.getActive() == null);
	}
}
