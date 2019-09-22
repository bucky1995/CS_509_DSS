package iteratorPoem;

import java.util.Iterator;
import java.util.StringTokenizer;

/**
 * Given a string "With Time's injurious hand crush'd and o'erworn;" this
 * returns all words (in lowercase) without any punctuation marks.
 * 
 *   with
 *   time's
 *   injurious
 *   hand
 *   crush'd
 *   and
 *   o'erworn
 * 
 * Ignored punctuation: ~`!@#$%^&*()_=+{[}]\|:;”<,>./?
 * 
 * Allowed punctuation: ' and -
 * 
 * @author heineman
 *
 */
public abstract class LineIterator implements Iterator<String> {

	StringTokenizer st;
	
	public LineIterator (String s) {
		st = new StringTokenizer(s, delimiters());
	}
	
	// tell us what the punctuation delimiters are to be....
	public abstract String delimiters () ;  
	
	public boolean hasNext() {
		return st.hasMoreTokens();
	}

	public String next() {
		return st.nextToken();
	}

	public void remove() {
		throw new UnsupportedOperationException("Can't help you");
	}

}
