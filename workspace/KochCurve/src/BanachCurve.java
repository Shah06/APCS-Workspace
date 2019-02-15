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
	
	
	// parameters: 
	public void drawBanachCurve(float x, float y, float diameter, int level, PApplet marker) {
		
		// base case
		//TODO go from large to small
		if (level < 1) {
			marker.ellipse(x, y, diameter, diameter);
		}
		
		else {
			// circle in the middle
			drawBanachCurve(x, y, diameter/2, level-1, marker);
			
			//TODO 8 circles around
			drawBanachCurve(x + diameter, y, diameter/2, level-1, marker);
			drawBanachCurve(x - diameter, y, diameter/2, level-1, marker);
			drawBanachCurve(x, y + diameter, diameter/2, level-1, marker);
			drawBanachCurve(x, y - diameter, diameter/2, level-1, marker);
			drawBanachCurve(x + diameter*PApplet.sin(PApplet.PI/4), y + diameter*PApplet.sin(PApplet.PI/4), diameter/2, level-1, marker);
			drawBanachCurve(x + diameter*PApplet.sin(PApplet.PI/4), y - diameter*PApplet.sin(PApplet.PI/4), diameter/2, level-1, marker);
			drawBanachCurve(x - diameter*PApplet.sin(PApplet.PI/4), y + diameter*PApplet.sin(PApplet.PI/4), diameter/2, level-1, marker);
			drawBanachCurve(x - diameter*PApplet.sin(PApplet.PI/4), y - diameter*PApplet.sin(PApplet.PI/4), diameter/2, level-1, marker);
		}
		
		
	}
	
}
