import java.awt.Point;

import processing.core.PApplet;

public class DrawingSurface extends PApplet {
	
	public void settings() {
		size(500, 600);
	}
	
	private ChessBoard board;
	private int speed = 10; // speed of game, in Hertz
	private boolean paused = true; // whether game is paused or not
	
	public void setup() {
		board = new ChessBoard("data\\life100.txt");
//		board = new Life("data\\life tester.txt");
//		board = new Life();
//		board.step(5);
		fill(0);
		surface.setResizable(false);
		surface.setFrameRate(60);
//		textSize(12);
//		textAlign(CENTER);
	}
	
	// 10 times / second
	// 60 / 10 = 6
	
	private int counter = 1;
	
	public void draw() {
		background(255);
		if (!paused) {
			if (counter == (60/speed)) {
				counter = 1;
				board.step();
			} else {
				counter++;
			}
		}
		board.draw(this, 0, 0, 500, 500);
		// manual
		textSize(12);
		fill(0);
		textAlign(CENTER);
		text("'s' or ENTER for advancing one generation", 250, 535);
		text("BACKSPACE or DELETE for flushing the board", 250, 555);
		text("SPACEBAR for toggling play/pause", 250, 575);
	}


	public void mousePressed() {
		if ((board.clickToIndex(new Point((int) mouseX, (int) mouseY), 0f, 0f, 500f, 500f) != null)) {
			Point p = (board.clickToIndex(new Point((int) mouseX, (int) mouseY), 0f, 0f, 500f, 500f));
			board.toggleCell((int) p.getX(), (int) p.getY());
		}
	}
	
//	public void mouseDragged() {
//		Point p = (board.clickToIndex(new Point((int) mouseX, (int) mouseY), 0f, 0f, 500f, 500f));
//		board.toggleCell((int) p.getX(), (int) p.getY()); 
//	}
	
	public void keyPressed() {
		if (key == 's' || keyCode == ENTER) {
			board.step();
		}
		if (keyCode == DELETE || keyCode == BACKSPACE) {
			board.clear();
		}
		if (keyCode == UP) {
			speed += 5;
		}
		if (keyCode == DOWN) {
			if (speed >= 0) {
				speed -= 5;
			}
		}
		if (key == ' ') {
			if (paused) {paused = false;}
			else {paused = true;}
		}
	}
	
}
