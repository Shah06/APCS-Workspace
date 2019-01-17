
public class Package {
	
	private double d1, d2, d3;
	private double weight;
	
	public Package (double d1, double d2, double d3, double w) {
		this.d1 = d1;
		this.d2 = d2;
		this.d3 = d3;
		this.weight = w;
	}
	
	public String checkmail() {
		
		double length;
		double girth;
		
		if (d1 > d2) {
			if (d1 > d3) {
				length = d1;
				girth = 2*(d2+d3);
			}
			else {
				length = d3;
				girth = 2*(d2+d1);
			}
		}
		else if (d2 > d3) {
			length = d2;
			girth = 2*(d1+d3);
		}
		else {
			length = d3;
			girth = 2*(d2+d1);
		}
		
		if ((length + girth) > 100) {
			if (weight > 70) {
				return new String("Package is too heavy");
			}
			return new String("Package is too large");
		}
		else {
			if (weight > 70) {
				return new String("Package is too heavy");
			}
			return new String("Package is acceptable");
		}
		
	}
	
}
