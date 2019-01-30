package Pieces;

import java.awt.Point;

public class Pawn extends ChessPiece {
	
	public Pawn(int row, int col, boolean color) {
		super(row, col, color);
	}

	@Override
	public boolean move(int row, int col, ChessPiece[][] board) {
		if (row != this.row || col != this.col) { // make sure not the same piece
			
			
			try {
				// check if enemy is diagonal opposite
				if (this.getColor()) { // white
					
//					 check [row-1][col+1] and [row-1][col-1]
					
				} else { // black
					
				}
			} catch (Exception e) {
				return false;
			}
			
			
		}
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected Point[] getPossibleMoves(ChessPiece[][] board) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
