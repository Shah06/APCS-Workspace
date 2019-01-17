import processing.core.PApplet;


public class Meteor {
	
	private float positionX;
	private float positionY;
	
	//private Line[] hitBox = new Line[2];
	
	public Meteor(float pos) {
		positionX = pos;
		positionY = -60F;
		//this.hitBox[0] = new Line(positionX-30, positionY, positionX+30, positionY+60);
		//this.hitBox[1] = new Line(positionX+30, positionY, positionX-30, positionY+60);
		
	}
	
	public void draw(PApplet drawer) {
		drawer.pushMatrix();
		
		drawer.strokeWeight(10);
		drawer.stroke(255, 0, 0);
		drawer.line(positionX-30, positionY, positionX-30, positionY-60);
		drawer.strokeWeight(1);
		drawer.stroke(0);
		
		// update hitbox
		//this.hitBox[0] = new Line(positionX-30, positionY, positionX+30, positionY+60);
		//this.hitBox[1] = new Line(positionX+30, positionY, positionX-30, positionY+60);
		
		//for (Line line : hitBox) {
		//	drawer.fill(0);
		//	line.draw(drawer);
		//	drawer.fill(255);
		//}
		
		fall();
		
		drawer.popMatrix();
	}
	
	private void fall() {
		this.positionY += 3;
		// update the hitbox
	}
	
	public float returnY() {
		return positionY;
	}
	
	//public Line[] getHitBox() {
	//	return hitBox;
	//}
	
	public float[] getPosition() {
		float[] pos = new float[] {positionX-30, positionY, positionX-30, positionY-60};
		return pos;
	}
	
}
