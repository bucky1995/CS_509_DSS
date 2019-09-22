package distributed.controller.client;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import distributed.model.Model;
import distributed.model.Value;
import distributed.view.Application;

import xml.Message;

/**
 * Extract the model changes from the message and update client model to synchronize with 
 * the server.
 */
public class ModelResponseController {

	// helper method to increment/decrement proper value
	void update (Value value, int num) {
		// see if we can raise value
		if (num > value.getValue()) {
			while (num > value.getValue() && value.getValue() < value.getMaximum()) {
				value.increment();
			}
		} else if (num < value.getValue()) {
			// see if we can lower value
			while (num < value.getValue() && value.getValue() > value.getMinimum()) {
				value.decrement();
			}
		}			
	}
	
	public void process(Message response) {
		// this refers to the outer node of the Message DOM (in this case, updateResponse).
		Node update = response.contents.getFirstChild();

		// we need each of the three children which appear as attributes
		NamedNodeMap map = update.getAttributes();
		String height = map.getNamedItem("height").getNodeValue();
		String width = map.getNamedItem("width").getNodeValue();
		String color = map.getNamedItem("color").getNodeValue();
		
		// update the model
		Model model = Model.getInstance();
		update (model.getHeight(), Integer.valueOf(height));
		update (model.getWidth(), Integer.valueOf(width));
		update (model.getColor(), Integer.valueOf(color));
		
		// now that model has been updated. Refresh views.
		Application app = Application.getInstance();
		app.modelChanged();
		
		// were we the ones locking this model?
		
	}

}
