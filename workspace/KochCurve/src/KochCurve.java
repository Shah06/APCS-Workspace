import processing.core.PApplet;
import java.awt.geom.Point2D;

/**
  @(#)KochCurve.java


  @author Atharva Shah
  @version 0.1
*/
public class KochCurve {

	// TODO

    public KochCurve(int level, int length) {
    	// TODO
    }
    
    public void draw(PApplet marker) {
    	drawKochCurve(/* TODO */);
    }

    private void drawKochCurve(int x1, int y1, int angle, int level, int length, PApplet marker) {
    	// base case
    	if (level <= 1) {
    		marker.line();
    	} else {
    		
    	}
    }

}
