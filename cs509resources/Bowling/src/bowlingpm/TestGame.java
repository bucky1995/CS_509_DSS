package bowlingpm;
import static org.junit.Assert.*;

import org.junit.Test;

import bowlingpm.Game;

public class TestGame {

	@Test
	public void test() {
		Game g = new Game();
		assertEquals (0, g.score());
	}

	@Test
	public void testSample() {
		Game g = new Game();
		assertEquals (0, g.score());
		g.roll(1);
		assertEquals (1, g.score());
		g.roll(4);
		assertEquals (5, g.score());
		g.roll(4);
		assertEquals (9, g.score());
		g.roll(5);
		assertEquals (14, g.score());
		g.roll(6);
		assertEquals (20, g.score());
		g.roll(4);  //spare
		assertEquals (24, g.score());
		g.roll(5);
		assertEquals (34, g.score());
		g.roll(5);
		assertEquals (39, g.score());
		g.roll(10);
		assertEquals (59, g.score());
		g.roll(0);
		g.roll(1);
		assertEquals (61, g.score());
		g.roll(7);
		g.roll(3);
		g.roll(6);
		g.roll(4);
		g.roll(10);
		assertEquals (117, g.score());
		
	}
}
