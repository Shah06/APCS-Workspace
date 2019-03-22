
public class Movie {

	private String[] tags;
	private String movieTitle;
	private int movieId;
	
	private float[] ratings;
	private float avgRating;
	
	public Movie(int movieId) {
		this.movieId = movieId;
		// update movieName
		// update tags
	}
	
	public float loadRatings() {
		// load ratings from ratings.csv
		// return average rating
		return 0f;
	}
	
	public String getTitle() {
		return new String(movieTitle);
	}
	
	public String toString() {
		//TODO
		return "\0";
	}
	
}
