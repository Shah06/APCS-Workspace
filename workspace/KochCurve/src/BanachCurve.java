import processing.core.PApplet;

public class BanachCurve extends PApplet {

	//TODO
	private float x;
	private float y;
	private float diameter;
	private int level;
	
	public BanachCurve(float x, float y, float diameter, int level) {
		this.x = x;
		this.y = y;
		this.diameter = diameter;
		this.level = level;
	}
	
	
	public void draw(PApplet marker) {
		drawBanachCurve(x, y, diameter, level, marker);
	}
	
	public void drawBanachCurve(float x, float y, float diameter, int level, PApplet marker) {
		// base case
		if (level < 1) {
			marker.ellipse(x, y, diameter, diameter);
		}
		// recursive case
		else {
//			diameter /= PApplet.PI;
			diameter /= 3.4;
			// 8 circles around with smaller diameter
			drawBanachCurve(x + diameter, y, diameter, level-1, marker);
			drawBanachCurve(x - diameter, y, diameter, level-1, marker);
			drawBanachCurve(x, y + diameter, diameter, level-1, marker);
			drawBanachCurve(x, y - diameter, diameter, level-1, marker);
			
			drawBanachCurve(x + diameter*PApplet.sin(PApplet.PI/4f), y + diameter*PApplet.sin(PApplet.PI/4f), diameter, level-1, marker);
			drawBanachCurve(x + diameter*PApplet.sin(PApplet.PI/4f), y - diameter*PApplet.sin(PApplet.PI/4f), diameter, level-1, marker);
			drawBanachCurve(x - diameter*PApplet.sin(PApplet.PI/4f), y + diameter*PApplet.sin(PApplet.PI/4f), diameter, level-1, marker);
			drawBanachCurve(x - diameter*PApplet.sin(PApplet.PI/4f), y - diameter*PApplet.sin(PApplet.PI/4f), diameter, level-1, marker);
			
			drawBanachCurve(x, y, diameter, level-1, marker);
		}
	}
	
	public int getLevel() {
		return level;
	}
	
	
}
