

public class User_OLD {
	
}







































//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.HashMap;
//
//public class User {
//	
//	private int userID;
//	ArrayList<String[]> ratings = null;
//	ArrayList<String[]> movies = null;
//	
//	public User (int userID) {
//		this.userID = userID;
//	}
//	
//	public void loadData(ArrayList<String[]> ratings, ArrayList<String[]> movies) {
//		if (null == ratings || null == movies) {
//			throw new NullPointerException("Parameters cannot be null.");
//		}
//		else {
//			this.ratings = ratings;
//			this.movies = movies;
//		}
//	}
//	
//	public float predictRating(int movieID) {
//		/*
//		 * ratings:
//		 * userId,movieId,rating,timestamp
//		 * 
//		 * movies:
//		 * movieId,title,genres
//		 */
//		
//		if (null == movies || null == ratings) {
//			throw new NullPointerException("Please ensure loadData() has been run");
//		}
//		
//		// hashmap of all movies and genres
//		HashMap<Integer, String[]> movies_and_genres = new HashMap<Integer, String[]>();
//		
//
//		
//		// load hashmap
//		String[] genres = null;
//		String[] movieGenres = null;
//		for (String[] movie : movies) {
//			int id = Integer.parseInt(movie[0]);
//			genres = (movie[2].split("\\|"));
//			if (id == movieID) {
//				movieGenres = movie[2].split("\\|");
//			}
//			movies_and_genres.put(id, genres);
//		}
//		
//		float total = 0;
//		int p = 0;
//		for (String[] rating : ratings) {
//			int uid = Integer.parseInt(rating[0]);
//			if (uid == userID) {
//				int mid = Integer.parseInt(rating[1]);
//				// if anything in movieGenres[] in movies_and_genres.get(mid)
//				for (String s : movies_and_genres.get(mid)) {
//					for (String k : movieGenres) {
//						if (s.equals(k)) {
//							total += Float.parseFloat(rating[2]);
//							p++;
//							break;
//						}
//					}
//				}
//				
//			}
//		}
//		
//		// user has not rated anything in the genre(s)
//		if (p == 0) {
//			System.out.println("user is new or has no applicable ratings");
//			// get average rating of ALL movies
//			float s = 0;
//			int n = 0;
//			float s_user = 0;
//			int n_user = 0;
//			float s_m = 0;
//			int n_m = 0;
//			for (String[] rating : ratings) {
//				float r = Float.parseFloat(rating[2]);
//				s += r;
//				n ++;
//				// if user has rated, add to weighted average
//				if (Integer.parseInt(rating[0]) == userID) {
//					s_user += r;
//					n_user ++;
//				}
//				// if its the movie, then add to movie avg score
//				if (Integer.parseInt(rating[1]) == movieID) {
//					s_m += r;
//					n_m ++;
//				}
//			}
//			float avg_global = s/n;
//			float avg_m = s_m/n_m;
////			System.out.println("Global avg: " + avg_global);
////			System.out.println("Movie avg: " + avg_m);
//			if (n_user == 0) {
//				return avg_m;
//			}
//			float avg_user = s_user/n_user;
////			System.out.println("User avg: " + avg_user);
//			return (avg_user * (avg_m/avg_global));
//			
//			// get average user ratings, if any
//		}
//		
//		// generate list of movies that the user has rated that have any of the genres??
//		System.out.println("user has rated movies for this genre(s) before");
//		return total/p;
//	}
//	
//}
