import processing.core.*;

public class Map {

	PShape map;
	PApplet marker;
	PImage texture;
	
	public Map(String filepath, PApplet marker) {
		this.map = marker.loadShape(filepath);
		this.marker = marker;
		this.texture = null;
	}
	
	public void setTexture(PImage texture) {
		this.texture = texture;
	}
	
	public void draw() {
		marker.shape(map, 0, 0);
		if (null != texture) {
			map.setTexture(texture);
		}
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
