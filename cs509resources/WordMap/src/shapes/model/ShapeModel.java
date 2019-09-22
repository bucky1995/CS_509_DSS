package shapes.model;

import java.util.Comparator;
import javax.swing.table.AbstractTableModel;

/**
 * Instead of making Board responsible for handling the JTable stuff, delegate
 * those responsibilities to this class.
 * 
 * @author heineman
 */
public class ShapeModel extends AbstractTableModel {

	/** Keep Eclipse Happy. */
	private static final long serialVersionUID = 1L;
	
	/** Board maintains the state. */
	Board board;
	
	/** Current sortingMethod used. Default to word. */
	Comparator<Shape> sortingMethod = new Comparator<Shape>() {

		@Override
		public int compare(Shape s1, Shape s2) {
			return (s1.getContents().compareTo(s2.getContents()));
		}
	};
	
	/** Key values. */
	public static final String wordLabel = "Shape";
	public static final String xLabel = "X";
	public static final String yLabel = "Y";
	
	/** The Table model needs to know the board which contains the shapes. */
	public ShapeModel (Board b) {
		this.board = b;
	}
	
	@Override
	public int getColumnCount() {
		return 3;
	}

	@Override
	public int getRowCount() {
		return board.size();
	}

	
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Shape s = board.get(rowIndex);
		
		if (columnIndex == 0) { 
			return s.getContents();
		} else if (columnIndex == 1) { 
			return s.getX();
		} else if (columnIndex == 2) {// table number
			return s.getY();
		}
		
		// no idea who you are...
		return "";
	}

	/**
	 * Sort the ArrayList of cars by given field, whose value is
	 * either {@link UserModelGUI#uidStr}, {@link UserModelGUI#rNameStr},
	 * {@link UserModelGUI#tableNumStr}, or {@link UserModelGUI#timeStr}.
	 * 
	 * @param columnIndex
	 */
	public void determineSortingMethod(final String fieldName) {
		sortingMethod = new Comparator<Shape>() {

			@Override
			public int compare(Shape s1, Shape s2) {
				if (fieldName.equals(xLabel)) {
					return s1.getX() - s2.getX();
				} else if (fieldName.equals(yLabel)) {
					return s1.getY() - s2.getY();
				} 
				
				// default to word
				return (s1.getContents().compareTo(s2.getContents()));
			}
		};
	}	
	
	public void sort() {
		board.sort(sortingMethod);
	}
}
