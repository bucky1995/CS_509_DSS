import hangman.model.Model;
import hangman.view.HangmanGUI;


public class Main {
	public static void main(String[] args) {
		Model m = new Model ("Lincoln");
		
		HangmanGUI g = new HangmanGUI (m);
		g.setVisible(true);
	}
}
