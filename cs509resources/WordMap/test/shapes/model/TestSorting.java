package shapes.model;

import javax.swing.JTable;

import shapes.controller.SortController;
import shapes.view.Application;
import shapes.view.WordDrawer;
import shapes.view.WordTable;
import junit.framework.TestCase;

public class TestSorting extends TestCase {
	
	Application app;
	Model model;
	Shape shape1, shape2, shape3;
	
	protected void setUp() {
		Board b = new Board();
		shape1 = new WordShape (10, 50, new WordDrawer(), "sample");
		shape2 = new WordShape (30, 30, new WordDrawer(), "other");
		shape3 = new WordShape (70, 80, new WordDrawer(), "ball");
		b.add(shape1);
		b.add(shape2);
		b.add(shape3);
		model = new Model(b);
		app = new Application(model);

		// launch everything and go!
		app.setVisible (true);
	}
	
	protected void tearDown() {
		if (app != null) {
			app.dispose();
		}
	}
	
	public void testSort() {
		WordTable table = app.getWordTable();
		SortController sc = new SortController(table);
		JTable jtab = table.getShapesJTable();
		
		// sort by x
		sc.process(jtab.getTableHeader(), 1);
		
		Shape[] order1 = new Shape[3];
		int idx = 0;
		for (Shape s : model.getBoard()) {
			order1[idx++] = s;
		}
		assertEquals (shape1, order1[0]);
		assertEquals (shape2, order1[1]);
		assertEquals (shape3, order1[2]);
		
		idx = 0;
		
		// sort by y
		sc.process(jtab.getTableHeader(), 2);
		Shape[] order2 = new Shape[3];
		for (Shape s : model.getBoard()) {
			order2[idx++] = s;
		}
		idx = 0;
		assertEquals (shape2, order2[0]);
		assertEquals (shape1, order2[1]);
		assertEquals (shape3, order2[2]);
		
		// sort by word
		sc.process(jtab.getTableHeader(), 0);
		Shape[] order3 = new Shape[3];
		for (Shape s : model.getBoard()) {
			order3[idx++] = s;
		}
		assertEquals (shape3, order3[0]);
		assertEquals (shape2, order3[1]);
		assertEquals (shape1, order3[2]);

		
	}
	

}
