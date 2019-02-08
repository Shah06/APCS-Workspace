

public class RecursionPractice {

	
	// data collection
	public static int iterations;
	
	

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
	
	public static int fib(int n) {
		iterations++; // for diagnostic purposes
		if (n == 0) return 0;
		else if (n == 1) return 1;
		else {
			return fib(n-1) + fib(n-2);
		}
	}
	
	public static int fib2(int n) {
		
		if (n == 0) {
			return 0;
		} else if (n == 1) {
			return 1;
		}
		
		int sum = 0;
		int n1 = 1; //F1 = 1
		int n2 = 0; //F0 = 0
		
		for (int i = 1; i < n; i++) {
			iterations++; // for diagnostic purposes
			sum = n1 + n2;
			n2 = n1;
			n1 = sum;
		}
		
		return sum;
	}





	public static void main(String[] args) {
//		int n = 5;
//		int test = factorial(n);
//		System.out.println(n + " factorial is " + test);
		
		
//		int s = 6;
//		int test2 = fib(s);
//		System.out.println(test2);
//		
//		int test3 = fib2(s);
//		System.out.println(test3);
		
		for (int i = 0; i <= 40; i++) {
			
			System.out.println("F(" + i + ")");
			
			iterations = 0;
			long start = System.currentTimeMillis();
			int test = fib(i);
			long end = System.currentTimeMillis();
			System.out.println("Recursively, the method took " + (end-start) + " milliseconds and " + iterations + " iterations.");
			
			iterations = 0;
			long start2 = System.currentTimeMillis();
			int test2 = fib2(i);
			long end2 = System.currentTimeMillis();
			System.out.println("Using a for-loop, the method took " + (end2-start2) + " milliseconds and " + iterations + " iterations.");

			System.out.println();
			
		}
		
				
	}


}