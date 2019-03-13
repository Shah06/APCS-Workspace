
/**
 * 
 * The Package class represents a package to be delivered. The class is capable of checking
 * the delivery status of the package based on its dimensions and weight.
 * 
 * @author john_shelby
 *
 */
public class Package {

	private double dim1, dim2, dim3, weight;
	
	public static final int ACCEPTABLE = 0, 
			TOO_LARGE = 1, 
			TOO_HEAVY = 2, 
			TOO_LARGE_AND_TOO_HEAVY = 3;
	
	
	/**
	 * Initializes a Package object with the passed arguments.
	 * 
	 * @param dim1 The first dimension of the package (must be > 0)
	 * @param dim2 The second dimension of the package (must be > 0)
	 * @param dim3 The third dimension of the package (must be > 0)
	 * @param weight The weight of the package (must be > 0)
	 */
	public Package(double weight, double dim1, double dim2, double dim3) {
		if (weight <= 0 || dim1 <= 0 || dim2 <= 0 || dim3 <= 0) {
			throw new IllegalArgumentException("All values must be positive and greater than zero.");
		}
		this.dim1 = dim1;
		this.dim2 = dim2;
		this.dim3 = dim3;
		this.weight = weight;
	}
	
	
	
	
	/**
	 * Checks the status of the Package and returns a code representing the outcome.
	 * 
	 * @return The status code. Will be either ACCEPTABLE, TOO_LARGE, TOO_HEAVY, or TOO_LARGE_AND_TOO_HEAVY.
	 */
	public int checkStatus() {
		
		int z = 0;
		double size;
		
		if (dim1 > dim2 && dim1 > dim3) {
			double girth = 2 * (dim2 + dim3);
			size = dim1 + girth;
		} else if (dim2 > dim1 && dim3 > dim2) {
			double girth = 2 * (dim1 + dim3);
			size = dim2 + girth;
		} else {
			double girth = 2 * (dim1 + dim2);
			size = dim3 + girth;
		}
		
		if (size > 100) {
			z++;
		}
		
		if (weight > 70) {
			z+=2;
		}
		
		return z;
	}
	
	
}
