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
		
		MovieLensCSVTranslator translator = new MovieLensCSVTranslator();
		ArrayList<Movie> movies = new ArrayList<Movie>();
		
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
			for (Movie m : movies) {
				System.out.println(m.toString());
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			System.out.println("exiting...");
		}
		
		for (Movie m : movies) {
			System.out.println(m);
		}
		
	}
	
}
