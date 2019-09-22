package distributed.controller.client;

import distributed.model.ClearModelInstance;
import distributed.model.Model;
import distributed.view.Application;

import xml.Message;
import junit.framework.TestCase;

/**
 * This test case shows how to test client-side controllers that process Responses from server.
 */
public class TestLockResponseController extends TestCase {


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

	public void testProcess() {
		String xmlResponse = Message.responseHeader("123") + "<lockResponse secured='true'/></response>";
		Message response = new Message(xmlResponse);
		new LockResponseController().process(response);

		// ensure locked and GUI updated
		assertTrue (Model.getInstance().isLocked());

		// GUI lock is disabled
		assertFalse (client.getInnerPanel().getServerPanel().getExclusiveButton().isEnabled());
		
	}
}
