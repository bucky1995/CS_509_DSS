package iteratorPoem;

// ~`!@#$%^&*()_=+{[}]\|:;”<,>./?
public class PoemIterator extends LineIterator {

	public PoemIterator(String s) {
		super (s);
	}
	
	
	/** Remove all punctuation and lowercase everything. */
	@Override
	public String delimiters() {
		return " ~`!@#$%^&*()_=+{[}]|:;<,>./?\"\\";
	}
	
	@Override
	public String next () {
		return super.next().toLowerCase();
	}

}
