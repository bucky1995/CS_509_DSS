package oval;

import java.awt.event.*;

import javax.swing.*;
import oval.model.*;
import oval.view.*;
import oval.controller.*;

public class Main {
	public static void main(String[] args) {
		Model m = new Model();

		final Application app = new Application(m);
		app.addWindowListener (new WindowAdapter() {

			public void windowClosing(WindowEvent e) {
				new ExitApplicationController(app).process();
			}

		});

		// Tell app window that we will be responsible for closing application  
		app.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		app.setVisible(true);
	}
}