package bidirectional;

public class Rectangle {
	Point topLeft;
	Point bottomRight;
	
	public Rectangle(int x, int y, int width, int height ) {
		if (width <= 0 || height <= 0) {
			throw new RuntimeException("invalid width or height");
		}
		
		topLeft = new Point(x,y,this);
		bottomRight = new Point(x+width, y+height, this);
	}
}
