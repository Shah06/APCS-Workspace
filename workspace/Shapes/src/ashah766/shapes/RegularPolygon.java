package ashah766.shapes;

import processing.core.PApplet;
import java.util.ArrayList;

import ashah766.testers.Shapes;


/**
 * RegularPolygon can be used to model the properties and features of a regular polygon. Calculations such as area, perimeter, and angles are available, and the polygon can be drawn to a PApplet
 * @author Atharva Shah
 * @version 10-12-18
 */
public class RegularPolygon extends Shape2D {

	private int n;
	private double s;
	private ArrayList<Line> segments = new ArrayList<Line>();
	
	/**
	 * Draws a triangle with side length 100 with 250,250 as the centerpoint
	 */
	public RegularPolygon() {
		this(3,100D);
	}
	
	/**
	 * Creates a regular polygon with the first vertex at 250,250
	 * @param n number of sides. If n is negative, it draws the polygon counter-clockwise from the starting point
	 * @param s side length
	 * @pre absolute value of n should be greater than or equal to 3
	 */
	public RegularPolygon(int n, double s) {
		super(250,250); // default position is center
		if (Math.abs(n) < 3) {
			throw new IllegalArgumentException();
		}
		this.n = n;
		this.s = s;
		
		// iterates through for correct line coordinates
		double lastAngle = 0;
		final double CI = (Math.PI)-Math.toRadians(this.calcVertexAngle()); // increment constant
		for (int i = 0; i < Math.abs(n); i++) {
			if (i == 0) {
				segments.add(new Line(this.getX(), this.getY(), this.getX() + (s * Math.sin(lastAngle)), this.getY() + (s * Math.cos(lastAngle))));
			} else {
				segments.add(new Line(
						segments.get(i-1).getPoint2()[0], // previous x coordinate
						segments.get(i-1).getPoint2()[1], // previous y coordinate
						segments.get(i-1).getPoint2()[0] + (s * Math.sin(lastAngle)),
						segments.get(i-1).getPoint2()[1] + (s * Math.cos(lastAngle))
						));
			}
			lastAngle += CI;
		}
	}
	
	
	// METHODS
	
	/**
	 * Returns radius of inscribed circle
	 * @return double value representing radius
	 */
	public double calcr() {
		return 0.5*s*(1/(Math.tan(Math.PI/(double)n)));
	}
	
	/**
	 * Returns radius of circumscribed circle
	 * @return double value representing radius
	 */
	public double calcR() {
		return 0.5*s*(1/(Math.sin(Math.PI/(double)n)));
	}
	
	/**
	 * Calculate the the interior vertex angle between two sides. In a regular polygon, all vertex angles will be the same
	 * @return double value representing vertex angle.
	 */
	public double calcVertexAngle() {
		return (((double)n-2)/(double)n)*180D;
	}
	
	/**
	 * 
	 * @return number of sides
	 */
	public double getNumSides() {
		return n;
	}
	
	/**
	 * 
	 * @return length of each side
	 */
	public double getSideLength() {
		return s;
	}
	
	/**
	 * Method that draws two circles; one circumscribed around the polygon, and one inscribed inside.
	 * @param drawer PApplet to draw the bounding circles on
	 */
	public void drawBoundingCircles(PApplet drawer) {
	
		// TODO store these in an array
		new Circle(this.getCenterPoint()[0], this.getCenterPoint()[1], this.calcr()).draw(drawer);
		new Circle(this.getCenterPoint()[0], this.getCenterPoint()[1], this.calcR()).draw(drawer);
		
	}
	
	/**
	 * Method for finding the centerpoint
	 * @return centerpoint as a double array. First element is x-coordinate, second element is y-coordinate
	 */
	public double[] getCenterPoint() {
		return new double[] {
				this.getX() + this.calcR()*(Math.sin(Math.toRadians(this.calcVertexAngle()/2))),
				this.getY() + this.calcR()*(Math.cos(Math.toRadians(this.calcVertexAngle()/2)))
		};
	}
	
	@Override
	public double getArea() {
		return 0.5*n*(Math.pow(this.calcR(), 2))*Math.sin((2*Math.PI)/n);
	}

	@Override
	public double getPerimeter() {
		return (double)n*s;
	}

	/**
	 * Unusable method, will always return false
	 */
	@Override
	public boolean isPointInside(double x, double y) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void move(double x, double y) {
		for (int i = 0; i < segments.size(); i++) {
			segments.get(i).translate(x-this.getX(), y-this.getY());
		}
		this.setX1(x);
		this.setY1(y);
	}

	@Override
	public Rectangle getBounds() {
		return new Circle(this.getCenterPoint()[0], this.getCenterPoint()[1], this.calcR()).getBounds();
	}

	@Override
	public void draw(PApplet drawer) {
		// draws segments
		for (int k = 0; k < segments.size(); k++) {
			segments.get(k).draw(drawer);
			segments.get(k).setStyle(this.getWeight(), 0);
		}
	}

	@Override
	public void translate(double x, double y) {
		// TODO Auto-generated method stub
		this.setX1(this.getX() + x);
		this.setY1(this.getY() + y);
		for (int i = 0; i < segments.size(); i++) {
			segments.get(i).translate(x, y);
		}
	}

}
