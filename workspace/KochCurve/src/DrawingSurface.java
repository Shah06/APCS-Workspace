import java.awt.event.KeyEvent;

import processing.core.PApplet;
import processing.event.MouseEvent;


public class DrawingSurface extends PApplet {

	private KochCurve curve, curve1, curve2;
	private int level, length, angle;
	
	public DrawingSurface() {
		level = 0;
		length = 250;
		angle = 30;
		curve = new KochCurve(350, 300, level, length, 180);
		curve1 = new KochCurve(100, 300, level, length, 60);
		curve2 = new KochCurve(225, 85, level, length, 300);
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
		textSize(12);
		fill(0);
		text("Use the mouse wheel to change length, use UP/DOWN keys to change level.",0,15);
		text("Length: " + curve.getLength(), 0, 30);
		text("Level: " + curve.getLevel(), 0, 45);
		
		stroke(0);
		curve.draw(this);
		curve1.draw(this);
		curve2.draw(this);
	}
	
	
	public void mouseWheel(MouseEvent event) {
		  int num = event.getCount();
		  length -= num*10;
		  curve = new KochCurve(350, 300, level, length, 180);
		  curve1 = new KochCurve(100, 300, level, length, 60);
		  curve2 = new KochCurve(225, 85, level, length, 300);
	}
	
	public void keyPressed() {
		if (keyCode == KeyEvent.VK_UP) {
			level++;
			curve = new KochCurve(350, 300, level, length, 180);
			curve1 = new KochCurve(100, 300, level, length, 60);
			curve2 = new KochCurve(225, 85, level, length, 300);
		} else if (keyCode == KeyEvent.VK_DOWN) {
			level--;
			curve = new KochCurve(350, 300, level, length, 180);
			curve1 = new KochCurve(100, 300, level, length, 60);
			curve2 = new KochCurve(225, 85, level, length, 300);
		}
	}
	
	
}










