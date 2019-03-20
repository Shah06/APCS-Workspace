import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class NetflixPrizeTester {
	
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
			JOptionPane.showMessageDialog(null, "Cannot load theme", "Warning", JOptionPane.WARNING_MESSAGE);
		}
		
		// GOAL TO PREDICT WHAT A USER WOULD RATE A MOVIE
		ArrayList<String> s = null;
		
		try {
			s = FileIO.readFile("data" + FileIO.FILE_SEP + "ml-latest-small" + FileIO.FILE_SEP + "tags.csv");
		} catch (IOException e) {
			// do something
		} finally {
			if (null != s) {
				for (String line : s) {
					System.out.println(line);
				}
			}
		}
		
	}
	
}
