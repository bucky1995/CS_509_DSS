package distributed.controller.client;

import distributed.model.ClearModelInstance;
import distributed.model.Model;
import distributed.view.Application;

import xml.Message;
import junit.framework.TestCase;

/**
 * This test case shows how to test client-side controllers that process Responses from server.
 */
public class TestModelResponseController extends TestCase {


	// client to connect
	Application client;

	protected void setUp() {
		// FIRST thing to do is register the protocol being used.
		if (!Message.configure("distributedEBC.xsd")) {
			fail ("unable to configure protocol");
		}

		// prepare client and connect to server.
		client = Application.getInstance();
		client.setVisible(true);

		// make sure to clear model instance
		ClearModelInstance.clearInstance();
	}

	/**
	 * The real test case whose purpose is to validate that selecting the Locked button
	 * sends a GrabLock request to the server.
	 */
	public void testProcess() {
		String xmlResponse = Message.responseHeader("123") + "<modelResponse height='30' width='29' color='28'/></response>";
		Message response = new Message(xmlResponse);
		new ModelResponseController().process(response);

		// ensure model updated (no need for lock -- could be some third party update)
		assertEquals (30, Model.getInstance().getHeight().getValue());
		assertEquals (29, Model.getInstance().getWidth().getValue());
		assertEquals (28, Model.getInstance().getColor().getValue());
		
		// test dropping down as well...
		xmlResponse = Message.responseHeader("123") + "<modelResponse height='22' width='21' color='20'/></response>";
		response = new Message(xmlResponse);
		new ModelResponseController().process(response);

		// ensure model updated (no need for lock -- could be some third party update)
		assertEquals (22, Model.getInstance().getHeight().getValue());
		assertEquals (21, Model.getInstance().getWidth().getValue());
		assertEquals (20, Model.getInstance().getColor().getValue());
	}
}
