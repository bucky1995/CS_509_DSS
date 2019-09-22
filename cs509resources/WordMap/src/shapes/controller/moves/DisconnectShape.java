package shapes.controller.moves;

import shapes.controller.Move;
import shapes.model.*;

/**
 * Disconnect shape as requested by user
 */
public class DisconnectShape extends Move {

	/** State about the disconnection. */
	DisconnectState state;
	
	/** Where target shape receives trigger of event. */
	int x; 
	int y;
	
	/** Where new shape was dragged. */
	int finalx;
	int finaly;
	
	/** Disconnect will update the model. */
	final Model model;

	public DisconnectShape(Model model, DisconnectState state, int x, int y, int finalx, int finaly) {
		this.model = model;
		this.state = state;
		this.x = x;
		this.y = y;
		this.finalx = finalx;
		this.finaly = finaly;
	}

	// has already been done.
	@Override
	public boolean execute() {
		if (state.getNewShape() == null) {
			DisconnectState ds = state.original.split(x,y);
			if (ds == null) {
				return false;
			}
			
			Shape newShape = ds.getNewShape();
			model.getBoard().add(newShape);
			state.setNewShape(newShape);
			newShape.setLocation(finalx, finaly);
		}
		
		// was already during initial controller
		return true;
	}

	@Override
	public boolean undo() {
		Board b = model.getBoard();
		
		Shape s = state.original;
		s.reconnect(this.state);
		
		// this will "snap" together everyone back into place...
		s.setLocation (s.getX(), s.getY());
		
		// remove and clear. If user requests to undo move, then execute handles that case
		b.remove(state.getNewShape());
		state.setNewShape(null);
		return true;
	}

}
