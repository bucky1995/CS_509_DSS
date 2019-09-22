package oval.model;

import java.awt.Point;

import junit.framework.TestCase;

public class TestModel extends TestCase {
	public void testStart() {
		Model m = new Model();
		
		assertTrue (m.getActive() == null);
	}
	
	public void testOperate() {
		Model m = new Model();
		
		Point p = new Point (30, 60);
		Oval o = new Oval(p, 10, 20);
		
		m.addOval(o);
		assertEquals (1, m.getOvals().size());
	}
	
	public void testActive() {
		Model m = new Model();
		
		Point p = new Point (30, 60);
		Oval o = new Oval(p, 10, 20);
		
		m.setActiveOval(o);
		assertEquals (o, m.getActive());
	}
}
