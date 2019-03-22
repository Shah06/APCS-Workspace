import java.util.Arrays;

public class MovieLensCSVTranslator {
	
	public Movie translateMovie(String line) {
		String[] splits = line.split(",", 2);
		int movieId = Integer.parseInt(splits[0]);
		String movieTitle = splits[1].substring(0, splits[1].lastIndexOf(","));
		String[] movieGenres = splits[1].substring(splits[1].lastIndexOf(",") + 1).split("\\|");
		return new Movie(movieId, movieTitle, movieGenres);
	}
	
}
