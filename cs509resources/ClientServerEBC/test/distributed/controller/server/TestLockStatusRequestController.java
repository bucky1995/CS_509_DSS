package distributed.controller.server;

import org.w3c.dom.NamedNodeMap;

import xml.Message;
import distributed.model.ClearModelInstance;
import distributed.model.Model;
import distributed.server.MockClient;
import junit.framework.TestCase;

// validate that server generates appropriate lockStatusResponse for each lockStatusRequest
public class TestLockStatusRequestController extends TestCase {

	MockClient client;
	
	Model model;
	
	protected void setUp() {
		// FIRST thing to do is register the protocol being used.
		if (!Message.configure("distributedEBC.xsd")) {
			fail ("unable to configure protocol");
		}
				
		client = new MockClient();
		
		// when working with singletons, the problem invariably arises during testing about
		// how to "start from scratch". One partial solution is provided via a clearInstance 
		// method. However, this MUST ONLY be called during testing...
		ClearModelInstance.clearInstance();
	}
	
	public void testSimple() {
		model = Model.getInstance();
		
		String xmlString = Message.requestHeader() + "<lockStatusRequest/></request>";
		Message request = new Message(xmlString);
		
		// get response after processing this request
		Message response = new LockStatusRequestController().process(client, request);
				
		// make sure model is well-represented
		assertTrue (response.success());
		
		// get attributes of 'lockStatusResponse' (firstChild) 
		assertEquals ("lockStatusResponse", response.contents.getFirstChild().getLocalName());
		NamedNodeMap map = response.contents.getFirstChild().getAttributes();
		assertEquals ("false", map.getNamedItem("secured").getNodeValue());
	}
}
