package Pieces;

import java.awt.Point;

public abstract class ChessPiece {
	
	protected int row;
	protected int col;
	
	protected boolean color;
	
	/**
	 * 
	 * @param row must be between 0 and 8, inclusive
	 * @param col must be between 0 and 8, inclusive
	 * @param color true for white, false for black
	 */
	public ChessPiece(int row, int col, boolean color) {
		this.row = row;
		this.col = col;
		this.color = color;
	}
	
	/**
	 * 
	 * @param row row to move to
	 * @param col column to move to
	 * @return true if legal to move, false if illegal move
	 */
	protected abstract boolean move(int row, int col);
	
	protected Point getPos() {
		return new Point(row, col);
	}
	
}
