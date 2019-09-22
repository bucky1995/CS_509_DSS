package hangman.model;

import junit.framework.TestCase;

public class TestFails extends TestCase {
	
	// showing feature in JUnit
	public void testFailExample() {
		try {
			Model m = new Model (null);
			fail ("Shouldn't be able to construct Model from null.");
		} catch (RuntimeException e) {
			// success
		}
			
	}
}
