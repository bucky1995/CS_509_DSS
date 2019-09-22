package visitor;

import java.util.*;

/**
 * Simple set representation
 * <p>
 * Set maintains a 1::* relationship with Numbers. A Set can
 * be an element of a set.
 * <p>
 */
public class Set extends Element implements Iterable<Element> {

	/**
	 * elements : Element [*]
	 */
	protected ArrayList<Element> ourElements = new ArrayList<Element>();

	/**
	 * Set constructor comment.
	 */
	public Set() {
		super();
	}

	/**
	 * Accept a concrete visitor at this Set. 
	 */
	public void accept (IVisitor v) {
		// To accept a set, we must visit each member of the set and be accepted on each element.
		for (Element e : this) {
			e.accept (v);
		}	    

		// Finally recognize that we have visited the set
		v.visitSet (this);
	}
	
	/**
	 * Element can be added into the set ONLY if it is not already in the set.
	 * <p>
	 */
	public void add(Element elt) {
		if (contains (elt)) return;

		ourElements.add (elt);
	}

	/**
	 * Determines whether the set contains the given number.
	 * <p>
	 * Not required in the Set but essential for any real computation.
	 * @return boolean
	 */
	public boolean contains(Element elt) {
		return ourElements.contains (elt);
	}

	/**
	 * Returns elements in the set.
	 * <p>
	 * Not required to be in any particular order. To return a sorted set, use the sorted()
	 * method.
	 * <p>
	 * @return boolean
	 */
	public Iterator<Element> iterator () {
		return ourElements.iterator();
	}

	/**
	 * Determines whether two Set objects are equal.
	 * <p>
	 * Two Set are equal if and only if (1) o is not null; (2) o is an instance
	 * of the Set class; (3) matches entire structure.
	 * <p>
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
	 * Return string representarion of set.
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
