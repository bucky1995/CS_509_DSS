package composite;

import java.util.*;

/**
 * Simple set representation
 * <p>
 * Set maintains a 1::* relationship with Numbers. A Set can
 * be an element of a set.
 *
 */
public class Set extends Element implements Iterable<Element> {

	/**
	 * elements : Element [*]
	 */
	protected ArrayList<Element> elements = new ArrayList<Element>();
	
	/**
	 * Set constructor comment.
	 */
	public Set() {
		super();
	}
	
	/**
	 * Element can be added into the set ONLY if it is not already in the set.
	 * <p>
	 * Does anyone protect against recursive additions?
	 *   s.add (s); ???
	 * what about
	 *   s1 = set {1,2}
	 *   s2 = set {3,4,5}
	 *   s3 = set {9}
	 *   s3.add (s1);     {9,{1,2}}
	 *   s2.add (s3);     {3,4,5,{9,{1,2}}}
	 * 
	 *   // should fail because s1 is already a descendant of s2. Can't perform
	 *   // equals() check since that would be overly restrictive
	 *   s1.add (s2);     {1,2,{3,4,5,{9,{1,2}}}}
	 *                     +++            +++
	 * ???
	 * <p>	 
	 * @param num java.lang.Integer
	 */
	public void add(Element elt) {
		if (contains (elt)) return;

		// ensure that this is not already a descendant of elt. This is only meaningful
		// if elt is a set. We have to cast Element into a Set to solve this problem.
		// on the other hand, we could resolve the issue within the toString() by
		// simply breaking the iteration when we return to a previously seen node.
		// At this point: no good solution.

		elements.add (elt);
	}
	
	/**
	 * Determines whether the set contains the given number.
	 * <p>
	 * Not required in the Set but essential for any real computation.
	 * @return boolean
	 * @param num java.lang.Integer
	 */
	public boolean contains(Element elt) {
		return elements.contains (elt);
	}
	
	/**
	 * Returns elements in the set.
	 * <p>
	 * Not required to be in any particular order. To return a sorted set, use the sorted()
	 * method.
	 * <p>
	 * @return boolean
	 * @param num java.lang.Integer
	 */
	public Iterator<Element> iterator () {
		return elements.iterator();
	}
	
	/**
	 * Determines whether two Set objects are equal.
	 * <p>
	 * Two Set are equal if and only if (1) o is not null; (2) o is an instance
	 * of the Set class; (3) matches entire structure.
	 * <p>
	 * Creation date: (1/21/2003 11:12:35 PM)
	 * @return boolean
	 * @param o java.lang.Object
	 */
	public boolean equals(Object o) {
		if (o == null) return false;
		
		// if we aren't an Set, return false now
		if (!(o instanceof Set)) {
			return false;
		}

		// test out structure. Since iterators are unordered, we can only perform
		// check by comparing that (1) every element of this object is found in other,
		// and that (2) every element of other object is found in this. Only by checking
		// both can we assure pure equality. That is (R subset S) and (S subset R) ==> R=S

		// This algorithm fails-fast, that is, returns false at the first non-inclusion.
		Set other = (Set) o;

		// Make sure that (this subset other)
		for (Element e : this) {
			if (!other.contains(e)) {
				return false;
			}
		}

		// Make sure that (other subset this)
		for (Element e : other) {
			if (!contains (e)) {
				return false;
			}
		}

		return true;
	}
	
	/**
	 * Return string representation of set.
	 * <p>
	 * @return java.lang.String
	 */
	public String toString() {
		StringBuffer sb = new StringBuffer ("{");

		for (Iterator<Element> it = iterator(); it.hasNext(); ) {
			Element elt = it.next();
			sb.append(elt);
			if (it.hasNext()) sb.append (", ");
		}

		sb.append("}");
		return sb.toString();
	}
}
