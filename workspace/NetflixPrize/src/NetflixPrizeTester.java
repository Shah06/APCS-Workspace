import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;


// TODO make a working/half decent UI

public class NetflixPrizeTester {
	
	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
//		try {
//			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
//		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
//			JOptionPane.showMessageDialog(null, "Cannot load theme", "Warning", JOptionPane.WARNING_MESSAGE);
//		}
		
		MovieLensCSVTranslator translator = new MovieLensCSVTranslator();
		ArrayList<Movie> movies = new ArrayList<Movie>();
		HashMap<Integer, ArrayList<Rating>> ratings = new HashMap<Integer, ArrayList<Rating>>();
		
		String moviesFile = "data" + FileIO.FILE_SEP + "ml-latest-small" + FileIO.FILE_SEP + "movies.csv";
		String ratingsFile = "data" + FileIO.FILE_SEP + "ml-latest-small" + FileIO.FILE_SEP + "ratings.csv";
		String tagsFile = "data" + FileIO.FILE_SEP + "ml-latest-small" + FileIO.FILE_SEP + "tags.csv";
		
		ArrayList<String> movieStrings = null;
		
		try {
			movieStrings = FileIO.readFile(moviesFile, 1);
			for (String line : movieStrings) {
				Movie m = translator.translateMovie(line);
				movies.add(m);
			}
			
			// consolidate ratings into HashMap
			ratings = translator.parseRatings(ratingsFile);
			
			// loads ratings into movies
			for (Movie m : movies) {
				m.setRatings(ratings);
				float f = m.calcAvgRating();
//				System.out.println(m.toString());
//				System.out.println("Average rating: " + f);
//				m.printRatings();
//				System.out.println();
			}
			
			// gets userRatings
			HashMap<Integer, ArrayList<Integer>> userRatings = new HashMap<Integer, ArrayList<Integer>>();
			// test load a user
			User user = new User(1);
//			user.loadRatings();
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			System.out.println("exiting...");
		}
		
		long endTime = System.currentTimeMillis();
		long spentTime = endTime - startTime;
		System.out.println("spend a total of " + spentTime/1000f + " seconds");
		
	}
	
}
