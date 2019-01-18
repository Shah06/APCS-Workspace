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
		//draw a line from each point
		for (int i = 0; i < myPolygon.size(); i++) {
			if (i == myPolygon.size()-1) {
				marker.line((float)myPolygon.get(i).getX(), (float)myPolygon.get(i).getY(), (float)myPolygon.get(0).getX(), (float)myPolygon.get(0).getY());
			} else {
				marker.line((float)myPolygon.get(i).getX(), (float)myPolygon.get(i).getY(), (float)myPolygon.get(i+1).getX(), (float)myPolygon.get(i+1).getY());
			}
		}
	}
	
	public double calcPerimeter() {
		return 0D;
	}
	
	public double calcArea() {
		return 0D;
	}

	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void move(double arg0, double arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void translate(double arg0, double arg1) {
		// TODO Auto-generated method stub
		
	}
	
}
