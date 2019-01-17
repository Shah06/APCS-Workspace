import processing.core.PApplet;

public class Person {
	private int x, y;
	private Line[] hitBox = new Line[4];
	private boolean alive = true;
	
	public Person() {
		
	}
	
	public Person(int x, int y) {
		this.x += x;
		this.y += y;
	}
	
	public void draw(PApplet drawer) {
		
		drawer.pushMatrix();
		drawer.strokeWeight(2.5F);
		
		if (!alive) {
			drawer.fill(255, 0, 0);
		}
		
		drawer.translate(x, y);
		drawer.noFill();
		drawer.rect(50, 50, 50, 50);
		drawer.line(75, 100, 75, 170);
		drawer.line(75, 170, 25, 250);
		drawer.line(75, 170, 125, 250);
		
		this.hitBox[0] = new Line(50+x, 50+y, 100+x, 50+y); // y max
		this.hitBox[1] = new Line(100+x, 50+y, 125+x, 250+y); // x max
		this.hitBox[2] = new Line(125+x, 250+y, 25+x, 250+y); // x min
		this.hitBox[3] = new Line(25+x, 250+y, 50+x, 50+y); // y min
		
		alive = true;
		
		drawer.strokeWeight(1);
		
		drawer.popMatrix();
		
	}
	
	public void move(int x, int y) {
		this.x += x;
		this.y += y;
	}
	
	public boolean hitsMeteor(float[] coordinates) {
		Line other = new Line(coordinates[0], coordinates[1], coordinates[2], coordinates[3]);
		for (Line line : hitBox) {
			if (line.intersects(other)) {
				return true;
			}
		}
		return false;
	}
	
	public void die() {
		alive = false;
		this.x = 0;
		this.y = 0;
	}
	
	public boolean canMoveLeft() {
		if (this.hitBox[1].returnCoordinates()[2] > 500) {
			return false;
		}
		return true;
	}
	public boolean canMoveRight() {
		if (this.hitBox[2].returnCoordinates()[2] < 0) {
			return false;
		}
		return true;
	}
	public boolean canMoveUp() {
		if (this.hitBox[0].returnCoordinates()[1] < 0) {
			return false;
		}
		return true;
	}
	public boolean canMoveDown() {
		if (this.hitBox[3].returnCoordinates()[2] > 500) {
			return false;
		}
		return true;
	}
	
}
