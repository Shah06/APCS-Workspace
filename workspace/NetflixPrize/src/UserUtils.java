import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;


// important: this class is a STATE MACHINE; it does not model an actual user

public class UserUtils {
	
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
			ArrayList<Rating> mRatings = ratings.get(movie);
			// the following binary search doesn't need to sort; ratings is already sorted (see MovieLensCSVTranslator)
			int i = Collections.binarySearch(mRatings, new Rating(userId));
			if (i > -1) {
				moviesRated.put(mRatings.get(i).getMovieId(), mRatings.get(i).getRatingInt());
			}
			
//			for (Rating r : mRatings) {
//				// throw Movie, Rating*10 inside of moviesRated
//				// TODO get rid of this linear search
//				if (r.getUser() == userId) {
//					moviesRated.put(r.getMovieId(), r.getRatingInt());
//				}
//			}
		}
	}
	
	public static int uGetId() {
		return userId;
	}
	
	// return -1 if movie not yet rated
	public static float uGetRating(int movieId) {
		if (null == moviesRated) return -1f;
		if (moviesRated.containsKey(movieId)) {
			return ((float)moviesRated.get(movieId)) / 10f;
		}
		else {
			return -1f;
		}
	}
	
	public static float uGetAvgRating() {
		if (null == moviesRated) {
			throw new NullPointerException("please call uLoad() first");
		} else {
			float avg = 0;
			int i = 0;
			for (int k : moviesRated.keySet()) {
				avg += moviesRated.get(k);
				i++;
			}
			return avg/i;
		}
	}
	
	// gets average from an ArrayList<Integer> of movies
	public static float uGetAvgRating(ArrayList<Integer> movies) {
		float sum = 0;
		int n = 0;
		
		for (int m : movies) {
			// lookup in moviesRated
			sum += ((float)moviesRated.get(m) / 10f);
			n++;
		}
		
		return sum / n;
	}
	
}
