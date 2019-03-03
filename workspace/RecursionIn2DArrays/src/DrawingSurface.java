import java.awt.Point;

import processing.core.PApplet;

public class DrawingSurface extends PApplet {

	private char[][] grid;
	
//	private Erase er;
	private Maze er;
	
	
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
		
		er = new Maze("data\\level2\\test6.txt");
		System.out.print(er.toString());
		er.solve();
		System.out.println(er.toString());
		er.printPath();
//		System.out.println();
//		System.out.println();
//		System.out.println();
//		System.out.println();
////		er.eraseObject(4, 2);
////		er.solve(7, 8);
////		er.solve(1, 4);
//		System.out.print(er.toString());
//		System.out.println(er.getIterations() + " iteration(s)");
		
	}
	
	public void draw() {
		// each cell is height/20, width/20 in dimension
		background(255);
		int x = 0;
		int y = 0;
		fill(0);
		grid = er.getData();
		for (int i = 0; i < grid.length; i++) {
			x = 0;
			for (int k = 0; k < grid[i].length; k++) {
				if (grid[k][i] == '*') {
					rect(x, y, height/grid.length, width/grid[i].length);
				}
				x += 500/20;
			}
			y += 500/20;
		}
		
	}
	
	
	public void mousePressed() {
		if ((er.clickToIndex(new Point((int) mouseX, (int) mouseY), 0f, 0f, width, height) != null)) {
			Point p = (er.clickToIndex(new Point((int) mouseX, (int) mouseY), 0f, 0f, width, height));
			er.toggleCell((int) p.getX(), (int) p.getY());
		}
	}

	
}
