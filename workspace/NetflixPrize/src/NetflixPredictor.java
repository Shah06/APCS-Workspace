import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class NetflixPredictor {


	// Add fields to represent your database.
	private MovieLensCSVTranslator translator;
	private ArrayList<Movie> movies;
	private HashMap<Integer, ArrayList<Rating>> ratings;
	private HashMap<Integer, Movie> movieLookupTable;
	private final float gAvg = 3.50115f;
	
	/**
	 * 
	 * Use the file names to read all data into some local structures. 
	 * 
	 * @param movieFilePath The full path to the movies database.
	 * @param ratingFilePath The full path to the ratings database.
	 * @param tagFilePath The full path to the tags database.
	 * @param linkFilePath The full path to the links database.
	 */
	public NetflixPredictor (String movieFilePath, String ratingFilePath, String tagFilePath, String linkFilePath) {
		translator = new MovieLensCSVTranslator();
		movies = new ArrayList<Movie>();
		ratings = new HashMap<Integer, ArrayList<Rating>>(); // easily get ratings for movies
		movieLookupTable= new HashMap<Integer, Movie>(); // can lookup Movie object by Id quickly
		
		String moviesFile = movieFilePath;
		String ratingsFile = ratingFilePath;
		String tagsFile = tagFilePath;
		
//		String moviesFile = "data" + FileIO.FILE_SEP + "ml-latest-small" + FileIO.FILE_SEP + "movies.csv";
//		String ratingsFile = "data" + FileIO.FILE_SEP + "ml-latest-small" + FileIO.FILE_SEP + "ratings.csv";
//		String tagsFile = "data" + FileIO.FILE_SEP + "ml-latest-small" + FileIO.FILE_SEP + "tags.csv";
		
		ArrayList<String> movieStrings = null;
		
		try {
			movieStrings = FileIO.readFile(moviesFile, 1);
			for (String line : movieStrings) {
				Movie m = translator.translateMovie(line);
				movies.add(m);
			}
			
			// consolidate ratings into HashMap
			ratings = translator.parseRatings(ratingsFile);
			
			// loads ratings into movies
			for (Movie m : movies) {
				// populate movie lookup hashmap
				movieLookupTable.put(m.getId(), m);
				
				// populate the ratings for this movie
				m.setRatings(ratings);
//				System.out.println(m.toString());
//				System.out.println("Average rating: " + f);
//				m.printRatings();
//				System.out.println();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			System.out.println("exiting...");
		}
	}
		
	/**
	 * If userNumber has rated movieNumber, return the rating. Otherwise, return -1.
	 * 
	 * @param userNumber The ID of the user.
	 * @param movieNumber The ID of the movie.
	 * @return The rating that userNumber gave movieNumber, or -1 if the user does not exist in the database, the movie does not exist, or the movie has not been rated by this user.
	 */
	public double getRating(int userID, int movieID) {
		User.uSet(userID);
		User.uLoad(ratings);
		return User.uGetRating(movieID);
	}
	
	/**
	 * If userNumber has rated movieNumber, return the rating. Otherwise, use other available data to guess what this user would rate the movie.
	 * 
	 * @param userNumber The ID of the user.
	 * @param movieNumber The ID of the movie.
	 * @return The rating that userNumber gave movieNumber, or the best guess if the movie has not been rated by this user.
	 * @pre A user with id userID and a movie with id movieID exist in the database.
	 */
	public double guessRating(int userID, int movieID) {
		// check if user already rated movie
		User.uSet(userID);
		User.uLoad(ratings);
		float uRating = User.uGetRating(movieID);
		if (uRating < 0) {
			// lookup movie genres of specified movie
			String genres[] = movieLookupTable.get(movieID).getGenres();
			
			// loop through user rating file, check for genre
			float sumRating = 0;
			int n =0;
			HashMap<Integer, Integer> moviesRated = User.uGetRatings(); // get rating for that user
			for (Integer movie : moviesRated.keySet()) {
				// check if that movie has anything in genres[]
				String[] movieGenres = movieLookupTable.get(movie).getGenres();
				for (String movieGenre : movieGenres) {
					for (String genre : genres) {
						if (movieGenre.equals(genre)) {
							// one or more genres match, add weighting to rating
							sumRating += User.uGetRating(movie);
							n++;
						}
					}
				}
			}
			
			// user has rated items in the genre before
			if (0 != n) {
				// weight with movie average rating
				float mAvg = movieLookupTable.get(movieID).calcAvgRating();
				float div = mAvg/gAvg; // use this somehow
				float uAvg = sumRating / ((float)n);
				return uAvg;
			}
			
			// user does not have anything rated in the genre, return average movie rating
			return movieLookupTable.get(movieID).calcAvgRating();
			
		} else {
			return uRating;
		}
		
	}
	
	/**
	 * Recommend a movie that you think this user would enjoy (but they have not currently rated it). 
	 * 
	 * @param userNumber The ID of the user.
	 * @return The ID of a movie that data suggests this user would rate highly (but they haven't rated it currently).
	 * @pre A user with id userID exists in the database.
	 */
	public int recommendMovie(int userID) {

		return 0;
	}
	
}
