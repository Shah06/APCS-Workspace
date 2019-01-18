import processing.core.PApplet;

public class DrawingSurface extends PApplet{
	
	private IrregularPolygon tester;
	
	public void settings() {
		size(500, 500);
	}
	
	public void setup() {
		fill(255);
		surface.setResizable(false);
		surface.setFrameRate(60);
		
		tester = new IrregularPolygon();
	}
	
	public void draw() {
		background(255);
		tester.draw(this);
	}
	
}
