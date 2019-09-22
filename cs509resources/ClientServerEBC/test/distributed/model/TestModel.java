package distributed.model;

import junit.framework.TestCase;

public class TestModel extends TestCase {

	public void testSingleton() {
		Model m = Model.getInstance();
		Model m2 = Model.getInstance();
		
		assertTrue (m == m2);
	}
	
	public void testModel() {
		Model m = new Model();
		Value c = m.getColor();
		Value h = m.getHeight();
		Value w = m.getWidth();
		
		assertTrue (c != null);
		assertTrue (h != null);
		assertTrue (w != null);
			
		// all distinct
		assertTrue (c != h && h != w && c != w);
	}
	
	// locking
	public void testLocking() {
		Model m = new Model();
		assertFalse (m.isLocked());
		m.setLocked(true);
		assertTrue(m.isLocked());
		m.setLocked(false);
		assertFalse (m.isLocked());
	}
}
