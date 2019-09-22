package bridge.old.postscript;

import java.awt.Graphics;
import java.io.IOException;
import java.io.OutputStream;

import bridge.old.core.Rectangle;

/**
 * Specialized class that knows how to draw the rectangle on the AWT.
 * 
 * @author heineman
 *
 */
public class PS_rectangle extends Rectangle {
	
	/** Where Postscript commands are to be sent. */
	OutputStream stream;

	
	/**
	 * Constructor for AWT_rectangle object needs to know of the graphics.
	 * @param stream
	 * @param x,y,x2,y2
	 */
	public PS_rectangle (OutputStream stream, int x, int y, int x2, int y2) {
		super (x, y, x2, y2);
		
		this.stream = stream;
	}
	
	/**
	 * Finally! The concrete implementation
	 * 
	 * @param x
	 * @param y
	 * @param x2
	 * @param y2
	 */
	public void drawLine(int x, int y, int x2, int y2) {
		String s = "" + ((float)x)/100.0f  + " " + ((float)y)/100.0f  + " newpath moveto\n";
		s += "" + ((float)x2)/100.0f  + " " + ((float)y2)/100.0f  + " lineto stroke\n";
		
		try {
			stream.write(s.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
