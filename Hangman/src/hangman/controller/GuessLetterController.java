package hangman.controller;

import hangman.model.Model;
import hangman.view.HangmanGUI;

public class GuessLetterController {
	
	Model model;
	HangmanGUI gui;
	
	public GuessLetterController (HangmanGUI gui, Model m) {
		this.model = m;
		this.gui = gui;
	}
	
	public void process() {
		String letter = gui.getGuessTextField().getText();
		
		// make guess
		model.guessLetter(letter.charAt(0));
		
		// clear the input text field
		gui.getGuessTextField().setText("");
		
		gui.redrawYourself();
	}
}
