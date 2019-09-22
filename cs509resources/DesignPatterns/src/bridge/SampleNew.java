package bridge;

import java.applet.Applet;
import java.awt.Graphics;
import java.util.*;

import bridge.done.awt.AWT_Drawer;
import bridge.done.core.Circle;
import bridge.done.core.Drawer;
import bridge.done.core.Rectangle;
import bridge.done.core.Shape;
import bridge.done.core.Triangle;
import bridge.done.ps.PS_Drawer;

public class SampleNew extends Applet {

	/** Array list. */
	ArrayList<Shape> objects = new ArrayList<Shape>();
	
	/**
	 * This is the default constructor
	 */
	public SampleNew() {
		super();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	public void init() {
		this.setSize(473, 407);
		
		//objects.add (new AWT_rectangle(this.getGraphics(), 30, 80, 10, 50));
		//objects.add (new AWT_circle(this.getGraphics(), 100, 100, 13));
		//objects.add (new PS_circle(System.out, 100, 100, 13));
		//objects.add (new PS_rectangle(System.out, 30, 80, 10, 50));
		
		Drawer awt = new AWT_Drawer(this.getGraphics());
		Drawer ps = new PS_Drawer (System.out);
		
		objects.add (new Circle (awt, 100, 100, 13));
		objects.add (new Rectangle (awt, 30, 80, 10, 50));
		
		objects.add (new Circle (ps, 100, 100, 113));
		objects.add (new Rectangle (ps, 30, 80, 10, 50));
		
		objects.add (new Triangle (ps, 80, 80, 50));
		
	}
	
	public void paint(Graphics g) {
		// HACK. to show how the Ps is also generated.
		System.out.println("%!PS");
		System.out.println ("matrix currentmatrix /originmat exch def");
		System.out.println ("/umatrix {originmat matrix concatmatrix setmatrix} def");
		System.out.println ("[28.3465 0 0 28.3465 10.5 100.0] umatrix");
		
		for (Shape s : objects) {
			s.draw();
		}
		
		System.out.println ("showpage");  // PS thing again.
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
