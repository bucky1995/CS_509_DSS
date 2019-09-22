package contacts;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.DefaultListModel;

import contacts.controller.QuitController;
import contacts.model.*;
import contacts.view.ContactFrame;
import storage.IStorage;
import util.Util;

public class Main {
		public static void main(String args[]) {
			Model m = new Model();

			// load up from test storage
			IStorage st = new storage.RealStorage();
			st.contacts().forEachRemaining(c -> m.add(c));

			ContactFrame frame = new ContactFrame(m);
			frame.populate();

			frame.addWindowListener (new WindowAdapter() {

				public void windowClosing(WindowEvent e) {
					if (new QuitController().confirm(frame)) {
						st.store(m.contacts());
						frame.dispose();
					}
				}
			});

			Util.centerFrame(frame);
			frame.setVisible(true);
		}
	}
