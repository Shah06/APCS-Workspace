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
	
	private int bgColor;
	
	public void draw() {
		
		if (255 == bgColor) {
			stroke(0);
		} else {
			stroke(255);
		}
		background(bgColor);
		if (255 == bgColor) {
			fill(0);
		} else {
			fill(255);
		}
		textAlign(CENTER);
		board.draw(this, 100, 100, 500, 500);
		
		textSize(12);
		if (255 == bgColor) {
			fill(0);
		} else {
			fill(255);
		}
		textAlign(CENTER);
		text("SCORE: " + board.getScore(), 250, 535);
		text("ARROW KEYS TO MOVE", 250, 555);
		text("CLICK TO TOGGLE DARK MODE", 250, 575);
		
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
	
	public void mousePressed() {
		if (255 == bgColor) {
			bgColor = 0;
		} else {
			bgColor = 255;
		}
	}
	
	public int getBgColor() {
		return bgColor;
	}
	
}
