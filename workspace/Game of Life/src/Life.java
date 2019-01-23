import java.awt.Point;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

import processing.core.PApplet;

/*

	Represents a Game Of Life grid.

	Coded by: Atharva Shah
	Modified on: 1/23/19

*/

public class Life {
	
	private boolean[][] grid;

	// Constructs an empty grid
	public Life() {
		grid = new boolean[20][20];
	}

	// Constructs the grid defined in the file specified
	public Life(String filename) {
		this();
		readData(filename, grid);
	}

	// Runs a single turn of the Game Of Life
	public void step() {
		boolean[][] nextGrid = new boolean[20][20]; // test if not broken by 2d array?
//		boolean[][] nextGrid = Arrays.copyOf(grid, grid.length); // <-- WHY IS THAT BROKEN????
		// iterates through grid
		
		for (int row = 0; row < grid.length; row++) {			
			for (int col = 0; col < grid[row].length; col++) {
				int neighbors = 0;
				
				// check top row
				if (row > 0) {
					if (col > 0) {
						if (grid[row-1][col-1]) neighbors++; // top left
					}
					if (grid[row-1][col]) neighbors++; // directly above
					if (col < grid[row].length-1) {
						if (grid[row-1][col+1]) neighbors++; // top right
					}
				}
				
				// check directly left and directly right
				if (col > 0) {
					if (grid[row][col-1]) neighbors++;
				}
				if (col < grid[row].length-1) {
					if (grid[row][col+1]) neighbors++;
				}
				
				// check bottom row
				if (row < grid.length-1) {
					if (col > 0) {
						if (grid[row+1][col-1]) neighbors++; // bottom left
					}
					if (grid[row+1][col]) neighbors++; // directly below
					if (col < grid[row].length-1) {
						if (grid[row+1][col+1]) neighbors++; // bottom right
					}
				}
				
				if (neighbors == 3) {
					nextGrid[row][col] = true;
				} else if (neighbors == 2) {
					nextGrid[row][col] = grid[row][col];
				} else {
					nextGrid[row][col] = false;
				}
				
				
			}
		}
		
		grid = nextGrid;
	}

	// Runs n turns of the Game Of Life
	public void step(int n) {
		for (int i = 0; i < n; i++) {
			step();
		}
	}
	
	// Formats this Life grid as a String to be printed (one call to this method returns the whole multi-line grid)
	public String toString() {
		String s = new String();
		
		for (int row = 0; row < grid.length; row++) {
			
			
			s = s + row + ": ";
			for (int col = 0; col < grid[row].length; col++) {
				
				if (grid[row][col] == true) {
					s = s + "*";
				} else {
					s = s + " ";
				}
			}
			s = s + "\n";
		}
		
		return s;
	}

	// Reads in array data from a simple text file containing asterisks (*)
	public void readData (String filename, boolean[][] gameData) {
		File dataFile = new File(filename);

		if (dataFile.exists()) {
			int count = 0;

			FileReader reader = null;
			Scanner in = null;
			try {
					reader = new FileReader(dataFile);
					in = new Scanner(reader);

					while (in.hasNext()) {
						String line = in.nextLine();
						for(int i = 0; i < line.length(); i++)
							if (count < gameData.length && i < gameData[count].length && line.charAt(i)=='*')
								gameData[count][i] = true;

						count++;
					}
			} catch (IOException ex) {
				throw new IllegalArgumentException("Data file " + filename + " cannot be read.");
			} finally {
				if (in != null)
					in.close();
			}
			
		} else {
			throw new IllegalArgumentException("Data file " + filename + " does not exist.");
		}
    }
	
	
	
	
	
	
	
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
		marker.rect(10, 10, 100, 100);
		float startX = x;
		float startY = y;
		float boxWidth = width/20;
		float boxHeight = height/20;
		
		for (int i = 0; i < grid.length; i ++) {
			for (int k = 0; k < grid[i].length; k++) {
				if (grid[i][k]) {
					marker.rect(startX, startY, boxWidth, boxHeight);
				}
				startX += boxWidth;
				startY += boxHeight;
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
		return null;
	}
	
	/**
	 * Optionally, complete this method to toggle a cell in the game of life grid
	 * between alive and dead.
	 * 
	 * @param i The x coordinate of the cell in the grid.
	 * @param j The y coordinate of the cell in the grid.
	 */
	public void toggleCell(int i, int j) {
	}

	
	
}