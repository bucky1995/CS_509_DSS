package shapes.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import shapes.model.*;

/**
 */
public class WordDrawer extends ShapeDrawer {
	
	/**
	 * Serializable key
	 */
	private static final long serialVersionUID = 5600436832677222052L;
	
	final int arcWidth = 5;
	final int arcHeight = 5;
	final int hSpacing = 4;
	final int vSpacing = 1;

	@Override
	public int getHeight(Shape shape) {
		if (actualMetrics == null) {
			return defaultFontHeight;
		}
		int h = 2*vSpacing + actualMetrics.getHeight();
		return h;
	}

	@Override
	public int getWidth(Shape shape) {
		WordShape ws = (WordShape) shape;
		if (actualMetrics == null) { 
			return ws.getContents().length() * defaultFontHeight;
		}
		
		int w = 2*hSpacing + actualMetrics.stringWidth(ws.getContents());
		return w;
	}

	@Override
	public void outlineBox(Graphics g, Shape shape) {
		if (actualMetrics == null) { if (!ensureFont(g)) { return; }}
		WordShape ws = (WordShape) shape;
		
		Color oldColor = g.getColor();
		g.setColor(getForeground());
		g.drawRoundRect(shape.getX(), shape.getY(), 
				2*hSpacing + actualMetrics.stringWidth(ws.getContents()), 2*vSpacing + actualMetrics.getHeight(),
				arcWidth, arcHeight);
		g.setColor(oldColor);
	}

	@Override
	public void fillBox(Graphics g, Shape shape) {
		if (actualMetrics == null) { if (!ensureFont(g)) { return; }}
		WordShape ws = (WordShape) shape;
		
		Color oldColor = g.getColor();
		g.setColor(getBackground());
		g.fillRoundRect(shape.getX(), shape.getY(), 
				2*hSpacing + actualMetrics.stringWidth(ws.getContents()), 2*vSpacing + actualMetrics.getHeight(),
				arcWidth, arcHeight);
		g.setColor(oldColor);
	}

	@Override
	public void drawText(Graphics g, Shape shape) {
		if (actualMetrics == null) { if (!ensureFont(g)) { return; }}
		WordShape ws = (WordShape) shape;
		Color oldColor = g.getColor();
		g.setColor(getForeground());
		Font oldFont = g.getFont();
		g.setFont(actualFont);
		
		// only offset based on ascent to allow for descent 
		g.drawString(ws.getContents(), shape.getX() + hSpacing, shape.getY() + vSpacing + actualMetrics.getAscent());
		g.setColor(oldColor);
		g.setFont(oldFont);
	}

}
