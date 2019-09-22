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
public class TestUpdate extends TestCase {

	Model model;
	ContactFrame frame;
	Contact c1;
	
	public void setUp() {
		model = new Model();
		c1 = new Contact("Someone", "1-508-831-0000", "someone@wpi.edu");
		model.add(c1);
		frame = new ContactFrame(model);
		frame.populate();
		frame.setVisible(true);
	}
	
	public void tearDown() {
		frame.setVisible(false);
		frame.dispose();
	}
	
	public void testLoadedProperly() {
		JList<Contact> list = frame.getContactsList();
		list.setSelectedIndex(0);
		
		IndividualContactFrame icf = new IndividualContactFrame(c1);
		icf.getNameField().setText("name1");
		icf.getPhoneField().setText("phone1");
		icf.getEmailField().setText("email1");
		icf.setVisible(true);
		
		// act on the dialog using the 2nd half of the controller.
		new UpdateContactController(model, frame).act(icf);
		
		assertEquals ("name1", c1.getName());
		assertEquals ("phone1", c1.getPhone());
		assertEquals ("email1", c1.getEmail());		
	}
}
