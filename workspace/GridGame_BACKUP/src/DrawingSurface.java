import java.awt.Point;

import processing.core.PApplet;

public class DrawingSurface extends PApplet {
	
	public void settings() {
		size(500, 600);
	}
	
	private Twenty48 board;
	
	public void setup() {
		board = new Twenty48();
		board.generate();
		fill(0);
		surface.setResizable(false);
		surface.setFrameRate(60);
		textSize(12);
		textAlign(CENTER);
	}
	
	public void draw() {
		
		background(255);
		fill(0);
		textAlign(CENTER);
		board.draw(this, 100, 100, 500, 500);
		
		textSize(12);
		fill(0);
		textAlign(CENTER);
		text("SCORE: " + board.getScore(), 250, 535);
		text("ARROW KEYS TO MOVE", 250, 555);
		
	}
		
	
	public void keyPressed() {
		
		if (keyCode == UP) {
			board.up();
			board.generateCell();
		}
		if (keyCode == RIGHT) {
			board.right();
			board.generateCell();
		}
		if (keyCode == DOWN) {
			board.down();
			board.generateCell();
		}
		if (keyCode == LEFT) {
			board.left();
			board.generateCell();
		}
		
	}
	
}
