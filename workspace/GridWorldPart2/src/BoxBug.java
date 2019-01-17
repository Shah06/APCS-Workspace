

import info.gridworld.actor.Bug;

/**
 * A <code>BoxBug</code> traces out a square "box" of a given size. <br />
 * The implementation of this class is testable on the AP CS A and AB exams.
 */
public class BoxBug extends Bug
{
    private int steps;
    private int sideLength;

    /**
     * Constructs a box bug that traces a square of a given side length
     * @param length the side length
     */
    public BoxBug(int length)
    {
        steps = 0;
        sideLength = length;
    }

    /**
     * Moves to the next location of the square.
     */
    
    /*
    public void act()
    {
        if (steps < sideLength && canMove())
        {
            move();
            steps++;
        }
        else
        {
            turn();
            turn();
            steps = 0;
        }
    }
    */
    
    public void turn() {
    	super.turn();
    	super.turn();
    	steps = 0;
    }
    
    public boolean canMove() {
    	return (steps < sideLength && super.canMove());
    }
    
    public void move() {
    	super.move();
    	steps++;
    }
    
    public void setSideLength(int length) {
    	this.sideLength = length;
    }
    
    public int getSideLength() {
    	return this.sideLength;
    }
    
    
}
