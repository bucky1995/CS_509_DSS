package contacts.controller;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JList;

import contacts.model.Contact;
import contacts.model.Model;
import contacts.view.ContactFrame;
import contacts.view.IndividualContactFrame;
import junit.framework.TestCase;

/** Actually validates IndividualContactFrame does the right thing. */
public class TestSelect extends TestCase {

	Model model;
	ContactFrame frame;
	Contact c1, c2;
	
	public void setUp() {
		model = new Model();
		c1 = new Contact("Someone", "1-508-831-0000", "someone@wpi.edu");
		c2 = new Contact("ZPerson At End", "1-508-831-0000", "someone@wpi.edu");
		model.add(c1);
		model.add(c2);
		frame = new ContactFrame(model);
		frame.populate();
		frame.setVisible(true);
	}
	
	public void tearDown() {
		frame.setVisible(false);
		frame.dispose();
	}
	
	public void testRemove() {
		assertTrue (frame.getAddButton().isEnabled());

		// nothing selected, so buttons not active
		assertFalse (frame.getUpdateButton().isEnabled());
		assertFalse (frame.getRemoveButton().isEnabled());

		// select first one (which is Someone)
		JList list = frame.getContactsList();
		list.setSelectedIndex(0); // choose first one
		
		// act as if selected
		new SelectItemController(model, frame).process();
		
		// buttons are now enabled
		assertTrue (frame.getUpdateButton().isEnabled());
		assertTrue (frame.getRemoveButton().isEnabled());
	}
}
