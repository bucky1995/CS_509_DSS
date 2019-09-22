package raw;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

/**
 * See following painting in swing tutorial
 * 
 * http://www.oracle.com/technetwork/java/painting-140037.html#db
 * 
 * @author heineman
 *
 */
public class BoardPanel extends JPanel {
	/**
	 * Keep Eclipse Happy.
	 */
	private static final long serialVersionUID = 2375458956021173772L;
	
	public static final Font defaultFont = new Font("Comic Sans MS", Font.PLAIN, 14);
	
	Board board;
	final int arcWidth = 5;
	final int arcHeight = 5;

	final int hSpacing = 4;
	final int vSpacing = 2;

	public static final Dimension WordPanelSize = new Dimension(900, 600);
	
	/**
	 * Construct ApplicationPanel with a Model instance used for information.
	 */
	public BoardPanel(Board b) {
		super();
		this.board = b;
	}

	@Override
	public Dimension getPreferredSize() {
		return WordPanelSize;
	}
	
	/** 
	 * To Draw within a JPanel, you need to have a protected void method of this name.
	 * Note that the first operation of this method MUST BE to invoke super.paintComponent(g)
	 * 
	 * @param g
	 */
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		for (int i = 0; i < board.getRowCount(); i++) {
			paintShape(g, board.get(i), Color.lightGray);
		}
		
		PoemShape targetShape = board.getTargetShape();
		if (targetShape != null) {
			paintShape(g, targetShape, Color.orange);
		}
		
		// draw selected on top
		PoemShape selectedShape = board.getSelectedShape();
		if (selectedShape != null) {
			paintShape(g, selectedShape, Color.yellow);
		}
	}
	
	/** Paint the shape into the given graphics context using its drawer. */
	public void paintShape(Graphics g, PoemShape s, Color color) {
		if (g == null) { return; }
		g.setFont(defaultFont);

		int posX = s.x;
		int posY = s.y;
		for (int i = 0; i < s.state.length; i++) {
			for (int j = 0; j < s.state[i].length; j++) {
				g.setColor(color);
				g.fillRoundRect(posX, posY, 
						2*hSpacing + s.state[i][j].length()*s.charWidth,  s.charHeight,
						arcWidth, arcHeight);
				
				g.setColor(Color.black);
				g.drawRoundRect(posX, posY, 
						2*hSpacing + s.state[i][j].length()*s.charWidth,  s.charHeight,
						arcWidth, arcHeight);

				g.drawString(s.state[i][j], posX + hSpacing, posY + s.charHeight - vSpacing);
				posX += 2*hSpacing + s.state[i][j].length()*s.charWidth;
			}
			posY += s.charHeight;
			posX = s.x;
		}

	}	
}
