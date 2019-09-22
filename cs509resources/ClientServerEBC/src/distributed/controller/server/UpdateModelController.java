package distributed.controller.server;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import distributed.model.Model;
import distributed.model.Value;
import server.ClientState;
import server.Server;
import xml.Message;

/**
 * A client has updated the model and now we need to distributed to all connected clients.
 * This controller sends the response to all connected clients, and then return a response
 * just for originating client.
 */
public class UpdateModelController {

	/**
	 * This package-private method is off limits outside of this package,
	 * but it serves the essential purpose of being available for testing.
	 * Through this method you test that changes to TextField result in 
	 * changes to the Value governed by this controller.
	 */
	void update (Value value, int num) {
	// see if we can raise value
		if (num > value.getValue()) {
			while (num > value.getValue() && value.getValue() < value.getMaximum()) {
				value.increment();
			}

		} else if (num < value.getValue()) {
			// see if we can lower value
			while (num < value.getValue() && value.getValue() > value.getMinimum()) {
				value.decrement();
			}
			
		}			
	}
	
	/** 
	 * Retrieve w/h/c values from the request, update our model, and broadcast changes out to clients.
	 * 
	 * @param st
	 * @param request
	 * @return
	 */
	public Message process(ClientState st, Message request) {
	
		Node update = request.contents.getFirstChild();
		NamedNodeMap map = update.getAttributes();
		
		Model model = Model.getInstance();
		
		// make relevant changes to the model
		update (model.getHeight(), Integer.valueOf(map.getNamedItem("height").getNodeValue()));
		update (model.getWidth(), Integer.valueOf(map.getNamedItem("width").getNodeValue()));
		update (model.getColor(), Integer.valueOf(map.getNamedItem("color").getNodeValue()));
		
		
		// now broadcast out.
		
		String xmlString = Message.responseHeader(request.id()) + "<modelResponse " +
				"height = '" + model.getHeight() + "' " +
				"width = '"  + model.getWidth()  + "' " + 
				"color = '"  + model.getColor()  + "'/>" +
				"</response>";
		
		Message r = new Message(xmlString);	
		
		// for all connected servers (EXCEPT originating client) send updated state.
		for (String id : Server.ids()) {
			ClientState cs = Server.getState(id);
			
			cs.sendMessage(r);
		}
		
		// this response goes back to originating client.
		return r;
	}

}
