package bowling.view;

import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

import bowling.IBowling;
import bowling.IFrame;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Final frame has bonus square
 * @author Laptop
 *
 */
public class FramesView extends JPanel {

	IBowling model;
	public static final String fontName = "Comic Sans MS";
	Font smallFont = null;
	Font largeFont = null;

	/** Width of frame. */
	int boxWidth;

	/** x-offset into frame for inner box. */
	int offset;

	/**
	 * Create the panel.
	 */
	public FramesView(IBowling model) {
		super();
		this.model = model;
		setBackground(Color.ORANGE);

		smallFont = new Font(fontName, Font.PLAIN, 12);
		largeFont = new Font(fontName, Font.PLAIN, 24);
	}

	/** Draw nth frame. Note that final frame has room for an additional bonus roll. */
	void drawFrame(Graphics g, int n) {

		int frameWidth = boxWidth;
		if (n == 9) { frameWidth += boxWidth-offset; }

		g.drawRect(n*boxWidth, 0, frameWidth, getHeight());
	}

	/**
	 * Paint frame in the space provided.
	 */
	@Override
	public void paintComponent (Graphics g) {
		super.paintComponent(g);

		boxWidth = getWidth() / 10;
		offset = 2*boxWidth/3;
		boxWidth -= offset/10;  // shave fraction to leave room in final frame.

		// get each frame, one at a time.
		int x = 0;
		int y = 0;

		// quick hack to generate default empty iterator.
		Iterator<IFrame> it = new ArrayList<IFrame>().iterator();
		if (model != null) {
			it = model.iterator();
		}
		g.setColor(Color.black);
		int score = 0;
		for (int n = 0; n < 10; n++) {
			drawFrame(g, n);


			// inner one; be sure to draw an additional one in final frame
			g.drawRect(x + offset, y, boxWidth - offset, boxWidth-offset);
			if (n == 9) {
				g.drawRect(x + boxWidth, y, boxWidth- offset, boxWidth-offset);
			}

			if (it.hasNext()) {
				IFrame frame = it.next();
				if (frame.isComplete()) {
					if (frame.isStrike()) {
						g.fillRect(x + offset, y, boxWidth - offset, boxWidth-offset);
					} else if (frame.isSpare()) {
						int startx = x + 10;
						g.setFont(smallFont);
						g.drawString("" + frame.getRoll(0), startx, y + 15);
						startx += boxWidth;
						
						g.fillPolygon(new int[] {x+offset, x+boxWidth, x+boxWidth, x+offset},
								new int[] {boxWidth-offset, 0, boxWidth-offset, boxWidth-offset},
								4);
					} else {
						int startx = x + 10;
						for (int i = 0; i < frame.getNumRolls(); i++) {
							g.setFont(smallFont);
							g.drawString("" + frame.getRoll(i), startx, y + 15);
							startx += boxWidth/2;
						}
					}
				} else {
					int startx = x + 10;
					g.setColor(Color.red);
					g.setFont(smallFont);
					g.drawString("" + frame.getRoll(0), startx, y + 15);
					startx += boxWidth;

					if (frame.isStrike()) {
						g.fillRect(x + offset, y, boxWidth - offset, boxWidth-offset);
					} else if (frame.isSpare()) {

						g.fillPolygon(new int[] {x+offset, x+boxWidth, x+boxWidth, x+offset},
								new int[] {boxWidth-offset, 0, boxWidth-offset, boxWidth-offset},
								4);
					}
				}

				g.setColor(Color.black);

				// score in big, if complete
				score += frame.score();
				if (frame.isComplete()) {
					g.setFont(largeFont);
					g.drawString("" + score, x+boxWidth/5, y+boxWidth);
				}
			}

			x += boxWidth;
		}
	}

}
