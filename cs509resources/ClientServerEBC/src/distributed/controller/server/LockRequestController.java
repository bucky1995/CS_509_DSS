package distributed.controller.server;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import server.ClientState;
import server.IProtocolHandler;
import server.Server;
import xml.Message;
import distributed.model.Model;

/**
 * Controller to manage whether the model is locked on the server side.
 */
public class LockRequestController implements IProtocolHandler {

	/** Error messages are exposed for testing. */
	public static final String ModelLocked = "Model already locked.";
	
	public Message process(ClientState client, Message request) { 
		Model m = Model.getInstance();
		String xmlString;
		
		Node lock = request.contents.getFirstChild();
		NamedNodeMap map = lock.getAttributes();
		
		String locked = map.getNamedItem("grab").getNodeValue();
		Boolean isLocked = Boolean.valueOf(locked);
		
		// releasing lock! Broadcast to everyone
		if (!isLocked) {
			m.setLocked(false);
			
			// broadcast state
			xmlString = Message.responseHeader(request.id()) + "<lockStatusResponse secured='false'/></response>";
			Message r = new Message(xmlString);
			
			// send to all connected clients except self
			for (String id : Server.ids()) {
				if (!id.equals(client.id())) {
					Server.getState(id).sendMessage(r);
				}
			}
			
			// be sure to send back to self as well 
			return r;
		}
		
		// requesting a lock... Might not be able to succeed if someone else has just grabbed it
		if (m.isLocked()) {
			// not possible to lock
			xmlString = Message.responseHeader(request.id(), ModelLocked) + 
			"<lockStatusResponse secured='false'/></response>";
			// send this response back to the client which sent us the request.
			return new Message (xmlString);
		}

		// update state and record that WE have it by associating a Boolean.TRUE object with the client.
		// if this client ever logs out unexpectedly, then we can release lock on its behalf.
		m.setLocked(true);
		client.setData(Boolean.TRUE); 
		
		// broadcast state
		xmlString = Message.responseHeader(request.id()) + "<lockStatusResponse secured='true'/></response>";
		Message r = new Message(xmlString);
		
		// send to all connected clients except self
		for (String id : Server.ids()) {
			if (!id.equals(client.id())) {
				Server.getState(id).sendMessage(r);
			}
		}
		
		// be sure to send back to self as well but WE now have lock
		xmlString = Message.responseHeader(request.id()) + "<lockResponse secured='true'/></response>";
		r = new Message(xmlString);
		return r;
	}
}
