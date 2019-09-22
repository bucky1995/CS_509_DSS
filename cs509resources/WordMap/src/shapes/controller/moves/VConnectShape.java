package shapes.controller.moves;

import shapes.controller.Move;
import shapes.model.*;

/**
 * Needs access to the model to make this happen.
 * 
 */
public class VConnectShape extends Move {

	final Shape shape;
	final Shape target;
	int oldSelectedX;
	int oldSelectedY;
	int releaseX;   // where mouse was released (x,y) 
	int releaseY;
	int targetx;
	int targety;
	int lastSelectedY;
	int lastSelectedX;
	final Model model;
	Shape newShape;
	
	public VConnectShape(Model model, Shape movedShape, int oldx, int oldy, Shape target, int relx, int rely) {
		this.model = model;
		this.shape = movedShape;
		this.target = target;
		this.targetx = target.getX();
		this.targety = target.getY();		
		this.releaseX = relx;
		this.releaseY = rely;
		this.oldSelectedX = oldx;
		this.oldSelectedY = oldy;		
		this.lastSelectedX = movedShape.getX();
		this.lastSelectedY = movedShape.getY();
	}

	@Override
	public boolean execute() {
		Board b = model.getBoard();
			
		// should work regardless of the structure of 'target' and 'shape', which 
		// are both Shapes (but one could be WordShape the other PoemShape)
		System.out.println(target.getX() + "," + target.getY() + "," + oldSelectedX + "," + oldSelectedY + "," + shape.getX()+ ","+ shape.getY()+ ","+ releaseX+ ","+ releaseY);
		shape.setLocation(lastSelectedX, lastSelectedY);
		newShape = target.verticalConnect(shape, releaseX, releaseY);
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
		System.out.println(target.getX() + "," + target.getY() + "," + oldSelectedX + "," + oldSelectedY + "," + shape.getX()+ ","+ shape.getY()+ ","+ releaseX+ ","+ releaseY + " UNDO");
		
		shape.setLocation(oldSelectedX,  oldSelectedY);

		target.setLocation(targetx, targety);
		return true;
	}

}
