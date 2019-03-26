
public class Rating {
	private int user;
	private int rating; // stored as rating*10
	
	public Rating (float rating, int user) {
		this.rating = (int) (rating * 10);
		this.user = user;
	}
	
	public int getUser() {
		return user;
	}
	
	public float getRating() {
		return ((float)rating) / 10f;
	}
	
	// returns rating * 10 to save space
	public int getRatingInt() {
		return rating;
	}
}
