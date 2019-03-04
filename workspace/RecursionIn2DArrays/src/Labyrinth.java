import java.awt.Point;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

/*

	Coded by:
	Modified on:

*/

public class Labyrinth {

	private static final int rows = 20;
	private static final int cols = 20;
	private char[][] data;

	private static final char CHARACTER = 'C';
	private static final int EXIT = 'X';
	private static final int WALL = '#';
	private static final int MONSTER = 'A';
	private static final int CLOAK = '@';
	private static final int FLOOR = '.';
	
	// Constructs an empty grid
	public Labyrinth() {
		data = new char[rows][cols];
	}

	// Constructs the grid defined in the file specified
	public Labyrinth(String filename) {
		this();
		readData(filename, data);
	}
	
	public char[][] getData() {
		char[][] temp = new char[rows][cols];
		for (int i = 0; i < data.length; i++) {
			for (int j = 0; j < data.length; j++) {
				data[i][j] = temp[i][j];
			}
		}
		return temp;
	}

	// Finds a path through the labyrinth and returns the number of moves required to exit
	public int findPath() {
		//find character, then call findPath with false, 0
		int x = 0;
		int y = 0;
		for (int i = 0; i < data.length; i++) {
			for (int j = 0; j < data[i].length; j++) {
				if (data[i][j] == CHARACTER) {
					x = i; y = j;
				}
			}
		}
		return findPath(x, y, false, 0);
	}
	
	int iterations;
	// Private recursive version of findPath(), -1 is equivalent to false;
	private boolean[][] markedgrid = new boolean[rows][cols];
	private int findPath(int x, int y, boolean hasInvisibilityCloak, int moves) {
		iterations++;
		boolean xInBounds = (x >= 0) && (x < data.length);
		if (!xInBounds) return -1;
		boolean yInBounds = (y >= 0) && (y < data[x].length);
		if (!yInBounds) return -1;
		
		
		
		
		// base cast exit, wall, cloak, monster
		if (markedgrid[x][y]) return -1;
		if (data[x][y] == EXIT) return moves;
		if (data[x][y] == WALL) return -1;
		if (data[x][y] == CLOAK) {
			data[x][y] = FLOOR;
			// recursive call on same position with hasInvisibilityCloak set to true
		}
		if (data[x][y] == MONSTER && !hasInvisibilityCloak) {
			return -1;
		}
		
		else {
			markedgrid[x][y] = true;
			
			int[] sol = new int[4];
			
			
			// 4 recursive calls
			sol[0] = findPath(x+1, y, hasInvisibilityCloak, moves+1);
			if (-1 != sol[0]) {
				return sol[0];
			}
			
			sol[1] = findPath(x-1, y, hasInvisibilityCloak, moves+1);
			if (-1 != sol[1]) {
				return sol[1];
			}
			
			sol[2] = findPath(x, y+1, hasInvisibilityCloak, moves+1);
			if (-1 != sol[2]) {
				return sol[2];
			}
			
			sol[3] = findPath(x, y-1, hasInvisibilityCloak, moves+1);
			if (-1 != sol[3]) {
				return sol[3];
			}
			
			// return the smallest of the solutions, not including -1
			int lowestSol = 0;
			for (int i = 0; i < sol.length; i++) {
				if (0 == i) {
					lowestSol = i;
					continue;
				}
				if (sol[i] < lowestSol) {
					lowestSol = sol[i];
				}
			}
			
			return lowestSol;
			
		}
		
		
		
		
		
//		// base case exit
//		if (data[x][y] == EXIT) {
//			System.out.println("found the exit?/");
//			return moves;
//		}
//		
//		if (data[x][y] == WALL) {
//			return -1;
//		}
//		
//		if (data[x][y] == MONSTER && !hasInvisibilityCloak) {
//			System.out.println("found monster at "  + moves + " moves");
//			markedgrid[x][y] = true;
//			return -1;
//		}
//		
//		
//		// recursive case(s)
//		else {
//			if (markedgrid[x][y]) {
//				return -1;
//			}
//			// reset if cloak
//			if (data[x][y] == CLOAK) {
//				markedgrid = new boolean[20][20];
//				markedgrid[x][y] = true;
//				System.out.println("picked up the cloak at " + moves + " moves");
//				data[x][y] = '.';
//				return findPath(x, y, true, moves+1);
//			}
//			
//			
//			else {
//				System.out.println("currently at " + iterations);
//				if (hasInvisibilityCloak) {
//					System.out.println("i have the cloak");
//				}
//				markedgrid[x][y] = true;
//				int minMoves = moves;
//				System.out.println("minMoves is " + minMoves);
//				int tempMoves = -1;
//				tempMoves = findPath(x+1, y, hasInvisibilityCloak, moves+1);
//				if (tempMoves != -1 && tempMoves < minMoves) minMoves = tempMoves;
//				tempMoves = findPath(x-1, y, hasInvisibilityCloak, moves+1);
//				if (tempMoves != -1 && tempMoves < minMoves) minMoves = tempMoves;
//				tempMoves = findPath(x, y+1, hasInvisibilityCloak, moves+1);
//				if (tempMoves != -1 && tempMoves < minMoves) minMoves = tempMoves;
//				tempMoves = findPath(x, y-1, hasInvisibilityCloak, moves+1);
//				if (tempMoves != -1 && tempMoves < minMoves) minMoves = tempMoves;
//				System.out.println("about to return minMoves " + minMoves + " with tempMoves being " + tempMoves + " on iteration " + iterations);
//				// TODO fix
//				return minMoves;
//			}
			
		
	}

	// Formats this grid as a String to be printed (one call to this method returns the whole multi-line grid)
	public String toString() {
		String s = new String();
		for (int i = 0; i < data.length; i++) {
			for (int k = 0; k < data[i].length; k++) {
				s += data[k][i];
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

}