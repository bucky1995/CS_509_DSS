package distributed.controller.client;

import java.util.ArrayList;

import distributed.client.MockServerAccess;
import distributed.model.ClearModelInstance;
import distributed.view.Application;

import xml.Message;
import junit.framework.TestCase;

/**
 * This test case is needed when the job of a controller is to send a Request to the server.
 * <P> 
 * To make this work we need to create a "mock" Server whose only purpose is to WAIT for requests to come
 * from the client being pressed into service here in this test case. 
 * 
 * @author heineman
 */
public class TestGrabLockListener extends TestCase {
	
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
		 new GrabLockListener().actionPerformed(null);
		 
		 // validate from mockServer
		 
		 ArrayList<Message> reqs = mockServer.getAndClearMessages();
		 assertTrue (reqs.size() == 1);
		 Message r = reqs.get(0);
		 
		 // a lock request is sent out.
		 assertEquals ("lockRequest", r.contents.getFirstChild().getLocalName());
		 
		 // make sure "grab" attribute is there, and true
		 assertEquals ("grab", r.contents.getFirstChild().getAttributes().getNamedItem("grab").getNodeName());
		 assertEquals ("true", r.contents.getFirstChild().getAttributes().getNamedItem("grab").getNodeValue());
	}
}
