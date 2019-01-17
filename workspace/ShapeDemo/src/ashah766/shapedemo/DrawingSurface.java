package ashah766.shapedemo;
import processing.core.PApplet;
import java.awt.geom.Point2D;
import smalani530.shapes.*;
import java.util.ArrayList;

public class DrawingSurface extends PApplet {
	
	private ArrayList<PhysicsShape> rectangles;
//	private ArrayList<PhysicsShape> circles;
	private PhysicsShape shape1;
//	private PhysicsShape shape2;
	private Rectangle bounds;
	private Rectangle slowRegion;
	private Rectangle fastRegion;
	
	public DrawingSurface() {
		shape1 = new PhysicsShape(new Circle(250, 40, 40));
		shape1.setFill(255, 255, 255);
		shape1.setUk(0.99);
//		shape2 = new PhysicsShape(new Rectangle(130,130,60,70));
//		shape2.setFill(255, 255, 255);
		rectangles = new ArrayList<PhysicsShape>();
//		circles = new ArrayList<PhysicsShape>();
		
		slowRegion = new Rectangle(0,0,250,500);
		slowRegion.setFill(255, 58, 58);
		fastRegion = new Rectangle(250,0,250,500);
		fastRegion.setFill(26, 224, 63);
	}
	
	
	public void settings() {
		size(500, 500);
	}
	
	public void setup() {
		fill(255);
		surface.setResizable(false);
		surface.setFrameRate(60);
		bounds = new Rectangle(0,0,width,height); // have to declare here
	}
	
	public void draw() {
		background(255);
		
		// draws fast and slow region
		slowRegion.draw(this);
		fastRegion.draw(this);
		
		shape1.draw(this);
		shape1.checkPath(bounds);
		if (shape1.isInsideOf(fastRegion)) {
			shape1.setVScale(1.3);
		} else if (shape1.isInsideOf(slowRegion)) {
			shape1.setVScale(0.3);
		}
		shape1.act();
		
//		// draws entire circles array
//		for (int i = 0; i < circles.size(); i++) {	
//			circles.get(i).draw(this);
//			circles.get(i).checkPath(bounds);
//			circles.get(i).act();
//		}
		// draws entire rectangles array
		for (int k = 0; k < rectangles.size(); k++) {
			rectangles.get(k).draw(this);
			rectangles.get(k).checkPath(bounds);
			if (rectangles.get(k).isInsideOf(fastRegion)) {
				rectangles.get(k).setVScale(1.3);
			} else if (rectangles.get(k).isInsideOf(slowRegion)) {
				rectangles.get(k).setVScale(0.3);
			}
			rectangles.get(k).setUk(1);
			rectangles.get(k).act();
		}
		
		textSize(20);
		text("interactions: click+drag mouse/press number 1", width/2, 440); 
		textAlign(CENTER);
		
	}
	
	
	public void mouseDragged() {
		shape1.move(super.mouseX, super.mouseY);
		shape1.setVelocity(super.mouseX-pmouseX, super.mouseY-pmouseY);
	}
	
	public void keyPressed() {
		if (key == '1') {
			rectangles.add(new PhysicsShape(new Rectangle(mouseX, mouseY, 25, 25)));
			rectangles.get(rectangles.size()-1).setVelocity(Math.random()*60-30, Math.random()*60-30);
			rectangles.get(rectangles.size()-1).setFill(255, 255, 255);
		}
	}
	
}
