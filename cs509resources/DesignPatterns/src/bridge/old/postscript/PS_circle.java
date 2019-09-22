package bridge.old.postscript;

import java.awt.Graphics;
import java.io.IOException;
import java.io.OutputStream;

import bridge.old.core.Circle;

/**
 * Specialized class that knows how to draw the rectangle on the AWT.
 * 
 * @author heineman
 *
 */
public class PS_circle extends Circle {
	
	/** Where Postscript commands are to be sent. */
	OutputStream stream;
	
	/**
	 * Constructor for AWT circles must keep stream object handy for drawing later.
	 * 
	 * @param stream
	 * @param x
	 * @param y
	 * @param r
	 */
	public PS_circle(OutputStream stream, int x, int y, int r) {
		super(x, y, r);
		
		this.stream = stream;
	}

	/**
	 * Draw the circle.
	 * @param x
	 * @param y
	 * @param x2
	 * @param y2
	 */
	public void drawCircle(int x, int y, int r) {
		String s = "" + ((float)x)/100.0f + " " + ((float)y)/100.0f + " " + ((float)r)/100.0f  + " 0 360 arc closepath stroke\n";
		
		try {
			stream.write(s.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
