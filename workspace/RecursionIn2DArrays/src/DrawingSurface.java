import processing.core.PApplet;

public class DrawingSurface extends PApplet {

	private char[] grid;
	
	public DrawingSurface() {}
	
	public DrawingSurface(char[] grid) {
		this.grid = grid;
	}
	
	public void settings() {
		size(500, 500);
	}
	
	public void setup() {
		fill(255);
		surface.setResizable(false);
		surface.setFrameRate(60);
		
		Erase er = new Erase("C:\\Users\\atharva\\APCS-Workspace\\workspace\\RecursionIn2DArrays\\digital.txt");
		System.out.print(er.toString());
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
//		er.eraseObject(4, 2);
		er.eraseObject(7, 8);
		System.out.print(er.toString());
		System.out.println(er.getIterations() + " iteration(s)");
	}
	
}
