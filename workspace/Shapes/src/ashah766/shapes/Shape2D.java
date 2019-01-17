package ashah766.shapes;

/**
 * Used as a parent class for creating 2 dimensional Shape Objects. Contains useful method for finding area, perimeter, finding if a point is inside a shape, and setting various style attributes
 * @author Atharva Shah
 * @version 10/5/18
 */
public abstract class Shape2D extends Shape {

	private boolean isFilled;
	
	/**
	 * Generates a Shape with x1, and y1 at x, y. This point may represent different parts of the Shape (eg upper-right corner or center)
	 * @param x x-coordinate of point
	 * @param y y-coordinate of point.
	 */
	public Shape2D(double x, double y) {
		super(x, y);
		isFilled = false;
	}
	
	/**
	 * Generates a Shape with the default x1 and y1 value of 0, 0
	 */
	public Shape2D() {
		this(0, 0);
		isFilled = false;
	}
	
	// METHODS
	
	/**
	 * Find the area of a Shape
	 * @return the area of the Shape, as a double
	 */
	public abstract double getArea();
	
	/**
	 * Find the perimeter of the Shape
	 * @return the perimeter of the Shape, as a double
	 */
	public abstract double getPerimeter();
	
	/**
	 * Used to find whether a point lies inside the Shape
	 * @param x x-coordinate of the point
	 * @param y y-coordinate of the point
	 * @return true if the point is in the Shape, false otherwise
	 */
	public abstract boolean isPointInside(double x, double y);
	
	/**
	 * Check if the Shape will be filled when rendered in a PApplet window
	 * @return true if filled, false if not filled
	 */
	public boolean getIsFilled() {
		return isFilled;
	}
	
	/**
	 * Sets the fill state of the Shape when it is rendered on a PApplet window
	 * @param isFilled true if filled, false if not filled
	 */
	public void setFill(boolean isFilled) {
		this.isFilled = isFilled;
		this.setStyle(this.getWeight(), this.getColor()[0], this.getColor()[1], this.getColor()[2]); // fills the object with its current colors
	}
	
	/**
	 * Changes the strokeWeight() and greyscale color value of the Shape
	 * @param weight sets the weight of the Shape, in pixels
	 * @param shade greyscale shade of the Shape from 0 (black) to 255 (white)
	 * @param isFilled true if filled, false if not filled
	 * @pre shade must be between 0 and 255, inclusively
	 */
	public void setStyle(int weight, int shade, boolean isFilled) {
		this.setWeight(weight);
		this.setColor(new int[] {shade, shade, shade});
		this.isFilled = isFilled;
	}
	
	/**
	 * Changes the strokeWeight() and greyscale color value of the Shape
	 * @param weight sets the weight of the Shape, in pixels
	 * @param shade greyscale shade of the Shape from 0 (black) to 255 (white)
	 * @pre shade must be between 0 and 255, inclusively
	 */
	public void setStyle(int weight, int shade) {
		this.setWeight(weight);
		this.setColor(new int[] {shade, shade, shade});
	}
	
}
