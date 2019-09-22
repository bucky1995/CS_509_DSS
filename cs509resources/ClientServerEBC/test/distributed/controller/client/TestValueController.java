package distributed.controller.client;

import distributed.model.Model;
import distributed.view.Application;
import junit.framework.TestCase;

public class TestValueController extends TestCase {

	/** Model */
	Model model;
	
	/** View is under control of this controller. */
	Application view;
	
	/**
	 * To test the controller you need a working Boundary object. That is what
	 * the view object is. Note that you just construct it and make it visible,
	 * then during tearDown() you dispose of it.
	 */
	protected void setUp() {
		model = Model.getInstance();
		view = Application.getInstance();
		
		view.setVisible(true);
	}
	
	protected void tearDown() {
		view.dispose();
	}
	
	// Go up to max, and then go down to min. If this works, then it will
	// work for all controllers.
	public void testFullRange() {
		int val = model.getColor().getValue();
		ValueController vc = new ValueController(model.getColor(), view);
		vc.incrementCount(3);
		assertEquals (val+3, model.getColor().getValue());
		
		vc.decrementCount(3);
		assertEquals (val, model.getColor().getValue());
	}
	
}
