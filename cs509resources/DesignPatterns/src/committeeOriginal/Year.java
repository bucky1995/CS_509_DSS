package committeeOriginal;

import java.util.Hashtable;
/**
 * Represents a year during which a committee is in session.
 * <p>
 * <ol>
 * <li>[C1] A person can serve on as many as two committees only in any given year.</li>
 * <li>[C2] Each committee can have three to five persons as members in any given year.</li>
 * <li>[C3] A person can serve between one to four years on any specific committee.</li>
 * </ol>
 * <p>
 * Since we are not a core object, we would only be responsible for a subset of these
 * constraints. However, the only reasonable one for us is [C2] but this MUST be
 * resolved by the Committee class. Why? Because of the 3..5 requirements, the committee
 * must be in either the 'open' or 'active' state, and this determination SHOULD NOT BE
 * DELEGATED to such a minor class as Year.
 * <p>
 * For this scheme to work, we must ensure that each Year has only one object
 * <p>
 * @author: George T. Heineman (heineman@cs.wpi.edu)
 */
public class Year {
	/** Each Year has an integer year. [package private]. */
	int year;

	/** 
	 * Each Year has a series of post pairs. [package private].
	 * <p>
	 * Entries in post are Committe [0] and Person [1]
	 */
	Object posts[][] = new Object[0][2];	
/**
 * Each Year represents a given calendar year.
 * @param year   the int representing the year. 
 */
public Year(int year) {
	super();

	// Can you believe that I actually left this out and had to add it in
	// during my debugging!  Goes to show that we must always be careful when
	// writing ANY code. [12-Jan-2003 3:55 PM]
	this.year = year;
}
/**
 * Add a Post to the committee for the given Person and Year.
 * <p>
 * We must ensure the constraints regarding (Person,Committee,Year):
 * <p>
 * <ol>
 * <li>[C1] A person can serve on as many as two committees only in any given year.</li>
 * <li>[C2] Each committee can have three to five persons as members in any given year.</li>
 * <li>[C3] A person can serve between one to four years on any specific committee.</li>
 * </ol>
 * Since we are not a core operation, we only verify [C1]. We can't verify C2 here because
 * that has more to do with whether the committee is open, active, or full.
 * <p>
 * @param committee the <code>Committee</code> object for the committee to which this
 *                  person is joining.
 * @param person    the <code>Person</code> object for the person joining the committee
 *                  in the given year.
 * @return true if the post was added; false otherwise.
 */
public boolean addPost(Committee committee, Person person) {
	// Assume we can add it.
	Object newPosts[][] = new Object[posts.length+1][2];

	// Now the element to be added could be in the array at any location. This enables
	// us to search and, when found, return false.
	for (int i = 0; i < posts.length; i++) {
		Committee existingCommittee = (Committee) posts[i][0];
		Person    existingPerson    = (Person)    posts[i][1];
		
		if (committee.getName().equals (existingCommittee.getName())) {
			if (person.getName().equals (existingPerson.getName())) {
				return false;  // nothing to do since already in the list.
			}
		}

		newPosts[i][0] = posts[i][0];
		newPosts[i][1] = posts[i][1];
	}

	// If we get here, then we aren't in the array so we can add at the end.
	newPosts[newPosts.length-1][0] = committee;
	newPosts[newPosts.length-1][1] = person;

	// NOW we can verify [C1] and [C3].
	// [C1] A person can serve on as many as two committees only in any given year.
	Hashtable personCount = new Hashtable();
	for (int i = 0; i < newPosts.length; i++) {
		Committee c = (Committee) newPosts[i][0];
		Person    p = (Person) newPosts[i][1];

		// increment count.
		Integer count = (Integer) personCount.get (p.getName());
		if (count == null) {
			count = new Integer (1);   // HAH! This was actually ZERO. Nice Defect!
		} else {
			count = new Integer (count.intValue()+1);
		}

		personCount.put (p.getName(), count);
		if (count.intValue() > 2) {
			// error
			System.err.println ("In year \"" + year+ "\", Person \"" + p.getName() + "\" is on more than two committees.");
			return false;
		}
	}
	
	// passed the constraints, so we update ourselves and return true
	posts = newPosts;

	// Since we are not a core operation, we simply return now
	return true;
}
/**
 * Return the posts for this Year.
 * <p>
 * @return java.lang.Object[][]
 */
public Object[][] getPosts() {
	return posts;
}
/**
 * Return the value of this year.
 * <p>
 * @return int
 */
public int getYear() {
	return year;
}
/**
 * Remove a Post to the committee for the given Person and Year.
 * <p>
 * @param committee the <code>Committee</code> object for the committee from which this
 *                  person is being removed.
 * @param person    the <code>Person</code> object for the person being removed from 
 *                  the committee in the given year.
 * @return true if the post was added; false otherwise.
 */
public boolean removePost(Committee committee, Person person) {
	// assume we can delete
	Object tmpPosts[][] = new Object[posts.length][2];

	// In this algorithm, we remove a post by replacing the entry with the last entry
	// and then truncating the array.
	System.arraycopy (posts, 0, tmpPosts, 0, posts.length);

	boolean found = false;
	for (int i = 0; i < tmpPosts.length; i++) {
		Committee existingCommittee = (Committee) posts[i][0];
		Person    existingPerson    = (Person)    posts[i][1];
		
		if (person.getName().equals (existingPerson.getName())) {
			if (committee.getName().equals (existingCommittee.getName())) {
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
	System.arraycopy (tmpPosts, 0, newPosts, 0, tmpPosts.length-1);  // had copied from poss. NICE DEFECT
	
	posts = newPosts;

	// update our status (not required for Year) and then return true.
	return true;	

}
/**
 * Return the string representation of this object.
 * @return java.lang.String
 */
public String toString() {
	return ""+year;
}
}
