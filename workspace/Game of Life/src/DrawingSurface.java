import processing.core.PApplet;

public class DrawingSurface extends PApplet {
	
	public void settings() {
		size(500, 500);
	}
	
	private Life board;
	
	public void setup() {
		board = new Life("data\\life tester.txt");
//		board.step(5);
		fill(0);
//		surface.setResizable(false);
		surface.setResizable(true);
		surface.setFrameRate(60);
		
		
//		textSize(12);
//		textAlign(CENTER);
	}
	
	public void draw() {
		background(255);
		board.draw(this, 0, 0, 500, 500);
	}


	public void mousePressed() {
		
	}
	
	public void keyPressed() {
		
	}
	
}
