package hangman.controller;

import hangman.model.Model;
import hangman.view.HangmanGUI;
import junit.framework.TestCase;

public class TestGuessLetterController extends TestCase {
	
	/** GUI being used, and model on which it depends. */
	HangmanGUI gui;
	Model model;
	
	/** Controller under test. */
	GuessLetterController controller;
	
	// this executes once for each test case
	@Override
	protected void setUp() {
		model = new Model("Lincoln");
		gui = new HangmanGUI(model);
		gui.setVisible(true);
		
		controller = new GuessLetterController(gui, model);
	}
	
	// make sure to dispose of GUI resource, otherwise in the long run
	// we will run out of memory.
	@Override
	protected void tearDown() {
		gui.dispose();
	}
	
	// test case to validate process works from the GUI.
	public void testValidGuess () {
		// prepare GUI
		gui.getGuessTextField().setText("n");
		
		controller.process();
				
		// Validate that text field cleared, new letters added, and the model 
		// is updated accordingly

		// REPLACE with new code that is on SL115 computer (commit prior to class!)
		int ct = 0;
		for (int i = 0; i < model.length(); i++) {
			if (model.isDiscovered(i)) {
				ct++;
			}
		}
		

		assertEquals (2, ct);
		assertEquals ("", gui.getGuessTextField().getText());
		
		// n's appear in these two
		assertEquals ("n", gui.getLettersField(2).getText());
		assertEquals ("n", gui.getLettersField(6).getText());
	}
}
