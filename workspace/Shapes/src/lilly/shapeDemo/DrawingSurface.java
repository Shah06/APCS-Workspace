package lilly.shapeDemo;
import processing.core.PApplet;
import ashah766.shapes.Shape;
import ashah766.shapes.Rectangle;
import ashah766.shapes.Circle;
import ashah766.shapes.Line;

public class DrawingSurface extends PApplet {
	
	private PhysicsShape shape1;
	private PhysicsShape shape2;
	
	public DrawingSurface() {
		shape1 = new PhysicsShape(new Rectangle(50, 300, 300, 50));
		shape2 = new PhysicsShape(new Circle(100, 100, 20));
	}
	public void draw() {
		background(255);   // Clear the screen with a white background
		
		shape1.setStyle();
		shape1.draw(this);
		
		shape2.act();
		shape2.setStyle();
		shape2.draw(this);
		
	}
	public void mouseDragged() {
		if (shape2.isPointInside(mouseX, mouseY))
			shape2.move(super.mouseX, super.mouseY);
	}
}
