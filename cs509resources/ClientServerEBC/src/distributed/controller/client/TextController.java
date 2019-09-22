package distributed.controller.client;

import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import distributed.model.*;
import distributed.view.*;

/**
 * Controller to manage the text views of the model.
 */
public class TextController implements ActionListener {

	/** Element being controlled. */
	Value value;
	
	/** Entity to react to changes in model. */
	IModelUpdated view;
	
	/** When value changes, notify the desired entity */
	public TextController (Value value, IModelUpdated view) {
		this.value = value;
		this.view = view;
	}
	
	/**
	 * Respond to attempts to change the text field.
	 * 
	 * Convert this into a method invocation that doesn't require user action. 
	 * This method will not be tested automatically. You review this method
	 * by code review post-coverage to validate that it works as intended.
	 */
	public void actionPerformed(ActionEvent ae) {
		TextField tf = (TextField) ae.getSource();
		update (tf);
		
		// at this point, the view has been updated locally and we propagate the values on to the server
		// place this common method in the Application class as a static method
		Application.updateModelOnServer();
	}
	
	/**
	 * This package-private method is off limits outside of this package,
	 * but it serves the essential purpose of being available for testing.
	 * Through this method you test that changes to TextField result in 
	 * changes to the Value governed by this controller.
	 */
	void update (TextField tf) {
		try {
			int num = Integer.valueOf(tf.getText());
			
			// see if we can raise value
			if (num > value.getValue()) {
				while (num > value.getValue() && value.getValue() < value.getMaximum()) {
					value.increment();
				}

				view.modelChanged();
			} else if (num < value.getValue()) {
				// see if we can lower value
				while (num < value.getValue() && value.getValue() > value.getMinimum()) {
					value.decrement();
				}
				
				view.modelChanged();
			}			
			
		} catch (Exception e) {
			// just put old value back in
			tf.setText("" + value);
		}
	}
}
