package distributed.controller.server;

import distributed.model.Model;
import server.ClientState;
import server.IProtocolHandler;
import xml.Message;

/**
 * Controller on server to package up the current state of the model
 * as an updateResponse message and send it back to the client.
 */
public class SendModelController implements IProtocolHandler {

	public Message process(ClientState client, Message request) {
		Model m = Model.getInstance();
		
		// Construct message reflecting state
		String xmlString = Message.responseHeader(request.id()) +
						   "<modelResponse " + 
						   "height='" + m.getHeight() + "' " + 
						   "width='"  + m.getWidth()  + "' " +  
						   "color='"  + m.getColor()  + "'/>" +  
						   "</response>";
		
		// send this response back to the client which sent us the request.
		return new Message (xmlString);
	}
}
