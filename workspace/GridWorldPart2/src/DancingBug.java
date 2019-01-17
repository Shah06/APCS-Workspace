

import info.gridworld.actor.Bug;
import info.gridworld.grid.Location;

/**
 * A <code>BoxBug</code> traces out a square "box" of a given size. <br />
 * The implementation of this class is testable on the AP CS A and AB exams.
 */
public class DancingBug extends Bug
{
    private int[] danceSteps;
    private int index = 0;
//    private boolean hasAlreadyDanced = false;
    
    /**
     * Constructs a box bug that traces a square of a given side length
     * @param length the side length
     */
    public DancingBug(int[] danceSteps)
    {
        this.danceSteps = danceSteps;
    }

    public void act() {
    	// code for turning
//    	if (!hasAlreadyDanced) {
//    		if (index+1 > danceSteps.length) {
//        		index = 0;
//        	}
//        	for (int i = 0; i < danceSteps[index]; i++) {
//        		setDirection(getDirection() + Location.HALF_RIGHT);
//    		}
//        	index++;
//        	hasAlreadyDanced = true;
//        	return;
//    	}
//    	super.act();
//    	hasAlreadyDanced = false;
    	
    	
		if (index+1 > danceSteps.length) {
    		index = 0;
    	}
    	for (int i = 0; i < danceSteps[index]; i++) {
    		setDirection(getDirection() + Location.HALF_RIGHT);
		}
    	index++;
    	super.act();
    }
    
    
}
