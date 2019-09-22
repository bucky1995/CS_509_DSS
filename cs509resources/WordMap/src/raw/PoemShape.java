package raw;

import java.awt.Rectangle;

/**
 * Collection of rows, each containing WordShape
 * 
 */
public class PoemShape {
	int x;
	int y;

	int charWidth = 9;
	int charHeight = 23;

	final int hSpacing = 4;
	final int vSpacing = 2;
	
	/** State is a 2d-array of strings, one per row. */
	String[][] state;

	public PoemShape (int x, int y) {
		state  = new String[0][0];
	}

	public PoemShape(int x, int y, String[][] state) {
		this.x = x;
		this.y = y;
		this.state = new String[state.length][];
		for (int row = 0; row < state.length; row++) {
			this.state[row] = new String[state[row].length];
			System.arraycopy(state[row], 0, this.state[row], 0, state[row].length);
		}
	}

	public boolean intersect(int px, int py) {
		int posX = x;
		int posY = y;
		for (int i = 0; i < state.length; i++) {
			for (int j = 0; j < state[i].length; j++) {
				Rectangle r = new Rectangle (posX, posY, charWidth*state[i][j].length(), charHeight);
				if (r.contains(px, py)) {
					return true;
				}
				posX += 2*hSpacing +state[i][j].length()*charWidth;
			}
			posY += charHeight;
			posX = x;
		}

		return false;
	}

	public int getX() { return x; }
	public int getY() { return y; }
	public int getWidth() {
		int maxW = 0;
		for (int i = 0; i < state.length; i++) {
			int w = 0;
			for (int j = 0; j < state[i].length; j++) {
				w += state[i][j].length();
			}
			if (w > maxW) {
				maxW = w;
			}
		}
		return maxW * charWidth;

	}
	public int getHeight() { return charHeight * state.length; }

	/**
	 * Must pass along to all elements via delta.
	 */
	public void setLocation(int x, int y) {
		this.x = x;
		this.y = y;
	}


	/** 
	 * If the existing shape intersects any of our shapes, return true
	 * 
	 * @param other
	 */
	public boolean intersects(PoemShape other) {
		int posX = x;
		int posY = y;

		for (int r = 0; r < state.length; r++) {
			for (int c = 0; c < state[r].length; c++) {
				Rectangle me = new Rectangle (posX, posY, charWidth*state[r][c].length(), charHeight);

				int oposX = x;
				int oposY = y;
				for (int i = 0; i < other.state.length; i++) {
					for (int j = 0; j < other.state[i].length; j++) {
						Rectangle them = new Rectangle (oposX, oposY, charWidth*other.state[i][j].length(), charHeight);

						if (me.intersects(them)) {
							return true;
						}

						posX += 2*hSpacing +state[i][j].length()*charWidth;
					}
					posY += charHeight;
					posX = x;
				}


				posX += 2*hSpacing +state[r][c].length()*charWidth;
			}

			posX = 0;
			posY += charHeight;
		}

		return false;
	}

	// need to check whether two objects are identical. Java interface
	public boolean equals (Object o) {
		if (o == null) { return false; }
		if (o instanceof PoemShape) {
			PoemShape other = (PoemShape) o;
			return other.x == x && other.y == y;
		}

		return false; // can't compare different classes.
	}

	public PoemShape copy() {
		String[][] copyState = new String[state.length][];
		for (int row = 0; row < state.length; row++) {
			copyState[row] = new String[state[row].length];
			System.arraycopy(state[row], 0, copyState[row], 0, state[row].length);
		}
		return new PoemShape(x, y, copyState);
	}

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


	public int getNumberRows() {
		return state.length;
	}


