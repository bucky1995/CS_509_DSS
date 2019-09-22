package distributed.controller.client;

import distributed.model.ClearModelInstance;
import distributed.model.Model;
import distributed.view.Application;

import xml.Message;
import junit.framework.TestCase;

/**
 * This test case shows how to test client-side controllers that process Responses from server.
 */
public class TestLockStatusResponseController extends TestCase {


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
		String xmlResponse = Message.responseHeader("123") + "<lockStatusResponse secured='true'/></response>";
		Message response = new Message(xmlResponse);
		new LockStatusResponseController().process(response);

		// ensure locked and GUI updated
		assertTrue (Model.getInstance().isLocked());

		// GUI lock is disabled
		assertFalse (client.getInnerPanel().getServerPanel().getExclusiveButton().isEnabled());
		
		// now have "server release lock"
		xmlResponse = Message.responseHeader("123") + "<lockStatusResponse secured='false'/></response>";
		response = new Message(xmlResponse);
		new LockStatusResponseController().process(response);

		// ensure !locked and GUI updated
		assertFalse (Model.getInstance().isLocked());

		// GUI lock is disabled
		assertTrue (client.getInnerPanel().getServerPanel().getExclusiveButton().isEnabled());
		
	}
}
