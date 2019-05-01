import processing.core.*;
import queasycam.*;

public class DrawingSurface extends PApplet {

	public static int SCALING_FACTOR = 3;
	
	// image
	PImage img;
	QueasyCam cam;
	
	public void settings() {
	  size(400*SCALING_FACTOR, 400*SCALING_FACTOR, P3D);
	}
	
	public void setup() {
		img = loadImage("Rock.jpg");
		textureMode(NORMAL);
		background(100);
		rectMode(CENTER);
		fill(51);
		stroke(255);
		cam = new QueasyCam(this);
		cam.speed = 5;
		cam.sensitivity = 0.3f;
	}

	public void draw() {
		
		// no mouse cursor
		noCursor();
		
//		pushMatrix();
//		translate(width/2, height/2, 0);
//		rotateZ(PI/8);
//		scale(SCALING_FACTOR); // scale should always directly precede shapes
//		rect(0, 0, 100, 100);
//		popMatrix();
		
		// set the camera to rotate with the mouse
		
		background(0);
		pushMatrix();
		scale(SCALING_FACTOR);
		box(200);
		popMatrix();
		
		
//		pushMatrix();
//		translate(width/2, height/2, 0);
//		rotateY(PI/8);
//		scale(SCALING_FACTOR);
//		box(50, 50, 50);
//		popMatrix();
		
	}
	
}
