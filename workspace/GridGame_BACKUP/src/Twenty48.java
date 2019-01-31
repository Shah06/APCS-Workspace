import java.awt.Point;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import processing.core.PApplet;

/*

	Represents a Game Of Life grid.

	Coded by: Atharva Shah
	Modified on: 1/23/19

*/

public class Twenty48 {
	
	
	// grid with numerical representation of values of 2048
	private int[][] grid;
	private boolean lost = false;
	private int score = 0; // total value of all items on the board
	
	// Constructs an empty grid
	public Twenty48() {
		grid = new int[4][4];
	}
	
	public int getScore() {
		score = 0;
		for (int i = 0; i < grid.length; i++) {
			for (int k = 0; k < grid[i].length; k++) {
				score += grid[i][k];
			}
		}
		return score;
	}
	
	public boolean isOccupied(int x, int y) {
		if (0 == grid[x][y]) return false;
		return true;
	}
	
	/** 
	 * fills with random 2, or 4
	 */
	public void generateCell() {
		int emptyCells = 0;
		for (int i = 0; i < grid.length; i++) {
			for (int k = 0; k < grid[i].length; k++) {
				if (!isOccupied(i, k)) {
					emptyCells++;
				}
			}
		}
		
		if (emptyCells == 0 && !up() && !down() && !left() && !right()) {
			lost = true;
		}
		
		int rand = (int) Math.random() * emptyCells;
		
		int randCount = rand;
		
		for (int i = 0; i < grid.length; i++) {
			for (int k = 0; k < grid[i].length; k++) {
				if (!isOccupied(i, k) && randCount == 0) {
					randCount--;
					double num = Math.random();
					if (num <= 0.3) {
						grid[i][k] = 4;
						break;
					} else {
						grid[i][k] = 2;
						break;
					}
				}
			}
		}
	}
	
	
	/**
	 * Moves cell to position on the grid if legal
	 * @param cell Point representing target cell
	 * @param x x coordinate to move to
	 * @param y y coordinate to move to
	 * @return false if illegal move, true if legal move
	 */
	public boolean move(Point cell, int x, int y) {
		if (isOccupied(x, y) && grid[(int) cell.getX()][(int) cell.getY()] != grid[x][y]) return false;
		
		if (grid[(int) cell.getX()][(int) cell.getY()] == grid[x][y]) {
			grid[x][y] *= 2; // double cell
			grid[(int) cell.getX()][(int) cell.getY()] = 0;
			return true;
		}
		
		grid[x][y] = grid[(int) cell.getX()][(int) cell.getY()];
		grid[(int) cell.getX()][(int) cell.getY()] = 0;
		return true;
		
	}
	
	// shifts cells up, if possible
	public boolean up() {
		boolean moved = false;
		for (int i = 1; i < grid.length; i++) {
			for (int k = 0; k < grid[i].length; k++) {
				Point p = new Point(i, k);
				if (move(p, i-1, k)) {
					moved = true;
				}
				move(p, i-1, k);
			}
		}
		
		if (moved) {
			return true;
		} return false;
	}
	
	// shifts cells down, if possible
	public boolean down() {
		boolean moved = false;
		for (int i = 2; i >= 0; i--) {
			for (int k = 0; k < grid[i].length; k++) {
				Point p = new Point(i, k);
				if (move(p, i+1, k)) {
					moved = true;
				}
				move(p, i+1, k);
			}
		}
		
		if (moved) {
			return true;
		} return false;
	}
	
	// shifts cells right, if possible
	public boolean right() {
		boolean moved = false;
		for (int i = 0; i < grid.length; i++) {
			for (int k = 2; k >= 0; k--) {
				Point p = new Point(i, k);
				if (move(p, i, k+1)) {
					moved = true;
				}
				move(p, i, k+1);
			}
		}
		
		if (moved) {
			return true;
		} return false;
	}
	
	// shifts cells left, if possible
	public boolean left() {
		boolean moved = false;
		for (int i = 0; i < grid.length; i++) {
			for (int k = 1; k < grid[i].length; k++) {
				Point p = new Point(i, k);
				if (move(p, i, k-1)) {
					moved = true;
				}
				move(p, i, k-1);
			}
		}
		
		if (moved) {
			return true;
		} return false;
	}
	
	/**
	 * generates a random 2048 grid
	 */
	public void generate() {
		
		for (int i = 0; i < 5; i++) {
			generateCell();
		}
		
	}
	
	
	
	
	// Formats this Life grid as a String to be printed (one call to this method returns the whole multi-line grid)
	public String toString() {
		//TODO 
		return null;
	}
	
	
	
	
	
	// TODO clean this clutter up
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
		
		float cellWidth = width / grid[0].length; // x-axis length
		float cellHeight = height / grid.length; // y-axis length
		
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				
				
				// choose background rectangle color
				
				// light mode
				if (((DrawingSurface) marker).getBgColor() == 255) {
					switch (grid[i][j]) {
						case 2: marker.fill(254, 255, 229); break;
						case 4: marker.fill(255, 238, 188); break;
						case 8: marker.fill(255, 220, 117); break;
						case 16: marker.fill(252, 184, 83); break;
						case 32: marker.fill(252, 150, 103); break;
						case 64: marker.fill(255, 112, 99); break;
						case 128: marker.fill(255, 239, 98); break;
						case 256: marker.fill(201, 158, 255); break;
						default: marker.fill(((DrawingSurface) marker).getBgColor()); break;
					}
				}
				// dark mode
				else {
					switch (grid[i][j]) {
						case 2: marker.fill(254/2, 255/2, 229/2); break;
						case 4: marker.fill(255/2, 238/2, 188/2); break;
						case 8: marker.fill(255/2, 220/2, 117/2); break;
						case 16: marker.fill(252/2, 184/2, 83/2); break;
						case 32: marker.fill(252/2, 150/2, 103/2); break;
						case 64: marker.fill(255/2, 112/2, 99/2); break;
						case 128: marker.fill(255/2, 239/2, 98/2); break;
						case 256: marker.fill(201/2, 158/2, 255/2); break;
						default: marker.fill(((DrawingSurface) marker).getBgColor()); break;
					}
				}
				
				marker.rect(j*cellWidth, i*cellHeight, cellWidth, cellHeight);
				
				if (((DrawingSurface) marker).getBgColor() == 255) {
					marker.fill(0);
				} else {
					marker.fill(255);
				}
				
				marker.textSize(24);
				marker.color(255);
				if (grid[i][j] != 0) {
					marker.text(grid[i][j], j*cellWidth + cellWidth/2, i*cellHeight + cellHeight/2);
				}
				
				
			}
		}
		
		if (lost) {
			marker.textSize(72);
			if (((DrawingSurface) marker).getBgColor() == 255) {
				marker.fill(0);
			} else {
				marker.fill(255);
			}
			marker.text("GAME OVER", 250, 300);
		}
		
		
	}

	
	
}