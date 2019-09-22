package bowlingpm;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestFrame {

	@Test
	public void test() {
		Frame f = new Frame(null);
		assertEquals (0, f.score());
	}
	
	@Test
	public void testRoll() {
		Frame f = new Frame(null);
		f.roll(6);
		assertEquals (6, f.score());
	}
	
	@Test
	public void testRollTwice() {
		Frame f = new Frame(null);
		f.roll(6);
		assertEquals (6, f.score());
		f.roll(3);
		assertEquals (9, f.score());
	}
}
