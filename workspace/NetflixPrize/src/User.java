import java.util.ArrayList;
import java.util.HashMap;

public class User {
	
	private static int userId;
	// movieId, rating *10
	private static HashMap<Integer, Integer> moviesRated; // list of movies user has rated, with their rating
	
//	public User(int userId) {
//		this.userId = userId;
//	}
	
	public static HashMap<Integer, Integer> uGetRatings() {
		return moviesRated;
	}
	
	public static void uSet(int uid) {
		userId = uid;
	}
	
	public static void uLoad(HashMap<Integer, ArrayList<Rating>> ratings) {
		// overwrite moviesRated each time
		moviesRated = new HashMap<Integer, Integer>();
		// for each movie in ratings
		for (Integer movie : ratings.keySet()) {
//			System.out.println("movie is " + movie);
			// for each rating in that movie
			for (Rating r : ratings.get(movie)) {
				// throw Movie, Rating*10 inside of moviesRated
				if (r.getUser() == userId) {
					moviesRated.put(r.getMovieId(), r.getRatingInt());
				}
			}
		}
	}
	
	public static int uGetId() {
		return userId;
	}
	
	// return -1 if movie not yet rated
	public static float uGetRating(int movieId) {
		if (moviesRated.containsKey(movieId)) {
			return ((float)moviesRated.get(movieId)) / 10f;
		}
		else {
			return -1f;
		}
	}
	
}
