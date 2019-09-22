package distributed.controller.client;

import java.awt.Point;

import distributed.controller.client.BoxController;
import distributed.model.ClearModelInstance;
import distributed.model.Model;
import distributed.view.Application;
import distributed.view.DrawingCanvas;
import junit.framework.TestCase;

public class TestBoxController extends TestCase {

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
		Application.clearInstance();
		ClearModelInstance.clearInstance();
	}
	
	public void testActions() {
		// BoxController operates on width x height, so it takes model.
		BoxController bc = new BoxController(model, view);
		bc.start = bc.end = new Point (DrawingCanvas.OffsetX + 20,
									   DrawingCanvas.OffsetY + 60);
		bc.react();
		
		assertEquals (20, model.getWidth().getValue());
		assertEquals (60, model.getHeight().getValue());
		
		// move around some
		
		bc.end = new Point (DrawingCanvas.OffsetX + 10,
							DrawingCanvas.OffsetY + 30);
		bc.react();
		
		assertEquals (10, model.getWidth().getValue());
		assertEquals (30, model.getHeight().getValue());
	}
	
}
