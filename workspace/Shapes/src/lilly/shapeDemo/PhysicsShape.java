package lilly.shapeDemo;
import ashah766.shapes.*;
import processing.core.PApplet;

public class PhysicsShape {

	private Shape2D s;
	private double vX, vY;
	
	public PhysicsShape(Shape2D s) {
		this.s = s;
		this.vX = 0;
		this.vY = 5;

	}
	public void draw(PApplet drawer) {
		s.draw(drawer);
	}
	public void move(double x, double y) {
		s.setX1(x);
		s.setY1(y);
	}
	public void act() {
		Double x = s.getX();
		Double y = s.getY();
		
		s.move(x + vX, y + vY);
		
		if (s.getY() + 20 >= 300) {
			vY *= -0.8;
		}
		else if (s.getY() + 20 <= 150) {
	
			vY *= -0.8;
		}
		vY *= 0.99;
	}
	public void setStyle() {
		s.setStyle(4, 0);
	}
	public boolean isPointInside(double x, double y) {
		if (s.isPointInside(x, y)) {
			return true;
		}
		return false;
	}
}
