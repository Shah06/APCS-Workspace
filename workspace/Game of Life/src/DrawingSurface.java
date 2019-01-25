import java.awt.Point;

import processing.core.PApplet;

public class DrawingSurface extends PApplet {
	
	public void settings() {
		size(500, 500);
	}
	
	private Life board;
	
	public void setup() {
//		board = new Life("data\\life100.txt");
//		board = new Life("data\\life tester.txt");
		board = new Life();
//		board.step(5);
		fill(0);
		surface.setResizable(false);
		surface.setFrameRate(60);
		
		
//		textSize(12);
//		textAlign(CENTER);
	}
	
	public void draw() {
		background(255);
		board.draw(this, 0, 0, 500, 500);
	}


	public void mousePressed() {
		Point p = (board.clickToIndex(new Point((int) mouseX, (int) mouseY), 0f, 0f, 500f, 500f));
		board.toggleCell((int) p.getX(), (int) p.getY());
	}
	
	public void keyPressed() {
		if (key == 's') {
			board.step();
		}
	}
	
}
