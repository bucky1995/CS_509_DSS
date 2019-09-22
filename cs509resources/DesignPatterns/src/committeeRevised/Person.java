package committeeRevised;

/**
 * Represents a Person.
 * <p>
 * For this scheme to work, we must ensure that each Person has only one object.
 * <p>
 * @author: George T. Heineman (heineman@cs.wpi.edu)
 */
public class Person {
	/** Each committee has a name. [package private]. */
	String name;
/**
 * Each Person object represents a person on the committee and must have a name.
 * @param name   the <code>String</code> representing the name of the person.
 */
public Person(String name) {
	this.name = name;
}
/**
 * Return the name of this person.
 * <p>
 * @return java.lang.String
 */
public String getName() {
	return name;
}
/**
 * Return the string representation of this object.
 * @return java.lang.String
 */
public String toString() {
	return name;
}
}
