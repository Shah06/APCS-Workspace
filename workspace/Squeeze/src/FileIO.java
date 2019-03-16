import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileIO {
	
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
		FileWriter writer = new FileWriter(fname);
		for (int i = 0; i < fileData.size(); i++) {
			writer.write(fileData.get(i) + "\r\n");
		}
		writer.close();
	}
	
}
