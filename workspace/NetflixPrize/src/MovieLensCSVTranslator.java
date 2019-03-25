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
	
	
	public int[] translateRating(String line) {
		String[] splits = line.split(",", 4);
		int movieId = Integer.parseInt(splits[1]);
		int rating = (int) (Float.parseFloat(splits[2]) * 10);
		return new int[] {movieId, rating};
	}
}
