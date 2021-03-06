import java.awt.Point;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import Pieces.*;
import processing.core.PApplet;


/*

	Represents a Game Of Life grid.

	Coded by: Atharva Shah
	Modified on: 1/23/19

*/

public class ChessBoard {
	
	private ChessPiece[][] grid;
	
	private boolean currentTurn = false;

	// Constructs an empty grid
	public ChessBoard() {
		grid = new ChessPiece[8][8];
		
		// initialize starting pieces
		
	}
	
	public int isOccupied(int row, int col) { // return 0 if not occupied, 1 if friendly fire, 2 if capture
		if (null == grid[row][col]) {
			return 0; // illegal move
		} else if (grid[row][col].getColor() == currentTurn) {
			return 1; // friendly
		} else {
			return 2; // all clear
		}
	}
	
	
	/**
	 * Switches the turn
	 */
	public void switchTurn() {
		if (currentTurn) {
			currentTurn = false;
		} else {
			currentTurn = true;
		}
	}

	/**
	 * 
	 * @param piece Point object from clickToIndex
	 * @param x grid row to move to
	 * @param y grid col to move to
	 * @return true if move completed, false if move not completed
	 */
	public boolean move(Point piece, int x, int y) {
		
		
		// make sure that piece isn't moving into a friendly area
		if (1 == isOccupied(x, y)) {
			return false;
		}
		
		
		// check to see if the chess piece can legally make move
		if (grid[x][y].move(x, y, grid)) { 
			grid[x][y] = grid[(int)piece.getX()][(int)piece.getY()]; // places object in new position
			grid[x][y] = null;
			return true;
		}
		
		return false;
		
		
	}
	
	// Formats this Life grid as a String to be printed (one call to this method returns the whole multi-line grid)
	public String toString() {
		String s = new String();
		
		for (int row = 0; row < grid.length; row++) {
			
			
//			s = s + row + ": ";
			for (int col = 0; col < grid[row].length; col++) {
				
				if (null != grid[row][col]) { // check for null
					s = s + grid[row][col];
				} else {
					s = s + " ";
				}
			}
			s = s + "\n";
		}
		
		return s;
	}

	// Reads in array data from a simple text file containing asterisks (*)
//	public void readData (String filename, char[][] gameData) {
//		File dataFile = new File(filename);
//
//		if (dataFile.exists()) {
//			int count = 0;
//
//			FileReader reader = null;
//			Scanner in = null;
//			try {
//					reader = new FileReader(dataFile);
//					in = new Scanner(reader);
//
//					while (in.hasNext()) {
//						String line = in.nextLine();
//						for(int i = 0; i < line.length(); i++)
//							if (count < gameData.length && i < gameData[count].length && line.charAt(i)=='*')
//								gameData[count][i] = true;
//
//						count++;
//					}
//			} catch (IOException ex) {
//				throw new IllegalArgumentException("Data file " + filename + " cannot be read.");
//			} finally {
//				if (in != null)
//					in.close();
//			}
//			
//		} else {
//			throw new IllegalArgumentException("Data file " + filename + " does not exist.");
//		}
//    }
	
	
	
	
	
	
	
	/**
	 * Optionally, complete this method to draw the grid on a PApplet.
	 * 
	 * @param marker The PApplet used for drawing.
	 * @param x The x pixel coordinate of the upper left corner of the grid drawing. 
	 * @param y The y pixel coordinate of the upper left corner of the grid drawing.
	 * @param width The pixel width of the grid drawing.
	 * @param height The pixel height of the grid drawing.
	 */
	public void draw(PApplet marker, float x, float y, float width, float height) {
		/*
		 * Dimensions for each box: width/20, height/20;
		 */
//		float startX = x;
//		float startY = y;
//		float boxWidth = width/20;
//		float boxHeight = height/20;
//		
//		for (int i = 0; i < grid.length; i++) {
//			for (int k = 0; k < grid[i].length; k++) {
//				if (grid[i][k]) {
//					marker.rect(startX, startY, boxWidth, boxHeight);
//				}
//				startX += 25;
//			}
//			startY += 25;
//			startX = x;
//		}
		
		float cellWidth = width / grid[0].length; // x-axis length
		float cellHeight = height / grid.length; // y-axis length
		
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				if (grid[i][j]) {
					marker.fill(0);
				} else {
					marker.fill(255);
				}
				marker.rect(j*cellWidth, i*cellHeight, cellWidth, cellHeight);
			}
		}
		
		
	}
	
	/**
	 * Optionally, complete this method to determine which element of the grid matches with a
	 * particular pixel coordinate.
	 * 
	 * @param p A Point object representing a graphical pixel coordinate.
	 * @param x The x pixel coordinate of the upper left corner of the grid drawing. 
	 * @param y The y pixel coordinate of the upper left corner of the grid drawing.
	 * @param width The pixel width of the grid drawing.
	 * @param height The pixel height of the grid drawing.
	 * @return A Point object representing a coordinate within the game of life grid.
	 */
	public Point clickToIndex(Point p, float x, float y, float width, float height) {
		int tempX = -1; // code that indicates not in the grid
		int tempY = -1; // code that indicates not in the grid
		for (int i = 0; i < width; i+=(width/8) ) {
			if (p.getX() >= (float)(i)+x && p.getX() < (float)(i)+(x+width/20)) {
				tempX = (int) (p.getX()/(width/8));
				break;
			}
		}
		
		for (int j = 0; j < height; j+=(height/8) ) {
			if (p.getY() >= (float)(j)+y && p.getY() < (float)(j)+(y+width/20)) {
				tempY= (int) (p.getY()/(height/8));
				break;
			}
		}
		
		if (tempX != -1 && tempY != -1) {
			return new Point(tempX, tempY);
		} return null;
		
	}
	
	/**
	 * Marks the cell
	 * 
	 * @param i The row of the cell in the grid.
	 * @param j The row of the cell in the grid.
	 */
	public void markCells(int i, int j) {
//		grid[i][j].getPossibleMoves(grid);
	}
	
	public void clear() {
		grid = new ChessPiece[8][8];
	}

	
	
}