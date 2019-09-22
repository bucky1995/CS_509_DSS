package visitor;

/**
 * Numbers are possible elements of a Set.
 */
public class Number extends Element {
	/** The value. */
	public final int value;

	/** Construct a Number */
	public Number (int val) {
		this.value = val;
	}

	/**
	 * Accept a concrete visitor at this Element.
	 */
	public void accept (IVisitor v) {
		v.visitNumber (this);
	}

	/**
	 * Determines whether two Number objects are equal.
	 * <p>
	 * Two Number are equal if and only if (1) o is not null; (2) o is an instance
	 * of the Number class; (3) value is the same.
	 * <p>
	 * @return boolean
	 * @param o java.lang.Object
	 */
	public boolean equals(Object o) {
		if (o == null) return false;

		// if we aren't an Number, return false now
		if (!(o instanceof Number)) {
			return false;
		}

		// test out value.
		Number other = (Number) o;
		return other.value == value;
	}

	/**
	 * Return String representation of Number.
	 * @return java.lang.String
	 */
	public String toString() {
		return "" + value;
	}
}
