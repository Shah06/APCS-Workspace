import java.util.ArrayList;
import java.util.Arrays;

public class Movie {

	private String[] userTags = null;
	private String[] movieGenres = null;
	private String movieTitle = null;
	private int movieId;
	
	private float[] ratings;
	private float avgRating;
	
	public Movie(int movieId) {
		this.movieId = movieId;
		// update movieName
		// update tags
	}
	
	public Movie(int movieID, String movieTitle, String[] movieGenres) {
		this.movieId = movieID;
		this.movieTitle = movieTitle;
		this.movieGenres = movieGenres;
	}
	
	public float loadRatings(String fname) {
		// load ratings from ratings.csv
		// return average rating
		return 0f;
	}
	
	public String getTitle() {
		return new String(movieTitle);
	}
	
	public String toString() {
		//TODO
		return new String("Title: " + movieTitle + "\n"
				+ "movieId: " + movieId + "\n"
				+ "genres: " + Arrays.toString(movieGenres) + "\n"
				/*todo print user tags*/);
	}
	
}
