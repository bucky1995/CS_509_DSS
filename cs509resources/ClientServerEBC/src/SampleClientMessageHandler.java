import xml.Message;
import client.IMessageHandler;
import distributed.controller.client.ConnectController;
import distributed.controller.client.LockResponseController;
import distributed.controller.client.LockStatusResponseController;
import distributed.controller.client.ModelResponseController;
/**
 * Sample implementation of a protocol handler to respond to messages received from the server.
 * You should follow this template when designing YOUR message handler.
 */
public class SampleClientMessageHandler implements IMessageHandler {

	@Override
	public void process(Message response) {
		String type = response.contents.getFirstChild().getLocalName();

		if (type.equals ("connectResponse")) {
			// What happens now that we are connected?
			new ConnectController().process();
		} else if (type.equals ("modelResponse")) {
			// Refresh client model with one from server
			new ModelResponseController().process(response);
		} else if (type.equals ("lockResponse")) {
			// the model has now been locked by someone.
			new LockResponseController().process(response);
		} else if (type.equals ("lockStatusResponse")) {
			// update locking status 
			new LockStatusResponseController().process(response);
		}
		
		// only here to show messages as they are received by the client
		// this isn't needed.
		System.out.println("Received:" + response);
	}

}
