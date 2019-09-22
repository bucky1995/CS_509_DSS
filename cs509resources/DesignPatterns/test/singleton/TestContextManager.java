package singleton;

import junit.framework.TestCase;

public class TestContextManager extends TestCase {

	/** Pre-execution before tests. */
	public void setUp() {
		// clear singleton. ONLY USE WITHIN TEST FRAMEWORK
		ContextManager.inst = null;
	}
	
	/** 
	 * This test case checks that instance is configured properly
	 */
	public void testIdentity() {
		ContextManager cm = ContextManager.instance("heineman");
		
		ContextManager cm2 = ContextManager.instance();
		
		// same
		assertTrue (cm == cm2);
	}
	
	/**
	 * Future test cases would be challenged since singleton already exists
	 */
	public void testSomethingElse() {
		ContextManager cm = ContextManager.instance("heineman");
		assertEquals ("heineman", cm.userid);
	}
	
	/** 
	 * This test case checks that multiple instances cannot be created.
	 */
	public void testMultipleFailure() {
		ContextManager cm = ContextManager.instance("heineman");
		
		try {
			ContextManager cm2 = ContextManager.instance("other");
			fail ("Should have failed a multiple create attempt!");
		} catch (IllegalStateException e) {
			// proper
		}
	}
	
}
