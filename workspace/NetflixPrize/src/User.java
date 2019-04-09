import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class User {
	
	private int userID;
	private String[] genres;
	private HashMap<Integer, Integer> ratings; //movieId, rating*10
	private MVector mvec;
	private static int pos = -1;
	
	public User(int userID) {
		this.userID = userID;
		ratings = new HashMap<Integer, Integer>();
		pos++;
	}
	
	public int getPos() {
		return pos;
	}
	
	public int getUserID() {
		return this.userID;
	}
	
	public void setGenres(String[] genres) {
		this.genres = genres;
	}
	
	public void setMVec(MVector mvec) {
		this.mvec = mvec;
	}
	
	public float getRating(int movieID) {
		if (null == ratings.get(movieID)) return -1;
		if (ratings.containsKey(movieID)) {
			return (float)ratings.get(movieID) / 10f;
		}
		return -1f;
	}
	
	public String[] getGenres() {
		return this.genres;
	}
	
	public MVector getMVec() {
		return this.mvec;
	}
	
	public float uGetAvgRating() {
		if (null == ratings) {
			throw new NullPointerException("please call uLoad() first");
		} else {
			float avg = 0;
			int i = 0;
			for (int k : ratings.keySet()) {
				avg += ratings.get(k);
				i++;
			}
			return avg/i;
		}
	}
	
	// gets average from an ArrayList<Integer> of movies
	public float uGetAvgRating(ArrayList<Integer> movies) {
		float sum = 0;
		int n = 0;
		
		for (int m : movies) {
			// lookup in ratings
			sum += ((float)ratings.get(m) / 10f);
			n++;
		}
		
		return sum / n;
	}
	
	public HashMap<Integer, Integer> getRatings() {
		return ratings;
	}
	
	public void uLoad(HashMap<Integer, ArrayList<Rating>> oRatings) {
		// for each movie in oRatings
		for (Integer movie : oRatings.keySet()) {
//			System.out.println("movie is " + movie);
			// for each rating in that movie
			ArrayList<Rating> mRatings = oRatings.get(movie);
			// the following binary search doesn't need to sort; ratings is already sorted (see MovieLensCSVTranslator)
			int i = Collections.binarySearch(mRatings, new Rating(userID));
			if (i > -1) {
				ratings.put(mRatings.get(i).getMovieId(), mRatings.get(i).getRatingInt());
			}
		}
	}
	
}
