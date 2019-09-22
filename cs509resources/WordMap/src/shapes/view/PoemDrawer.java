package shapes.view;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Iterator;

import shapes.model.*;

public class PoemDrawer extends ShapeDrawer {
	/**
	 * Serializable key
	 */
	private static final long serialVersionUID = 5600436832677222052L;
	
	final int arcWidth = 5;
	final int arcHeight = 5;

	final int hSpacing = 4;
	final int vSpacing = 2;
	
	@Override
	public int getHeight(Shape shape) {
		int numRows = shape.getNumberRows();
		if (actualMetrics == null) {
			return defaultFontHeight*numRows;
		}
		
		return numRows * (2*vSpacing + actualMetrics.getHeight());
	}

	@Override
	public int getWidth(Shape shape) {
		PoemShape ps = (PoemShape) shape;
		int widestRow = -1;
		for (Iterator<String> ri = ps.rows(); ri.hasNext(); ) {
			String row = ri.next();
			int w;
			if (actualMetrics == null) {
				w = row.length() * defaultFontHeight;
			} else {
				w = actualMetrics.stringWidth(row);
			}
			
			if (w > widestRow) {
				widestRow = w;
			}
		}
		
		return 2*hSpacing + widestRow;
	}

	// outline all boxes
	@Override
	void outlineBox(Graphics g, Shape shape) {
		if (actualMetrics == null) { if (!ensureFont(g)) { return; }}
		PoemShape ps = (PoemShape) shape;

		Color oldColor = g.getColor();
		for (Iterator<WordShape> it = new DoubleArrayIterator<WordShape>(ps.getState()); it.hasNext(); ) {
			WordShape ws = it.next();
			ws.getDrawer().outlineBox(g, ws);
		}
		g.setColor(oldColor);
	}

	@Override
	void fillBox(Graphics g, Shape shape) {
		if (actualMetrics == null) { if (!ensureFont(g)) { return; }}
		PoemShape ps = (PoemShape) shape;
		
		Color oldColor = g.getColor();
		g.setColor(getBackground());
		for (Iterator<WordShape> it = new DoubleArrayIterator<WordShape>(ps.getState()); it.hasNext(); ) {
			WordShape ws = it.next();
			g.fillRoundRect(ws.getX(), ws.getY(), ws.getWidth(), ws.getHeight(),
					arcWidth, arcHeight);
			
		}
		g.setColor(oldColor);
	}

	@Override
	void drawText(Graphics g, Shape shape) {
		if (actualMetrics == null) { if (!ensureFont(g)) { return; }}
		PoemShape ps = (PoemShape) shape;
		Color oldColor = g.getColor();
		for (Iterator<WordShape> it = new DoubleArrayIterator<WordShape>(ps.getState()); it.hasNext(); ) {
			WordShape ws = it.next();
			ws.getDrawer().drawText(g, ws);
		}
		g.setColor(oldColor);
	}

}
