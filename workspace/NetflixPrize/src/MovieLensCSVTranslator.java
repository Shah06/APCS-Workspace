import java.io.FileReader;
import java.util.HashMap;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

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
					ratings.get(movieId).add(new Rating(rating, userId, movieId));
				} 
				// otherwise, create a new key
				else {
					ArrayList<Rating> r = new ArrayList<Rating>();
					r.add(new Rating(rating, userId, movieId));
					ratings.put(movieId, r);
				}
			}
			
			// sort each ArrayList<Rating> in the hashmap
			for (Integer i : ratings.keySet()) {
				Collections.sort(ratings.get(i));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ratings;
	}

}
