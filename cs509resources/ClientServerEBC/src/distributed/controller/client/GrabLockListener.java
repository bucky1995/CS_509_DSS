package distributed.controller.client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import xml.Message;

import distributed.model.Model;
import distributed.view.Application;

/**
 * User is requesting to grab the lock from the server.
 */
public class GrabLockListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		Model model = Model.getInstance();
		
		// technically possible for user to issue second request just as response is updating model.
		// Just check in case.
		if (!model.isLocked()) {
			// send the request to grab the lock
			String xmlString = Message.requestHeader() + "<lockRequest grab='true'/></request>";
			Message m = new Message (xmlString);
			
			// Request the lock (this might not succeed).
			Application app = Application.getInstance();
			app.getServerAccess().sendRequest(m);
		}
	}

}
