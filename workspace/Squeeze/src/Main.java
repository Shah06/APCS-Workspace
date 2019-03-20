import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Main {
	
	public static void main(String[] args) {
		
		
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
			JOptionPane.showMessageDialog(null, "Cannot load theme", "Warning", JOptionPane.WARNING_MESSAGE);
		}
		
		boolean legal = false;
		ArrayList<String> s = null;
		String fname = null;
		
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
			    	fname = chooser.getSelectedFile().getAbsolutePath();
			    	s = FileIO.readFile(fname);
			    	
			    	// modify arraylist
			    	if (null != s) {
						for (int i = 0; i < s.size(); i++) {
							// find number of whitespaces
							String str = s.get(i);
							int spaces = 0;
							while (str.length() > 0 && str.charAt(0) == ' ') {
								spaces++;
								str = str.substring(1, str.length());
							}
							str = "" + spaces + " " + str;
							s.set(i, str);
						}
					}
			    	
			    	
			    	// write to file
			    	FileIO.writeFile(fname, s); // hardcode filename if testing
					JOptionPane.showMessageDialog(null, "Success", "Success", JOptionPane.INFORMATION_MESSAGE);
			    }
			    else if (returnVal == JFileChooser.CANCEL_OPTION) {
			    	legal = true;
			    }
		    
			} catch (IOException e) {
				String trace = "";
				for (int i = 0; i < e.getStackTrace().length; i++) {
					trace += "        " + e.getStackTrace()[i] + "\n";
				}
				JOptionPane.showMessageDialog(null, "Please choose a valid file that you have permission to read/write. More info:\n" + trace);
				legal = false;
			}
			
			
		}
	    
	}
	
}
