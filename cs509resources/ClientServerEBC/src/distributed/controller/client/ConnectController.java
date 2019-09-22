package distributed.controller.client;


import xml.Message;
import distributed.view.Application;

/** 
 * Once we have connected, we need to request the model
 */
public class ConnectController {

	public void process() {
		// make sure we are visible
		Application app = Application.getInstance();
		app.setVisible(true);
		
		// request lock status
		String xmlString = Message.requestHeader() + "<lockStatusRequest/></request>";
		Message m = new Message(xmlString);
		app.getServerAccess().sendRequest(m);
		
		// request the model to be sent back to our client.
		xmlString = Message.requestHeader() + "<modelRequest/></request>";
		m = new Message(xmlString);
		app.getServerAccess().sendRequest(m);
		
		// now we have to allow another controller to take it from here...
	}

}
