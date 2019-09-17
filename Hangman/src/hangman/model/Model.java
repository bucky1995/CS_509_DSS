package hangman.model;

public class Model {

	final String word;
	final String match;
	
	int numberFailedGuesses;
	boolean discovered[];
	
	/**
	 * This model stores the lowercase value of the word being stored.
	 * 
	 * @param word
	 */
	public Model (String word) {
		this.word = word;
		this.match = word.toLowerCase();
		
		numberFailedGuesses = 0;
		discovered = new boolean[word.length()];
	}
	
	/** The number of letters in the model. */
	public int length() {
		return word.length();
	}
	
	/** Determine status of the ith letter. */
	public boolean isDiscovered (int i) {
		return discovered[i];
	}
	
	/** Return ith letter. */
	public char getLetter (int i) {
		return word.charAt(i);
	}
	
	/**
	 * With every guess be sure to update the number of failed guesses.
	 * Be careful to not differentiate between cases.
	 * @param c
	 * @return true if letter is in word; false otherwise and numFailedGuess increases.
	 */
	
	public boolean guessLetter (char c) {
		c = Character.toLowerCase(c);
		
		if (match.indexOf(c) == -1) {
			numberFailedGuesses++;
			return false;
		}
		
		// uncover letters in the word.
		for (int i = 0; i < match.length(); i++) {
			if (match.charAt(i) == c) {
				discovered[i] = true;
			}
		}
		
		return true;
	}

	public int getNumberFailed() {
		return numberFailedGuesses;
	}

	/**
	 * Return number of discovered letters in model.
	 */
	public int countDiscovered() {
		int ct = 0;
		for (int i = 0; i < length(); i++) {
			if (isDiscovered(i)) {
				ct++;
			}
		}
		
		return ct;
	}
	
	
}
