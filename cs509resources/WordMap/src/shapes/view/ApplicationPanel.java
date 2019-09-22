package shapes.view;

import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import shapes.model.*;

/**
 * See following painting in swing tutorial
 * 
 * http://www.oracle.com/technetwork/java/painting-140037.html#db
 * 
 * @author heineman
 *
 */
public class ApplicationPanel extends JPanel {
	/**
	 * Keep Eclipse Happy.
	 */
	private static final long serialVersionUID = 2375458956021173772L;
	
	Model model;
	
	/** Double Buffering technique requires an offscreen image. */
	Image offscreenImage;
	Graphics offscreenGraphics;
	Graphics canvasGraphics;

	public static final Dimension WordPanelSize = new Dimension(900, 600);
	
	// Current mouse listener
	MouseListener        activeListener;
	MouseMotionListener  activeMotionListener;
	
	/** Properly register new listener (and unregister old one if present). */
	public void setActiveListener(MouseListener ml) {
		this.removeMouseListener(activeListener);
		activeListener = ml;
		if (ml != null) { 
			this.addMouseListener(ml);
		}
	}
	
	/** Properly register new motion listener (and unregister old one if present). */
	public void setActiveMotionListener(MouseMotionListener mml) {
		this.removeMouseMotionListener(activeMotionListener);
		activeMotionListener = mml;
		if (mml != null) {
			this.addMouseMotionListener(mml);
		}
	}

	/**
	 * Construct ApplicationPanel with a Model instance used for information.
	 */
	public ApplicationPanel(Model m) {
		super();
		this.model = m;
		initialize();
	}

	@Override
	public Dimension getPreferredSize() {
		return WordPanelSize;
	}
	
	/**
	 * Initialize frame by adding all views
	 */
	void initialize() {		
	}
	
	public void redraw() {
		// nothing to draw into? Must stop here.
		if (offscreenImage == null) return;
		if (offscreenGraphics == null) return;    // detected during testing
		
		// clear the image.
		offscreenGraphics.clearRect(0, 0, this.getWidth(), this.getHeight());
		
		/** Draw all shapes. Note selected shape is not part of the model. */
		for (Shape s : model.getBoard()) {
			paintShape(offscreenGraphics, s);
		}
	}
	
	/** Make sure that image is created as needed. */
	void ensureImageAvailable(Graphics g) {
		if (offscreenImage == null) {
			offscreenImage = this.createImage(this.getWidth(), this.getHeight());
			offscreenGraphics = offscreenImage.getGraphics();
			canvasGraphics = g;
			
			redraw();
		}
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
		
		ensureImageAvailable(g);
		g.drawImage(offscreenImage, 0, 0, getWidth(), getHeight(), this);
		
		// draw selected on top of offscreen image, since not part of the model.
		Shape selected = model.getSelected();
		if (selected != null) {
			paintShape(g, selected);
		}
	}

	/** Paint the shape right to the screen */
	public void paintShape(Shape s) {
		paintShape(canvasGraphics, s);
	}
	
	/** Paint the shape into the given graphics context using its drawer. */
	public void paintShape(Graphics g, Shape s) {
		if (g == null) { return; }
		
		ShapeDrawer sd = s.getDrawer();
		sd.drawShape(g, s);
	}	
	
	/** Repaint to the screen just the given part of the image. */
	public void paintBackground(Shape s) {
		// Only updates to the screen the given region
		if (canvasGraphics != null) {
			canvasGraphics.drawImage(offscreenImage, s.getX(), s.getY(), s.getWidth(), s.getHeight(), this);
			repaint(s.getX(), s.getY(), s.getWidth(), s.getHeight());
		}
	}
	
}
