package ashah766.shapes;
import processing.core.PApplet;

/**
 * Creates a Rectangle with double precision. Basic geometric calculations including perimeter and area is available. The Rectangle can also be drawn on a PApplet
 * @author ashah766
 * @version 10/5/18
 */
public class Rectangle extends Shape2D {
	
	private double width;
	private double height;
	
	/**
	 * Creates a blank rectangle with width and height of 0, at 0,0
	 */
	public Rectangle() {
		super();
	}
	
	/**
	 * Creates a rectangle that can be drawn to a PApplet
	 * @param x x-coordinate of rectangle's upper left corner
	 * @param y y-coordinate of rectangle's upper left corner
	 * @param width width of rectangle
	 * @param height height of rectangle
	 */
	public Rectangle(double x, double y, double width, double height) {
		super(x, y);
		this.width = width;
		this.height = height;
	}
	
	// inherits javadoc
	public void move(double x, double y) {
		setX1(x);
		setY1(y);
	}
	
	/**
	 * 
	 * @return perimeter of the rectangle
	 */
	public double getPerimeter() {
		double p = (2*width) + (2*height);
		return p;
	}
	
	/**
	 * 
	 * @return area of the rectangle
	 */
	public double getArea() {
		double a = (width*height);
		return a;
	}
	
	/**
	 * 
	 * @param x x value of the coordinate
	 * @param y y value of the coordinate
	 * @return true if a point x, y is inside the rectangle, false otherwise
	 */
	public boolean isPointInside(double x, double y) {
		// find out xMax, xMin
		double xMax, xMin, yMax, yMin;
		if (this.x1 < (this.width + this.x1)) {
			xMin = this.x1;
			xMax = this.width + this.x1;
		} else {
			xMax = this.x1;
			xMin = this.width + this.x1;
		}
		if (this.y1 < (this.y1 + this.height)) {
			yMin = this.y1;
			yMax = this.height + this.y1;
		} else {
			yMax = this.y1;
			yMin = this.height + this.y1;
		}
		
		// calculates if inside
		if ((yMin < y) && (y < yMax)) {
			if ((xMin < x) && (x < xMax)) {
				return true;
			}
		}
		return false;
		
	}
	
	/**
	 * Flips the height and width of the rectangle. The upper-left corner remains the same.
	 */
	public void flip() {
		double f = width;
		width = height;
		height = f;
		System.out.println("" + this.height + this.width);
	}
	
	/**
	 * 
	 * @param s scales the width and height of the rectangle by s amount.
	 */
	public void scale(double s) {
		width *= s;
		height *= s;
	}
	
	/**
	 * 
	 * @param x moves the rectangle x units to the left
	 * @param y moves the rectangle y units up
	 */
	public void translate(double x, double y) {
		x1 += x;
		y1 += y;
	}
	
	/**
	 * Draws the rectangle inside a PApplet window
	 * @param marker PApplet to draw on. Note that the y-value will be inverted, meaning a point on (x, y) will be on (x, -y), and the origin is located at the upper left corner of the window.
	 * @pre marker != null
	 * @post marker will have its stroke attribute set to 0
	 */
	public void draw(PApplet marker) {
		marker.pushStyle();
		marker.strokeWeight(getWeight());
		marker.stroke(getColor()[0], getColor()[1], getColor()[2]);
		if (getIsFilled()) {
			marker.fill(getColor()[0], getColor()[1], getColor()[2]);
		} else {
			marker.noFill();
		}
		marker.rect((float)x1, (float)y1, (float)width, (float)height);
		marker.popStyle();
	}

	/**
	 * Useless method for finding the rectangle around a rectangle. Don't use this please
	 * @return returns an unnecessary Rectangle object with the exact same everything as this Rectangle.
	 */
	public Rectangle getBounds() {
		return this;
	}
	
}
