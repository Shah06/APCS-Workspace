import java.io.FileReader;
import java.util.HashMap;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class MovieLensCSVTranslator {
	
	public Movie translateMovie(String line) {
		String[] splits = line.split(",", 2);
		int movieId = Integer.parseInt(splits[0]);
		int year;
		try {
			int yearIndex = splits[1].lastIndexOf("(");
			year = Integer.parseInt(splits[1].substring(yearIndex + 1, yearIndex + 5));
		} catch (Exception e) {
			year = -1; // code for invalid year
		}
		String movieTitle = splits[1].substring(0, splits[1].lastIndexOf(","));
		String[] movieGenres = splits[1].substring(splits[1].lastIndexOf(",") + 1).split("\\|");
		return new Movie(movieId, movieTitle, movieGenres, year);
	}
	
	public void translateLink(String line, ArrayList<Movie> movies) {
		// todo check imdb, etc.
	}
	
	// returns hashmap of userId and their ratings (int * 10)
	public HashMap<Integer, ArrayList<Integer>> getUserRatings(int uid, String fname) {
		HashMap<Integer, ArrayList<Integer>> userRatings = new HashMap<Integer, ArrayList<Integer>>();
		HashMap<Integer, ArrayList<Rating>> ratings = parseRatings(fname);
		for (Integer movie : ratings.keySet()) {
			// iterate through  movie->rating
			// if rating belongs to user, then add to hashmap
			for (Rating rating : ratings.get(movie)) {
				if (rating.getUser() == uid) {
					// if no key exists, make one, else add to arraylist
					if (userRatings.containsKey(uid)) {
						userRatings.get(uid).add(rating.getRatingInt());
					} else {
						ArrayList<Integer> uRating = new ArrayList<Integer>();
						uRating.add(rating.getRatingInt());
						userRatings.put(uid, uRating);
					}
				}
			}
		}
		return userRatings;
	}
	
	//Hashmap<movieId, ArrayList<Rating>>
	public HashMap<Integer, ArrayList<Rating>> parseRatings(String fname) {
		HashMap<Integer, ArrayList<Rating>> ratings = new HashMap<Integer, ArrayList<Rating>>();
		ArrayList<String> ratingsFile = null;
		try {
			ratingsFile = FileIO.readFile(fname, 1);
			String[] splits = null;
			for (String line : ratingsFile) {
				splits = line.split(",");
				int movieId = Integer.parseInt(splits[1]);
				float rating = Float.parseFloat(splits[2]);
				int userId = Integer.parseInt(splits[0]);	
				// if it already contains movie, add it to rating[] for that key
				if (ratings.containsKey(movieId)) {
					ratings.get(movieId).add(new Rating(rating, userId));
				} 
				// otherwise, create a new key
				else {
					ArrayList<Rating> r = new ArrayList<Rating>();
					r.add(new Rating(rating, userId));
					ratings.put(movieId, r);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ratings;
	}

}
