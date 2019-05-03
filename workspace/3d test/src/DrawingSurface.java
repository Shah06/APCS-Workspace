import processing.core.*;
import queasycam.*;

public class DrawingSurface extends PApplet {

	public static int SCALING_FACTOR = 300;
	
	// image
	PImage img;
	QueasyCam cam;
	
	// test map
	Map map;
	
	// test shape
	PShape shape;
	
	public void settings() {
		size(displayWidth, displayHeight, P3D);
//		size(800, 600, P3D);
		fullScreen();
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
		cam.sensitivity = 0.17f;
		cam.friction = 0.8f;
//		float fov = PI/3;
//		float cameraZ = (float) ((height/2.0) / tan ((float) (PI*60.0/360.0)));
//		perspective(fov, (float) width/height, cameraZ/10, cameraZ*100);
		perspective(); // solves issue where black bars would cover everything
//		shape = loadShape("model.obj");
		shapeMode(CORNER);
		map = new Map("shape.obj", this);
		map.setTexture(loadImage("Rock.jpg"));
	}

	PVector light;
	float x, y, z;
	PVector pos;
	float xPos, yPos, zPos;
	
	public void draw() {
		
		light = cam.getForward();
		x = light.x;
		y = light.y;
		z = light.z;
		
		pos = cam.position;
		xPos = pos.x;
		yPos = pos.y;
		zPos = pos.z;
		
		// no mouse cursor
		noCursor();
		background(0);
		lights();
		ambientLight(255, 255, 255);
		noFill();
//		ambientLight(75, 75, 75);
//		spotLight(  0,   0, 255, xPos, yPos, zPos, x, y, z, PI/2, 2);
		spotLight( 255, 255, 255, xPos, yPos, zPos, x, y, z, PI/4, 2);
//		spotLight(255,   0,   0, xPos, yPos, zPos, x, y, z, PI/6, 20);
		
		pushMatrix();
		pushMatrix();
		scale(SCALING_FACTOR);
		map.draw();
		popMatrix();
//		box(map.getWidth()*SCALING_FACTOR, map.getHeight()*SCALING_FACTOR, map.getDepth()*SCALING_FACTOR); // bounding rectangles
		popMatrix();
//		
//		// test shape
//		pushMatrix();
//		scale(SCALING_FACTOR);
//		translate(0, 0, 0); // could translate by some amount
//		shape(shape, 0, 0);
//		popMatrix();
//		
//		pushMatrix();
//		box(shape.getWidth()*SCALING_FACTOR, shape.getHeight()*SCALING_FACTOR, shape.getDepth()*SCALING_FACTOR);
//		popMatrix();
//					
		
	}
	
}
