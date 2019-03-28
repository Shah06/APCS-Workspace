import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class TestFileCreator {

	public static void main(String[] args) throws IOException {
		
		String ratingsPath = "ml-latest-small" + FileIO.FILE_SEP + "ratings.csv";
		
		ArrayList<String> ratingsStrings = FileIO.readFile(ratingsPath);
		String firstLine = ratingsStrings.remove(0);
		
		Scanner kboard = new Scanner(System.in);
		System.out.println("How many records should be separated? (10000 is a typical test)");
		int num = kboard.nextInt();
		
		ArrayList<String> testFileString = new ArrayList<String>();
		for (int i = 0; i < num; i++) {
			int x = (int)(Math.random()*ratingsStrings.size());
			testFileString.add(ratingsStrings.remove(x));
		}
		
		ratingsStrings.add(0,firstLine);
		
		System.out.println("What do you want to call this batch of test files? (1 word name)");
		String name = kboard.next();
		
		String newRatingsFile = name+"Ratings.csv";
		String newTestsFile = name+"Tests.csv";
		
		FileIO.writeFile(newRatingsFile, ratingsStrings);
		FileIO.writeFile(newTestsFile, testFileString);
		
		System.out.println("Files written as " + newRatingsFile + " and " + newTestsFile + "!");
		
		

	}

}
