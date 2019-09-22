package shapes.controller;

import java.awt.Point;
import javax.swing.table.JTableHeader;
import shapes.model.*;
import shapes.view.*;

/**
 * Controller that handles user actions over the JTable.
 */
public class SortController {
	/** View being controlled. */
	WordTable table;
	
	/** Controller needs to know of the JTable view. */
	public SortController(WordTable table) {
		this.table = table; 
	}

	/**
	 * Implements the handler for clicking on a table column in the display.
	 * 
	 * Updates the sorting method in the model, and forces a resort of its elements.
	 * 
	 * @param point 
	 * @param h 
	 */
	public void process (JTableHeader h, int columnIndex) {
		//int columnIndex = h.columnAtPoint(point);
		
		// if one is selected, get the field that identifies that column
		if (columnIndex != -1) {
			String fieldName = h.getColumnModel().getColumn(columnIndex).getHeaderValue().toString();
			ShapeModel model = (ShapeModel) h.getTable().getModel();
			model.determineSortingMethod(fieldName);
			model.sort();
		}
	}
}
