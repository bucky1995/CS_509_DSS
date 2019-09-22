package raw;

import java.awt.Point;
import java.awt.event.*;


public class WordMapAdapter extends MouseAdapter {
	protected static final int MOVE = 1;
	protected static final int VCONNECT = 2;
	protected static final int HCONNECT = 3;
	protected static final int DISCONNECT = 4;

	// move, vconnect, hconnect, disconnect
	int mode = MOVE;

	Board board;
	BoardPanel panel;
	WordTable table;
	
	Point anchor;
	int originalX, originalY;
	int deltaX, deltaY;

	public WordMapAdapter(Board b, BoardPanel panel, WordTable table) {
		this.board = b;
		this.panel = panel;
		this.table = table;
	}

	public void setMode(int m) {
		mode = m;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (mode == MOVE) {
			moveMousePressed(e);
		} else if (mode == VCONNECT) {
			moveMousePressed(e);
			PoemShape selected = board.getSelectedShape();
			if (selected != null) {
				board.remove(selected);
			}
		} else if (mode == HCONNECT) {
			moveMousePressed(e);
			PoemShape selected = board.getSelectedShape();
			if (selected != null) {
				board.remove(selected);
			}
		} else if (mode == DISCONNECT) {
			disconnectPressed(e);
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (mode == MOVE) {
			moveMouseReleased(e);
		} else if (mode == VCONNECT) {
			connectReleased(e, mode);
		} else if (mode == HCONNECT) {
			connectReleased(e, mode);
		} else if (mode == DISCONNECT) {
			PoemShape selected = board.getSelectedShape();
			moveMouseReleased(e);
			if (selected != null) {
				board.add(selected);
				board.setSelectedShape(null);
			}
		}
		
		board.sort();
		table.refreshTable();
	}
	
	void disconnectPressed(MouseEvent e) {
		anchor = e.getPoint();

		// pieces are returned in order of Z coordinate
		PoemShape s = board.findShape(anchor.x, anchor.y);
		if (s == null) { return; }

		
		// find individual intersection point where (x,y) inside and return a state
		// object that knows how to separate (and rejoin later when needed)  the new shape 
		PoemShape newShape = s.split(e.getX(), e.getY());
		
		if (newShape == null) {
			// nothing to split. Leave now.
			return;
		}
		
		originalX = e.getX();
		originalY = e.getY();
		
		// not yet in the board, but select it so we can move it around...
		board.setSelectedShape(newShape);
		
		// set anchor for smooth moving
		deltaX = anchor.x - originalX;
		deltaY = anchor.y - originalY;
		
		panel.repaint();
	}
	
	void connectReleased(MouseEvent e, int m) {
		PoemShape selected = board.getSelectedShape();
		if (selected == null) { return; }

		PoemShape target = board.getTargetShape();
		board.setTargetShape(null);
		if (target == null) {
			// turn this into a move
			board.add(selected);
			board.setSelectedShape(null);
		} else {
			PoemShape newShape = null;
			if (m == VCONNECT) {
				newShape = target.verticalConnect(selected, e.getX(), e.getY());
				if (newShape == null) {
					board.add(selected);
					board.setSelectedShape(null);
					board.setTargetShape(null);
					panel.repaint();
					return;
				}
			} else if (m == HCONNECT) {
				newShape = target.horizontalConnect(selected, e.getX(), e.getY());
				if (newShape == null) {
					board.add(selected);
					board.setSelectedShape(null);
					board.setTargetShape(null);
					panel.repaint();
					return;
				}
			}
			
			board.remove(selected);
			board.remove(target);
			board.add(newShape);
		}
		
		board.setSelectedShape(null);
		
		panel.repaint();
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		if (mode == MOVE) {
			moveMouseDragged(e);
		} else if (mode == VCONNECT) {
			connectDragged(e);
		} else if (mode == HCONNECT) {
			connectDragged(e);
		} else if (mode == DISCONNECT) {
			moveMouseDragged(e);
		}
	}

	void moveMousePressed(MouseEvent e) {
		anchor = e.getPoint();

		// pieces are returned in order of Z coordinate
		PoemShape s = board.findShape(anchor.x, anchor.y);
		if (s == null) { return; }

		// Select it
		board.setSelectedShape(s);
		originalX = s.getX();
		originalY = s.getY();

		// set anchor for smooth moving
		deltaX = anchor.x - originalX;
		deltaY = anchor.y - originalY;

		panel.repaint();
	}

	public void moveMouseReleased(MouseEvent e) {
		PoemShape selected = board.getSelectedShape();
		if (selected == null) { return; }

		selected.setLocation(e.getX() - deltaX, e.getY() - deltaY);

		board.setSelectedShape(null);
		panel.repaint();
	}

	public void connectDragged(MouseEvent e) {
		// no board? no behavior! No dragging of right-mouse buttons...
		if (e.getButton() == MouseEvent.BUTTON3) { return; }
		PoemShape selected = board.getSelectedShape();

		if (selected == null) { return; }

		int oldx = selected.getX();
		int oldy = selected.getY();

		selected.setLocation(e.getX() - deltaX, e.getY() - deltaY);

		boolean ok = true;

		// must still be visible!
		if (selected.getX() < 0) { ok = false; }
		if (selected.getX() + selected.getWidth() > panel.getWidth()) { ok = false; }
		if (selected.getY() < 0) { ok = false; }
		if (selected.getY() + selected.getHeight() > panel.getHeight()) { ok = false; }

		if (!ok) {
			selected.setLocation(oldx, oldy);
		}
		
		/** Have we found a target to which we connect? Also make sure (x,y) intersects as well. */
		PoemShape found = board.findShape(e.getX(), e.getY());

		board.setTargetShape(found);
		panel.repaint();
	}

	public void moveMouseDragged(MouseEvent e) {
		// no board? no behavior! No dragging of right-mouse buttons...
		if (e.getButton() == MouseEvent.BUTTON3) { return; }
		PoemShape selected = board.getSelectedShape();

		if (selected == null) { return; }

		int oldx = selected.getX();
		int oldy = selected.getY();

		selected.setLocation(e.getX() - deltaX, e.getY() - deltaY);

		boolean ok = true;

		// must still be visible!
		if (selected.getX() < 0) { ok = false; }
		if (selected.getX() + selected.getWidth() > panel.getWidth()) { ok = false; }
		if (selected.getY() < 0) { ok = false; }
		if (selected.getY() + selected.getHeight() > panel.getHeight()) { ok = false; }

		if (!ok) {
			selected.setLocation(oldx, oldy);
		} else {
			panel.repaint();
		}
	}


}
