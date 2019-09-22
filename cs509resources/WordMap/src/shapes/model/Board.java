package shapes.model;

import java.util.*;

import shapes.controller.Listener;

/**
 * Supports listeners for changes.
 * 
 * @author heineman
 */
public class Board implements Iterable<Shape> {
	/** Shapes being maintained. */
	ArrayList<Shape> shapes = new ArrayList<Shape>();
	
	/** Listeners. */
	ArrayList<Listener> listeners = new ArrayList<Listener>();
	
	/** Add a listener. */
	public void addListener (Listener list) {
		listeners.add(list);
	}
	
	/** Remove a listener. */
	public void removeListener (Listener list) {
		listeners.remove(list);
	}
	
	/** 
	 * Reset board to state encoded by memento
	 * 
	 * @param m
	 */
	public void restore(BoardMemento m) {
		shapes = new ArrayList<Shape>();
		for (Shape s : m.stored) {
			shapes.add(s);
		}
		
		// state changed
		notifyListeners();
	}
	
	public BoardMemento getState() {
		return new BoardMemento(shapes);
	}
	
	/** Add shape to board. */
	public void add (Shape s) {
		if (s == null) {
			throw new NullPointerException("Cannot add null shape to board.");
		}
		shapes.add(s);

		// state changed
		notifyListeners();
	}
	
	/** Remove shape from board. */
	public void remove(Shape s) {
		shapes.remove(s);
		
		// state changed
		notifyListeners();
	}
	
	/**
	 * Return shape that intersects (x,y) point.
	 * 
	 * Allow to run through ALL shapes because we actually want the LAST one that intersects; this is because
	 * drawing will be done in this same order.
	 */
	public Shape findShape(int x, int y) {
		Shape found = null;
		for (Shape s : shapes) {
			if (s.intersect(x,y)) {
				found = s;
			}
		}
		
		return found;
	}
	
	/** Return all shapes in the board. */
	public Iterator<Shape> iterator() {
		return shapes.iterator();
	}

	/** Return the number of words on the board. */
	public int size() { 
		return shapes.size();
	}

	/** Return the given shape by index position. */
	public Shape get(int rowIndex) {
		return shapes.get(rowIndex);
	}

	/** Sort shapes using given comparator. */
	public void sort(Comparator<Shape> comparator) {
		Collections.sort(shapes, comparator);
	}
	
	/** 
	 * Notify all listeners.
	 * 
	 * During this event, no new changes can happen.
	 */
	void notifyListeners() {
		synchronized (listeners) {
			for (Listener list : listeners) {
				list.update();
			}
		}
	}

	/**
	 * Return the nth shape by sorted order (or null if too high).
	 * 
	 * @param selectedIndex
	 * @return
	 */
	public Shape getShape(int selectedIndex) {
		if (0 < selectedIndex && selectedIndex < shapes.size()) {
			return shapes.get(selectedIndex);
		}
		
		return null;
	}
	
}
