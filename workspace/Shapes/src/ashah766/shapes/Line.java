package ashah766.shapes;
import processing.core.PApplet;

/**
 * Creates a line object that is able to detect collision with other lines and be rendered to a PApplet window
 * @author Atharva Shah
 * @version 10/5/18
 *
 */
public class Line extends Shape {
	
	private double x2, y2;
	private double intersection[] = new double[2];
	
	
	// CONSTRUCTORS
	
	/**
	 * Creates a line from 0, 0 to 0, 0. May appear as a point if rendered on a PApplet
	 */
	public Line() {
		this(0D, 0D, 0D, 0D);
	}
	
	/**
	 * Creates a line from x1, y1 to x2, y2
	 * @param x1 x-coordinate of first endpoint
	 * @param y1 y-coordinate of first endpoint
	 * @param x2 x-coordinate of second endpoint
	 * @param y2 y-coordinate of second endpoint
	 */
	public Line(double x1, double y1, double x2, double y2) {
		super(x1, y1);
		this.x2 = x2;
		this.y2 = y2;
	}
	
	
	// METHODS
	
	// inherits javadoc from Shape
	public void move(double x, double y) {
		setX1(x);
		setY1(y);
		this.x2 = x + (this.x2-this.getY());
		this.y2 = y + (this.y2-this.getY());
	}
	
	/**
	 * Used to find the intersection of Lines with different slopes (lines cannot be collinear)
	 * @return returns the intersection (x, y) in a double array
	 */
	public double[] getIntersection() {
		return intersection;
	}
	
	/**
	 * This function is normally used when the user drags and places the endpoint of a line in a PApplet window
	 * @param x2 sets the x-coordinate of the second endpoint
	 * @param y2 sets the y-coordinate of the second endpoint
	 */
	public void setPoint2(double x2, double y2) {
		this.x2 = x2;
		this.y2 = y2;
	}
	
	/**
	 * Function to draw the line to a window
	 * @param window a PApplet is necessary to render the Line. Make sure that the PApplet is NOT null
	 * @pre window != null
	 */
	public void draw (PApplet window) {
		window.pushStyle();
		window.strokeWeight(this.getWeight());
		window.stroke(this.getColor()[0], this.getColor()[1], this.getColor()[2]);
		window.line((float)x1, (float)y1, (float)x2, (float)y2);
		window.popStyle();
	}
	
	public double[] getLine() {
		return new double[] {x1, y1, x2, y2};
	}
	
	/**
	 * The intersects method can be used to find out if a Line intersects another Line
	 * @param other user must pass in a Line to check for intersection
	 * @return true if the lines intersect with one another, false otherwise
	 * @pre other != null
	 */
	public boolean intersects(Line other) {
		// points for two lines
		double x3 = other.getLine()[0];
		double y3 = other.getLine()[1];
		double x4 = other.getLine()[2];
		double y4 = other.getLine()[3];
		
		// minimum values
		double min_y1, max_y1;
		double min_y2, max_y2;
		double min_x1, max_x1;
		double min_x2, max_x2;
		
		// slopes and intercepts
		Double m1;
		Double m2;
		double b1;
		double b2;
		
		try {
			intersection[0] = ((x1*y2 - x2*y1)*(x3 - x4) - (x1 - x2)*(x3*y4 - y3*x4))/((x1 - x2)*(y3 - y4) - (y1 - y2)*(x3 - x4));
			intersection[1] = ((x1*y2 - x2*y1)*(y3 - y4) - (y1 - y2)*(x3*y4 - y3*x4))/((x1 - x2)*(y3 - y4) - (y1 - y2)*(x3 - x4));
			
			
			// test for lines with same equation (not parallel)
			m1 = new Double (y2-y1)/(x2-x1);
			m2 = new Double (y4-y3)/(x4-x3);
			//System.out.println("" + m1 + " " + m2);
			b1 = m1*(-x1) + y1;
			b2 = m2*(-x3) + y3;
			
			if (m1.equals(m2)) {
				// special case vertical parallel lines
				if (m1.isInfinite()) {
					if (x1 == x3) {
						return true;
					}
					return false;
				}
				if (b1 == b2) {
					// TODO check for collinear lines that don't intersect
					return true;
				}
			}
			
			
			// test for point that lies on a line
			if ((x1 == x2) && (y1 == y2)) {
				// both are points and are the same
				if ((x3 == x4) && (y3 == y4)) {
					if ((x1 == x3) && (y1 == y3)) {
						return true;
					}
				}
				
				// if point 1 lies on line 2
				if (((m2 * x1) + b2) == y1) {
					return true;
				}
				
			} else if ((x3 == x4) && (y3 == y4)) {
				if (((m1 + x3) + b1) == y3) {
					return true;
				}
				return false;
			}
			
			
			// intersection point must be in between ranges and domains of BOTH lines
			if (x1 < x2) {
				min_x1 = x1;
				max_x1 = x2;
			} else {
				min_x1 = x2;
				max_x1 = x1;
			}
			
			if (y1 < y2) {
				min_y1 = y1;
				max_y1 = y2;
			} else {
				min_y1 = y2;
				max_y1 = y1;
			}
			
			if (x3 < x4) {
				min_x2 = x3;
				max_x2 = x4;
			} else {
				min_x2 = x4;
				max_x2 = x3;
			}
			
			if (y3 < y4) {
				min_y2 = y3;
				max_y2 = y4;
			} else {
				min_y2 = y4;
				max_y2 = y3;
			}
			
			//System.out.print("intersection[0]: " + intersection[0]);
			//System.out.print("intersection[1]: " + intersection[1] + " ");
			
			// check for intersection
			if ((intersection[0] >= min_x1) && (intersection[0] <= max_x1) && (intersection[1] >= min_y1) && (intersection[1] <= max_y1)) {
				if ((intersection[0] >= min_x2) && (intersection[0] <= max_x2) && (intersection[1] >= min_y2) && (intersection[1] <= max_y2)) {
					//System.out.println("TRUE");
					return true;
				}
			}
			
			//System.out.println("FALSE");
			return false;
			
		} catch (Exception e) {
			System.out.println("the use case wasn't accounted for :(");
			return false;
		}
		
	}

	/**
	 * Useful method to get a x and y components around the Line
	 * @return Rectangle object with dimensions that wrap around the line
	 */
	public Rectangle getBounds() {
		double xMax, yMax, xMin, yMin;
		//xMax and yMax
		if (x1 > x2) {
			xMax = x1;
			xMin = x2;
		} else {
			xMax = x2;
			xMin = x1;
		}
		if (y1 > y2) {
			yMax = y1;
			yMin = y2;
		} else {
			yMax = y2;
			yMin = y1;
		}
		return (new Rectangle(xMin, yMin, xMax-xMin, yMax-yMin));
	}

	/**
	 * Useful method for translating the entire line by x,y
	 * @param x translates the entire line x units right
	 * @param y translates the entire line y units up (down when rendering on PApplet)
	 */
	public void translate(double x, double y) {
		this.setX1(this.getX() + x);
		this.setY1(this.getY() + y);
		x2 += x;
		y2 += y;
	}
	
	public double[] getPoint1() {
		return new double[] {this.getX(), this.getY()};
	}
	
	public double[] getPoint2() {
		return new double[] {x2, y2};
	}
	
}
