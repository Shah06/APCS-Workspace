import java.awt.geom.*;
import java.util.ArrayList;
import processing.core.PApplet;
import ashah766.shapes.*;

public class IrregularPolygon extends Shape {
	
	private ArrayList <Point2D.Double> myPolygon;
	
	// constructors
	public IrregularPolygon() {
		myPolygon = new ArrayList<>();
	}
	
	// public methods
	public void add(Point2D.Double aPoint) {
		myPolygon.add(aPoint);
	}
	
	public void draw(PApplet marker) {
		// draw a line from each point
		for (int i = 0; i < myPolygon.size(); i++) {
			if (i == myPolygon.size()-1) {
				marker.line((float)myPolygon.get(i).getX(), (float)myPolygon.get(i).getY(), (float)myPolygon.get(0).getX(), (float)myPolygon.get(0).getY()); // last point to first point
			} else {
				marker.line((float)myPolygon.get(i).getX(), (float)myPolygon.get(i).getY(), (float)myPolygon.get(i+1).getX(), (float)myPolygon.get(i+1).getY());
			}
		}
	}
	
	public double calcPerimeter() {
		double distance = 0;
		for (int i = 0; i < myPolygon.size(); i++) {
			if (i == myPolygon.size()-1) {
				distance += Math.sqrt(Math.pow((myPolygon.get(i).getX()-myPolygon.get(0).getX()), 2) + Math.pow((myPolygon.get(i).getY()-myPolygon.get(0).getY()), 2));
			} else {
				distance += Math.sqrt(Math.pow((myPolygon.get(i).getX()-myPolygon.get(i+1).getX()), 2) + Math.pow((myPolygon.get(i).getY()-myPolygon.get(i+1).getY()), 2));
			}
		}
		
		return distance;
	}
	
	public double calcArea() {
		double n1 = 0;
		double n2 = 0;
		for (int i = 0;  i < myPolygon.size(); i++) {
			if (i == myPolygon.size()-1) {
				n1 += myPolygon.get(i).getX() * myPolygon.get(0).getY();
			} else {
				n1 += myPolygon.get(i).getX() * myPolygon.get(i+1).getY();
			}
		}
		for (int i = 0;  i < myPolygon.size(); i++) {
			if (i == myPolygon.size()-1) {
				n2 += myPolygon.get(i).getY() * myPolygon.get(0).getX();
			} else {
				n2 += myPolygon.get(i).getY() * myPolygon.get(i+1).getX();
			}
		}
		return Math.abs(0.5D * (n1-n2));
	}

	@Override
	public Rectangle getBounds() {
		double xMax = 0;
		double xMin = 0;
		double yMax = 0;
		double yMin = 0;
		
		for (int i = 0; i < myPolygon.size(); i++) {
			if (i == 0) {
				xMax = myPolygon.get(0).getX();
				xMin = myPolygon.get(0).getX();
				yMax = myPolygon.get(0).getY();
				yMin = myPolygon.get(0).getY();
			}
			double tempX = myPolygon.get(i).getX();
			double tempY = myPolygon.get(i).getY();
			
			if (tempX > xMax) xMax = tempX;
			if (tempX < xMin) xMin = tempX;
			if (tempY > yMax) yMax = tempY;
			if (tempY < yMin) yMin = tempY;
		}
		
		
		return new Rectangle(xMin, yMin, (xMax-xMin), (yMax-yMin)); // returns bounding rectangle
	}

	@Override
	public void move(double x, double y) {
		// TODO Auto-generated method stub
		for (int i = 0; i < myPolygon.size(); i++) {
			myPolygon.get(i).setLocation(x, y);
		}
	}

	@Override
	public void translate(double x, double y) {
		// TODO Auto-generated method stub
		for (int i = 0; i < myPolygon.size(); i++) {
			myPolygon.get(i).setLocation(myPolygon.get(i).getX()+x, myPolygon.get(i).getY()+y);
		}
	}
	
	// deletes most recent vertex
	public void pop() {
		if (myPolygon.size() == 0) return;
		myPolygon.remove(myPolygon.size()-1);
	}
	
	// deletes oldest vertex
	public void push() {
		if (myPolygon.size() == 0) return;
		myPolygon.remove(0);
	}

	public int getNumVertices() {
		return myPolygon.size();
	}
	
}
