import processing.core.PApplet;

public class Mandelbrot {
	
	/*
	 * pseudocode from wikipedia:
	 * 
	 * For each pixel (Px, Py) on the screen, do:
		{
		  x0 = scaled x coordinate of pixel (scaled to lie in the Mandelbrot X scale (-2.5, 1))
		  y0 = scaled y coordinate of pixel (scaled to lie in the Mandelbrot Y scale (-1, 1))
		  x = 0.0
		  y = 0.0
		  iteration = 0
		  max_iteration = 1000
		  while (x*x + y*y <= 2*2  AND  iteration < max_iteration) {
		    xtemp = x*x - y*y + x0
		    y = 2*x*y + y0
		    x = xtemp
		    iteration = iteration + 1
		  }
		  color = palette[iteration]
		  plot(Px, Py, color)
		}
	 */
	
	// Z(n+1) = Z(n)^2 + c;
	// use this: https://web.stanford.edu/class/archive/cs/cs106b/cs106b.1178//yeah/fractals/yeah_fractals.pdf
	
	private int winHeight;
	private int winWidth;
	
	public void draw(PApplet marker) {
		winHeight = marker.height;
		winWidth = marker.width;
		// call to render
	}
	
	int mandelbrot(int z, Complex c) {
		//base case 1
		if (z == 0) {
			return 0;
		}
		//base case 2
		else if (z == 1) {
			return c; //????????? figure this out??
		}
		
		//recursive case
		else {
			return mandelbrot((z-1)*(z-1), cA, cB);
		}
	}
	
	private class Complex {
		private double realPart;
		private double imagPart;
		
		public Complex(double a, double b) {
			realPart = a;
			imagPart = b;
		}
		
		public double abs() {
			// TODO distance between origin and point in the complex plane
			return Math.sqrt(realPart*realPart + imagPart*imagPart);
		}
		
		public double realPart() {
			return realPart;
		}
		
		public double imagPart() {
			return imagPart;
		}
		
		public Complex add(Complex other) {
			return new Complex(this.realPart + other.realPart,
					this.imagPart + other.imagPart);
		}
	}
	
}
