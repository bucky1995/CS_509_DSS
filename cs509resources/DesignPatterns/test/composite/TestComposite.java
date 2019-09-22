package composite;

import junit.framework.TestCase;

public class TestComposite extends TestCase {
	public void testComposite() {
		Set s = new Set();

		s.add (new Number (1));
		s.add (new Number (2));
		s.add (new Number (9));

		Set s2 = new Set();
		s2.add (new Number (18));
		s2.add (new Number (22));
		s2.add (new Number (25));
		s.add (s2);

		// note that this subset is not added to the set because it is already there.
		s2 = new Set();
		s2.add (new Number (25));
		s2.add (new Number (22));
		s2.add (new Number (18));
		s.add (s2);

		// does anyone protect against recursive additions?
		//    s.add (s); ???
		// what about
		//    s1 = set
		//    s2 = set
		//    s3 = set
		//    s3.add (s1);
		//    s2.add (s3);
		//    s1.add (s2);
		// ???
		
		s2 = new Set();
		s2.add (new Number (189));
		s2.add (new Number (44));
		s2.add (new Number (55));
		s.add (s2);

		s.add (new Number (99));
		System.err.println ("Set is:" + s.toString());
		
	}
}
