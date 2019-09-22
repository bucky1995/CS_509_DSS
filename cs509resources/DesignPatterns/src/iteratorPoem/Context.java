package iteratorPoem;

public class Context {
	String word;
	int pos;
	
	public Context (String word, int pos) {
		this.word = word.toLowerCase();
		this.pos = pos;
	}
	
	public String toString () {
		return word + "@" + pos;
	}
}
