package shapes.model;

import java.awt.Rectangle;

import shapes.controller.moves.DisconnectState;
import shapes.view.PoemDrawer;
import shapes.view.WordDrawer;

public class WordShape extends Shape {
	/**
	 * Unique ID for serialized Shape objects. 
	 */
	private static final long serialVersionUID = 9038630607217373669L;
	
	final String word;
	
	public WordShape (int x, int y, WordDrawer drawer, String word) {
		super (x, y, drawer);
		this.word = word;
	}

	public boolean intersect(int px, int py) {
		if (px < x) { return false; }
		if (px > x + drawer.getWidth(this)) { return false; }
		if (py < y) { return false; }
		if (py > y + drawer.getHeight(this)) { return false; }

		return true;
	}
	

	@Override
	public int getNumberRows() {
		return 1;
	}
	
	/**
	 * See if other shape intersects us.
	 */
	public boolean intersects(Shape other) {
		Rectangle me = new Rectangle (x, y, drawer.getWidth(this), drawer.getHeight(this));
		Rectangle them = new Rectangle (other.x, other.y, other.drawer.getWidth(other), other.drawer.getHeight(other));
		return me.intersects(them);
	}
	
	// need to check whether two objects are identical. Java interface
	public boolean equals (Object o) {
		if (o == null) { return false; }
		if (o instanceof WordShape) {
			WordShape other = (WordShape) o;
			return other.x == x && other.y == y && other.word.equals(word);
		}
		
		return false; // can't compare different classes.
	}

	@Override
	public Shape copy() {
		return new WordShape(x, y, new WordDrawer(), getContents());
	}

	@Override
	public String getContents() {
		return word;
	}

	@Override
	public WordShape[][] getState() {
		WordShape[][] st = new WordShape[][] { new WordShape[] { this }};
		return st;
	}
	
	/**
	 * We are a word, and we have been asked to connect this shape.
	 * 
	 */
	@Override
	public Shape verticalConnect(Shape shape, int newx, int newy) {
		PoemShape newOne = new PoemShape(shape.getX(), shape.getY(), new PoemDrawer(), shape.getState());
		WordShape[][] rows = getState();
		for (int row = 0; row < rows.length; row++) {
			newOne.append(rows[row]);
		}
		return newOne;
	}
	
	/**
	 * We are a word, and we have been asked to connect this shape horizontally
	 * to the closest internal wordshape in shape to (newx, newy)
	 * 
	 */
	@Override
	public Shape horizontalConnect(Shape shape, int newx, int newy) {
		PoemShape newOne = new PoemShape(shape.getX(), shape.getY(), new PoemDrawer(), shape.getState());
		
		// For all rows, work it
		WordShape[][] rows = getState();
		for (int row = 0; row < rows.length; row++) {
			newOne.extend(row, rows[row]);
		}
		return newOne;
	}
	
	public String toString() { 
		return "[" + word + "]";
	}

	/** WordShape cannot be split. */
	@Override
	public DisconnectState split(int x, int y) {
		return null;
	}

	/** No reconnection necessary. */
	@Override
	public boolean reconnect(DisconnectState ds) {
		return true;
	}
}
