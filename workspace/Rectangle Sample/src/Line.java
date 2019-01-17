import java.awt.geom.Point2D;

import processing.core.PApplet;

public class Line {

	double x1, y1, x2, y2;
	
	
	public Line() {
		x1 = 0;
		x2 = 0;
		y1 = 0;
		y2 = 0;
	}
	
	public Line(double x1, double y1, double x2, double y2) {
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
	}
	
	public void setPoint1(double x1, double y1) {
		this.x1 = x1;
		this.y1 = y1;
	}
	
	public void setPoint2(double x2, double y2) {
		this.x2 = x2;
		this.y2 = y2;
	}
	
	
	public double distance() {
		double xdif = x2-x1;
		double ydif = y2-y1;
		return Math.sqrt(xdif*xdif + ydif*ydif);
	}
	
	
	public boolean intersects(Line other) {
		
		Point2D.Double hit = calculateIntersectionPoint(other);
		if (hit == null)
			return false;
		
		double bigX = Math.max(x1, x2);
		double bigX2 = Math.max(other.x1, other.x2);
		double upperX = Math.min(bigX, bigX2);
		
		double smallX = Math.min(x1, x2);
		double smallX2 = Math.min(other.x1, other.x2);
		double lowerX = Math.max(smallX, smallX2);
		
		double bigY = Math.max(y1, y2);
		double bigY2 = Math.max(other.y1, other.y2);
		double upperY = Math.min(bigY, bigY2);
		
		double smallY = Math.min(y1, y2);
		double smallY2 = Math.min(other.y1, other.y2);
		double lowerY = Math.max(smallY, smallY2);
		
		if (lowerX <= hit.x+0.000001 && hit.x-0.000001 <= upperX && lowerY <= hit.y+0.000001 && hit.y-0.000001 <= upperY)
			return true;
		else
			return false;
		
	}
	
	
	public Point2D.Double calculateIntersectionPoint(Line other) {
		
		double denom = (x1-x2)*(other.y1-other.y2) - (y1-y2)*(other.x1-other.x2);
		if (denom == 0)
			return null;
		
		double xHit = ((x1*y2-y1*x2)*(other.x1-other.x2) - (x1-x2)*(other.x1*other.y2 - other.y1*other.x2))/denom;
		double yHit = ((x1*y2-y1*x2)*(other.y1-other.y2) - (y1-y2)*(other.x1*other.y2 - other.y1*other.x2))/denom;

		return new Point2D.Double(xHit,yHit);
		
	}
	
	
	
	public void draw(PApplet drawer) {
		drawer.line((float)x1, (float)y1, (float)x2, (float)y2);
	}
	
}
