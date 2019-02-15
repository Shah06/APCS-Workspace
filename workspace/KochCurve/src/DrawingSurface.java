import java.awt.event.KeyEvent;

import processing.core.PApplet;
import processing.event.MouseEvent;


public class DrawingSurface extends PApplet {

	private BanachCurve bCurve;
//	private KochCurve curve, curve1, curve2, curve3, curve4, curve5;
	private int level, length, angle, level2;
	
	public DrawingSurface() {
		level = 0;
		length = 250;
		angle = 30;
		level2 = 0;
		bCurve = new BanachCurve(250, 250, 50, level2);
//		curve = new KochCurve(350, 300, level, length, 180);
//		curve1 = new KochCurve(100, 300, level, length, 60);
//		curve2 = new KochCurve(225, 85, level, length, 300);
//		curve3 = new KochCurve(400, 255, level, length, 180);
//		curve4 = new KochCurve(150, 255, level, length, 300);
//		curve5 = new KochCurve(275, 473, level, length, 60);
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
//		text("Length: " + curve.getLength(), 0, 30);
//		text("Level: " + curve.getLevel(), 0, 45);
		
		stroke(0);
		noFill();
		
		bCurve.draw(this);
		
//		curve.draw(this);
//		curve1.draw(this);
//		curve2.draw(this);
//		curve3.draw(this); // curve 3 is for testing purposes only; not part of the koch triangle
//		curve4.draw(this);
//		curve5.draw(this);
	}
	
	
	public void mouseWheel(MouseEvent event) {
		  int num = event.getCount();
		  length -= num*10;
//		  curve = new KochCurve(350, 300, level, length, 180);
//		  curve1 = new KochCurve(100, 300, level, length, 60);
//		  curve2 = new KochCurve(225, 85, level, length, 300);
//		  curve3 = new KochCurve(400, 255, level, length, 180);
//		  curve4 = new KochCurve(150, 255, level, length, 300);
//		  curve5 = new KochCurve(275, 473, level, length, 60);
	}
	
	public void keyPressed() {
		if (keyCode == KeyEvent.VK_UP) {
			level++;
			level2++;
			bCurve = new BanachCurve(250, 250, 50, level2);
//			curve = new KochCurve(350, 300, level, length, 180);
//			curve1 = new KochCurve(100, 300, level, length, 60);
//			curve2 = new KochCurve(225, 85, level, length, 300);
//			curve3 = new KochCurve(400, 255, level, length, 180);
//			curve4 = new KochCurve(150, 255, level, length, 300);
//			curve5 = new KochCurve(275, 473, level, length, 60);
		} else if (keyCode == KeyEvent.VK_DOWN) {
			level--;
			level2--;
			bCurve = new BanachCurve(250, 250, 50, level2);
//			curve = new KochCurve(350, 300, level, length, 180);
//			curve1 = new KochCurve(100, 300, level, length, 60);
//			curve2 = new KochCurve(225, 85, level, length, 300);
//			curve3 = new KochCurve(400, 255, level, length, 180);
//			curve4 = new KochCurve(150, 255, level, length, 300);
//			curve5 = new KochCurve(275, 473, level, length, 60);
		}
	}
	
	
}










