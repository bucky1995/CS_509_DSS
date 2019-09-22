package hangman.model;

import hangman.model.Model;
import junit.framework.TestCase;

public class TestModel extends TestCase {
	
	// test word is stored properly
	public void testStorage() {
		Model m = new Model("SECRET");
		assertEquals ("SECRET", m.word);
	}
	
	public void testGuessLetterNoChance() {
		Model m = new Model ("platypus");
		
		// Guess missing letter
		m.guessLetter('q');
		assertEquals (1, m.numberFailedGuesses);
		
		// assert none are visible
		assertEquals (0, m.countDiscovered());
	}
	
	
	// test guesses
	public void testGuessesWhereLetterExists() {
		Model m = new Model("secret");
		assertEquals (0, m.numberFailedGuesses);
		
		// question: Does case matter 'E' vs. 'e'?
		m.guessLetter('E');
		assertEquals (0, m.numberFailedGuesses);
		
		int numChosen = m.countDiscovered();
		
		assertEquals (2, numChosen);
		
	}
}
