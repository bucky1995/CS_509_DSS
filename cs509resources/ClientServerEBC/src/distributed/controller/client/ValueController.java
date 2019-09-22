package distributed.controller.client;

import java.awt.Scrollbar;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

import distributed.model.*;
import distributed.view.*;

/**
 * Controller to manage access to a Value by means of a scrollbar. 
 */
public class ValueController implements AdjustmentListener {

	/** Element being controlled. */
	Value value;
	
	/** Entity to react to changes in model. */
	IModelUpdated view;
	
	/** When value changes, notify the desired entity */
	public ValueController (Value value, IModelUpdated view) {
		this.value = value;
		this.view = view;
	}
	
	/** Increment value by a fixed count. */
	void incrementCount (int ct) {
		for (int i = 0; i < ct; i++) {
			if (value.getValue() != value.getMaximum()) {
				value.increment();
			}
		}

		view.modelChanged();
	}
	
	/** Decrement value by a fixed count. */
	void decrementCount (int ct) {
		for (int i = 0; i < ct; i++) {
			if (value.getValue() != value.getMinimum()) {
				value.decrement();
			}
		}
		
		view.modelChanged();
	}

	/**
	 * Controller to respond to scrollbar events and update the model element accordingly.
	 */
	public void adjustmentValueChanged(AdjustmentEvent e) {
		
		if (!e.getValueIsAdjusting()) {
			// final value. Time to tell server about what's going on!
			Application.updateModelOnServer();
			return;
		}
		
		// act differently based upon the action.
		switch (e.getAdjustmentType()) {
				
		case AdjustmentEvent.UNIT_INCREMENT:
			incrementCount(1);
			break;
			
		case AdjustmentEvent.UNIT_DECREMENT:
			decrementCount(1);
			break;
			
		case AdjustmentEvent.BLOCK_INCREMENT:
			Scrollbar sc = (Scrollbar) e.getSource();  // scrollbar which generated event
			incrementCount (sc.getBlockIncrement());
			break;
			
		case AdjustmentEvent.BLOCK_DECREMENT:
			Scrollbar dc = (Scrollbar) e.getSource();  // scrollbar which generated event
			decrementCount (dc.getBlockIncrement());
			break;
			
		// dynamically track the changes as they come in. 
		case AdjustmentEvent.TRACK:
			int newValue = e.getValue();
			
			if (newValue < value.getValue()) {
				decrementCount (value.getValue() - newValue);
			} else if (newValue > value.getValue()) {
				incrementCount (newValue - value.getValue());
			}
			break;
		}
	}
}
