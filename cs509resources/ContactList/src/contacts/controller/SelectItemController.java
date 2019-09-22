package contacts.controller;

import javax.swing.JList;

import contacts.model.*;
import contacts.view.*;

public class SelectItemController {
	
	Model model;
	ContactFrame frame;
	
	public SelectItemController (Model m, ContactFrame frame) {
		this.model = m;
		this.frame = frame;
	}
	
	public void process() {
		JList<? extends Contact> list =  frame.getContactsList();
		int idx = list.getSelectedIndex();
		
		frame.getUpdateButton().setEnabled(idx >= 0);
		frame.getRemoveButton().setEnabled(idx >= 0);
	}
}
