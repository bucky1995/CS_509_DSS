package shapes.controller;

import java.awt.Point;
import java.awt.event.*;

import shapes.controller.moves.DisconnectShape;
import shapes.controller.moves.DisconnectState;
import shapes.model.*;
import shapes.view.*;

public class DisconnectController extends MouseAdapter {

	/** Needed for controller behavior. */
	Model model;
	Application app;
	ApplicationPanel panel;
	
	/** Original shape that was mutated. */
	Shape originalShape;
	
	/** Handles the disconnection. */
	DisconnectState originalState;
	
	/** Original x,y where shape was before move. */
	int originalx;
	int originaly;
	
	/** Anchor point where first grabbed and delta from that location. */
	Point anchor;
	int deltaX;
	int deltaY;
	
	/** Button that started off. */
	int buttonType;
	
	/** Constructor holds onto key manager objects. */
	public DisconnectController(Model model, Application app) {
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
		
		// find individual intersection point where (x,y) inside and return a state
		// object that knows how to separate (and rejoin later when needed)  the new shape 
		originalState = s.split(x,y);
		if (originalState == null) {
			// nothing to split. Leave now and nothing will be selected
			model.setSelected(null);
			return false;
		}
		
		// remember original location where disconnect (x,y) was first launched...
		originalShape = s;
		originalx = x;
		originaly = y;
		
		// no longer in the board since we are moving it around...
		Shape newShape = originalState.getNewShape();
		model.setSelected(newShape);
		
		
		// set drawer to highlight
		ShapeDrawer sd = newShape.getDrawer();
		sd.setState(ShapeDrawer.StateSelected);
		
		// set anchor for smooth moving
		deltaX = anchor.x - originalx;
		deltaY = anchor.y - originaly;
		
		panel.redraw();
		panel.repaint();
		return true;
	}	
	
	/** Separate out this function for testing purposes. */
	protected boolean drag (int x, int y) {
		// no board? no behavior! No dragging of right-mouse buttons...
		if (buttonType == MouseEvent.BUTTON3) { return false; }
		Shape selected = model.getSelected();
		
		if (selected == null) { return false; }
		
		panel.paintBackground(selected);
		int oldx = selected.getX();
		int oldy = selected.getY();
		
		selected.setLocation(x - deltaX, y - deltaY);
		
		boolean ok = true;
		
		// must still be visible!
		if (selected.getX() < 0) { ok = false; }
		if (selected.getX() + selected.getWidth() > panel.getWidth()) { ok = false; }
		if (selected.getY() < 0) { ok = false; }
		if (selected.getY() + selected.getHeight() > panel.getHeight()) { ok = false; }
		
		if (!ok) {
			selected.setLocation(oldx, oldy);
		} else {
			panel.repaint();
		}
	
		return true;
	}
	
	/** Separate out this function for testing purposes. */
	protected boolean release (int x, int y) {
		Shape selected = model.getSelected();
		if (selected == null) { return false; }

		// now released we can create Move
		model.getBoard().add(selected);
		app.getWordTable().getShapeModel().sort();
		DisconnectShape move = new DisconnectShape(model, originalState, originalx, originaly, x, y);
		if (move.execute()) {
			model.recordMove(move);
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
