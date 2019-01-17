
import info.gridworld.actor.Actor;
import info.gridworld.actor.Bug;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

/**
 * A <code>ChallengeBug</code> traces out a square "box" of a given size. <br />
 * The implementation of this class is testable on the AP CS A and AB exams.
 */
public class ChallengeBug extends Bug
{
	
	public int turnsCompleted = 0;
	
	public void act() {
		if (wantsToMove()) {
			move();
			turnsCompleted = 0;
		}
		else if (super.canMove()) {
			if (hasToMove()) {
				super.move();
				turnsCompleted = 0;
			}
			else {
				turn();
				turnsCompleted++;
			}
		}
		else {
			turn();
		}
	}
	
//	public boolean canMove() {
//		if (wantsToMove()) {
//			return true;
//		}
//		else if (hasToMove()) {
//			turnsCompleted = 0;
//			return true;
//		}
//		return false;
//	}
	
	public boolean wantsToMove() {
		Grid<Actor> gr = getGrid();
        if (gr == null)
            return false;
        Location loc = getLocation();
        Location next = loc.getAdjacentLocation(getDirection());
        if (!gr.isValid(next))
            return false;
        Actor neighbor = gr.get(next);
        return (neighbor == null);
	}
	
	public boolean hasToMove() {
		if (turnsCompleted > 7) {
			return true;
		}
		return false;
	}
    
}
