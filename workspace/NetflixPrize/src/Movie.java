import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Movie implements Comparable<Movie> {

	private String[] userTags = null;
	private String[] movieGenres = null;
	private String movieTitle = null;
	private int movieId;
	private int year;
	private int[] ratings = null;
	private float avgRating;
	private String imdbID;
	private int numRatings = 0;
	
	public Movie(int movieId) {
		this.movieId = movieId;
	}
	
	public Movie(int movieId, String movieTitle, String[] movieGenres, int year) {
		this.movieId = movieId;
		this.movieTitle = movieTitle;
		this.movieGenres = movieGenres;
		this.year = year;
	}
	
	public int getNumRatings() {
		return numRatings;
	}
	
	public int getId() {
		return this.movieId;
	}
	
	// false means either ratingsMap is null or there are no ratings
	public boolean setRatings(HashMap<Integer, ArrayList<Rating>> ratingsMap) {
		if (null == ratingsMap) {
//			System.out.println("ratingsMap is null");
			return false;
		}
		ArrayList<Rating> m_ratings = ratingsMap.get(movieId);
		if (m_ratings == null || m_ratings.size() == 0) {
//			System.out.println("No ratings available for " + movieId);
			return false;
		}
		ratings = new int[m_ratings.size()];
		for (int i = 0; i < m_ratings.size(); i++) {
			numRatings++;
			ratings[i] = m_ratings.get(i).getRatingInt();
		}
		return true;
	}
	
	// returns -1f if no valid rating
	public float calcAvgRating() {
		if (null == ratings) return -1f;
		int sum = 0;
		int n = ratings.length;
		for (int i = 0; i < ratings.length; i++) {
			sum += ratings[i];
		}
		avgRating = ((float)sum) / (10f * (float)n);
		return avgRating;
	}
	
	// tester
	public void printRatings() {
		if (null == ratings) {
			System.out.println("no ratings for this movie");
			return;
		}
		System.out.println("printing ratings for movieId: " + movieId);
		for (int i : ratings) {
			float rating = ((float) i) / 10f;
			System.out.println(rating);
		}
	}
	
	public String getTitle() {
		return movieTitle;
	}
	
	public String toString() {
		String yearDisplay = "" + year;
		if (-1 == year) {
			yearDisplay = "NO VALID YEAR";
		}
		return new String("Title: " + movieTitle + "\n"
				+ "movieId: " + movieId + "\n"
				+ "genres: " + Arrays.toString(movieGenres) + "\n"
				+ "year: " + yearDisplay
				/*todo print user tags*/);
	}
	
	public String getImdbURL() {
		String s = "https://www.imdb.com/title/tt" + this.imdbID + "/";
		return s;
	}
	
	public void setImdbID(String s) {
		this.imdbID = s;
	}
	
	public String[] getGenres() {
		return this.movieGenres;
	}

	@Override
	public int compareTo(Movie arg0) {
		return this.getId() - arg0.getId();
	}
	
}
