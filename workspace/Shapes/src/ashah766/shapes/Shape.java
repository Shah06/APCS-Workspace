package ashah766.shapes;
import processing.core.*;

/**
 * Shape is a parent class that contains useful methods.
 * @author Atharva Shah
 * @version 10/5/18
 */
public abstract class Shape {
	
	private int weight;
	private int[] color;
	/**
	 * the first x coordinate of the Shape
	 */
	protected double x1;
	/**
	 * the first y coordinate of the Shape
	 */
	protected double y1;
	
	
	// CONSTRUCTORS
	
	/**
	 * Generates a Shape with the default x1 and y1 value of 0, 0
	 */
	public Shape() {
		this(0,0);
	}
	
	/**
	 * Generates a Shape with x1, and y1 at x, y. This point may represent different parts of the Shape (eg upper-right corner or center)
	 * @param x x-coordinate of point
	 * @param y y-coordinate of point.
	 */
	public Shape(double x, double y) {
		this.color = new int[] {0, 0, 0};
		this.x1 = x;
		this.y1 = y;
	}
	
	
	
	// METHODS
	
	/**
	 * Moves the entire shape by x, y
	 * @param x x-amount to move by
	 * @param y y-amount to move by
	 */
	public abstract void move(double x, double y);
	
	/**
	 * Useful for finding areas of intersection, etc. Returns a rectangle
	 * @return a Rectangle object that fits around the Shape
	 */
	public abstract Rectangle getBounds();
	
	/**
	 * Draw method for rendering Shape on PApplet
	 * @param drawer a PApplet on which the Shape is drawn on
	 * @pre PApplet != null
	 * @post changes the state of drawer
	 */
	public abstract void draw(PApplet drawer);
	
	/**
	 * Translates the entire Shape by x, y
	 * @param x increase x-coordinate by this amount
	 * @param y increase y-coordinate by this amount
	 */
	public abstract void translate(double x, double y);
	
	/**
	 * Changes the strokeWeight() and rgb color value of the Shape
	 * @param weight sets the weight of the Shape, in pixels
	 * @param r sets the red component of a rgb color
	 * @param g sets the green component of a rgb color
	 * @param b sets the blue component of a rgb color
	 * @pre all values r, g, b must be inclusively between 0 and 255
	 */
	public void setStyle(int weight, int r, int g, int b) {
		this.weight = weight;
		this.color[0] = r;
		this.color[1] = g;
		this.color[2] = b;
	}
	
	/**
	 * Changes the rgb color value of the Shape
	 * @param r sets the red component of a rgb color
	 * @param g sets the green component of a rgb color
	 * @param b sets the blue component of a rgb color
	 */
	public void setStyle(int r, int g, int b) {
		this.color[0] = r;
		this.color[1] = g;
		this.color[2] = b;
	}
	
	/**
	 * Changes the strokeWeight() and greyscale color value of the Shape
	 * @param weight sets the weight of the Shape, in pixels
	 * @param shade greyscale shade of the Shape from 0 (black) to 255 (white)
	 * @pre shade must be between 0 and 255, inclusively
	 */
	public void setStyle(int weight, int shade) {
		this.weight = weight;
		this.color[0] = shade;
		this.color[1] = shade;
		this.color[2] = shade;
	}
	
	/**
	 * The stroke weight of the shape when rendered on a PApplet.
	 * @return stroke weight
	 */
	public int getWeight() {
		return weight;
	}
	
	/**
	 * The color of the shape when rendered on a PApplet in r, g, b values from 0-255 inclusively
	 * @return color (r, g, b)
	 */
	public int[] getColor() {
		return color;
	}
	
	/**
	 * Sets getWeight() to the specified value
	 * @param weight - stroke weight
	 */
	public void setWeight(int weight) {
		this.weight = weight;
	}
	
	/**
	 * Sets the color of the shape, visible when rendered on a PApplet
	 * @param color r, g, b value of the color. Color must have a length of 3
	 */
	public void setColor(int[] color) {
		this.color[0] = color[0];
		this.color[1] = color[1];
		this.color[2] = color[2];
	}
	
	/**
	 * Method for setting the value of x1
	 * @param x x value
	 */
	public void setX1(double x) {
		this.x1 = x;
	}
	
	/**
	 * Method for setting the value of y1
	 * @param y y value
	 */
	public void setY1(double y) {
		this.y1 = y;
	}
	
	public double getX() {
		return x1;
	}
	
	public double getY() {
		return y1;
	}
	
	
}
