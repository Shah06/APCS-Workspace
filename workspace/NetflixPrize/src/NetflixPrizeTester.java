import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;


// TODO make a working/half decent UI

public class NetflixPrizeTester {
	
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
			JOptionPane.showMessageDialog(null, "Cannot load theme", "Warning", JOptionPane.WARNING_MESSAGE);
		}
		
		// GOAL TO PREDICT WHAT A USER WOULD RATE A MOVIE
		ArrayList<String> s = null;
		ArrayList<String[]> ratings = new ArrayList<String[]>();
		ArrayList<String[]> movies = new ArrayList<String[]>();
		
		try {
			s = FileIO.readFile("data" + FileIO.FILE_SEP + "ml-latest-small" + FileIO.FILE_SEP + "ratings.csv");
			ratings = FileIO.parseCSV(s);
			s = FileIO.readFile("data" + FileIO.FILE_SEP + "ml-latest-small" + FileIO.FILE_SEP + "movies.csv");
			movies = FileIO.parseCSV(s);
//			User user = new User(298182); // new user
//			user.loadData(ratings, movies);
//			float f = user.predictRating(179819); // The Last Jedi
//			System.out.println(f);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
//			if (null != s) {
//				for (String line : s) {
//					System.out.println(line);
//				}
//			}
//			
//			for (String[] line : ratings) {
//				System.out.println(Arrays.toString(line));
//			}
			System.out.println("exiting...");
		}
		
	}
	
}
