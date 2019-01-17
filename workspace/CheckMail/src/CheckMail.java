import javax.swing.JOptionPane;

public class CheckMail {
	
	public static void main(String[] args) {
		
		
		try {
			
			double d1 = Double.parseDouble(JOptionPane.showInputDialog("1st Dimension:"));
			double d2 = Double.parseDouble(JOptionPane.showInputDialog("2nd Dimension:"));
			double d3 = Double.parseDouble(JOptionPane.showInputDialog("3rd Dimension:"));
			double w = Double.parseDouble(JOptionPane.showInputDialog("What is the weight of your package?"));
			
			Package box = new Package(d1, d2, d3, w);
			
			JOptionPane.showMessageDialog(null, box.checkmail());
			
		} catch(Exception e) {System.out.println("something went wrong :( maybe you entered incorrect input?");}
		
		
		
	}
	
}
