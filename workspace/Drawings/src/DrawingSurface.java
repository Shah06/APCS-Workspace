import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import processing.awt.PSurfaceAWT;
import processing.core.PApplet;
import java.util.ArrayList;
import javax.swing.Timer;
import java.util.Stack;

/* + All fields are private
 * + The movement of the person is very smooth
 * - The person can move outside of the screen and dodge all meteors
 * - The house move point changes as it's scaled
 * ^ Make the person unable to leave the original window edges
 * ^ Change the way you translate the house so that the move point remains the same as it's scaled
*/
public class DrawingSurface extends PApplet implements ActionListener {
	
	private House house;
	private Person person, otherPerson;
	
	// 5 second countdown on 60hz monitor
	private int countdown = 300;
	private boolean gameOver = false;
	
	// use of ArrayList so # of meteors can easily be tweaked
	private ArrayList<Meteor> meteors = new ArrayList<Meteor>();
	
	// variables for smooth movement
	private int moveUP;
	private int moveSIDE;
	
	private int[] background = new int[] {255, 255, 255};
	
	private void checkBackground() {
		// limit background to 0 and 255
		if (background [0] < 0) {
			background[0] = 0;
		}
		if (background [1] < 0) {
			background[1] = 0;
		}
		if (background [2] < 0) {
			background[2] = 0;
		}
		if (background [0] > 255) {
			background[0] = 255;
		}
		if (background [1] > 255) {
			background[1] = 255;
		}
		if (background [2] > 255) {
			background[2] = 255;
		}
	}
	
	public DrawingSurface() {
		house = new House();
		person = new Person();
		otherPerson = new Person(300, 0);
		for (int i = 0; i < 6; i++) {
			meteors.add(new Meteor(random(0F, 500F)));
		}
	}
	
	public void draw() {
		
		if (!gameOver) {
			checkBackground();
			background(background[0], background[1], background[2]);
		}
		if (gameOver) {
			rect(0, 0, width, height);
			fill(255, 0, 0);
			textSize(72);
			textAlign(CENTER);
			text("YOU DIED", width/2, height/2);
		}
		else {
			house.draw(this);
			person.draw(this);
			otherPerson.draw(this);
		}
		
		// countdown timer
		if (countdown != 0) {
			fill(0);
			textSize(30);
			textAlign(CENTER);
			text(countdown/60+"", width/2, 50);
			countdown--;
		}
		
		else {
			// iterate through each meteor and move it
			for (int i = 0; i < meteors.size(); i++) {
				
				
				
				// collision detection
				if (person.hitsMeteor(meteors.get(i).getPosition())) {
					System.out.println("hit person");
					person.die();
					gameOver = true;
					new Timer(2000, this).start();
					// if person is dead, new countdown timer and reset "meteors"
					countdown = 300;
					for (int k = meteors.size(); k > 0; k--) {
						meteors.remove(k-1);
						meteors.add(new Meteor(random(0F, 500F)));
					}
				}
				
				
				
				
				
				if (meteors.get(i).returnY() > 500) {
					meteors.add(new Meteor(random(0F, 500F)));
					meteors.remove(i);
				}
				else {
					meteors.get(i).draw(this);
				}
			}
		}
		
		textAlign(CENTER);
		textSize(12);
		text("Interactions: wasd, arrow keys, mouse click,\n'r'/'g'/'b'/'f',  SHIFT + 'r'/'g'/'b', 1, 2, 9, 0", width/2, height-50);
		
		
		if (!gameOver) {
			person.move(0, moveUP);
			person.move(moveSIDE, 0);
		}
	}
	
	public void mousePressed() {
		house.move(mouseX-200, mouseY-125);
	}
	
	public void keyPressed() {
		if (!gameOver) {
			// Scale house up or down
			if (key == '2') {
				house.scaleUp();
			}
			else if (key == '1') {
				house.scaleDown();
			}
			
			// WASD for person
			/*
			 * if (key == 'W' || key == 'w') {
				person.move(0, -15);
			}
			if (key == 'A' || key == 'a') {
				person.move(-15, 0);
			}
			if (key == 'S' || key == 's') {
				person.move(0, 15);
			}
			if (key == 'D' || key == 'd') {
				person.move(+15, 0);
			}
			 */
			
			// TODO better WASD
			if (key == 'w') {
				moveUP = -4;
			} else if (key == 's') {
				moveUP = 4;
			} else if (key == 'd') {
				moveSIDE = 4;
			} else if (key == 'a') {
				moveSIDE = -4;
			}
			
			// arrow keys for person
			if (keyCode == RIGHT) {
				otherPerson.move(+15, 0);
			}
			if (keyCode == LEFT) {
				otherPerson.move(-15, 0);
			}
			if (keyCode == UP) {
				otherPerson.move(0, -15);
			}
			if (keyCode == DOWN) {
				otherPerson.move(0, 15);
			}
			
			// color slider
			if (key == 'R') {
				background[0] -= 15;
			}
			if (key == 'r') {
				background[1] -= 15;
				background[2] -= 15;
			}
			
			if (key == 'G') {
				background[1] -= 15;
			}
			if (key == 'g') {
				background[0] -= 15;
				background[2] -= 15;
			}
			
			if (key == 'B') {
				background[2] -= 15;
			}
			if (key == 'b') {
				background[1] -= 15;
				background[0] -= 15;
			}
			
			if (key == '9') {
				background[0] -= 15;
				background[1] -= 15;
				background[2] -= 15;
			}
			if (key == '0') {
				background[0] += 15;
				background[1] += 15;
				background[2] += 15;
			}
			if (key == 'f') {
				background[0] = 255;
				background[1] = 255;
				background[2] = 255;
			}
		}
		
	}
	
	public void keyReleased() {
		if (key == 'w' || key == 's') {
			moveUP = 0;
		} else if (key == 'a' || key == 'd') {
			moveSIDE = 0;
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		gameOver = false;
	}
	
	

	
	

}
