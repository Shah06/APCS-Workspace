/*
 * a. 6
 * b. 5
 * c. 2
 * d. 3
 */
public class TaxForm {
	
	private double income;
	private boolean isMarried;
	
	public TaxForm(double income, boolean isMarried) {
		if (income < 0) throw new IllegalArgumentException();
		this.income = income;
		this.isMarried = isMarried;
	}
	
	public double calcTax() {
		
		if (isMarried) {
			
			if (income < 19050) {
				return income * 0.1;
			}
			else if (income < 77400) {
				return 1905 + (0.12*(income-19050));
			}
			else if (income < 165000) {
				return 8907 + (0.22*(income-77400));
			}
			else if (income < 315000) {
				return 28179 + (0.24*(income-165000));
			}
			else if (income < 400000) {
				return 64179 + (0.32*(income-315000));
			}
			else if (income < 600000) {
				return 91379 + (0.35*(income-400000));
			}
			else {
				return 161379 + (0.37*(income-600000));
			}
			
		}
		
		else {
			if (income < 9525) {
				return income * 0.1;
			}
			else if (income < 38700) {
				return 952.50 + (0.12*(income-9525));
			}
			else if (income < 82500) {
				return 4453.5 + (0.22*(income-38700));
			}
			else if (income < 157500) {
				return 14089.5 + (0.24*(income-82500));
			}
			else if (income < 200000) {
				return 32089.50 + (0.32*(income-157500));
			}
			else if (income < 500000) {
				return 45689.5 + (0.35*(income-200000));
			}
			else {
				return 150689.5 + (0.37*(income-500000));
			}
		}
		
	}
	
	
}
