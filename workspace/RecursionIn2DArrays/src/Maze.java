import java.awt.Point;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;


/*

	Represents a 2D maze.

	Coded by:
	Modified on:

*/

public class Maze {

	private static final int rows = 20;
	private static final int cols = 20;
	
	private static final char CHARACTER = 'C';
	private static final char EXIT = 'X';
	private static final char WALL = '#';
	private static final char FLOOR = '.';
	private static final char MARKED = '!';

	private char[][] grid;

	// Constructs an empty grid
	public Maze() {
		grid = new char[rows][cols];
	}

	// Constructs the grid defined in the file specified
	public Maze(String filename) {
		this();
		readData(filename, grid);
	}

	public char[][] getData() {
		return grid;
	}
	
	// Attempts to find a path through the maze and returns whether a path was found or not
	public boolean solve() {
		// find where the CHARACTER is
		int x = 0;
		int y = 0;
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				if (grid[i][j] == CHARACTER) {
					x = i; y = j;
				}
			}
		}
		boolean result = solve(x, y);
		grid[x][y] = CHARACTER;
		return result;
	}
	
	public void printPath() {
		String s = new String();
		for (int i = 0; i < markedgrid.length; i++) {
			for (int k = 0; k < markedgrid[i].length; k++) {
				if (markedgrid[k][i]) {
					s += '*';
				} else {
					s += "-";
				}
			}
			s += '\n';
		}
		System.out.println(s);
	}
	
	private boolean[][] markedgrid = new boolean[20][20];
	// Private recursive version of solve()
	private boolean solve(int x, int y) {
		
		boolean xInBounds = (x >= 0) && (x < grid.length);
		if (!xInBounds) return false;
		boolean yInBounds = (y >= 0) && (y < grid.length);
		if (!yInBounds) return false;
		
		if (markedgrid[x][y]) {
			return false;
		} else if (grid[x][y] == EXIT) {
			return true;
		} else if (grid[x][y] == WALL) {
			return false;
		} else if (grid[x][y] == FLOOR || grid[x][y] == CHARACTER) {
			
			markedgrid[x][y] = true;
			
			for (int i = -1; i <= 1; i+=2) {
//				if (x+i != prevX) {
//					if (solve(x+i, y, x, y)) {
//						grid[x][y] = MARKED;
//						return true;
//					}
//				}
				if (solve(x+i, y)) {
					grid[x][y] = MARKED;
					return true;
				}
			}
			
			for (int i = -1; i <= 1; i+=2) {
				if (solve (x, y+i)) {
					grid[x][y] = MARKED;
					return true;
				}
			}
			
			return false;
			
		} else {
			return false;
		}
			
	}
	

	// Formats this grid as a String to be printed (one call to this method returns the whole multi-line grid)
	public String toString() {
		String s = new String();
		for (int i = 0; i < grid.length; i++) {
			for (int k = 0; k < grid[i].length; k++) {
				s += grid[k][i];
			}
			s += '\n';
		}
		return s;
	}

	public void readData (String filename, char[][] gameData) {
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
							if (i < gameData.length && count < gameData[i].length)
								gameData[i][count] = line.charAt(i);

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
	
	public Point clickToIndex(Point p, float x, float y, float width, float height) {
		int tempX = -1; // code that indicates not in the grid
		int tempY = -1; // code that indicates not in the grid
		for (int i = 0; i < width; i+=(width/20) ) {
			if (p.getX() >= (float)(i)+x && p.getX() < (float)(i)+(x+width/20)) {
				tempX = (int) (p.getX()/(width/20));
				break;
			}
		}
		
		for (int j = 0; j < height; j+=(height/20) ) {
			if (p.getY() >= (float)(j)+y && p.getY() < (float)(j)+(y+width/20)) {
				tempY= (int) (p.getY()/(height/20));
				break;
			}
		}
		
		if (tempX != -1 && tempY != -1) {
			return new Point(tempX, tempY);
		} return null;
		
	}
	
	public void toggleCell(int x, int y) {
		//TODO
//		solve(x, y);
	}

}