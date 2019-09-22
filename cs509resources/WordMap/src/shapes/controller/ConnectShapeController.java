package shapes.controller;

import java.awt.Point;
import java.awt.event.*;

import shapes.model.*;
import shapes.view.*;

/**
 * Master mouse controller for dragging a shape and seeking to interact with another shape in some fashion.
 * 
 * Key idea is that once pressed on a shape, one becomes the 'selected' shape, and then as it drags through the board
 * it interacts with other target shapes (one at a time), and if the user releases the mouse over one of these, then the
 * controller action takes place. That action is delegated to a subclass.
 */
public abstract class ConnectShapeController extends MouseAdapter {

	/** Needed for controller behavior. */
	Model model;
	Application app;
	ApplicationPanel panel;
	
	/** Original x,y where shape was before move. */
	int originalx;
	int originaly;
	
	/** Anchor point where first grabbed and delta from that location. */
	Point anchor;
	int deltaX;
	int deltaY;

	/** Button that started off. */
	int buttonType;
	
	/** Original drawer. */
	Shape originalTarget;
	
	/** Constructor holds onto key manager objects. */
	public ConnectShapeController(Model model, Application app) {
		this.model = model;
		this.app = app;
		this.panel = app.getWordPanel();
	}

	/** Set up press events but no motion events. */
	public void register() {
		panel.setActiveListener(this);
		panel.setActiveMotionListener(this);
	}

	/**
	 * Whenever mouse is pressed (left button), attempt to select object.
	 * This is a GUI controller.
	 */
	@Override
	public void mousePressed(MouseEvent me) {
		buttonType = me.getButton();
		select(me.getX(), me.getY());
	}
	
	/**
	 * Whenever mouse is dragged, attempt to start object.
	 * This is a GUI controller.
	 */
	@Override
	public void mouseDragged(MouseEvent me) {
		drag(me.getX(), me.getY());
	}
	
	/**
	 * Whenever mouse is released, complete the move. 
	 * This is a GUI controller.
	 */
	@Override
	public void mouseReleased(MouseEvent me) {
		release(me.getX(), me.getY());
	}
	
	/** Separate out this function for testing purposes. */
	protected boolean select(int x, int y) {
		anchor = new Point (x, y);
			
		// pieces are returned in order of Z coordinate
		Shape s = model.getBoard().findShape(anchor.x, anchor.y);
		if (s == null) { return false; }
		
		// no longer in the board since we are moving it around...
		model.getBoard().remove(s);
		model.setSelected(s);
		panel.paintBackground(s);
		originalx = s.getX();
		originaly = s.getY();
		
		// set drawer to highlight
		ShapeDrawer sd = s.getDrawer();
		sd.setState(ShapeDrawer.StateSelected);
			
		// set anchor for smooth moving
		deltaX = anchor.x - originalx;
		deltaY = anchor.y - originaly;
		
		// paint will happen once moves. This redraws state to prepare for paint
		panel.redraw();
		return true;
	}	
	
	/** Separate out this function for testing purposes. */
	protected boolean drag (int x, int y) {
		// no board? no behavior! No dragging of right-mouse buttons...
		if (buttonType == MouseEvent.BUTTON3) { return false; }
		Shape selected = model.getSelected();
		if (selected == null) { return false; }
		
		selected.setLocation(x - deltaX, y - deltaY);
		
		/** Have we found a target to which we connect? Also make sure (x,y) intersects as well. */
		Shape found = model.getBoard().findShape(x, y);
		
		// take care of previously selected one
		if (originalTarget != found && originalTarget != null) {
			ShapeDrawer sd = originalTarget.getDrawer();
			sd.setState(ShapeDrawer.StateNormal);
			panel.redraw();
		}
		
		if (found != null ) {
			if (originalTarget != found) {
				originalTarget = found;
				
				ShapeDrawer sd = originalTarget.getDrawer();
				sd.setState(ShapeDrawer.StateTarget);
				panel.redraw();
			}
		} else {

			originalTarget = null;
		}
		
		panel.repaint();
		return true;
	}

	/** Meant for subclasses to override. */
	protected abstract boolean takeAction (Shape selected, int x, int y);
	
	/** Separate out this function for testing purposes. */
	protected boolean release (int x, int y) {
		Shape selected = model.getSelected();
		if (selected == null) { return false; }

		if (originalTarget == null) {
			// turn this into a move
			model.getBoard().add(selected);
		} else {
			
			if (takeAction(selected, x,y)) {
				// move succeeded. Nothing to do.
			} else {
				// put back in proper spot if mistake or no connection at all
				model.getBoard().add(selected);
				selected.setLocation(originalx, originaly);
				
				// clear up target if it exists
				if (originalTarget != null) {
					ShapeDrawer sd = originalTarget.getDrawer();
					sd.setState(ShapeDrawer.StateNormal);
				}
			}
		}
		
		// return to original drawer
		ShapeDrawer sd = selected.getDrawer();
		sd.setState(ShapeDrawer.StateNormal);
		
		// no longer selected
		model.setSelected(null);
		
		panel.redraw();
		panel.repaint();
		return true;
	}
}
