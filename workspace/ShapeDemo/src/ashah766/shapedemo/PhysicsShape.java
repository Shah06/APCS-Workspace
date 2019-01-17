package ashah766.shapedemo;
import smalani530.shapes.*;
import java.awt.geom.Point2D;
import processing.core.PApplet;

public class PhysicsShape {
	
	private Shape s;
	private double vx, vy;
	private double uk; // friction coefficient
	private double vScale; // velocity scale
	public PhysicsShape(Shape s) {
		this.s = s;
		this.vx = 0;
		this.vy = 0;
		this.uk = 0.92;
		this.vScale = 1;
	}
	
	public void draw(PApplet drawer) {
		s.draw(drawer);
	}
	
	
	public void move(double x, double y) {
		s.move(x, y);
	}
	
	
	public void act() {
		
		double x = s.getX();
		double y = s.getY();
		
		
		vy += .981;
		
		s.move(x+vx*vScale, y+vy*vScale);
		
		
		
		
		// stops from going off screen (sometimes still does when user drags mouse off screen)
		if (vx >= 60) {
			vx = 59;
		} if (vy >= 60) {
			vy = 59;
		}
		
		vx *= uk;
		vy *= uk;
		
	}
	
	public void accelerate (double x, double y) {
		vx += x;
		vy += y;
	}
	
	public void setVelocity (double x, double y) {
		vx = x;
		vy = y;
	}
	
	
	public double getAngle() {
		return Math.toDegrees(Math.atan(vy/vx));
	}
	
	
	// checks and adjusts path given the bounds
	public void checkPath(Rectangle bounds) {
		// check if centerpoint is within bounds
		
		Point2D centerpoint = s.getCenterPoint();
		double centerX = centerpoint.getX();
		double centerY = centerpoint.getY();
		
		if (bounds.isPointInside(centerX, centerY) == false) {
			vx = vx * -1;
			vy = vy * -1;
			
//			if (s.getCenterPoint().getX() < bounds.getX() || s.getCenterPoint().getX() > bounds.getX() + 2 * (bounds.getCenterPoint().getX() - bounds.getX())) {
//				vx *= -0.9;
//				while (bounds.isPointInside(centerX, centerY) == false) {
//					s.move(s.getX()+vx, s.getY());
//				}
//			} else {
//				vy *= -0.9;
//			}
			
		}
		
	}
	
	public boolean isInsideOf(Rectangle bounds) {
		Point2D centerpoint = s.getCenterPoint();
		double centerX = centerpoint.getX();
		double centerY = centerpoint.getY();
		if (!bounds.isPointInside(centerX, centerY)) {
			return false;
		}
		return true;
	}
	
	public double getX() {
		return s.getX();
	}
	
	public double getY() {
		return s.getY();
	}
	
	public Point2D getCenterPoint() {
		return s.getCenterPoint();
	}
	
	public void setVScale(double v) {
		this.vScale = v;
	}
	
	public void setUk(double u) {
		this.uk = u;
	}
	
	public void setFill(int r, int g, int b) {
		s.setFill(r, g, b);
	}
	
}
