package distributed.view;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import distributed.model.Model;

/**
 * View showing a model by representing the width x height value as a gray-scale shaded rectangle.
 */
public class DrawingCanvas extends Canvas implements IModelUpdated {

	/** Double buffer for fast redraw. */
	Image screenImage = null;
	
	/** Graphics into which all drawing occurs. Create ONCE to avoid disposing with each change. */
	Graphics sg = null;
	
	public static final int OffsetX = 10;
	public static final int OffsetY = 10;
	public static final Color bgcolor = new Color(204, 0, 204);
	
	public DrawingCanvas () {
		super();
	}
	
	/**
	 * Ensures that the image file into which decorators are drawn is properly constructed.
	 *
	 * Because of the way Java AWT works, you can't create an Image object until the Application
	 * is visible; however, this leads to a catch-22, so we perform "lazy computation" by
	 * creating the image once it is really needed.
	 */
	boolean ensureImageAvailable() {
		// not yet created the background image. Must do now; can't do this until
		// we have a valid Graphics object.
		if (screenImage == null) {
			screenImage = this.createImage(this.getWidth(), this.getHeight());
			sg = screenImage.getGraphics();			
		}
		
		return (sg != null);
	}

	/** All changes to the view are redrawn into the off-screen buffer. */
	public void redrawState() {
		if (!ensureImageAvailable()) {
			return;
		}
		
		// clear the image.
		sg.setColor(bgcolor);
		sg.fillRect(0, 0, this.getWidth(), this.getHeight());
		
		// now draw rectangle. 
		Model model = Model.getInstance();
		int s = model.getColor().getValue();
		
		Color c = new Color (s,s,s);
		sg.setColor(c);
		sg.fillRect(OffsetX, OffsetY,
				model.getWidth().getValue(),
				model.getHeight().getValue());
	}
	
	/** Paint a rectangle based upon model parameters. */
	public void paint (Graphics g) {
		// not visible yet (RARELY occurs).
		if (g == null) {
			return;
		}
		
		// simply overwrite the image.
		int w = getWidth();
		int h = getHeight();
		g.drawImage(screenImage, 0, 0, w, h, this);
	}


	/** Respond to changes in the underlying model. */
	public void modelChanged() {
		redrawState();
		repaint();
	}
	
}
