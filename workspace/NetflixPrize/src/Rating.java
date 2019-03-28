
public class Rating {
	private int user;
	private int rating; // stored as rating*10
	private int movieId;
	
	public Rating (float rating, int user, int movieId) {
		this.rating = (int) (rating * 10);
		this.user = user;
		this.movieId = movieId;
	}
	
	public int getUser() {
		return user;
	}
	
	public int getMovieId() {
		return movieId;
	}
	
	public float getRating() {
		return ((float)rating) / 10f;
	}
	
	// returns rating * 10 to save space
	public int getRatingInt() {
		return rating;
	}
}
