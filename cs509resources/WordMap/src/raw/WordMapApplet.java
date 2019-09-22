package raw;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.Random;

import javax.swing.JApplet;

public class WordMapApplet extends JApplet {
	
	static Board initializeWords(Dimension d) {
		Board board = null;
		int baseX = (int) (.05 * d.width);
		int baseY = (int) (.5 * d.height); // half way down
		int width = (int) (.9 * d.width);
		int height = (int) (.45 * d.height);
		
		Random r = new Random();
		board = new Board();
		for (int i = 0; i < Words.words.length; i++) {
			String word = Words.words[i];
			
			int x = baseX + r.nextInt(width);
			int y = baseY + r.nextInt(height);
			PoemShape ws = new PoemShape(x, y, new String[][] { {word}});
			board.add(ws);
		}
		return board;
	}
	
	public void init() {
		setSize(1200, 700);
		setLayout (new FlowLayout());
		
		Board board = initializeWords(BoardPanel.WordPanelSize);
		
		Application.init(board, this.getContentPane());
	}
}
