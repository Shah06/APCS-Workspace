import java.util.Scanner;
import java.text.NumberFormat;
import javax.swing.*;
import java.text.DecimalFormat;

/**
 *  a.	$50,000		Married		$5619
	b.	$25,000		Single		$2809.50
	c.	$300,000	Married		$60579
	d.	$170,000	Single		__________
	e.	$30,000		Married		__________
	f.	$500,000	Single		__________
	g.	$170,000	Married		__________
	h.	$45,000		Single		__________
	i.	$130,000	Married		$20479
	j.	$120,000	Single		__________

 * @author ashah766
 *
 */

// TODO NumberFormat
public class IRS {
	
	public static void main(String[] args) {
		
		try { // try-catch because clicking cancel would result in a null-pointer
			boolean isMarried;
			String[] choices = {"Single", "Married"};
			String input = (String) JOptionPane.showInputDialog(null, "Filing status: ",
				 "Tax Calculator", JOptionPane.QUESTION_MESSAGE, null,
				 choices,
				 choices[0]);
			if (input.equals("Single")) {
				isMarried = false;
			} else {
				isMarried = true;
			}
			double income;
			TaxForm f;
			try {
				income = Double.parseDouble(JOptionPane.showInputDialog(null, "Enter salary (number only, no commas)"));
				f = new TaxForm(income, isMarried);
				JOptionPane.showMessageDialog(null, "Tax is $" + String.format("%.2f", f.calcTax()));
			} catch(Exception e) {System.err.println("" + e);}
		} catch (Exception e) {}
		
	}
	
}
