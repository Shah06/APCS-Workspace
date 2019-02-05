

public class RecursionPractice {


	public static int factorial(int n)
	{
		if (n <= 1) {
			return n;
		} else {
			return n * factorial(n-1);
		}
	}


	public static int squareNumber(int n) {
		if (n == 1) {
			return 1;
		} else {
			return squareNumber(n-1) + 2*n - 1;
		}
	}

	
	// must be a valid base of 2, cannot be negative
	public static int logBase2(int n) {
		if (n==1) {
			return 0;
		} else {
			return 1 + logBase2(n/2);
		}
	}


	public static int pow(int n) {
		if (n == 0) {
			return 1;
		} else {
			return 2 * pow(n-1);
		}
	}


	public static int pentagonalNumber(int n) {
		if (n == 1) {
			return 1;
		} else {
			return 3*n - 2 + pentagonalNumber(n-1);
		}
	}





	public static void main(String[] args) {
		int n = 5;
		int test = factorial(n);
		System.out.println(n + " factorial is " + test);
		int s = 4;
		int test2 = pentagonalNumber(s);
		System.out.println(s + " pent is " + test2);
	}


}