package committeeOriginal;

import java.util.Hashtable;
/**
 * Represents a Person.
 * <p>
 * <ol>
 * <li>[C1] A person can serve on as many as two committees only in any given year.</li>
 * <li>[C2] Each committee can have three to five persons as members in any given year.</li>
 * <li>[C3] A person can serve between one to four years on any specific committee.</li>
 * </ol>
 * <p>
 * For this scheme to work, we must ensure that each Person has only one object.
 * <p>
 * @author: George T. Heineman (heineman@cs.wpi.edu)
 */
public class Person {
	/** Each committee has a name. [package private]. */
	String name;

	/** 
	 * Each Person has a series of post pairs. [package private].
	 * <p>
	 * Entries in post are Committee [0] and Year [1]
	 */	Object posts[][] = new Object[0][2];
/**
 * Each Person object represents a person on the committee and must have a name.
 * @param name   the <code>String</code> representing the name of the person.
 */
public Person(String name) {
	this.name = name;
}
/**
 * Add a Post to the Person for the given Committee and Year.
 * <p>
 * We must ensure the constraints regarding (Person,Committee,Year):
 * <p>
 * <ol>
 * <li>[C1] A person can serve on as many as two committees only in any given year.</li>
 * <li>[C2] Each committee can have three to five persons as members in any given year.</li>
 * <li>[C3] A person can serve between one to four years on any specific committee.</li>
 * </ol>
 * <p>
 * Since we are not a core operation, we can only handle [C1] and [C3].
 * <p>
 * @param committee the <code>Committee</code> object for the committee to which this
 *                  person is joining.
 * @param year     the <code>Year</code> object for the year during which the person is
 *                 joining the committee.
 * @return  true if the post was added; false otherwise.
 */
public boolean addPost(Committee committee, Year year) {
	// Assume we can add it.
	Object newPosts[][] = new Object[posts.length+1][2];

	// Now the element to be added could be in the array at any location. This enables
	// us to search and, when found, return false.
	for (int i = 0; i < posts.length; i++) {
		Committee existingCommittee = (Committee) posts[i][0];
		Year      existingYear      = (Year)   posts[i][1];
		
		if (committee.getName().equals (existingCommittee.getName())) {
			if (year.getYear() == existingYear.getYear()) {
				return false;  // nothing to do since already in the list.
			}
		}

		newPosts[i][0] = posts[i][0];
		newPosts[i][1] = posts[i][1];
	}

	// If we get here, then we aren't in the array so we can add at the end.
	newPosts[newPosts.length-1][0] = committee;
	newPosts[newPosts.length-1][1] = year;

	// NOW we can verify [C1] and [C3].
	// [C1] A person can server on as many as two committees only in any given year.
	Hashtable yearCount = new Hashtable();
	for (int i = 0; i < newPosts.length; i++) {
		Committee c = (Committee) newPosts[i][0];
		Year      y = (Year) newPosts[i][1];

		// increment count.
		Integer count = (Integer) yearCount.get (""+ y.getYear());
		if (count == null) {
			count = new Integer (1);   // HAH! This was actually ZERO! Nice defect!
		} else {
			count = new Integer (count.intValue()+1);
		}

		yearCount.put (""+y.getYear(), count);
		if (count.intValue() > 2) {
			// error
			System.err.println ("Person \"" + name + "\" is serving on three committees in year " + y.getYear() + ".");
			return false;
		}
	}

	
    // [C3] A person can serve between one to four years on any specific committee.
	Hashtable committeeCount = new Hashtable();
	for (int i = 0; i < newPosts.length; i++) {
		Committee c = (Committee) newPosts[i][0];
		Year      y = (Year) newPosts[i][1];

		// increment count.
		Integer count = (Integer) committeeCount.get (c.getName());
		if (count == null) {
			count = new Integer (1);   // HAH! Was Zero. Nice Defect!
		} else {
			count = new Integer (count.intValue()+1);
		}

		committeeCount.put (c.getName(), count);
		if (count.intValue() > 4) {
			// error
			System.err.println ("Person \"" + name + "\" is serving on the committee \"" + c.getName() + "\" for more than four years.");
			return false;
		}
	}

	// passed the constraints, so we update ourselves and return true
	posts = newPosts;

	// Since we are not a core operation, we simply return now
	return true;
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
 * Return the posts for this Person.
 * <p>
 * @return java.lang.Object[][]
 */
public Object[][] getPosts() {
	return posts;
}
/**
 * Remove a Post to the Person for the given Committee and Year.
 * <p>
 * @param committee the <code>Committee</code> object for the committee from which this
 *                  person is being removed.
 * @param year     the <code>Year</code> object for the year during which the person is
 *                 being removed.
 * @return  true if the post was added; false otherwise.
 */
public boolean removePost(Committee committee, Year year) {
	// assume we can delete
	Object tmpPosts[][] = new Object[posts.length][2];

	// In this algorithm, we remove a post by replacing the entry with the last entry
	// and then truncating the array.
	System.arraycopy (posts, 0, tmpPosts, 0, posts.length);

	boolean found = false;
	for (int i = 0; i < tmpPosts.length; i++) {
		Committee existingCommittee = (Committee) posts[i][0];
		Year      existingYear      = (Year)      posts[i][1];
		
		if (committee.getName().equals (existingCommittee.getName())) {
			if (year.getYear() == existingYear.getYear()) {
				// swap with last position.
				tmpPosts[i] = tmpPosts[tmpPosts.length-1];
				found = true;
				break;    // THIS WAS CONTINUE. Nice Defect!
			}
		}
	}

	// not removed, so leave now
	if (!found) return false;
	
	// Truncate
	Object newPosts[][] = new Object[posts.length-1][2];
	System.arraycopy (tmpPosts, 0, newPosts, 0, tmpPosts.length-1);   // had copied from posts. NICE DEFECT
	
	posts = newPosts;

	// update our status (not required for Person) and then return true.
	return true;	
}
/**
 * Return the string representation of this object.
 * @return java.lang.String
 */
public String toString() {
	return name;
}
}
