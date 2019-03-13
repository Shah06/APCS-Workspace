import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 * 
 * The U.S. post office has rules about mailing packages. A package cannot be mailed first class 
 * if the sum of its length and girth is greater than 100 inches, or if the package weighs more 
 * than 70 pounds.  The girth is the perimeter around the height and width, where the length is 
 * defined as the longest of the three dimensions.
 * 
 * Write a program that takes in the weight of the package and the three dimensions of the 
 * package in any order.  The program should determine the longest dimension of the package, 
 * calculate the girth, and compute the size of the box.  The program should then print out one 
 * of the following messages about this package:
 * 
 * 1.	Package is too large and too heavy.
 * 2.	Package is too large.
 * 3.	Package is too heavy.
 * 4.	Package is acceptable.
 * 
 * Test cases:
 * dim1 = 10, dim2 = 20, dim3 = 30, weight = 50:  ACCEPTABLE
 * dim1 = 25, dim2 = 20, dim3 = 30, weight = 50:  TOO_LARGE
 * dim1 = 40, dim2 = 15, dim3 = 15, weight = 75:  TOO_HEAVY
 * dim1 = 70, dim2 = 10, dim3 = 10, weight = 80:  TOO_LARGE_AND_TOO_HEAVY
 * 
 * @author john_shelby
 *
 */
public class CheckMail {

	public static void main(String[] args) {
		
		JPanel p = new JPanel(new BorderLayout(5,5));

        JPanel labels = new JPanel(new GridLayout(0,1,2,2));
        labels.add(new JLabel("Weight:", SwingConstants.RIGHT));
        labels.add(new JLabel("Dim1:", SwingConstants.RIGHT));
        labels.add(new JLabel("Dim2:", SwingConstants.RIGHT));
        labels.add(new JLabel("Dim3:", SwingConstants.RIGHT));
        p.add(labels, BorderLayout.WEST);

        JPanel controls = new JPanel(new GridLayout(0,1,2,2));
        JTextField weight = new JTextField();
        JTextField dim1 = new JTextField();
        JTextField dim2 = new JTextField();
        JTextField dim3 = new JTextField();
        controls.add(weight);
        controls.add(dim1);
        controls.add(dim2);
        controls.add(dim3);
        p.add(controls, BorderLayout.CENTER);

        
        double w = 0;
        double d1 = 0;
        double d2 = 0;
        double d3 = 0;
        String message;
        
       boolean legal = false;
       while (!legal) {
    	   JOptionPane.showMessageDialog(null, p, "Enter Package Properties", JOptionPane.QUESTION_MESSAGE);
           
    	   try {
        	   w = Double.parseDouble(weight.getText());
               d1 = Double.parseDouble(dim1.getText());
               d2 = Double.parseDouble(dim2.getText());
               d3 = Double.parseDouble(dim3.getText());
               Package pack = new Package(w,d1,d2,d3);
               int z = pack.checkStatus();
               if (z == Package.ACCEPTABLE) {
               	message = "Package is acceptable";
               } else if (z == Package.TOO_LARGE) {
               	message = "Package is too large";
               } else if (z == Package.TOO_HEAVY) {
               	message = "Package is too heavy";
               } else {
               	message = "Package is both too large and too heavy";
               }
               
               JOptionPane.showMessageDialog(null, message);
               legal = true;
           } catch (NumberFormatException e) {
        	   e.printStackTrace();
        	   message = "Please fill all fields with numbers";
        	   JOptionPane.showMessageDialog(null, message);
        	   legal = false;
           } catch (IllegalArgumentException e) {
        	   e.printStackTrace();
        	   message = "Please enter positive values";
        	   JOptionPane.showMessageDialog(null, message);
        	   legal = false;
           }
       }
        
        
        
		
	}

}
