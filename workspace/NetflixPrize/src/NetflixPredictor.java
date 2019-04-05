import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class NetflixPredictor {

	// Add fields to represent your database.
	private MovieLensCSVTranslator translator;
	private HashMap<Integer, ArrayList<Rating>> ratings;
	private HashMap<Integer, User> users;
	private ArrayList<Movie> movies;
	private MappableDouble[][] cSimMatrix; // rename later
	private HashMap<Integer, Movie> movieLookupTable;
	private ArrayList<MVector> mvecs;
	
//	private MovieLensCSVTranslator translator;
//	private ArrayList<Movie> movies;
//	private HashMap<Integer, ArrayList<Rating>> ratings;
//	private HashMap<Integer, Movie> movieLookupTable;
//	private double[][] cSimMatrix; // userId x userId cosine similarity matrix, for now keep shaped as a square
//	private HashMap<Integer, MovieVector> mVectors; // hashmap of userId, movieVector
//	private int mVectorSize;
//	private final float gAvg = 3.50115f;
	
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
//		translator = new MovieLensCSVTranslator();
//		movies = new ArrayList<Movie>();
//		ratings = new HashMap<Integer, ArrayList<Rating>>(); // easily get ratings for movies
//		movieLookupTable= new HashMap<Integer, Movie>(); // can lookup Movie object by Id quickly
//		mVectors = new HashMap<Integer, MovieVector>();
//		
		String moviesFile = movieFilePath;
		String ratingsFile = ratingFilePath;
		String tagsFile = tagFilePath;
		String linksFile = linkFilePath;
		translator = new MovieLensCSVTranslator();
		movies = new ArrayList<Movie>();
		ratings = new HashMap<Integer, ArrayList<Rating>>();
		users = new HashMap<Integer, User>();
		movieLookupTable = new HashMap<Integer, Movie>();
		mvecs = new ArrayList<MVector>();
		
		try {
			
			// fill movies and ratings
			ArrayList<String> movieStrings;
			movieStrings = FileIO.readFile(moviesFile, 1);
			for (String line : movieStrings) {
				Movie m = translator.translateMovie(line);
				movies.add(m);
			}
			ratings = translator.parseRatings(ratingsFile);
			
			ArrayList<Integer> imdb_urls = new ArrayList<Integer>();
			
			// loads ratings into movies
			for (Movie m : movies) {
				movieLookupTable.put(m.getId(), m);
				m.setRatings(ratings);
			}
			
			// populates the user hashmap with hashmap of ratings
			for (Integer mRatingIndex : ratings.keySet()) {
				for (Rating r : ratings.get(mRatingIndex)) {
					if (!users.containsKey(r.getUser())) {
						User u = new User(r.getUser());
						u.uLoad(ratings);
						// fill movieVector
						MVector mvec = new MVector();
						for (int i = 0; i < movies.size(); i++) {
							if (u.getRating(movies.get(i).getId()) == -1) {
								mvec.addSpace();
							} else {
								mvec.nextOn();
							}
						}
						u.setMVec(mvec);
						mvecs.add(mvec);
						users.put(r.getUser(), u);
					} 
				}
			}
			
			// converts mvecs into cSimMatrix
			cSimMatrix = new MappableDouble[users.size()][users.size()];
//			int counter1 = -1;
//			for (Integer index1 : users.keySet()) {
//				counter1++;
//				int counter2 = -1;
//				for (Integer index2 : users.keySet()) {
//					counter2++;
//					double magicNumber = calcCosineSim(users.get(index1).getMVec(), users.get(index2).getMVec());
//					cSimMatrix[counter1][counter2] = new MappableDouble();
//					cSimMatrix[counter1][counter2].value = magicNumber;
//					cSimMatrix[counter1][counter2].pair = new Pair(users.get(index1), users.get(index2));
//				}
//			}
			
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			System.out.println("exiting...");
		}
	}
	
	private double calcCosineSim(MVector v1, MVector v2) {
		
		// find magnitudes of both vectors
		int v1_sum = 0;
		for (int i = 0; i < v1.size(); i++) {
			v1_sum += v1.get(i);
		}
		double v1_magnitude = Math.sqrt(v1_sum);
		
		int v2_sum = 0;
		for (int k = 0; k < v2.size(); k++) {
			v2_sum += v2.get(k);
		}
		double v2_magnitude = Math.sqrt(v2_sum);
		
		// find the dotproduct of both vectors
		double dotprod = 0;
		for (int j = 0; j < v1.size(); j++) {
			dotprod += (v1.get(j) * v2.get(j));
		}
		
		// return the arccosine of the angle between them
		return Math.acos((dotprod)/(v1_magnitude * v2_magnitude));
		
	}
		
	/**
	 * If userNumber has rated movieNumber, return the rating. Otherwise, return -1.
	 * 
	 * @param userNumber The ID of the user.
	 * @param movieNumber The ID of the movie.
	 * @return The rating that userNumber gave movieNumber, or -1 if the user does not exist in the database, the movie does not exist, or the movie has not been rated by this user.
	 */
	public double getRating(int userID, int movieID) {
		User u = users.get(userID);
		if (null == u) return -1;
		return u.getRating(movieID);
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
		
		User u = users.get(userID);
		float uRating = u.getRating(movieID);
		if (uRating < 0) {
			// lookup movie genres of specified movie
			String genres[] = movieLookupTable.get(movieID).getGenres();
			
			// loop through user rating file, check for genre
			float sumRating = 0;
			int n =0;
			HashMap<Integer, Integer> moviesRated = u.getRatings(); // get rating for that user
			for (Integer movie : moviesRated.keySet()) {
				// check if that movie has anything in genres[]
				String[] movieGenres = movieLookupTable.get(movie).getGenres();
				for (String movieGenre : movieGenres) {
					for (String genre : genres) {
						// get rid of linear search?? String??
						if (movieGenre.equals(genre)) {
							// one or more genres match, add weighting to rating
							sumRating += u.getRating(movie);
							n++;
						}
					}
				}
			}
			
			// weight with movie average rating
			float mAvg = movieLookupTable.get(movieID).calcAvgRating(); // weight: 30
			float uAvg = sumRating / ((float)n); // weight: 70
		
			
			// user has rated items in the genre before
			if (0 != n) {
				// new alg; checks for user similarity
				float weightedAvg;
				// check for outliers
				if (Math.abs(mAvg - uAvg) > 2) {
					// weight closer to user
					weightedAvg = (0.9f*uAvg) + (0.1f*mAvg);
				} else {
					weightedAvg = (0.8f*uAvg) + (0.2f*mAvg);
				}
				return weightedAvg;
			}
			
			// user does not have anything rated in the genre, return average movie rating weighted with user average rating
			Collection<Integer> ratings = moviesRated.values(); // get rating for that user
			uAvg = 0;
			int iterations = 0;
			for (int rating : ratings) {
				uAvg += rating;
				iterations++;
			}
			uAvg /= (iterations*10); // accounts for the fact that ratings is populated w/Integers
			mAvg = movieLookupTable.get(movieID).calcAvgRating();
			return ((0.9*uAvg) + (0.1*mAvg)) * 0.9;
			
		} else {
			return uRating; // user has already rated the movie
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
		
		// fix this somehow
		
		User u = null;
		
//		int index1 = users.get(userID).getPos();
		int index1 = userID-1;
		// get this user's cosine similarity array
		MappableDouble[] cSimU = cSimMatrix[index1];
		
		// find the lowest value and position of the value associated with that
		MappableDouble lowest = new MappableDouble(); // garbage value
		lowest.value = 30; // garbage for now
		
		// sets lowest to lowest value
		for(int i = 0; i < cSimU.length; i++) {
			if (i == index1) continue;
			if (cSimU[i].value < lowest.value) {
				lowest = cSimU[i];
			}
		}
		
		//find user with position [index1, k] and set to user
		for (int k = 0; k < cSimMatrix.length; k++) {
			Pair p = lowest.pair;
			if (p.getFront().getUserID() == userID) {
				u = p.getBack();
			} else {
				u = p.getFront();
			}
		}
		
		
		// go through user's ratings, return highest one
		if (null != u) {
			HashMap<Integer, Integer> uRatings = u.getRatings();
			int highestRating = 0;
			int mid = 0;
			for (Integer i : uRatings.keySet()) {
				// if user already rated movie prior to this
				if (users.get(userID).getRating(i) != -1) break;
				if (uRatings.get(i) > highestRating) {
					highestRating = uRatings.get(i);
					mid = i;
				}
			}
			return mid;
		} 
		
		return -1;
		
	}
	
	public ArrayList<Movie> getMovies() {
		return movies;
	}
	
}
