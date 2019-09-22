package shapes.model;
import shapes.view.*;
import junit.framework.TestCase;

public class TestMemento extends TestCase {

	public void testConstruction() {
		Board b = new Board();
		
		Shape s = new WordShape(0,0,new WordDrawer(),"sample");
		b.add(s);
		
		// create memento and restore b2 to that state
		BoardMemento m = b.getState();
		Board b2 = new Board();
		b2.restore(m);
		
		// both b and b2 should have just single shape 
		assertEquals(b.shapes.get(0), b2.shapes.get(0));
	}
	
	public void testRestoration() {
		Board b = new Board();
		
		Shape s = new WordShape(0,0,new WordDrawer(),"sample");
		b.add(s);
		BoardMemento m = b.getState();
		b.remove(s);
		b.restore(m);
		
		// same shape back
		assertEquals(b.shapes.get(0), s);
	}
}
