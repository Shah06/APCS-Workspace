
import processing.core.PApplet;


public class DrawingSurface extends PApplet {

	private Line l1, l2;
	
	public DrawingSurface() {
		l1 = null;
		l2 = null;
		
		//l1 = new Line(0, 0, 300, 300);
		//l2 = new Line(400, 400, 600, 600);
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
		
		// test code begin

		//l1 = new Line(100, 200, 300, 200);
		//l2 = new Line(100, 200, 300, 200);
		
		// test code end
		
		if (l1 != null) {
			stroke(255,0,0);
			l1.draw(this);
		}
		if (l2 != null) {
			stroke(0,255,0);
			l2.draw(this);
		}
		
		if (l1 != null && l2 != null) {
			boolean intersect = l1.intersects(l2);
			fill(0);
			// draws a point if intersects
			if (intersect) {
				ellipse((float)l1.getIntersection()[0], (float)l1.getIntersection()[1], 10F, 10F);
			}
			textSize(30);
			textAlign(CENTER);
			text(intersect+"", width/2, 50);
		}
	}
	
	
	public void mousePressed() {
		if (mouseButton == LEFT) {
			l1 = new Line(mouseX,mouseY,mouseX,mouseY);
		} else if (mouseButton == RIGHT)
			l2 = new Line(mouseX,mouseY,mouseX,mouseY);
	}
	
	
	public void mouseDragged() {
		if (mouseButton == LEFT) {
			l1.setPoint2(mouseX,mouseY);
		} else if (mouseButton == RIGHT)
			l2.setPoint2(mouseX,mouseY);
	}
	
	
}










