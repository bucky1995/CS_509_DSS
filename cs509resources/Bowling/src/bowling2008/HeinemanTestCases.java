package bowling2008;

import junit.framework.TestCase;

public class HeinemanTestCases extends TestCase {
	
	public void testDoubleStrike () {

//	Frame 1, 10 pins (strike)
//	Frame 2, 10 pins (strike)
//	Frame 3, 4 pins
//	Frame 3, 2 pins
//
//	Score:
//
//	Frame one: 10 + (10 + 4) = 24
//	Frame two: 10 + (4 + 2)  = 16
//	Frame three: 4 + 2       =  6
//	Total score:               46
	
		Game g = new Game();
		g.addThrow (10);
		assertEquals (10, g.score());
		g.addThrow (10);
		assertEquals (30, g.score());  // at least 20 for frame1 and 10 for frame2 
		g.addThrow (4);
		assertEquals (42, g.score());  // at least 24 for frame1, 14 for frame2, and 4 for frame3 
		g.addThrow (2);
		
		assertEquals (46, g.score());  // so this is where the failure occurs.
	}
	
	public void testGameScore() {
		Game g = new Game();
		
		g.addThrow(1);
		g.addThrow(4);
		g.addThrow(4);
		g.addThrow(5);
		g.addThrow(6);
		g.addThrow(4);
		g.addThrow(5);
		g.addThrow(5);
		g.addThrow(10);
		g.addThrow(0);
		g.addThrow(1);
		g.addThrow(7);
		g.addThrow(3);
		g.addThrow(6);
		g.addThrow(4);
		g.addThrow(10);
		g.addThrow(2);
		g.addThrow(8);
		g.addThrow(6);
		
		assertEquals (133, g.score());
	}
}
