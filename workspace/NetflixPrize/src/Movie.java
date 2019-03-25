import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Movie {

	private String[] userTags = null;
	private String[] movieGenres = null;
	private String movieTitle = null;
	private int movieId;
	private int year;
	private ArrayList<Integer> ratings;
	private float avgRating;
	
	public Movie(int movieID, String movieTitle, String[] movieGenres, int year) {
		this.movieId = movieID;
		this.movieTitle = movieTitle;
		this.movieGenres = movieGenres;
		this.year = year;
		ratings = new ArrayList<Integer>();
	}
	
	public float loadRatings(String fname) {
		// load ratings from ratings.csv
		// return average rating
		
		FileReader reader;
		
		try {
			reader = new FileReader("fname");
		} catch (something e)
	}
	
	public String getTitle() {
		return new String(movieTitle);
	}
	
	public String toString() {
		String yearDisplay = "" + year;
		//TODO
		if (-1 == year) {
			yearDisplay = "NO VALID YEAR";
		}
		return new String("Title: " + movieTitle + "\n"
				+ "movieId: " + movieId + "\n"
				+ "genres: " + Arrays.toString(movieGenres) + "\n"
				+ "year: " + yearDisplay + "\n"
				/*todo print user tags*/);
	}
	
}
