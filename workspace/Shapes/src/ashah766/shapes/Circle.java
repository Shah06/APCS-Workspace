package ashah766.shapes;
import processing.core.PApplet;
import java.lang.Math;

/**
 * Creates a circle that can do basic geometric calculations such as circumference and area. The circle can also be drawn on a PApplet
 * @author Atharva Shah
 * @version 10/5/18
 *
 */
public class Circle extends Shape2D {
	
	private double r; // radius
	
	/**
	 * Creates circle at 0,0 with radius of 0
	 */
	public Circle() {
		super();
	}
	
	/**
	 * Creates a circle with centerpoint of x, y, with radius of r
	 * @param x x-coordinate of circle's center
	 * @param y y-coordinate of circle's center
	 * @param r radius of circle
	 */
	public Circle(double x, double y, double r) {
		super(x, y);
		this.r = r;
	}
	
	// inherits javadoc
	public void move(double x, double y) {
		setX1(x);
		setY1(x);
	}
	
	/**
	 * Method for finding the circumference of the circle
	 * @return circumference of the circle with double precision
	 */
	public double getPerimeter() {
		double c = (2*r*Math.PI);
		return c;
	}
	
	/**
	 * Method for finding the area of a circle
	 * @return area of the circle with double precision
	 */
	public double getArea() {
		double a = ((r*r)*Math.PI);
		return a;
	}
	
	/**
	 * Draws the circle inside a PApplet window
	 * @param marker PApplet to draw on. Note that the y-value will be inverted, meaning a point on (x, y) will be on (x, -y), and the origin is located at the upper left corner of the window.
	 * @pre marker != null
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
		marker.ellipse((float)x1, (float)y1, (float)r*2,(float) r*2);
		marker.popStyle();
	}

	/**
	 * Useful method for finding whether a point x,y lies inside a circle
	 * @param x x-coordinate of point
	 * @param y y-coordinate of point
	 * @return true if the point x,y is inside the circle, false if not inside
	 */
	public boolean isPointInside(double x, double y) {
		double d = Math.hypot(this.x1-x, this.y1-y); //distance from center
		if ((Math.abs(d) - r) < 0.01) {
			return true;
		}
		return false;
	}

	/**
	 * Useful method to get a x and y components around the Line
	 * @return Rectangle object with dimensions that wrap around the line
	 */
	public Rectangle getBounds() {
		return(new Rectangle(x1-r, y1-r, 2*r, 2*r));
	}

	/**
	 * Useful method for translating the entire circle by x,y
	 * @param x amount to move circle right by
	 * @param y amount to move circle up by (down when rendered on PApplet
	 */
	public void translate(double x, double y) {
		this.x1 += x;
		this.y1 += y;
	}
	
}
