import processing.core.*;

public class Map {

	PShape map;
	PApplet marker;
	
	public Map(String filepath, PApplet marker) {
		map = marker.loadShape(filepath);
		this.marker = marker;
	}
	
	public void draw() {
		marker.shape(map, 0, 0);
	}
	
	public void rotate() {
		map.rotateX(PApplet.PI/200);
	}
	
	public float getWidth() {
		return map.getWidth();
	}
	
	public float getHeight() {
		return map.getHeight();
	}
	
	public float getDepth() {
		return map.getDepth();
	}
	
}
