package raw;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.*;

/**
 * Sample tutorial for JTables.
 * 
 * To properly refresh JTable when model changes, be sure that controller
 * calls {@link #refreshTable()}.
 */
public class WordTable extends JPanel {
	/* keep eclipse happy */
	private static final long serialVersionUID = 1L;

	/** Actual GUI entity. */
	JTable jtable = null;
	
	/**
	 * This constructor creates the display which manages the list of active players.
	 */
    public WordTable(final Board board, final BoardPanel panel) {
    	
    	// Scrollable panel will enclose the JTable and support scrolling vertically
		JScrollPane jsp = new JScrollPane();
		jsp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		jsp.setPreferredSize(new Dimension(210, 600));
		
		// Just add the JTable to the set. First create the list of Players,
		// then the SwingModel that supports the JTable which you now create.
		jtable = new JTable(board);
		jtable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		// let's tell the JTable about its columns.
		TableColumnModel columnModel = new DefaultTableColumnModel();

		// must build one by one...
		String[] headers = new String[] { "Word", "X", "Y" };
		int index = 0;
		for (String h : headers) {
			TableColumn col = new TableColumn(index++);
			col.setHeaderValue(h);
			columnModel.addColumn(col);
		}		
		jtable.setColumnModel(columnModel);
		
		// let's install a sorter and also make sure no one can rearrange columns
		JTableHeader header = jtable.getTableHeader();
		
		// purpose of this sorter is to sort by columns.
		header.addMouseListener(new MouseAdapter()	{
			public void mouseClicked (MouseEvent e)	{
				JTableHeader h = (JTableHeader) e.getSource();
				int columnIndex = h.columnAtPoint(e.getPoint());
				String fieldName = h.getColumnModel().getColumn(columnIndex).getHeaderValue().toString();
				
				board.determineSortingMethod(fieldName);
				board.sort();
				refreshTable();
			}
		});
		
		jtable.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
	        public void valueChanged(ListSelectionEvent event) {
	        	int rowIndex = jtable.getSelectedRow();
	        	int actualIndex = jtable.convertRowIndexToModel(rowIndex);
	        	if (event.getValueIsAdjusting()) {
	        		board.setSelectedShape(board.getShape(actualIndex));
	        	} else {
	        		board.setSelectedShape(null);
	        	}
	        	panel.repaint();
	        }
	    });
		
		// Here's the trick. Make the JScrollPane take its view from the JTable.
		jsp.setViewportView(jtable);

		// add the scrolling Pane which encapsulates the JTable
		this.add(jsp);
	}

    /**
     * Causes the display of the shapes to be updated to the latest data.
     */
    public void refreshTable() {
		// THIS is the key method to ensure JTable view is synchronized
		jtable.revalidate();
		jtable.repaint();
		this.revalidate();
		this.repaint();
	}
}