	public PoemShape verticalConnect(PoemShape shape, int newx, int newy) {
		// find which shape is intersected by (newx, newy).
		// find which shape is intersected by (newx, newy).
		int posX = x;
		int posY = y;
		int row = 0;
		int col = 0;
		out: 
			for (row = 0; row < state.length; row++) {
				for (col = 0; col < state[row].length; col++) {
					Rectangle r = new Rectangle (posX, posY, charWidth*state[row][col].length(), charHeight);
					if (r.contains(newx, newy)) {
						break out;
					}
					posX += 2*hSpacing +state[row][col].length()*charWidth;
				}
				posY += charHeight;
				posX = x;
			}

		// if col = 0 then add to head of that row; otherwise, add to end.
		PoemShape newOne = null;
		if (row == 0) {
			newOne = new PoemShape(shape.getX(), shape.getY(), shape.state);
			for (row = 0; row < state.length; row++) {
				newOne.append(state[row]);
			}
		} else {
			newOne = new PoemShape(getX(), getY(), state);
			String[][] rows = shape.state;
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
	public PoemShape horizontalConnect(PoemShape shape, int newx, int newy) {
		if (shape.getNumberRows() != 1) {
			return null;
		}

		int posX = x;
		int posY = y;

		// find which shape is intersected by (newx, newy).
		int row = 0;
		int col = 0;
		out: 
			for (row = 0; row < state.length; row++) {
				for (col = 0; col < state[row].length; col++) {
					Rectangle r = new Rectangle (posX, posY, charWidth*state[row][col].length(), charHeight);
					if (r.contains(newx, newy)) {
						break out;
					}
					posX += 2*hSpacing +state[row][col].length()*charWidth;
				}
				posY += charHeight;
				posX = x;
			}

		// if col = 0 then add to head of that row; otherwise, add to end.
		PoemShape newOne = new PoemShape(getX(), getY(), state);
		if (col == 0) {
			newOne.prepend(row, shape.state[0]);
		} else {
			newOne.extend(row, shape.state[0]);
		}
		return newOne;
	}

	/**
	 * Append row to be the new last row in the poem. Update x/y locations accordingly
	 * @param row
	 */
	public void append(String[] row) {
		int baseX = getX();
		int baseY = getY();

		// left-align row
		String[][] newState = new String[state.length+1][];
		newState[state.length] = new String[row.length];
		System.arraycopy(row, 0, newState[state.length], 0, row.length);

		// move over others.
		for (int i = 0; i < state.length; i++) {
			newState[i] = new String[state[i].length];
			System.arraycopy(state[i], 0, newState[i], 0, state[i].length);
		}

		this.state = newState;

		// align everyone
		setLocation(baseX, baseY);
	}

	public PoemShape split(int sx, int sy) {
		int posX = x;
		int posY = y;
		for (int row = 0; row < state.length; row++) {
			for (int col = 0; col < state[row].length; col++) {
				Rectangle r = new Rectangle (posX, posY, charWidth*state[row][col].length(), charHeight);
				if (r.contains(sx, sy)) {

					// compute number of rows in new state. 
					int newRows = state.length - row;
					int newFirstCols = state[row].length - col;

					// deal with splitting row at proper point. NewState will form basis
					// for newly created state. updatedState will record original state's 
					// changes.
					String newState[][] = new String[newRows][];
					int updatedRows = row+1;
					if (col == 0) { updatedRows--; }   // if first WordSshape in row, then will be excised.
					String updatedState[][] = new String[updatedRows][];

					newState[0] = new String[newFirstCols];
					System.arraycopy(state[row], col, newState[0], 0, newFirstCols);
					if (col != 0) {
						updatedState[row] = new String[col];
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
					PoemShape ps = new PoemShape(posX, posY,  newState);
					return ps;
				}
				posX += 2*hSpacing +state[row][col].length()*charWidth;
			}
			posY += charHeight;
			posX = x;
		}

		// nothing happening here...
		return null;
	}

	/**
	 * Extend existing row in poem with words 
	 * 
	 * @param row
	 */
	public void extend(int rowIndex, String[] row) {
		int baseX = getX();
		int baseY = getY();

		// must add row to our state
		String[][] newState = state;
		if (rowIndex >= state.length) {
			newState = new String[state.length+1][];
			System.arraycopy(state, 0, newState, 0, state.length);
			rowIndex = state.length;
			newState[rowIndex] = new String[0];
		}

		String[] old = newState[rowIndex];
		newState[rowIndex] = new String[row.length + old.length];
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
	public void prepend(int rowIndex, String[] row) {
		int baseX = getX();
		int baseY = getY();

		// must add row to our state
		String[][] newState = state;
		if (rowIndex >= state.length) {
			newState = new String[state.length+1][];
			System.arraycopy(state, 0, newState, 0, state.length);
			rowIndex = state.length;
			newState[rowIndex] = new String[0];
		}

		String[] old = newState[rowIndex];
		newState[rowIndex] = new String[row.length + old.length];
		System.arraycopy(row, 0, newState[rowIndex], 0, row.length);
		System.arraycopy(old, 0, newState[rowIndex], row.length, old.length);

		this.state = newState;
		setLocation(baseX, baseY);
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < state.length; i++) {
			for (int j = 0; j < state[i].length; j++) {
				sb.append(state[i][j]);
			}
			sb.append("\n");
		}

		return sb.toString();
	}

}
