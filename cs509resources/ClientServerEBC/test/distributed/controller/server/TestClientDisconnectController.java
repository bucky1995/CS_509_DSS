package distributed.controller.server;

import org.w3c.dom.NamedNodeMap;

import server.Server;

import xml.Message;
import distributed.model.ClearModelInstance;
import distributed.model.Model;
import distributed.server.MockClient;
import junit.framework.TestCase;

// Make sure that disconnecting client has its lock removed.
public class TestClientDisconnectController extends TestCase {

	MockClient client1, client2, client3;
	
	Model model;
	
	protected void setUp() {
		// FIRST thing to do is register the protocol being used.
		if (!Message.configure("distributedEBC.xsd")) {
			fail ("unable to configure protocol");
		}
				
		// make server think there are two connected clients...
		client1 = new MockClient("c1");
		client2 = new MockClient("c2");
		client3 = new MockClient("c3");
		
		Server.register("c1", client1);
		Server.register("c2", client2);
		Server.register("c3", client3);
		
		// when working with singletons, the problem invariably arises during testing about
		// how to "start from scratch". One partial solution is provided via a clearInstance 
		// method. However, this MUST ONLY be called during testing...
		ClearModelInstance.clearInstance();
	}
	
	protected void tearDown() {
		Server.unregister("c1");
		Server.unregister("c2");
		Server.unregister("c3");
	}
	
	public void testSimple() {
		model = Model.getInstance();
		
		String xmlString = Message.requestHeader() + "<lockRequest grab='true'/></request>";
		Message grab = new Message(xmlString);
		
		// get response after processing this request from client1
		Message response = new LockRequestController().process(client1, grab);
				
		// client1 locks it: get attributes of 'lockStatusResponse' (firstChild) 
		assertEquals ("lockResponse", response.contents.getFirstChild().getLocalName());
		NamedNodeMap map = response.contents.getFirstChild().getAttributes();
		assertEquals ("true", map.getNamedItem("secured").getNodeValue());
		
		// note that client2 MUST get lockStatusResponse
		Message c2response = client2.getAndRemoveMessage();
		assertTrue (c2response.success());
		assertEquals ("lockStatusResponse", c2response.contents.getFirstChild().getLocalName());
		assertEquals ("true", c2response.contents.getFirstChild().getAttributes().getNamedItem("secured").getNodeValue());
		
		// have client1 exit
		new ClientDisconnectController().process(client1);
		
		c2response = client2.getAndRemoveMessage();
		assertTrue (c2response.success());
		assertEquals ("lockStatusResponse", c2response.contents.getFirstChild().getLocalName());
		assertEquals ("false", c2response.contents.getFirstChild().getAttributes().getNamedItem("secured").getNodeValue());		
	}
	
	public void testClient1LocksButClient2Exits() {
		model = Model.getInstance();
		
		String xmlString = Message.requestHeader() + "<lockRequest grab='true'/></request>";
		Message grab = new Message(xmlString);
		
		// get response after processing this request from client1
		Message response = new LockRequestController().process(client1, grab);
				
		// client1 locks it: get attributes of 'lockStatusResponse' (firstChild) 
		assertEquals ("lockResponse", response.contents.getFirstChild().getLocalName());
		NamedNodeMap map = response.contents.getFirstChild().getAttributes();
		assertEquals ("true", map.getNamedItem("secured").getNodeValue());
		
		// note that client2 MUST get lockStatusResponse
		Message c2response = client2.getAndRemoveMessage();
		assertTrue (c2response.success());
		assertEquals ("lockStatusResponse", c2response.contents.getFirstChild().getLocalName());
		assertEquals ("true", c2response.contents.getFirstChild().getAttributes().getNamedItem("secured").getNodeValue());
		
		// and client3
		Message c3response = client3.getAndRemoveMessage();
		assertTrue (c3response.success());
		
		// have client2 exit
		new ClientDisconnectController().process(client2);
		
		// no messages sent because client2 didn't have it locked.
		assertNull(client3.getAndRemoveMessage());
	}
	
	// nothing is locked, so no need for message.
	public void testNothingLocked() {
		model = Model.getInstance();
		
		// have client1 exit
		new ClientDisconnectController().process(client1);
		
		Message c2response = client2.getAndRemoveMessage();
		assertNull (c2response);
	}
	
}
