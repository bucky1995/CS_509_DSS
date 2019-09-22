package shapes.model;

import java.util.Stack;

import shapes.controller.Move;

/**
 * Entry point into the Entity domain space.
 */
public class Model {
	
	Board board;
	Stack<Move> moves = new Stack<Move>();
	Stack<Move> redoStack = new Stack<Move>();
	
	/**
	 * Currently selected shape (or null if none). When a shape is
	 * selected, it is no longer part of the Model. 
	 */
	Shape selected;
	
	public Model(Board b) {
		board = b;
	}
	
	public Model() {
		this(new Board());
	}
	
	public void setBoard(Board b) {
		board = b;
	}

	public Board getBoard() {
		return board;
	}

	/**
	 * Record the move which can be undone in the future.
	 * 
	 * These are all "normal" moves, which mean that they invalidate any "undone" moves
	 * that might be currently on the redoStack, so that must be cleared.
	 * 
	 * @param move
	 */
	public void recordMove(Move move) {
		moves.add(move);
		redoStack.clear();
	}
	
	/** 
	 * Add this as a future move to be redone.
	 * 
	 * @param move
	 */
	public void recordRedoableMove(Move move) {
		redoStack.push(move);
	}

	/**
	 * If any moves have been undone, then they can be redone.
	 */
	public Move removeRedoMove() {
		if (redoStack.isEmpty()) { return null; }
		return redoStack.pop();
	}
	
	/**
	 * Redo Controller has executed a move that had previously been undone.
	 * This can go onto the move stack so it can be undone in future
	 * @param m
	 */
	public void recordRedoneMove(Move m) {
		moves.push(m);
	}
	
	/**
	 * Prepare for undo by getting last move.
	 */
	public Move removeLastMove() {
		if (moves.isEmpty()) { return null; }
		return moves.pop();
	}

	public void setSelected(Shape s) {
		selected = s;
	}

	public Shape getSelected() {
		return selected;
	}

	

}
