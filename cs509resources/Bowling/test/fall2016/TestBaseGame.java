package fall2016;

import java.util.Iterator;

import bowling.IBowling;
import bowling.IFrame;
import junit.framework.TestCase;

public class TestBaseGame extends TestCase {
	IBowling myGame;
	
	protected void setUp () {
		myGame = new BaseGame();
	}
	
	public void testOne () {
		assertEquals (0, myGame.score());
		
		myGame.roll(6);
		assertEquals (6, myGame.score());
	}
	
	public void testStrike () {
		myGame.roll(10);
		assertEquals (10, myGame.score());
		
		Iterator<IFrame> frames = myGame.iterator();
		IFrame frame = frames.next();
		
		assertTrue (frame.isStrike());
		assertFalse (frame.isSpare());
	}
	
	public void testSpare () {
		myGame.roll(7);
		assertEquals (7, myGame.score());
		
		Iterator<IFrame> frames = myGame.iterator();
		IFrame frame = frames.next();
		
		assertFalse (frame.isStrike());
		assertFalse (frame.isSpare());
		myGame.roll(3);
		
		frames = myGame.iterator();
		frame = frames.next();
		
		assertTrue (frame.isSpare());
	}
	
	public void testRolls2 () {
		myGame.roll(10);
		myGame.roll(2);
		myGame.roll(6);
		assertEquals  (26, myGame.score());
	}
	
	public void testRolls1 () {
		myGame.roll(7);
		myGame.roll(2);
		
		myGame.roll(7);
		myGame.roll(2);
		
		myGame.roll(7);
		myGame.roll(2);
		assertEquals  (27, myGame.score());
	}
	
	public void testRolls3 () {
		myGame.roll(1);
		myGame.roll(4);
		
		myGame.roll(4);
		myGame.roll(5);
		
		myGame.roll(6);
		myGame.roll(4);
		myGame.roll(5);
		myGame.roll(5);
		
		myGame.roll(10);
		myGame.roll(0);
		myGame.roll(1);
		
		myGame.roll(7);
		myGame.roll(3);
		myGame.roll(6);
		
		myGame.roll(4);
		myGame.roll(10);
		myGame.roll(2);
		
		myGame.roll(8);
		myGame.roll(6);
		
		assertEquals  (133, myGame.score());
	}
	
	public void testRollsForeign () {
		// 20   36    43    73    95    115  134    143   163    193
		myGame.roll(10);
		myGame.roll(3);
		myGame.roll(7);
		myGame.roll(6);
		myGame.roll(1);
		assertEquals  (43, myGame.score());  // CLEAN FRAME
		myGame.roll(10);
		myGame.roll(10);
		myGame.roll(10);
		myGame.roll(2);
		myGame.roll(8);
		myGame.roll(9);
		myGame.roll(0);
		assertEquals  (143, myGame.score());  // CLEAN FRAME
		myGame.roll(7);
		myGame.roll(3);
		myGame.roll(10);
		myGame.roll(10);
		myGame.roll(10);

		assertEquals  (193, myGame.score());
	}
}
