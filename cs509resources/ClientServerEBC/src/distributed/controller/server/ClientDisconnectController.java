package distributed.controller.server;

import java.util.UUID;

import server.ClientState;
import server.Server;
import xml.Message;
import distributed.model.Model;

/**
 * Controller to detect when terminating client has lock, and release if it is the case.
 * 
 * Take advantage of ClientServer ability to store extra information with the ClientState
 * object, in this case, the 
 */
public class ClientDisconnectController {

	public void process(ClientState state) {
		Model m = Model.getInstance();
		
		// model isn't locked so there is nothing to be done when client logs out.
		if (!m.isLocked()) { return; }
		
		// no associated state information? Then we didn't lock it [See LockRequestController]
		if (state.getData() == null) { return; }
		
		// releasing lock! Broadcast to everyone
		m.setLocked(false);
			
		// broadcast state
		String uid = UUID.randomUUID().toString();
		String xmlString = Message.responseHeader(uid) + "<lockStatusResponse secured='false'/></response>";
		Message r = new Message(xmlString);
		
		// send to all connected clients except self
		for (String id : Server.ids()) {
			if (!id.equals(state.id())) {
				Server.getState(id).sendMessage(r);
			}
		}
	}
}
