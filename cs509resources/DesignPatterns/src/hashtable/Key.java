package hashtable;

public class Key {
	int row, col;
	
	public Key (int row, int col){ 
		this.row = row;
		this.col = col;
	}
	
	@Override
	public int hashCode() {
		return row*12387 + col;
	}
	
	@Override
	public boolean equals (Object o) {
		if (o == null) { return false; }
		
		if (o instanceof Key) {
			Key other = (Key) o;
			return row == other.row && col == other.col;
		}
		
		return false; // nope
	}
}
