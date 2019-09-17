package hangman.view;

import hangman.model.Model;

import java.awt.Graphics;

import javax.swing.JPanel;

/** Draw a hangman person bit by bit. */
public class HangmanPanel extends JPanel {

	Model model;
	
	public HangmanPanel (Model m) {
		this.model = m;
	}
	
	@Override
	public void paintComponent (Graphics g) {
		super.paintComponent (g);
		// 1: head
		// 2: body
		// 3: left leg
		// 4: right leg
		// 5: left arm
		// 6 : right arm
		int n = model.getNumberFailed();
		if (n < 1 ) {
			return;
		}
		
		if (n > 0) {
			g.drawOval (80, 10, 40, 40);
		}
		if (n > 1) {
			g.drawLine (100, 50, 100, 110);
		}
		if (n > 2) {
			g.drawLine (100, 110, 60, 150);
		}
		if (n > 3) {
			g.drawLine (100, 110, 140, 150);
		}
		if (n > 4) {
			g.drawLine (100, 70, 50, 70);
		}
		if (n > 5 ) {
			g.drawLine (100, 70, 150, 70);
		}
	}
}
