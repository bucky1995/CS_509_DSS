package bridge.done.ps;

import java.io.IOException;
import java.io.OutputStream;

import bridge.done.core.Drawer;

/**
 * Be a postscript drawer.
 * 
 * Check out:
 * 
 * http://www.physics.emory.edu/~weeks/graphics/howtops1.html
 * 
 * @author heineman
 *
 */
public class PS_Drawer extends Drawer {
	
	/** Where Postscript commands are to be sent. */
	OutputStream stream;

	/**
	 * Constructor for PS drawer needs the stream.
	 * 
	 * @param stream
	 */
	public PS_Drawer (OutputStream stream) {
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
	
	/**
	 * Draw a line in postscript.
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
