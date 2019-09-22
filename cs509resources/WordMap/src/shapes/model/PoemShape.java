package shapes.model;

import java.util.Iterator;
import java.util.LinkedList;

import shapes.controller.moves.DisconnectState;
import shapes.view.PoemDrawer;
import shapes.view.ShapeDrawer;

/**
 * Collection of rows, each containing WordShape
 * 
 */
public class PoemShape extends Shape implements Iterable<WordShape> {
	/**
	 * Unique ID for serialized Shape objects. 
	 */
	private static final long serialVersionUID = 9038630607217373669L;
	
	/** State is a 2d-array of strings, one per row. */
	WordShape[][] state;
	
	public PoemShape (int x, int y, ShapeDrawer drawer) {
		super(x, y, drawer);
		state  = new WordShape[0][0];
	}

	
	public PoemShape(int x, int y, ShapeDrawer drawer, WordShape[][] state) {
		this(x, y, drawer);
		
		this.state = new WordShape[state.length][];
		for (int row = 0; row < state.length; row++) {
			this.state[row] = new WordShape[state[row].length];
			System.arraycopy(state[row], 0, this.state[row], 0, state[row].length);
		}
	}

	@Override
	public boolean intersect(int px, int py) {
		Iterator<WordShape> it = iterator();
		while (it.hasNext()) {
			Shape s = it.next();
			if (s.intersect(px, py)) {
				return true;
			}
		}
		
		return false;
	}

	public int getX() { return x; }
	public int getY() { return y; }
	public int getWidth() { return drawer.getWidth(this); }
	public int getHeight() { return drawer.getHeight(this); }
	
	/**
	 * Must pass along to all elements via delta.
	 */
	public void setLocation(int x, int y) {
		this.x = x;
		this.y = y;
		int delta = 0;
		
		for (int row = 0; row < state.length; row++) {
			
			for (int col = 0; col < state[row].length; col++) {
				state[row][col].setLocation(x, y);
				x += state[row][col].getWidth();
				delta = state[row][col].getHeight();
			}
			x = this.x;
			y += delta;
		}
	}

	/** Return Iterator of strings representing each row. */
	public Iterator<String> rows() {
		LinkedList<String> out = new LinkedList<String>();
		for (int row = 0; row < state.length; row++) {
			StringBuilder s = new StringBuilder();
			for (int col = 0; col < state[row].length; col++) {
				s.append(state[row][col].getContents());
				if (col < state[row].length-1) { s.append(' '); }
			}
			out.add(s.toString());
		}
		
		return out.iterator();
	}
	
