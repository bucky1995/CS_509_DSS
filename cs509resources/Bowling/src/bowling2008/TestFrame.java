package bowling2008;

import junit.framework.TestCase;

public class TestFrame extends TestCase {

	public void testStrikeFrame() {
		Frame f = new Frame();
		assertFalse (f.isDone());
		f.addPins(10);
		assertFalse (f.isDone());
		assertTrue (f.isStrike());
		assertTrue (f.isBonus());
		
		f.addPins(2);
		assertFalse (f.isDone());
		f.addPins(4);
		assertTrue(f.isDone());
	
		assertEquals (16, f.score());
	}
	
	
	
}
