import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileIO {
	
	public static final String FILE_SEP = System.getProperty("file.separator");
	public static final String LINE_SEP = System.getProperty("line.separator");
	
	public static ArrayList<String> readFile(String fname) throws IOException {
		Scanner scan = null;
		try {
			scan = new Scanner(new FileReader(fname));
			ArrayList<String> lines = new ArrayList<>();
			while (scan.hasNextLine()) {
				lines.add(scan.nextLine());
			}
			return lines;	
		} finally {
			if (null != scan) {
				scan.close();
			}
		}
	}
	
	public static void writeFile(String fname, ArrayList<String> fileData) throws IOException {
		FileWriter writer = null;
		try {
			writer = new FileWriter(fname);
			for (String line : fileData) {
				writer.write(line + LINE_SEP);
			}
		} catch (IOException e) {
			throw e;
		} finally {
			if (null != writer) {
				writer.close();
			}
		}
	}
	
}
