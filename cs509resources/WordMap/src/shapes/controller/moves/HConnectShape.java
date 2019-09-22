package shapes.controller.moves;

import shapes.controller.Move;
import shapes.model.*;

/**
 * Connect shape horizontally to extend rows.
 */
public class HConnectShape extends Move {

	/** Shape initiating the connection. */
	final Shape shape;
	
	/** Target shape receiving the connection. */
	final Shape target;
	
	/** Where original shape had been located. */
	int oldx;
	int oldy;
	
	/** Where target shape receives trigger of event. */
	int newx; 
	int newy;
	
	/** Original target location */
	int targetx;
	int targety;
	
	int lastSelectedY;
	int lastSelectedX;
	
	/** When connect, a new shape is constructed. Stored here to support undo. */
	Shape newShape;

	/** Move will update the model. */
	final Model model;

	public HConnectShape(Model model, Shape movedShape, int oldx, int oldy, Shape target, int newx, int newy) {
		this.model = model;
		this.shape = movedShape;
		this.oldx = oldx;
		this.oldy = oldy;
		this.target = target;
		this.targetx = target.getX();
		this.targety = target.getY();
		this.newx = newx;
		this.newy = newy;
		this.lastSelectedX = movedShape.getX();
		this.lastSelectedY = movedShape.getY();
	}

	@Override
	public boolean execute() {
		Board b = model.getBoard();
		
		// should work regardless of the structure of 'target' and 'shape', which 
		// are both Shapes (but one could be WordShape the other PoemShape)
		shape.setLocation(lastSelectedX, lastSelectedY);
		newShape = target.horizontalConnect(shape, newx, newy);
		if (newShape == null) {
			return false;
		}
		
		b.remove(shape);
		b.remove(target);
		b.add(newShape);
		return true;
	}

	@Override
	public boolean undo() {
		Board b = model.getBoard();
		b.remove(newShape);
		b.add(target);
		b.add(shape);
		
		shape.setLocation(oldx,  oldy);
		target.setLocation(targetx, targety);
		return true;
	}

}
