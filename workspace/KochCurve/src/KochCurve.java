import processing.core.PApplet;
import java.awt.geom.Point2D;

/**
  @(#)KochCurve.java


  @author Atharva Shah
  @version 0.1
*/
public class KochCurve {

	// TODO
	private int level;
	private float length;
	private float angle;
	private float x;
	private float y;
	
	public float getLevel() {
		return level;
	}
	public float getLength() {
		return length;
	}
	
    public KochCurve(float x, float y, int level, float length, float angle) {
    	this.x = x;
    	this.y = y;
    	this.angle = angle;
    	this.level = level;
    	this.length = length;
    }
    
    public void draw(PApplet marker) {
    	drawKochCurve(x, y, PApplet.radians(angle), level, length, marker);
    }

    private void drawKochCurve(float x1, float y1, float angle, int level, float length, PApplet marker) {
    	// base case
    	if (level < 1) {
    		marker.line(x1, y1, x1 + length * PApplet.cos(angle), y1 - length * PApplet.sin(angle));
    	} else {
    		
    		drawKochCurve(x1, y1, angle, level-1, length/3f, marker);
    		x1 = x1 + length/3f * PApplet.cos(angle);
    		y1 = y1 - length/3f * PApplet.sin(angle);
    		drawKochCurve(x1, y1, angle + PApplet.PI/3f, level-1, length/3f, marker);
    		x1 = x1 + length/3f * PApplet.cos(angle + PApplet.PI/3);
    		y1 = y1 - length/3f * PApplet.sin(angle + PApplet.PI/3);
    		drawKochCurve(x1, y1, angle - PApplet.PI/3f, level-1, length/3f, marker);
    		x1 = x1 + length/3f * PApplet.cos(angle - PApplet.PI/3f);
    		y1 = y1 - length/3f * PApplet.sin(angle - PApplet.PI/3f);
    		drawKochCurve(x1, y1, angle, level-1, length/3f, marker);
    		/*
    		Draw a k-1 level Koch curve of 1/3 the current length
  		  
    		Starting where the previous left off, draw a k-1 level Koch curve of 1/3 the current length, at an angle of 60 degrees with respect to the current angle
    		  
    		Starting where the previous left off, draw a k-1 level Koch curve of 1/3 the current length, at an angle of -60 degrees with respect to the current angle
    		  
    		Starting where the previous left off, draw a k-1 level Koch curve of 1/3 the current length
    		*/

    	}
    }

}
