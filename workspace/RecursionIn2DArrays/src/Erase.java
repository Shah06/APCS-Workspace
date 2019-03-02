import java.awt.Point;
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
	
	public char[][] getData() {
		// copy data over
		char[][] c = new char[rows][cols];
		for (int i = 0; i < c.length; i++) {
			for (int k = 0; k < c[i].length; k++) {
				c[i][k] = data[i][k];
			}
		}
		return c;
	}
	
	private int iterations = 0;
	public int getIterations() {
		return iterations;
	}
	
	public void solve (int x, int y) {
		char fg = data[x][y];
		char bg = ' ';
		if (fg == ' ') {
			bg = '*';
		}
		eraseObject(x, y, fg, bg);
	}
	
	// Erases an object beginning at x,y
	private void eraseObject(int x, int y, char fg, char bg) {
		
		iterations++;
		
		// for boundary checking
		boolean xInBounds = false;
		boolean yInBounds = false;
		xInBounds = (x >= 0) && (x < data.length);
		if (xInBounds) {
			yInBounds = (y >= 0) && (y < data[x].length);
		}
		
		// base case
		if (xInBounds && yInBounds && data[x][y] != fg) {
			return;
		}
		// recursive case
		else if (xInBounds && yInBounds) {
			data[x][y] = bg;
			
			for (int i = -1; i <= 1; i++) {
				for (int k = -1; k <= 1; k++) {
					if (i==0 && k==0) {
						continue;
					} else {
						eraseObject(x+i, y+k, fg, bg);
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
				s += data[k][i];
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
		solve(x, y);
	}

}