/*
 * This class simulates a elevator, including lights, motion, and door.
 *
 */

import java.awt.*;
import java.awt.geom.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.awt.event.*;
import javax.swing.*;


public class Elevator extends JPanel implements ActionListener, Serializable
{
  
	private static final long serialVersionUID = 1L;
	
	private static final int ACT_LAG_TIME = 2000;
	
	
	// GUI COMPONENTS
	private transient JButton swingAct;
	private transient JButton[] swingButtons;
	private transient Timer clock;
	
	private int floor;
	
	// OUTPUTS
	private boolean doorOpen, lightUp, lightDown;
	private boolean[] direction;
	
	// INPUTS
	private boolean[] buttons;
	
	
  public Elevator (JButton swingAct, JButton[] swingButtons) {
	  super();
	  this.swingButtons = swingButtons;
	  this.swingAct = swingAct;
	  floor = 1;
	  doorOpen = true;
	  direction = new boolean[2];
	  buttons = new boolean[3];
	  setBackground(Color.WHITE);
  	  clock = new Timer(ACT_LAG_TIME,this);
  }
  
  
  public static void saveState(Elevator e, String filename) {
	  FileOutputStream fos;
	  ObjectOutputStream oos = null;
	  try {
		  fos = new FileOutputStream(filename);
		  oos = new ObjectOutputStream(fos);
		  oos.writeObject(e);
	  } catch (IOException ioe) {
		  ioe.printStackTrace();
	  } finally {
		  if (null != oos) {
			  try {
				oos.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		  }
	  }
  }
  
  public static Elevator loadState(String filename) {
	  FileInputStream fis;
	  ObjectInputStream ois = null;
	  try {
		  fis = new FileInputStream(filename);
		  ois = new ObjectInputStream(fis);
		  Object o = ois.readObject();
		  return (Elevator) o;
	  } catch (IOException ioe) {
		  ioe.printStackTrace();
	  } catch (ClassNotFoundException cnfe) {
		  cnfe.printStackTrace();
	  } finally {
		  if (null != ois) {
			  try {
				ois.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		  }
	  }
	  
	  return null; // in case its invalid
	  
  }
  
  public void initializeGUI(JButton swingAct, JButton[] swingButtons) {
	  this.swingButtons = swingButtons;
	  this.swingAct = swingAct;
	  clock = new Timer(ACT_LAG_TIME,this);
  }
  

  public void act() {
  	swingAct.setEnabled(false);
  	clock.restart();
  	setFloor(floor);
  	repaint();
  }
  
  public void actionPerformed(ActionEvent e) {
  	clock.stop();
  	if (direction[0] && direction[1])
  		floor++;
  	else if (!direction[0] && direction[1])
  		floor--;
  	else if (direction[0] && !direction[1])
  		throw new RuntimeException("Error - Elevator got bad move code: 01");
  	if (floor < 1 || floor > 3)
  		throw new RuntimeException("Error - Elevator moved to bad floor number: " + floor);
  	setFloor(floor);
  	repaint();
  	resetButton();
  	swingAct.setEnabled(true);
  }

  public void setFloor(int f) {
  	floor = f;

  	boolean[] floorBits = new boolean[2];
  	for (int i = 0; i < 2; i++) {
  	  	int mask = (int)Math.pow(2,i);
  	  	floorBits[i] = ((floor & mask) == mask);
  	}

  	// Inputs consist of:
  	// floorBits[2] (in which floorBits[0] is the least significant bit)
  	// buttons[3] (in which buttons[0] represents the first floor button)

  	// Problem 4:
  	doorOpen = floorBits[1] && ((floorBits[0] && buttons[2]) || (!floorBits[0] && buttons[1])) || (!floorBits[1] && buttons[0]) || (!buttons[0] && !buttons[1] && !buttons[2]);

  	// Problem 5:
  	direction[0] = (floorBits[0] && !floorBits[1] && !buttons[0] && (buttons[1] || buttons[2])) || (!floorBits[0] && floorBits[1] && buttons[2] && !buttons[1]);
  	direction[1] = (!floorBits[1] && !buttons[0] && buttons[1]) ||
  			(floorBits[0] && floorBits[1] && buttons[0] && !buttons[2]) ||
  			(floorBits[0] && !floorBits[1] && !buttons[0] && buttons[2]) ||
  			(floorBits[0] && !buttons[0] && buttons[1] && !buttons[2]) ||
  			(!floorBits[0] && buttons[0] && !buttons[1]) || 
  			(!floorBits[0] && !buttons[1] && buttons[2]);
  	// Problem 6:
  	lightUp = (!floorBits[1] && floorBits[0]) || (buttons[2] && !floorBits[0]);
  	lightDown = (floorBits[1] && (floorBits[0] || !buttons[2]));

  }

  public void resetButton() {
  	buttons[floor-1] = false;
  	swingButtons[floor-1].setBackground(Color.LIGHT_GRAY);
  }

  public void pushButton(int b) {
  	buttons[b] = true;
  	swingButtons[b].setBackground(Color.RED);
  }

  public boolean buttonOn(int b) {
  	return buttons[b];
  }


  public void paintComponent(Graphics g)
  {

    super.paintComponent(g);

	Graphics2D g2 = (Graphics2D)g;
	g2.setColor(Color.BLACK);

    int width = getWidth();
    int height = getHeight();

    g2.drawRect(width/32,height/32,30*width/32,30*height/32);
    g2.drawLine(width/32,height/3,31*width/32,height/3);
    g2.drawLine(width/32,2*height/3,31*width/32,2*height/3);

    for (int i = 0; i < 3; i++) {
    	g2.setColor(Color.BLACK);

    	Rectangle2D.Double elevator = new Rectangle2D.Double(7*width/16,i*height/3 + height/16,width/8,7*height/32);
    	if (floor == 3-i) {
    		if (doorOpen) {
    			g2.setColor(Color.RED);
    			g2.fill(elevator);
    			g2.setColor(Color.BLACK);
		    	g2.draw(elevator);
		    	g2.fillRect((int)elevator.getX(),(int)elevator.getY(),width/32,7*height/32);
		    	g2.fillRect((int)(elevator.getX()+elevator.getWidth()) - width/32,(int)elevator.getY(),width/32,7*height/32);
		    } else {
		    	g2.fill(elevator);
		    }

		    g2.setColor(Color.BLUE);
			if (direction[0] == true && direction[1] == true) {
				g2.fillRect(5*width/16,(int)elevator.getY() + height/32-2,width/32,5*height/32+4);
				Polygon arrow = new Polygon();
				arrow.addPoint(5*width/16 + width/64,(int)elevator.getY()-height/64);
				arrow.addPoint(5*width/16 - 3*width/64,(int)elevator.getY() + height/32);
				arrow.addPoint(5*width/16 + 5*width/64,(int)elevator.getY() + height/32);
				g2.fill(arrow);
			} else if (direction[0] == false && direction[1] == true) {
				g2.fillRect(5*width/16,(int)elevator.getY() + height/32-2,width/32,5*height/32+4);
				Polygon arrow = new Polygon();
				arrow.addPoint(5*width/16 + width/64,(int)elevator.getY()+15*height/64);
				arrow.addPoint(5*width/16 - 3*width/64,(int)elevator.getY() + 6*height/32);
				arrow.addPoint(5*width/16 + 5*width/64,(int)elevator.getY() + 6*height/32);
				g2.fill(arrow);
			} else if (direction[0] == false && direction[1] == false) {
				g2.fillOval(4*width/16,(int)elevator.getY() + 3*height/32-2,3*width/32,2*height/32+4);
			}

    	} else {
    		g2.draw(elevator);
    	}

    	Polygon upPoly = new Polygon();
	    upPoly.addPoint(21*width/32,4*height/32+i*height/3);
	    upPoly.addPoint(20*width/32,5*height/32+i*height/3);
	    upPoly.addPoint(22*width/32,5*height/32+i*height/3);
	    Polygon downPoly = new Polygon();
	    downPoly.addPoint(21*width/32,7*height/32+i*height/3);
	    downPoly.addPoint(20*width/32,6*height/32+i*height/3);
	    downPoly.addPoint(22*width/32,6*height/32+i*height/3);

	    if (lightUp)
	    	g2.setColor(Color.YELLOW);
	    else
	    	g2.setColor(Color.GRAY);
	    g2.fill(upPoly);

	    if (lightDown)
	    	g2.setColor(Color.YELLOW);
	    else
	    	g2.setColor(Color.GRAY);
	    g2.fill(downPoly);

    }


  }

}















