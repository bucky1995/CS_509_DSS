package distributed.controller.client;

import javax.swing.*;

import xml.Message;
import distributed.view.Application;

/**
 * Controller to handle interaction where user requests to close application.
 * 
 * Return true if the request is allowed, false otherwise.
 */
public class QuitController {
	Application app;
	
	public QuitController (Application app) {
		this.app = app;
	}
	
	/** This method supports GUI-based selection of the user decision. */
	public boolean confirm() {

		int c = JOptionPane.showConfirmDialog (app, "Do you wish to exit Distributed EBC Application?");

		if (c == JOptionPane.OK_OPTION) {
			requestQuit();
			return true;
		}

		// don't exit
		return false;
	}

	/** Separate out this method which contains the REAL logic so it can be tested. */
	void requestQuit() {
		System.out.println();
		// If we had been modifying model, must remove lock first
		if (app.hasAccess()) {
			String xmlString = Message.requestHeader() + "<lockRequest grab='false'/></request>";
			Message m = new Message(xmlString);
			app.getServerAccess().sendRequest(m);
		}
		
		// any special provisions to cleanly shutdown application, do so here
		app.getServerAccess().disconnect();
	}  
}
