import java.util.ArrayList;
import java.util.HashMap;

public class User {
	
	private int userId;
	private float[] moviesRated; // list of movies user has rated, with their rating
	
	public User(int userId) {
		this.userId = userId;
	}
	
	public void loadRatings(HashMap<Integer, ArrayList<Integer>> ratings) {
		moviesRated = new float[ratings.size()];
		for (Integer user : ratings.keySet()) {
			if (this.userId == user) {
				for (Integer rating : ratings.get(userId)) {
					
				}
			}
		}
	}
	
}
