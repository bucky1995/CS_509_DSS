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
public class ContextIterator implements Iterator<Context> {

	public static final String delims = " ~`!@#$%^&*()_=+{[}]|:;<,>./?\"\\"; 
	StringTokenizer st;
	int idx = 0;
	Context   nextValue = null;
	
	public ContextIterator (String s) {
		st = new StringTokenizer(s, delims, true);
	}
	
	public boolean hasNext() {
		if (nextValue != null) { return true; }
		if (!st.hasMoreTokens()) { return false; } 
		
		String s = st.nextToken();
		while (delims.indexOf(s.charAt(0)) != -1) {
			// advance
			idx += s.length();
			
			if (!st.hasMoreTokens()) { return false; }
			
			s = st.nextToken();
		}
		
		nextValue = new Context (s, idx);
		return true;
	}

	public Context next() {
		idx += nextValue.word.length();
		Context ret = nextValue;
		nextValue = null;
		return ret;
	}

	public void remove() {
		throw new UnsupportedOperationException("Can't help you");
	}

}
