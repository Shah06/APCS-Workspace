import java.awt.geom.*;
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
//		tester.add(new Point2D.Double(100, 100));
//		tester.add(new Point2D.Double(250, 250));
//		tester.add(new Point2D.Double(25, 275));
		tester.add(new Point2D.Double(25, 25));
		tester.add(new Point2D.Double(25, 50));
		tester.add(new Point2D.Double(50, 50));
		tester.add(new Point2D.Double(50, 25));
		System.out.println(tester.calcPerimeter());
	}
	
	public void draw() {
		background(255);
		tester.draw(this);
	}
	
}
