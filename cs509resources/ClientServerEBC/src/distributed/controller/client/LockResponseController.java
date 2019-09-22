package distributed.controller.client;

import java.awt.Button;

import xml.Message;
import distributed.model.Model;
import distributed.view.Application;

/**
 * When client gets this response, he has the lock.
 */
public class LockResponseController {

	public void process(Message response) {
		// this refers to the outer node of the Message DOM (in this case, updateResponse).
		
		Application app = Application.getInstance();
		Button button = app.getInnerPanel().getServerPanel().getExclusiveButton();
		app.allowAccess(true);
		
		// note: Must secure lock!
		Model m = Model.getInstance();
		m.setLocked(true);
		
		// now enable appropriately
		button.setLabel("You Have Lock");
		button.setEnabled(false);
	}

}
