import java.awt.Point;

import processing.core.PApplet;

public class DrawingSurface extends PApplet {

	private char[][] grid;
	
	private Erase er;
	
	
	public DrawingSurface() {}
	
	public DrawingSurface(char[][] grid) {
		this.grid = grid;
	}
	
	public void settings() {
		size(500, 500);
	}
	
	public void setup() {
		fill(255);
		surface.setResizable(false);
		surface.setFrameRate(60);
		
		er = new Erase("digital.txt");
		System.out.print(er.toString());
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
//		er.eraseObject(4, 2);
		er.solve(7, 8);
		System.out.print(er.toString());
		System.out.println(er.getIterations() + " iteration(s)");
		
	}
	
	public void draw() {
		// each cell is height/20, width/20 in dimension
		background(255);
		int x = 0;
		int y = 0;
		fill(0);
		grid = er.getData();
		for (int i = 0; i < grid.length; i++) {
			for (int k = 0; k < grid[i].length; k++) {
				rect(x, y, height/grid.length, width/grid[i].length);
				x += 500/20;
			}
			y += 500/20;
		}
		
	}
	
	
//	public void mousePressed() {
//		if ((grid.clickToIndex(new Point((int) mouseX, (int) mouseY), 0f, 0f, 500f, 500f) != null)) {
//			Point p = (grid.clickToIndex(new Point((int) mouseX, (int) mouseY), 0f, 0f, 500f, 500f));
//			grid.toggleCell((int) p.getX(), (int) p.getY());
//		}
//	}
	
	public void keyPressed() {
		
//		if (key == 's' || keyCode == ENTER) {
//			board.step();
//		}
//		if (keyCode == DELETE || keyCode == BACKSPACE) {
//			board.clear();
//		}
//		if (keyCode == UP) {
//			speed += 5;
//		}
//		if (keyCode == DOWN) {
//			if (speed >= 0) {
//				speed -= 5;
//			}
//		}
//		if (key == ' ') {
//			if (paused) {paused = false;}
//			else {paused = true;}
//		}
	}
	
}
