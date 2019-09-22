package distributed.controller.client;

import java.awt.TextField;
import java.util.ArrayList;

import xml.Message;

import distributed.client.MockServerAccess;
import distributed.model.ClearModelInstance;
import distributed.model.Model;
import distributed.view.Application;

import junit.framework.TestCase;

public class TestTextController extends TestCase {

	/** Model */
	Model model;
	
	/** View is under control of this controller. */
	Application view;
	
	MockServerAccess mockServer;
	
	/**
	 * To test the controller you need a working Boundary object. That is what
	 * the view object is. Note that you just construct it and make it visible,
	 * then during tearDown() you dispose of it.
	 */
	protected void setUp() {
		// FIRST thing to do is register the protocol being used.
		if (!Message.configure("distributedEBC.xsd")) {
			fail ("unable to configure protocol");
		}

		model = Model.getInstance();
		view = Application.getInstance();
		
		view.setVisible(true);
		
		// prepare client and connect to server.

		// Create mockServer to simulate server, and install 'obvious' handler
		// that simply dumps to the screen the responses.
		mockServer = new MockServerAccess("localhost");
		
		// as far as the client is concerned, it gets a real object with which
		// to communicate.
		view.setServerAccess(mockServer);
		
	}
	
	protected void tearDown() {
		view.dispose();
		Application.clearInstance();
		ClearModelInstance.clearInstance();
	}
	
	// Go up to max, and then go down to min. If this works, then it will
	// work for all controllers.
	public void testFullRange() {
		TextController tc = new TextController(model.getColor(), view);
		TextField tf = view.getInnerPanel().getTextView().getTextFieldC();
		tf.setText("" + model.getColor().getMaximum());
		tc.update(tf);
		
		assertEquals (model.getColor().getMaximum(), model.getColor().getValue());
		
		tf.setText("" + model.getColor().getMinimum());
		tc.update(tf);
		
		assertEquals (model.getColor().getMinimum(), model.getColor().getValue());
	}
	
	// test with updates sent to server.
	public void testUpdates() {
		TextController tc = new TextController(model.getColor(), view);
		TextField tf = view.getInnerPanel().getTextView().getTextFieldC();
		tf.setText("" + model.getColor().getMaximum());
		tc.update(tf);
		
		assertEquals (model.getColor().getMaximum(), model.getColor().getValue());
		
		// at this point, the view has been updated locally and we propagate the values on to the server
		// place this common method in the Application class as a static method
		Application.updateModelOnServer();
		
		ArrayList<Message> reqs = mockServer.getAndClearMessages();
		assertFalse (reqs.isEmpty());
		
		assertEquals ("updateRequest", reqs.get(0).contents.getFirstChild().getNodeName());
	}
	
	// Validate invalid data
	public void testInvalidNumber() {
		int old = model.getColor().getValue();
		TextController tc = new TextController(model.getColor(), view);
		TextField tf = view.getInnerPanel().getTextView().getTextFieldC();
		tf.setText("non-numeric");
		tc.update(tf);
		
		assertEquals (old, model.getColor().getValue());
	}
}
