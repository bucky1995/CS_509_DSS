import junit.framework.TestCase;

public class TestBowling extends TestCase {

	public void testBowl1() {
		Bowling b = new Bowling();
		b.roll(3);
		
		assertEquals(3, b.getScore());
		
		b.roll(5);
		assertEquals(8, b.getScore());
	}
	
	public void testBowl2() {
		Bowling b = new Bowling();
		b.roll(10);
		
		assertEquals(10, b.getScore());
		
		b.roll(3);
		assertEquals(16, b.getScore());
	}
	
	public void testBowl25() {
		Bowling b = new Bowling();
		b.roll(6);
		b.roll(4);
		assertEquals (10, b.getScore());
		b.roll(5);
		assertEquals (20, b.getScore());
	}
		
	
	public void testBowl3() {
		Bowling b = new Bowling();
		b.roll(1);	 assertEquals (1, b.getScore()); 	assertEquals ("1", b.scores());
		b.roll(4);   assertEquals (5, b.getScore());	assertEquals ("5", b.scores());
		b.roll(4);   assertEquals (9, b.getScore());	assertEquals ("5,9", b.scores());
		b.roll(5);   assertEquals (14, b.getScore());   assertEquals ("5,14", b.scores());
		b.roll(6);   assertEquals (20, b.getScore());   assertEquals ("5,14,20", b.scores());
		b.roll(4);   assertEquals (24, b.getScore());   assertEquals ("5,14,24/", b.scores());
		   b.roll(5); 
		   assertEquals (34, b.getScore());				assertEquals ("5,14,29/,34", b.scores());
		b.roll(5);   // CAUSE SPARE in FRAME-4
		assertEquals(39, b.getScore());					assertEquals ("5,14,29/,39/", b.scores());
		b.roll(10);  // STRIKE as bonus ball for Frame4 but also adds ten to FRAME-5
		                                                assertEquals ("5,14,29/,49/,59X", b.scores());
		assertEquals(59, b.getScore());
		   b.roll(0);  assertEquals (59, b.getScore()); assertEquals ("5,14,29/,49/,59X,59", b.scores());
		   b.roll(1);  assertEquals (61, b.getScore());  // advanced frame
		                                                assertEquals ("5,14,29/,49/,60X,61", b.scores());
		b.roll(7);    assertEquals (68, b.getScore());  assertEquals ("5,14,29/,49/,60X,61,68", b.scores());
		b.roll(3);    assertEquals (71, b.getScore());  assertEquals ("5,14,29/,49/,60X,61,71/", b.scores());
		b.roll(6);    assertEquals (83, b.getScore());  assertEquals ("5,14,29/,49/,60X,61,77/,83", b.scores());
		b.roll(4);    assertEquals (87, b.getScore());  assertEquals ("5,14,29/,49/,60X,61,77/,87/", b.scores());
		b.roll(10);   assertEquals (107, b.getScore());  assertEquals ("5,14,29/,49/,60X,61,77/,97/,107X", b.scores()); 
		b.roll(2);    assertEquals (111, b.getScore());  assertEquals ("5,14,29/,49/,60X,61,77/,97/,109X,111", b.scores());
		b.roll(8);    assertEquals (127, b.getScore());  assertEquals ("5,14,29/,49/,60X,61,77/,97/,117X,127/", b.scores());
		b.roll(6);    assertEquals (133, b.getScore());  assertEquals ("[5,14,29/,49/,60X,61,77/,97/,117X,133/]", b.scores());
	}
}
