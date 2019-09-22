package visitor;

import java.util.*;
/**
 * Test functionality of classes.
 * <p>
 * Incorporates the visitor design pattern.
 * <p>
 * Creation date: (1/21/2003 7:55:11 PM)
 * @author: George T. Heineman (heineman@cs.wpi,edu)
 */
public class Driver {
	/**
	 * Driver constructor comment.
	 */
	public Driver() {
		super();
	}
	/**
	 * Return sample set S=s = {1, 3, 9, {17, 21, 25}, {189, 44, 55}, 99}.
	 * <p>
	 * @param args java.lang.String[]
	 */
	public static Set createS () {
		Set s = new Set();

		// s = {1, 3, 9, {17, 21, 25}, {189, 44, 55}, 99}
		s.add (new Number (1));
		s.add (new Number (3));
		s.add (new Number (9));

		Set s2 = new Set();
		s2.add (new Number (17));
		s2.add (new Number (21));
		s2.add (new Number (25));
		s.add (s2);

		// note that this subset is not added to the set because it is already there.
		s2 = new Set();
		s2.add (new Number (25));
		s2.add (new Number (21));
		s2.add (new Number (17));
		s.add (s2);

		// does anyone protect against recursive additions?
		//    s.add (s); ???

		s2 = new Set();
		s2.add (new Number (189));
		s2.add (new Number (44));
		s2.add (new Number (55));
		s.add (s2);

		s.add (new Number (99));

		return s;
	}
	/**
	 * Return sample set t = {-1, 5, 3, 0, 4, 2, 1}
	 * <p>
	 * @param args java.lang.String[]
	 */
	public static Set createT () {
		Set t = new Set();
		t.add (new Number (-1));
		t.add (new Number (5));
		t.add (new Number (3));
		t.add (new Number (0));
		t.add (new Number (4));
		t.add (new Number (2));
		t.add (new Number (1));

		return t;
	}
	/**
	 * Determine (using iterator) how to validate whether a set is within a set.
	 * <p>
	 * @param args java.lang.String[]
	 */
	public static boolean isSetWithinSet (Set s) {
		// count the elements from the Iterator and ensure they are all of instance Set.
		int count = 0;
		boolean setWithinSet = true; // assume best
		for (Iterator it = s.iterator(); it.hasNext(); ) {
			count++;
			if ((it.next() instanceof Set) == false) {
				setWithinSet = false;  // oh well.
				return false;
			}
		}

		return (count == 1);
	}
	/**
	 * Driver to test out core functionality within the Set and its Visitors.
	 * Creation date: (1/21/2003 7:55:59 PM)
	 * @param args java.lang.String[]
	 */
	public static void main(String[] args) {
		// s = {1, 3, 9, {17, 21, 25}, {189, 44, 55}}
		Set s = createS();

		System.out.println ("1. AllOddVisitor example");
		System.out.println ("========================");
		System.out.println ("Set s is:" + s.toString());
		AllOddVisitor aov = new AllOddVisitor ();
		s.accept (aov);
		if (aov.isAllOdd()) {
			System.out.println ("Set s is all odd.");
		} else {
			System.out.println ("Set contains non-odd number:" + aov.getNonOdd());
		}

		// t = {-1, 5, 3, 0, 4, 2, 1}
		Set t = createT();

		// is fully consective? Structure is ignored.
		System.out.println ("2. ConsecutiveVisitor example");
		System.out.println ("=============================");

		ConsecutiveVisitor cv = new ConsecutiveVisitor ();
		t.accept (cv);
		String range = "[" + cv.getMin() + "," + cv.getMax() + "]";
		if (cv.isWhole()) {
			System.out.println ("Set contains range from " + range);
		} else {
			System.out.println ("Set has missing elements in the range " + range);
		}

		// is set within set?
		System.out.println ("3. SetWithinSet example");
		System.out.println ("=======================");

		Set u = new Set ();
		u.add (t);
		System.out.println ("SetWithinSet:" + t.toString() + " evaluates to: " + isSetWithinSet(t));
		System.out.println ("SetWithinSet:" + u.toString() + " evaluates to: " + isSetWithinSet(u));

		// is a set either null or recursively a null set?
		System.out.println ("3. NullSetVisitor example");
		System.out.println ("=======================");
		testNullSet (t);
		Set z = new Set();
		testNullSet (z);
		Set zz = new Set ();
		zz.add (z);
		testNullSet (zz);
		Set zzz = new Set();
		zzz.add (new Set());
		zzz.add (zz);
		testNullSet (zzz);

		System.out.println ("4. Flatten example");
		System.out.println ("==================");
		FlattenVisitor fv = new FlattenVisitor();
		s.accept (fv);
		System.out.println (s.toString() + " flattened :" + fv.getResult());

		System.out.println ("5. Merge example");
		System.out.println ("================");
		Set ms = new Set();
		ms.add (new Number (4));
		ms.add (new Number (5));
		Set b = new Set ();
		b.add (new Number (1));
		b.add (new Number (2));
		Set inner = new Set();
		inner.add (new Number (3));
		inner.add (new Number (4));
		b.add (inner);

		// b = set {1, 2, {3, 4}}  and ms = {4, 5}
		// merge (b,ms) ==> {{1, 4, 5}, {2, 4, 5}, {3, 4, 5}}
		Set m = mergeSet (b, ms);
		System.out.println (b.toString() + " merged " + ms.toString() + " = " + m.toString());
	}
	/**
	 * Mege the two sets using Iterators.
	 */
	public static Set mergeSet(Set s1, Set s2) {
		Set result = new Set();
		for (Iterator it = s1.iterator(); it.hasNext();) {
			Element e = (Element) it.next();

			// there must be a better way than this!
			Set newSet = new Set();

			// if a set, then copy into newSet, otherwise create a new set with
			// just the element.
			if (e instanceof Set) {
				Set s = (Set) e;

				// copy old set
				for (Iterator inIT = s.iterator(); inIT.hasNext();) {
					e = (Element) inIT.next();
					newSet.add(e);
				}
			} else {
				newSet.add(e);
			}

			// merge in s2
			for (Iterator inIT = s2.iterator(); inIT.hasNext();) {
				newSet.add((Element) inIT.next());
			}

			// add into result
			result.add(newSet);
		}

		return result;

	}
	/**
	 * Run the NullSetVisitor on this set.
	 */
	public static void testNullSet (Set s) {
		NullSetVisitor nsv = new NullSetVisitor ();
		s.accept (nsv);
		System.out.println ("NullSetVisitor:" + s.toString() + " evaluates to: " + nsv.isNull());
	}
}
