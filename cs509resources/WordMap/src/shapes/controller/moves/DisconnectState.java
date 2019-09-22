package shapes.controller.moves;

import shapes.model.Shape;

/**
 * Records sufficient state about a disconnect to be able to reconnect later.
 * 
 * Disconnecting objects are specialized operations that require extra information to be
 * able to reconnect later, on undo for example. With this information, the original
 * poem shape can be reconstituted.
 * 
 */
public class DisconnectState {
	/** Original shape. */
	Shape original;
	
	/** Original row where shape was extracted from. */
	public final int originalRow;
	
	/** Offset into that row where shape was extracted from. */
	public final int originalOffset;
	
	/** New shape being created. Might be null. */
	Shape newShape;
	
	public DisconnectState(Shape s, int row, int offset) {
		this.original = s;
		this.originalRow = row;
		this.originalOffset = offset;
	}
	
	/** Set the new shape which was constructed as part of the split. */
	public void setNewShape(Shape s) {
		this.newShape = s;
	}

	/** Return shape that was constructed as part of the split. */
	public Shape getNewShape() {
		return newShape;
	}
}
