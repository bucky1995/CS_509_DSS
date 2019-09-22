package shapes.controller;

import shapes.model.Model;
import shapes.model.Shape;
import shapes.view.ApplicationPanel;
import shapes.view.ShapeDrawer;

/**
 * Respond to selection events in the WordTable, which has been configured to only 
 * allow individual rows to be selected.
 */
public class SelectItemController {

	/** Needs model to be able to select elements. */
	Model model;
	
	/** Needs panel so it can redraw the board. */
	ApplicationPanel panel;

	/** Controller constructed with model (entity) and panel (boundary). */
	public SelectItemController(Model model, ApplicationPanel panel) {
		this.panel = panel;
		this.model = model;
	}

	/**
	 * Selection event occurred; if activate is true, then selection starting, otherwise selection ending
	 * 
	 * @param shapeIndex which element from the model was selected
	 * @param activate whether selecting or un-selecting
	 */
	public void process(int shapeIndex, boolean activate) {
		
		Shape s = model.getSelected();
		ShapeDrawer sd;
		if (s != null) {
			// turn off prior one, if there is one
			sd = s.getDrawer();
			sd.setState(ShapeDrawer.StateNormal);
			
			panel.paintBackground(s);
			panel.paintShape(s);

			// make sure to deselect [detected during testing]
			model.setSelected(null);
			panel.redraw();
			panel.repaint();
		}
		
		// when false, user has released mouse on the item, otherwise still dragging...
		if (!activate) { return; }
		
		s = model.getBoard().getShape(shapeIndex);
		if (s != null) {
			model.setSelected(s);
			sd = s.getDrawer();
			sd.setState(ShapeDrawer.StateSelected);
			
			panel.paintBackground(s);
			panel.paintShape(s);
			panel.redraw();
			panel.repaint();
		}
	}
}
