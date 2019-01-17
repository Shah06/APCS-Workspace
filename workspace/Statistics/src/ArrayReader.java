import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class reads an unknown number of integers from a given file, 
 * assuming that all numbers are separated by one of the Scanner class' 
 * default token characters.
 * 
 * @author john_shelby
 */
public class ArrayReader {
	
	private String filename;
	
	public ArrayReader(String filename) {
		this.filename = filename;
	}
	
	public int fillArray(int[] fill) {

		FileReader reader = null;
		int i = 0;
    	try {
    			reader = new FileReader(filename);
				Scanner in = new Scanner(reader);
				while (in.hasNext()) {
					String input = in.next();
					int value = Integer.parseInt(input);
					fill[i] = value;
					i++;
				}
    	} catch (IOException ex) {
    		System.out.println("File cannot be read.");
			return 0;
		} catch (NumberFormatException ex) {
			System.out.println("File is in the wrong format.");
			return 0;
		} catch (ArrayIndexOutOfBoundsException ex) {
			System.out.println("Array passed in was not large enough to hold the data. Length: " + fill.length);
			return 0;
		} finally {
			try {
				reader.close();
			} catch (IOException ex) {
	    		System.out.println("File cannot be closed.");
				return 0;
			}
		}
    	
    	return i;
	}
	
}
