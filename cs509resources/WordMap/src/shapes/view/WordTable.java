package shapes.view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.*;

import shapes.controller.*;
import shapes.model.*;

/**
 * Sample tutorial for JTables.
 * 
 * To properly refresh JTable when model changes, be sure that controller
 * calls {@link #refreshTable()}.
 */
public class WordTable extends JPanel {
	/* keep eclipse happy */
	private static final long serialVersionUID = 1L;
	
	/** The model that "backs" the JTable. Different from Board. */
	ShapeModel shapeModel = null;
	
	/** Actual GUI entity. */
	JTable jtable = null;
	
	/** Model containing all data. */
	Model model;
	
	/** Enable access to ShapeModel. */
	public ShapeModel getShapeModel () {
		return shapeModel;
	}
	
	public JTable getShapesJTable() {
		return jtable;
	}
	
	/**
	 * This constructor creates the display which manages the list of active players.
	 */
    public WordTable(final Model model, final ApplicationPanel panel) {
    	this.model = model;
    	Board board = model.getBoard();
    	
    	// create the model for the data which backs this table
    	shapeModel = new ShapeModel(board);
    	
    	// add to listener chain
    	board.addListener(new RefreshWordTableController(this));
		
		// Scrollable panel will enclose the JTable and support scrolling vertically
		JScrollPane jsp = new JScrollPane();
		jsp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		jsp.setPreferredSize(new Dimension(210, 600));
		
		// Just add the JTable to the set. First create the list of Players,
		// then the SwingModel that supports the JTable which you now create.
		jtable = new JTable(shapeModel);
		jtable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		// let's tell the JTable about its columns.
		TableColumnModel columnModel = new DefaultTableColumnModel();

		// must build one by one...
		String[] headers = new String[] { ShapeModel.wordLabel, ShapeModel.xLabel, ShapeModel.yLabel};
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
				JTableHeader h = (JTableHeader) e.getSource() ;
				int columnIndex = h.columnAtPoint(e.getPoint());
				new SortController(WordTable.this).process(h, columnIndex);
			}
		});
		
		jtable.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
	        public void valueChanged(ListSelectionEvent event) {
	        	int rowIndex = jtable.getSelectedRow();
	        	int actualIndex = jtable.convertRowIndexToModel(rowIndex);
	            new SelectItemController(model, panel).process(actualIndex, event.getValueIsAdjusting());
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
