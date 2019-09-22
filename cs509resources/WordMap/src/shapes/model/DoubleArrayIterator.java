package shapes.model;

import java.util.Iterator;

public class DoubleArrayIterator<K> implements Iterator<K> {

	int row;
	int col;
	K[][] state;
	
	public DoubleArrayIterator(K[][] state) {
		this.state = state;
	}
	
	@Override
	public boolean hasNext() {
		if (row < state.length) { return true; }
		if (row >= state.length) { return false; } 
		if (col < state[row-1].length) { return true; } 
		return false;
	}

	@Override
	public K next() {
		K val = state[row][col];
		col++;
		if (col >= state[row].length) {
			row++;
			col = 0;
		}
		
		return val;
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException("Unable to remove elements from DoubleArrayIterator");
	}


}
