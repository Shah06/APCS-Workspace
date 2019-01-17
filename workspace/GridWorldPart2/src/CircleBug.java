
import info.gridworld.*;
import info.gridworld.grid.Location;

public class CircleBug extends BoxBug {

	public CircleBug(int length) {
		super(length);
	}
	
	public void turn() {
		// 45 degrees instead of 90
		super.turn();
		setDirection(getDirection() + Location.HALF_LEFT);
	}
	
}