	/** 
	 * If the existing shape intersects any of our shapes, return true
	 * 
	 * @param other
	 */
	public boolean intersects(Shape other) {
		Iterator<WordShape> it = iterator();
		while (it.hasNext()) {
			Shape s = it.next();
			if (s.intersects(other)) {
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * Check whether two PoemShape objects are the same.
	 * 
	 * Define as having same set of {@link WordShape} objects.
	 */
	public boolean equals (Object o) {
		if (o == null) { return false; }
		if (o instanceof PoemShape) {
			PoemShape other = (PoemShape) o;
			
			// not same location? not the same
			if (other.x != x || other.y != y) { return false; }
			
			// must try each one
			Iterator<WordShape> us = iterator();
			Iterator<WordShape> others = other.iterator();
			
			while (us.hasNext() && others.hasNext()) {
				WordShape ours = us.next();
				WordShape theirs = others.next();
				if (!ours.equals(theirs)) { return false; }
			}
			
			// both iterators must be empty
			return us.hasNext() == others.hasNext();
		}
		
		return false; // can't compare different classes.
	}

	public ShapeDrawer getDrawer() {
		return drawer;
	}

	@Override
	public Shape copy() {
		WordShape[][] copyState = new WordShape[state.length][];
		for (int row = 0; row < state.length; row++) {
			copyState[row] = new WordShape[state[row].length];
			System.arraycopy(state[row], 0, copyState[row], 0, state[row].length);
		}
		return new PoemShape(x, y, new PoemDrawer(), copyState);
	}

	@Override
	public String getContents() {
		StringBuffer sb = new StringBuffer();
		for (int row = 0; row < state.length; row++) {
			for (int col = 0; col < state[row].length; col++) {
				sb.append(state[row][col]).append(' ');
			}
			sb.append("\n");
		}
		return sb.toString();
	}
	

	@Override
	public int getNumberRows() {
		return state.length;
	}
	
	/**
	 * Returns actual elements for performance reasons. If you want to modify, you must 
	 * copy.
	 */
	@Override
	public WordShape[][] getState() {
		return state;
	}

	@Override
	public Shape verticalConnect(Shape shape, int newx, int newy) {
		// find which shape is intersected by (newx, newy).
		int row = 0;
		int col = 0;
		out: 
		for (row = 0; row < state.length; row++) {
			for (col = 0; col < state[row].length; col++) {
				if (state[row][col].intersect(newx,  newy)) {
					break out;
				}
			}
		}
		
		// if col = 0 then add to head of that row; otherwise, add to end.
		PoemShape newOne = null;
		if (row == 0) {
			newOne = new PoemShape(shape.getX(), shape.getY(), new PoemDrawer(), shape.getState());
			WordShape[][] rows = getState();
			for (row = 0; row < rows.length; row++) {
				newOne.append(rows[row]);
			}
		} else {
			newOne = new PoemShape(getX(), getY(), new PoemDrawer(), getState());
			WordShape[][] rows = shape.getState();
			for (row = 0; row < rows.length; row++) {
				newOne.append(rows[row]);
			}
		}
		return newOne;
	}
	
	/**
	 * Only allow horizontal connection by shapes that have a single row. Then they can be added to the end 
	 * of the row to which they were attached. 
	 */
	@Override
	public Shape horizontalConnect(Shape shape, int newx, int newy) {
		if (shape.getNumberRows() != 1) {
			return null;
		}
		
		// find which shape is intersected by (newx, newy).
		int row = 0;
		int col = 0;
		out: 
		for (row = 0; row < state.length; row++) {
			for (col = 0; col < state[row].length; col++) {
				if (state[row][col].intersect(newx,  newy)) {
					break out;
				}
			}
		}
		
		// if col = 0 then add to head of that row; otherwise, add to end.
		PoemShape newOne = new PoemShape(getX(), getY(), new PoemDrawer(), getState());
		if (col == 0) {
			newOne.prepend(row, shape.getState()[0]);
		} else {
			newOne.extend(row, shape.getState()[0]);
		}
		return newOne;
	}
	
	/**
	 * Append row to be the new last row in the poem. Update x/y locations accordingly
	 * @param row
	 */
	public void append(WordShape[] row) {
		int baseX = getX();
		int baseY = getY();
		
		// left-align row
		WordShape[][] newState = new WordShape[state.length+1][];
		newState[state.length] = new WordShape[row.length];
		System.arraycopy(row, 0, newState[state.length], 0, row.length);
			
		// move over others.
		for (int i = 0; i < state.length; i++) {
			newState[i] = new WordShape[state[i].length];
			System.arraycopy(state[i], 0, newState[i], 0, state[i].length);
		}
		
		this.state = newState;
		
		// align everyone
		setLocation(baseX, baseY);
	}
	
	@Override
	public DisconnectState split(int x, int y) {
		for (int row = 0; row < state.length; row++) {
			for (int col = 0; col < state[row].length; col++) {
				if (state[row][col].intersect(x,  y)) {
					DisconnectState ds = new DisconnectState(this, row, col);

					int newx = state[row][col].x;
					int newy = state[row][col].y;
					
					// compute number of rows in new state. 
					int newRows = state.length - row;
					int newFirstCols = state[row].length - col;
					
					// deal with splitting row at proper point. NewState will form basis
					// for newly created state. updatedState will record original state's 
					// changes.
					WordShape newState[][] = new WordShape[newRows][];
					int updatedRows = row+1;
					if (col == 0) { updatedRows--; }   // if first WordSshape in row, then will be excised.
					WordShape updatedState[][] = new WordShape[updatedRows][];
					
					newState[0] = new WordShape[newFirstCols];
					System.arraycopy(state[row], col, newState[0], 0, newFirstCols);
					if (col != 0) {
						updatedState[row] = new WordShape[col];
						System.arraycopy(state[row], 0, updatedState[row], 0, col);
					}
					state[row] = null;
					
					// now do remaining ones. Copy old ones over into updated State, and 
					// the ones being removed into newState.
					for (int i = 0; i < row; i++) {
						updatedState[i] = state[i];
						state[i] = null;
					}
					for (int i = row+1; i < state.length; i++) {
						newState[i-row] = state[i];
						state[i] = null;
					}
					
					// we are now reduced by this much
					state = updatedState;
					
					// new shape takes rest
					PoemShape ps = new PoemShape(newx, newy, new PoemDrawer(), newState);
					ds.setNewShape(ps);
					return ds;
				}
			}
		}
		
		// nothing happening here...
		return null;
	}

	@Override
	public boolean reconnect(DisconnectState ds) {
		Shape newShape = ds.getNewShape();
		WordShape[][] words = newShape.getState();
		
		if (ds.originalOffset == 0) {
			// these are simply appended to end
			for (int row = 0; row < words.length; row++) {
				this.append(words[row]);
			}
		} else {
			// add to existing row first, then add rest
			WordShape[] existingRow = state[ds.originalRow];
			state[ds.originalRow] = new WordShape[existingRow.length + words[0].length];
			System.arraycopy(existingRow, 0, state[ds.originalRow], 0, existingRow.length);
			System.arraycopy(words[0], 0, state[ds.originalRow], existingRow.length, words[0].length);
			
			for (int row = 1; row < words.length; row++) {
				this.append(words[row]);
			}
		}
		
		return true;
	}
	
	/**
	 * Extend existing row in poem with words 
	 * 
	 * @param row
	 */
	public void extend(int rowIndex, WordShape[] row) {
		int baseX = getX();
		int baseY = getY();
		
		// must add row to our state
		WordShape[][] newState = state;
		if (rowIndex >= state.length) {
			newState = new WordShape[state.length+1][];
			System.arraycopy(state, 0, newState, 0, state.length);
			rowIndex = state.length;
			newState[rowIndex] = new WordShape[0];
		}
		
		WordShape[] old = newState[rowIndex];
		newState[rowIndex] = new WordShape[row.length + old.length];
		System.arraycopy(old, 0, newState[rowIndex], 0, old.length);
		System.arraycopy(row, 0, newState[rowIndex], old.length, row.length);
		
		this.state = newState;
		setLocation(baseX, baseY);
	}
	
	/**
	 * Prepend existing row in poem with words 
	 * 
	 * @param row
	 */
	public void prepend(int rowIndex, WordShape[] row) {
		int baseX = getX();
		int baseY = getY();
		
		// must add row to our state
		WordShape[][] newState = state;
		if (rowIndex >= state.length) {
			newState = new WordShape[state.length+1][];
			System.arraycopy(state, 0, newState, 0, state.length);
			rowIndex = state.length;
			newState[rowIndex] = new WordShape[0];
		}
		
		WordShape[] old = newState[rowIndex];
		newState[rowIndex] = new WordShape[row.length + old.length];
		System.arraycopy(row, 0, newState[rowIndex], 0, row.length);
		System.arraycopy(old, 0, newState[rowIndex], row.length, old.length);
		
		this.state = newState;
		setLocation(baseX, baseY);
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		for (Iterator<String> it = rows(); it.hasNext(); ) {
			sb.append(it.next()).append("\n");
		}
		
		return sb.toString();
	}


	/**
	 * Returns an iterator of all {@link WordShape} objects that form this poem. 
	 */
	public Iterator<WordShape> iterator() {
		return new DoubleArrayIterator<WordShape>(state);
	}	
}
