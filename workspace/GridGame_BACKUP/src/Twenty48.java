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

	
	// Constructs an empty grid
	public Twenty48() {
		grid = new int[4][4];
	}
	
	public boolean isOccupied(int x, int y) {
		if (0 == grid[x][y]) return false;
		return true;
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
			grid[x][y] = grid[(int) cell.getX()][(int) cell.getY()]; // double cell
			return true;
		}
		
		grid[x][y] = grid[(int) cell.getX()][(int) cell.getY()];
		return true;
		
	}
	
	// shifts cells up, if possible
	public void up() {
		
	}
	
	// shifts cells down, if possible
	public void down() {
		
	}
	
	// shifts cells right, if possible
	public void right() {
		
	}
	
	// shifts cells left, if possible
	public void left() {
		
	}
	
	/**
	 * generates a random 2048 grid
	 */
	public void generate() {
		
		// for now, fills grid with 2s
		for (int i = 0; i < grid.length; i++) {
			for (int k = 0; k < grid[i].length; k++) {
				System.out.println("" + i + "" + k);
				grid[i][k] = 2;
			}
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
				
				marker.text(grid[i][j], j*cellWidth + 20, i*cellHeight + 20);
				
				
			}
		}
		
		
	}

	
	
}