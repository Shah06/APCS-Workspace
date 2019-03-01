import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

/*

	This program erases objects (connected stars) from a 2D grid. 

	Coded by: 
	Modified on: 

*/

public class Erase {

	private static final int rows = 20;
	private static final int cols = 20;
	private char[][] data;

	// Constructs an empty grid
	public Erase() {
		data = new char[rows][cols];
	}

	// Constructs the grid defined in the file specified
	public Erase(String filename) {
		this();
		readData(filename, data);
	}
	
	private int iterations = 0;
	public int getIterations() {
		return iterations;
	}
	
	// Erases an object beginning at x,y
	public void eraseObject(int x, int y) {
		
		iterations++;
		
		// for boundary checking
		boolean xInBounds = false;
		boolean yInBounds = false;
		xInBounds = (x >= 0) && (x < data.length);
		if (xInBounds) {
			yInBounds = (y >= 0) && (y < data[x].length);
		}
		
		// base case
		if (xInBounds && yInBounds && data[x][y] != '*') {
			return;
		}
		// recursive case
		else if (xInBounds && yInBounds) {
			data[x][y] = ' ';
			
			for (int i = -1; i <= 1; i++) {
				for (int k = -1; k <= 1; k++) {
					if (i==0 && k==0) {
						continue;
					} else {
						eraseObject(x+i, y+k);
					}
				}
			}
			
		}
//		else {
//			//System.out.println("index error");
//		}
		
	}

	// Formats this grid as a String to be printed (one call to this method returns the whole multi-line grid)
	public String toString() {
		String s = new String();
		for (int i = 0; i < data.length; i++) {
			for (int k = 0; k < data[i].length; k++) {
				s += data[i][k];
			}
			s += '\n';
		}
		return s;
	}

	public void readData (String filename, char[][] gameData) {
		File dataFile = new File(filename);

		// if (dataFile.exists())
		if (dataFile.isFile()) {
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