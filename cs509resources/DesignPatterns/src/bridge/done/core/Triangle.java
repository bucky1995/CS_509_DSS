package bridge.done.core;

import java.awt.Point;

public class Triangle extends Shape {

	int side;
	
	public void setSide (int s) {
		side = s;
	}
	
	public Triangle (Drawer d, int x, int y, int s) {
		super(new Point (x,y), d);
		
		side = s;
	}
	
	@Override
	public void draw() {
		
		drawLine(location.x, location.y, location.x + side, location.y -side);
		drawLine(location.x, location.y, location.x - side, location.y -side);
		drawLine(location.x- side, location.y- side, location.x - side, location.y -side);
		
	}

}












