import org.w3c.dom.Node;

import distributed.controller.server.ClientDisconnectController;
import distributed.controller.server.LockRequestController;
import distributed.controller.server.LockStatusRequestController;
import distributed.controller.server.SendModelController;
import distributed.controller.server.UpdateModelController;

import server.*;
import xml.*;

/**
 * Sample implementation of a protocol handler to respond to messages received from clients.
 * You should follow this template when designing YOUR protocol handler.
 * <p>
 * To avoid issues with multiple clients submitting requests concurrently,
 * simply make the {@link #process(ClientState, Message)} method synchronized.
 * This will ensure that no more than one server thread executes this method
 * at a time.
 * <p>
 * Also extended to support detection of client disconnects so these can release the lock
 * if indeed the client was the one locking the model.
 */
public class SampleProtocolHandler implements IShutdownHandler {
	
	@Override
	public synchronized Message process (ClientState st, Message request) {
		Node child = request.contents.getFirstChild();
		String type = child.getLocalName();
		
		System.out.println (request);
		
		// send back our model
		if (type.equals ("modelRequest")) {
			return new SendModelController().process(st, request);
		} else if (type.equals ("updateRequest")) {
			return new UpdateModelController().process(st, request);
		} else if (type.equals ("lockRequest")) {
			return new LockRequestController().process(st, request);
		} else if (type.equals ("lockStatusRequest")) {
			return new LockStatusRequestController().process(st, request);
		}

		// unknown? no idea what to do
		System.err.println("Unable to handle message:" + request);
		return null;
	}

	@Override
	public void logout(ClientState st) {
		new ClientDisconnectController().process(st);		
	} 
}
