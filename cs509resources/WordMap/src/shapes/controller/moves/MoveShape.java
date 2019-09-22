package shapes.controller.moves;

import shapes.controller.Move;
import shapes.model.*;

public class MoveShape extends Move {

	final Shape shape;
	final int   newx;
	final int   newy;
	int oldx;
	int oldy;
	
	public MoveShape(Shape s, int oldx, int oldy, int newx, int newy) {
		this.shape = s;
		this.oldx = oldx;
		this.oldy = oldy;
		this.newx = newx;
		this.newy = newy;
	}
	
	@Override
	public boolean execute() {
		shape.setLocation(newx, newy);
		return true;
	}

	@Override
	public boolean undo() {
		shape.setLocation(oldx,  oldy);
		return true;
	}

}
