package bidirectional;

public class Point {
	int x;
	int y;
	Rectangle parent;
	
	public Point (int x, int y, Rectangle r) {
		this.x = x;
		this.y = y;
		this.parent = r;
		
		if (r.topLeft != null && r.bottomRight != null) {
			throw new RuntimeException ("Already associated with rectangle");
		}
	}
}
