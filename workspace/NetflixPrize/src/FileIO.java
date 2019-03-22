import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileIO {
	
	public static final String FILE_SEP = System.getProperty("file.separator");
	public static final String LINE_SEP = System.getProperty("line.separator");
	
	public static ArrayList<String> readFile(String fname, int offset) throws IOException {
		Scanner scan = null;
		try {
			scan = new Scanner(new FileReader(fname));
			ArrayList<String> lines = new ArrayList<>();
			
			// offsets file by reading lines
			for (int i = 0; i < offset; i++) {
				scan.nextLine();
			}
			
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
	
	public static ArrayList<String> readFile(String fname) throws IOException {
		return readFile(fname, 0);
	}
	
//	public static ArrayList<String[]> parseCSV (ArrayList<String> file) {
//		ArrayList<String[]> list = new ArrayList<String[]>();
////		
////		for (String line : file) {
////			list.add(line.split(","));
////		}
//		
//		for (int i = 1; i < file.size(); i++) {
//			list.add(file.get(i).split(","));
//		}
//		
//		return list;
//	}
	
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
