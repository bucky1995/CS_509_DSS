package distributed.controller.client;

import java.util.ArrayList;

import distributed.client.MockServerAccess;
import distributed.model.ClearModelInstance;
import distributed.view.Application;

import xml.Message;
import junit.framework.TestCase;

/** Make sure that when client connects, it requests both the model and the status of the lock. */
public class TestConnectController extends TestCase {
	
	// Mock server object that extends (and overrides) ServerAccess for its purposes
	MockServerAccess mockServer;
	
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
		
		// Create mockServer to simulate server, and install 'obvious' handler
		// that simply dumps to the screen the responses.
		mockServer = new MockServerAccess("localhost");
		
		// as far as the client is concerned, it gets a real object with which
		// to communicate.
		client.setServerAccess(mockServer);
		
		// make sure to clear model instance
		ClearModelInstance.clearInstance();
	}
	
	/**
	 * The real test case whose purpose is to validate that selecting the Locked button
	 * sends a GrabLock request to the server.
	 */
	public void testProcess() {
		 new ConnectController().process();
		 
		 // validate from mockServer
		 
		 ArrayList<Message> reqs = mockServer.getAndClearMessages();
		 assertTrue (reqs.size() == 2);
		 
		 boolean lockStatus = false;
		 boolean model = false;
		 for (Message r : reqs) {
			if (r.contents.getFirstChild().getLocalName().equals("lockStatusRequest")) {
				lockStatus = true;
			} else if (r.contents.getFirstChild().getLocalName().equals("modelRequest")) {
				model = true;
			}
		 }

		 assertTrue (model && lockStatus);
	}
}
