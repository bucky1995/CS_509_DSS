package oval.controller;

import java.awt.*;
import java.awt.event.*;

import oval.model.*;
import oval.view.*;

public class OvalCreator extends MouseAdapter {
	Model        model;
	OvalPanel    panel;
	Point        origin;
	
	public OvalCreator(Model m, OvalPanel cp) {
		this.model = m;
		this.panel = cp;
	}
	
	@Override
	public void mousePressed(MouseEvent me) {
		origin = me.getPoint();
	}
	
	@Override
	public void mouseDragged(MouseEvent me) {
		int deltaX = Math.abs(me.getX() - origin.x);
		int deltaY = Math.abs(me.getY() - origin.y);
		model.setActiveOval(new Oval(origin, deltaX, deltaY));
		panel.repaint();
	}
	
	@Override
	public void mouseReleased(MouseEvent me) {
		int deltaX = Math.abs(me.getX() - origin.x);
		int deltaY = Math.abs(me.getY() - origin.y);
		model.setActiveOval(null);
		model.addOval(new Oval(origin, deltaX, deltaY));
		panel.repaint();
	}
	
}
