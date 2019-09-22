package raw;

import java.util.*;

import javax.swing.table.AbstractTableModel;


/**
 * Supports listeners for changes.
 * 
 * @author heineman
 */
public class Board extends AbstractTableModel {
	/**
	 * Keep Eclipse Happy
	 */
	private static final long serialVersionUID = 5470292972823744466L;

	/** Shapes being maintained. */
	ArrayList<PoemShape> shapes = new ArrayList<PoemShape>();
	
	// selected
	PoemShape selectedShape;
	
	// target (found when moving objects)
	PoemShape targetShape;
	
	/** Current sortingMethod used. Default to word. */
	Comparator<PoemShape> sortingMethod = new Comparator<PoemShape>() {

		@Override
		public int compare(PoemShape s1, PoemShape s2) {
			try {
				String w1 = s1.state[0][0];
				String w2 = s2.state[0][0];
				return w1.compareTo(w2);
			} catch (Exception e) {
				e.printStackTrace();
				return 0;
			}
		}
	};
	
	/** Add shape to board. */
	public void add(PoemShape s) {
		if (s == null) {
			throw new NullPointerException("Cannot add null shape to board.");
		}
		shapes.add(s);
	}
	
	/** Remove shape from board. */
	public void remove(PoemShape s) {
		shapes.remove(s);
	}
	
	/**
	 * Return shape that intersects (x,y) point.
	 * 
	 * Allow to run through ALL shapes because we actually want the LAST one that intersects; this is because
	 * drawing will be done in this same order.
	 */
	public PoemShape findShape(int x, int y) {
		PoemShape found = null;
		for (PoemShape s : shapes) {
			if (s.intersect(x,y)) {
				found = s;
			}
		}
		
		return found;
	}
	
	/** Return all shapes in the board. */
	public Iterator<PoemShape> iterator() {
		return shapes.iterator();
	}

	/** Return the number of words on the board. */
	public int size() { 
		return shapes.size();
	}

	/** Return the given shape by index position. */
	public PoemShape get(int rowIndex) {
		return shapes.get(rowIndex);
	}

	/** Sort shapes using given comparator. */
	public void sort() {
		Collections.sort(shapes, sortingMethod);
	}
	
	/**
	 * Return the nth shape by sorted order (or null if too high).
	 * 
	 * @param selectedIndex
	 * @return
	 */
	public PoemShape getShape(int selectedIndex) {
		if (0 < selectedIndex && selectedIndex < shapes.size()) {
			return shapes.get(selectedIndex);
		}
		
		return null;
	}

	@Override
	public int getRowCount() {
		return shapes.size();
	}

	@Override
	public int getColumnCount() {
		return 3;
	}

	@Override
	public Object getValueAt(int index, int columnIndex) {
		PoemShape s = shapes.get(index);
		if (columnIndex == 0) {
			return s.toString();
		} else if (columnIndex == 1) {
			return new Integer(s.getX());
		} else {
			return new Integer(s.getY());
		}
	}

	public void setSelectedShape(PoemShape s) {
		selectedShape = s;
	}

	public PoemShape getSelectedShape() {
		return selectedShape;
	}

	public PoemShape getTargetShape() {
		return targetShape;
	}
	
	public void setTargetShape(PoemShape found) {
		targetShape = found;
	}
	
	/**
	 * Sort the ArrayList of cars by given field, whose value is
	 * either {@link UserModelGUI#uidStr}, {@link UserModelGUI#rNameStr},
	 * {@link UserModelGUI#tableNumStr}, or {@link UserModelGUI#timeStr}.
	 * 
	 * @param columnIndex
	 */
	public void determineSortingMethod(final String fieldName) {
		sortingMethod = new Comparator<PoemShape>() {

			@Override
			public int compare(PoemShape s1, PoemShape s2) {
				if (fieldName.equals("X")) {
					return s1.getX() - s2.getX();
				} else if (fieldName.equals("Y")) {
					return s1.getY() - s2.getY();
				} 
				
				// default to word
				return (s1.getContents().compareTo(s2.getContents()));
			}
		};
	}	

	
}
