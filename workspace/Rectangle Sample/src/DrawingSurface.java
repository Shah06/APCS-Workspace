
import java.awt.geom.Point2D;

import processing.core.PApplet;


public class DrawingSurface extends PApplet {

	private RectangleA rA;
	private RectangleB rB;
	
	public DrawingSurface() {
		rA = null;
		rB = null;
	}
	
	// The statements in the setup() function 
	// execute once when the program begins
	public void setup() {

	}
	
	// The statements in draw() are executed until the 
	// program is stopped. Each statement is executed in 
	// sequence and after the last line is read, the first 
	// line is executed again.
	public void draw() { 
		background(255);   // Clear the screen with a white background
		fill(255);
		textAlign(CENTER);
		
		if (rA != null) {
			stroke(255,0,0);
			fill(255);
			rA.draw(this);
			Point2D.Double center = rA.getCenter();
			fill(0);
			text(rA.getPerimeter()+"\n"+rA.getArea(),(float)center.x,(float)center.y);
		}
		if (rB != null) {
			stroke(0,255,0);
			fill(255);
			rB.draw(this);
			Point2D.Double center = rB.getCenter();
			fill(0);
			text(rB.getPerimeter()+"\n"+rB.getArea(),(float)center.x,(float)center.y);
		}

		fill(0);
		textSize(12);
		
	}
	
	
	public void mousePressed() {
		if (mouseButton == LEFT) {
			rA = new RectangleA(mouseX,mouseY,0,0);
		} else if (mouseButton == RIGHT)
			rB = new RectangleB(mouseX,mouseY,0,0);
	}
	
	
	public void mouseDragged() {
		if (mouseButton == LEFT) {
			rA.setBottomRight(mouseX,mouseY);
		} else if (mouseButton == RIGHT)
			rB.setBottomRight(mouseX,mouseY);
	}
	
	
}










