package shapes.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import shapes.model.Shape;

/**
 * Knows how to render the Shape onto Graphics object.
 * 
 * All implementors declare themselves to be serializable.
 * 
 * An alternate design would have WordDrawer maintain a link to its Shape.
 * This makes perfect sense but further complicates behavior when restoring
 * shapes from persistent storage or for copying ShapeDrawer objects. It
 * leads to problems, too, when constructing ShapeDrawer objects; specifically
 * do you first create the Shape and then the Drawer? Do you do at the same 
 * time? Makes for problems.
 * 
 * 
 * @author heineman
 */
public abstract class ShapeDrawer implements java.io.Serializable {
	
	/**
	 * Serializable key
	 */
	private static final long serialVersionUID = -502913437545612040L;

	public static final String defaultFont = "Comic Sans MS";
	public static final int defaultFontHeight = 14;
	
	/** State modes. */
	public static final int StateNormal = 0;
	public static final int StateSelected = 1;
	public static final int StateTarget = 2;
	
	private Color background = Color.lightGray;
	private Color foreground = Color.black;
	private Color selected   = Color.yellow;
	private Color target     = Color.orange;
	
	int mode = StateNormal;
	
	/** Needed parameters to draw. */
	Font actualFont = new Font(defaultFont, Font.PLAIN, defaultFontHeight);
	FontMetrics actualMetrics;
	
	/** Height of rendered word. */
	public abstract int getHeight(Shape shape);
	
	/** Width of rendered word. */
	public abstract int getWidth(Shape shape);

	/** A shape can be 'selected', 'target', or 'normal'. */
	public int getState () {
		return mode;
	}
	
	/** Set the state of the drawer (selected, target, normal). */
	public void setState (int mode) {
		if (mode >= StateNormal && mode <= StateTarget) {
			this.mode = mode;
		} else {
			this.mode = StateNormal;
		}
	}
	
	/**
	 * Draws shape of word.
	 * 
	 * Fill in background and draw outline on top. Finally, draw the text.
	 * Subclass determines how to draw accordingly.
	 *  
	 * Strategy design pattern being applied here.
	 * 
	 * @param g
	 * @param s
	 */
	public void drawShape(Graphics g, Shape s) {
		fillBox(g, s);
		outlineBox(g, s);
		drawText(g, s);
	}
	
	/** Set background color. */
	//public void setBackground(Color c) {
	//	background = c;
	//}
	
	/** Set foreground color. */
	//public void setForeground(Color c) {
	//	foreground = c;
	//}
	
	/** Helper method to set font upon first use. */
	boolean ensureFont(Graphics g) {
		actualMetrics = g.getFontMetrics(actualFont);
		if (actualMetrics == null) {
			System.err.println("Font Not Found:" + actualFont);
		}
		return (actualFont != null);
	}
	
	/** Get foreground color. */
	public Color getForeground() { return foreground; }
	
	/** Get background color. */
	public Color getBackground() {
		if (mode == StateNormal) { return background; }
		if (mode == StateSelected) { return selected; }
		if (mode == StateTarget) { return target; }
		
		return background; 
	}
	
	/** Outline box. */
	abstract void outlineBox (Graphics g, Shape s);
	
	/** Fill box (done before outline). */
	abstract void fillBox (Graphics g, Shape s);
	
	/** Draw text. */
	abstract void drawText (Graphics g, Shape s);


}
