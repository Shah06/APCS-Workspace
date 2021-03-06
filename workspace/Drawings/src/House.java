import processing.core.PApplet;

/*
 * + house translates properly
 * - house doens't dilate at its spot, it moves when it grows or shrinks 
 * Delta - try to add that reverse translate just after you scale it and delete the second scale 
*/ 

public class House {
	
	private int x, y;
	private float scale;
	
	public House() {
		scale = 1f;
	}
	
	
	public void draw(PApplet drawer) {
		drawer.pushMatrix();
		
		drawer.translate(x, y);
		drawer.scale(scale, scale);
		drawer.noFill();
		drawer.rect(100, 100, 200, 100);
		drawer.triangle(100, 100, 300, 100, 200, 25);
		drawer.rect(185, 150, 30, 50); //door
		drawer.rect(125, 140, 30, 30); //window
		drawer.rect(245, 140, 30, 30); //window
		
		drawer.popMatrix();
	}
	
	
	public void scaleUp() {
		this.scale += 0.25;
	}
	
	public void scaleDown() {
		this.scale -= 0.25;
	}
	
	public void move(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	
}
