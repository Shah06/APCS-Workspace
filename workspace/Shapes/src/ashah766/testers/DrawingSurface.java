package ashah766.testers;

import processing.core.PApplet;
import ashah766.shapes.*;
import java.util.ArrayList;


/*
 * Please note: I would normally put each illusion in a separate class, but this lab only allows 2 classes.
 */
public class DrawingSurface extends PApplet {
	
	private ArrayList<Circle> circles1 = new ArrayList<Circle>();
	private Rectangle rectangle1;
	
	private ArrayList<Rectangle> rectangles2 = new ArrayList<Rectangle>();
	
	private ArrayList<Line> lines3 = new ArrayList<Line>();
	private ArrayList<Rectangle> rect3 = new ArrayList<Rectangle>();
	
	private ArrayList<Line> lines4 = new ArrayList<Line>();
	private ArrayList<Rectangle> rect4 = new ArrayList<Rectangle>();
	
	private int illusionChoice = 1;
	
	public void settings() {
		size(500, 500);
	}
	
	public void setup() {
		fill(255);
		surface.setResizable(false);
		surface.setFrameRate(60);
	}
	
	public DrawingSurface() {
		
		System.out.println("Click on the window and use the keys 1-4 to switch between illusions.");
		
		// illusion 1
		for (int i = 0; i < 15; i++) {
			circles1.add(new Circle(250, 250, i*16));
			circles1.get(i).setStyle(5, 0);
		}
		rectangle1 = new Rectangle(75, 75, 350, 350);
		rectangle1.setStyle(5, 161, 40, 237);
		
		//illusion 2
		for (int i = 0; i < 5; i++) {
			for (int k = 0; k < 6; k++) {
				rectangles2.add(new Rectangle(k*90, i*110, 45, 55));
				rectangles2.get((rectangles2.size()-1)).setFill(true);
				rectangles2.get((rectangles2.size()-1)).setStyle(1, 0, 61, 13);
			}
		}
		for (int i = 0; i < 2; i++) {
			for (int k = 0; k < 6; k++) {
				rectangles2.add(new Rectangle((k*90)-12, (i*220)+55, 45, 55));
				rectangles2.get((rectangles2.size()-1)).setFill(true);
				rectangles2.get((rectangles2.size()-1)).setStyle(1, 0, 61, 13);
			}
		}
		for (int i = 0; i < 2; i++) {
			for (int k = 0; k < 6; k++) {
				rectangles2.add(new Rectangle((k*90)+12, (i*220)+165, 45, 55));
				rectangles2.get((rectangles2.size()-1)).setFill(true);
				rectangles2.get((rectangles2.size()-1)).setStyle(1, 0, 61, 13);
			}
		}
		for (int i = 0; i < 8; i++) {
			rectangles2.add(new Rectangle(0, ((i+1)*55)-1, 500, 2));
			rectangles2.get((rectangles2.size()-1)).setStyle(0, 100, true);
		}
		
		
		// illusion 3
		int spokes = 40;
		for (int i = 0; i < spokes; i++) {
			lines3.add(new Line(250, 250, (200*cos(i*PI/(spokes/2)))+250, (200*sin(i*PI/(spokes/2))+250)));
			lines3.get(i).setStyle(3, 0);
		}
		for (int i = 0; i < 6; i++) {
			rect3.add(new Rectangle((i+1)*70, 50, 3, 400));
			rect3.get(i).setFill(true);
			rect3.get(i).setStyle(2, 219, 0, 0);
		}
		
		// illusion 4
		for (int i = 0; i < 5; i++) {
			lines4.add(new Line(0, ((i+1)*10)+250, 500, ((i+1)*30)+250));
			lines4.get(i).setStyle(2, 0);
		}
		for (int i = 0; i < 5; i++) {
			lines4.add(new Line(0, ((i+1)*-10)+250, 500, ((i+1)*-30)+250));
			lines4.get(i+5).setStyle(2, 0);
		}
		lines4.add(new Line(0, 250, 500, 250));
		lines4.get(10).setStyle(2, 0);
		rect4.add(new Rectangle(225, 175, 5, 150));
		rect4.get(0).setFill(true);
		rect4.get(0).setStyle(0, 0, 84, 219);
		rect4.add(new Rectangle(300, 175, 5, 150));
		rect4.get(1).setFill(true);
		rect4.get(1).setStyle(0, 0, 84, 219);
		
	}
	
	public void draw() {
		
		if (illusionChoice == 1) {
			illusion1();
		}
		if (illusionChoice == 2) {
			illusion2();
		}
		if (illusionChoice == 3) {
			illusion3();
		}
		if (illusionChoice == 4) {
			illusion4();
		}
		
		// ignore this
//		pushStyle();
//		fill(0);
//		ellipse(mouseX, mouseY, 10, 10);
//		popStyle();
		
	}
	
	public void illusion1() {
		background(255);
		//draws all 20 circles
		noFill();
		for (int i = 0; i < circles1.size(); i++) {
			circles1.get(i).draw(this);
		}
		rectangle1.draw(this);
	}
	
	public void illusion2() {
		background(255);
		noFill();
		for (int i = 0; i < rectangles2.size(); i++) {
			rectangles2.get(i).draw(this);
		}
	}
	
	public void illusion3() {
		background(255);
		noFill();
		for (int i = 0; i < lines3.size(); i++) {
			lines3.get(i).draw(this);
		}
		for (int i = 0; i < rect3.size(); i++) {
			rect3.get(i).draw(this);
		}
	}
	
	public void illusion4() {
		background(255);
		noFill();
		for (int i = 0; i < lines4.size(); i++) {
			lines4.get(i).draw(this);
		}
		rect4.get(0).draw(this);
		rect4.get(1).draw(this);
	}
	
	public void keyPressed() {
		if (key == '1') {
			illusionChoice = 1;
		}
		if (key == '2') {
			illusionChoice = 2;
		}
		if (key == '3') {
			illusionChoice = 3;
		}
		if (key == '4') {
			illusionChoice = 4;
		}
	}
	
}
