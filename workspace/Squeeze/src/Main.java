import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Main {
	
	public static void main(String[] args) {
		
		
		boolean legal = false;
		
		while (!legal) {
			try {
			
				JFileChooser chooser = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter(
			        "TEXT FILES", "txt");
			    chooser.setFileFilter(filter);
			    int returnVal = chooser.showOpenDialog(null);
			    if(returnVal == JFileChooser.APPROVE_OPTION) {
			    	legal = true;
			    	// read the file out
			    	ArrayList<String> s = FileIO.readFile(chooser.getSelectedFile().getAbsolutePath());
			    	for (String string : s) {
			    		System.out.println(string);
			    	}
			    }
			    else if (returnVal == JFileChooser.CANCEL_OPTION) {
			    	legal = true;
			    }
		    
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "Please choose a valid file that you have permission to read/write. More info:\n" + Arrays.toString(e.getStackTrace()));
			}
			
			// add code to re-prompt user when exception is thrown
			// modify the strings in arrayList to meet squeeze lab goals
			// write out arraylist to file
			
		}
	    
	}
	
}
