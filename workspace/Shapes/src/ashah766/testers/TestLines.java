package ashah766.testers;
import ashah766.shapes.*;
import processing.core.PApplet;
import java.util.ArrayList;

// A LOT OF BS TEST CODE PLS DONT GRADE HARSH BC OF THIS

public class TestLines extends PApplet {
	
	private Line l1, l2;
	private Rectangle r1, r2;
	private Circle c1, c2;
	private Rectangle bounds;
	private RegularPolygon poly;
	private ArrayList<RegularPolygon> regularPoly;
	private int polysize;
	private double polylength;
	
	// CONSTRUCTOR
	public TestLines() {
		
	}
	
	
	public void settings() {
		size(500, 500);
	}
	
	public void setup() {
		background(255);
		surface.setResizable(false);
		surface.setFrameRate(60);
		
		l1 = new Line(100, 200, 400, 300);
		l2 = new Line(300, 200, 100, 200);
		r1 = new Rectangle(200, 200, 90, 150);
		r2 = new Rectangle(250, 250, 80, 80);
		c1 = new Circle(250, 250, 100);
		c2 = new Circle(100, 200, 200);
		poly = new RegularPolygon(12, 100);
		System.out.println("n, s, theta, r, R: \n" + poly.getNumSides() + "\n"
				+ poly.getSideLength()
				+ "\n" + poly.calcVertexAngle()
				+ "\n" + poly.calcr()
				+ "\n" + poly.calcR()
				);
		System.out.println("Perimeter: " + poly.getPerimeter());
		System.out.println("Area: " + poly.getArea());
		regularPoly = new ArrayList<RegularPolygon>();
		polylength = 200;
		polysize = 1;
	}
	
	public void draw() {
		
		noFill();
		background(255);
		ellipseMode(CENTER);
		//poly.draw(this);
		//poly.drawBoundingCircles(this);
		//poly.move(mouseX, mouseY);
		//poly.getBounds().draw(this);
		for (int i = 0; i < regularPoly.size(); i++) {
			regularPoly.get(i).setStyle(2, 0);
			regularPoly.get(i).move(mouseX, mouseY);
			regularPoly.get(i).draw(this);
		}
		
		pushStyle();
		fill(0);
		ellipse(mouseX, mouseY, 10, 10);
		popStyle();
	}
	
	public void keyPressed() {
		System.out.println(polysize);
		try {
			if (key == '1') {
				polysize++;
				regularPoly.add(new RegularPolygon(polysize, 800D/polysize));
				if (regularPoly.size() > 1) {
					regularPoly.remove(0);
				}
			}
			else if (key == '2') {
				// create new element with polysides-1
				polysize--;
				regularPoly.add(new RegularPolygon(polysize, 800D/polysize));
				if (regularPoly.size() > 1) {
					regularPoly.remove(0);
				}
			}
		} catch (IllegalArgumentException e) {}
	}
	
	
	
}
