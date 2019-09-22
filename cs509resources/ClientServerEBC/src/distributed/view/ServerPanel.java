package distributed.view;

import distributed.controller.client.GrabLockListener;
import distributed.model.*;

import java.awt.*;

/**
 * Graphical elements used to present server-exclusive button to user.
 */
public class ServerPanel extends Panel implements IModelUpdated {
	
	/** GUI widgets used only in this class. */
	Button exclusiveButton = null;
	
	/** View is told the model so it can properly represent it. */
	public ServerPanel () {
		super();
		initialize();
	}

	/**
	 * Initialize the panel to contain all control and view widgets
	 */
	void initialize() {
		Label labelC = new Label();
		labelC.setBounds(new Rectangle(60, 9, 145, 23));
		labelC.setText("Exclusive Access");

		setLayout(null);
		setBounds(new Rectangle(14, 44, 226, 167));
		
		// add GUI elements and install controllers
		add(getExclusiveButton());
		add(labelC);
	}

	/**
	 * Instantiate and/or return button requesting exclusive access.
	 */
	public Button getExclusiveButton() {
		if (exclusiveButton == null) {
			exclusiveButton = new Button("Grab Lock");
			exclusiveButton.setBounds(new Rectangle(60, 60, 96, 32));
			
			exclusiveButton.addActionListener(new GrabLockListener());
		}
		return exclusiveButton;
	}

	/**
	 * Properly respond to updates to model.
	 */
	public void modelChanged() {
		Model model = Model.getInstance();
		if (model.isLocked()) {
			exclusiveButton.setEnabled(false);
		} else {
			exclusiveButton.setEnabled(true);
		}
	}
}
