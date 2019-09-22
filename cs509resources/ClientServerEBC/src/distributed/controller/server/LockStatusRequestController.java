package distributed.controller.server;

import server.ClientState;
import server.IProtocolHandler;
import xml.Message;
import distributed.model.Model;

/**
 * Controller to respond on the lock status on the server.
 */
public class LockStatusRequestController implements IProtocolHandler {

	public Message process(ClientState client, Message request) {
		Model m = Model.getInstance();
		
		String xmlString = Message.responseHeader(request.id()) + "<lockStatusResponse secured='" + m.isLocked() + "'/></response>";
		Message r = new Message (xmlString);
		return r;
	}
}
