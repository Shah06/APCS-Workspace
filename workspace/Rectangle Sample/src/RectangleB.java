import java.awt.geom.Point2D;

import processing.core.PApplet;

/*
 * + The line class is used, which has already been tested for collision detection
 * - Lines are used in place of just comparing width/height
 * ^ Add code that doesn't involve lines
 */

public class RectangleB {

	private Line left, right, top, bottom;
	
	// Constructors
	// Creates a default instance of a Rectangle object with all dimensions
	//   set to zero.
	public RectangleB() {
		left = new Line();
		right = new Line();
		top = new Line();
		bottom = new Line();
	}

	// Creates a new instance of a Rectangle object with the left and right
	// edges of the rectangle at x and x + width. The top and bottom edges
	// are at y and y + height.
	public RectangleB(double x, double y, double width, double height) {
		left = new Line(x,y+height,x,y);
		right = new Line(x+width,y,x+width,y+height);
		top = new Line(x,y,x+width,y);
		bottom = new Line(x+width,y+height,x,y+height);
	}

	// Methods
	// Calculates and returns the perimeter of the rectangle
	public double getPerimeter() {
		return 2*(left.distance()+top.distance());
	}

	// Calculates and returns the area of the rectangle
	public double getArea() {
		return left.distance()*top.distance();
	}
	
	// Returns the center point of the rectangle
	public Point2D.Double getCenter() {
		return new Point2D.Double((top.x1+top.x2)/2,(left.y1+left.y2)/2);
	}

	// Determines whether the point x,y is contained inside this rectangle
	public boolean isPointInside(double x, double y) {
		return (x >= left.x1 && y >= top.y1 && x <= right.x1 && y <= bottom.y1);
	}

	// Draws a new instance of a Rectangle object with the left and right
	// edges of the rectangle at x and x + width. The top and bottom edges
	// are at y and y + height.
	public void draw(PApplet drawer) {
		left.draw(drawer);
		right.draw(drawer);
		top.draw(drawer);
		bottom.draw(drawer);
	}

	// Sets the coordinate of the bottom right corner of the Rectangle
	public void setBottomRight(double x, double y) {
		top.x2 = x;
		right.x1 = x;
		right.x2 = x;
		right.y2 = y;
		bottom.x2 = x;
		bottom.y1 = y;
		bottom.y2 = y;
		left.y1 = y;
	}
	
	
	
}
