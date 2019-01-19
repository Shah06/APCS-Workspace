import java.awt.geom.*;
import processing.core.PApplet;

public class DrawingSurface extends PApplet{
	
	private IrregularPolygon tester;
	
	public void settings() {
		size(500, 500);
	}
	
	public void setup() {
		fill(0);
//		surface.setResizable(false);
		surface.setResizable(true);
		surface.setFrameRate(60);
		
		tester = new IrregularPolygon();
//		tester.add(new Point2D.Double(100, 100));
//		tester.add(new Point2D.Double(250, 250));
//		tester.add(new Point2D.Double(25, 275));
//		tester.add(new Point2D.Double(0, 3));
//		tester.add(new Point2D.Double(4, 0));
//		tester.add(new Point2D.Double(4, 3));
		textSize(20);
		textAlign(CENTER);
	}
	
	public void draw() {
		background(255);
		tester.getBounds().draw(this);
		tester.draw(this);
		text("Perimeter: " + tester.calcPerimeter(), width/2, 30);
		text("Area: " + tester.calcArea(), width/2, 50);
	}
	
	public void mousePressed() {
		tester.add(new Point2D.Double(mouseX, mouseY));
	}
	
}
