package committeeRevised;

/**
 * Glorified data structure storing information about a posting.
 * @author: George T. Heineman (heineman@cs.wpi.edu)
 */
public class Posting {
	/** The Committee. [package private]. */
	Committee committee;

	/** The Person.  [package private]. */
	Person person;

	/** The Year. [package private]. */
	Year year;
		/** Constructor for all three. */
		public Posting (Committee committee, Person person, Year year) {
			this.committee = committee;
			this.person    = person;
			this.year      = year;
		}
		/** equals() method is important to identify equivalent Posting objects. */
		public boolean equals (Posting p) {
			if (committee.getName().equals(p.committee.getName())) {
				if (year.getYear() == p.year.getYear()) {
					if (person.getName().equals (p.person.getName())) {
						return true;
					}
				}
			}

			// something is different
			return false;
		}
/** 
 * equals() method is important to identify equivalent Posting objects.
 * See documentation for java.lang.equals.
 */
public boolean equals(Object o) {
	// required since we don't know what is coming in
	if (o == null) return false;
	if (!(o instanceof Posting)) return false;

	Posting p = (Posting) o;
    if (committee.getName().equals(p.committee.getName())) {
        if (year.getYear() == p.year.getYear()) {
            if (person.getName().equals(p.person.getName())) {
                return true;
            }
        }
    }

    // something is different
    return false;
}
}
