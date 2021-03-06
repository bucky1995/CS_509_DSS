package distributed.controller.server;

import org.w3c.dom.NamedNodeMap;

import server.Server;

import xml.Message;
import distributed.model.ClearModelInstance;
import distributed.model.Model;
import distributed.server.MockClient;
import junit.framework.TestCase;

// validate that server deals properly with lockRequest, especially considering multiple
// clients could be connected...
public class TestLockRequestController extends TestCase {

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
		
		// note that client2/client3 MUST get lockStatusResponse
		Message c2response = client2.getAndRemoveMessage();
		Message c3response = client3.getAndRemoveMessage();
		assertTrue (c2response.success());
		assertTrue (c3response.success());
		assertEquals ("lockStatusResponse", c2response.contents.getFirstChild().getLocalName());
		assertEquals ("lockStatusResponse", c3response.contents.getFirstChild().getLocalName());
	}
	
	public void testDenied() {
		model = Model.getInstance();
		
		String xmlString = Message.requestHeader() + "<lockRequest grab='true'/></request>";
		Message grab = new Message(xmlString);
		
		// get response after processing this request from client1
		Message response1 = new LockRequestController().process(client1, grab);
				
		// get response after processing this request from client2 (should be denied)
		Message response2 = new LockRequestController().process(client2, grab);

		// client2 should be denied
		assertFalse (response2.success());
		assertEquals (LockRequestController.ModelLocked, response2.reason());
		
		// client1 locks it: get attributes of 'lockStatusResponse' (firstChild) 
		assertEquals ("lockResponse", response1.contents.getFirstChild().getLocalName());
		NamedNodeMap map = response1.contents.getFirstChild().getAttributes();
		assertEquals ("true", map.getNamedItem("secured").getNodeValue());
		
		
		// note that client2/client3 MUST get lockStatusResponse (from client1 response)
		Message c2response = client2.getAndRemoveMessage();
		Message c3response = client3.getAndRemoveMessage();
		assertTrue (c2response.success());
		assertTrue (c3response.success());
		assertEquals ("lockStatusResponse", c2response.contents.getFirstChild().getLocalName());
		assertEquals ("lockStatusResponse", c3response.contents.getFirstChild().getLocalName());
		
	}
	
}
