package distributed.controller.client;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import distributed.model.Model;
import distributed.view.Application;

import xml.Message;

/**
 * Tells the client whether the model is locked or not BY SOME OTHER CLIENT. This will never be returned to a client
 * to tell him that HE has the model locked (that is job of LockResponse).
 */
public class LockStatusResponseController {

	public void process(Message response) {
		// this refers to the outer node of the Message DOM (in this case, updateResponse).
		Node lockResponse = response.contents.getFirstChild();
		NamedNodeMap map = lockResponse.getAttributes();
		
		// If the model is Secured then it is by some other client so we must disallow GUI access
		String secured = map.getNamedItem("secured").getNodeValue();
		Boolean isSecured = Boolean.valueOf(secured);
			
		// if model is secured at the server then we don't have access on the client.
		Application app = Application.getInstance();
		app.allowAccess(false);
		Model model = Model.getInstance();
		model.setLocked(isSecured);

		if (!isSecured) {
			app.getInnerPanel().getServerPanel().getExclusiveButton().setLabel("Grab Lock");
		} else {
			app.getInnerPanel().getServerPanel().getExclusiveButton().setLabel("locked");
		}
		app.getInnerPanel().getServerPanel().getExclusiveButton().setEnabled(!isSecured);
	}

}
