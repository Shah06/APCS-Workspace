
import info.gridworld.*;
import info.gridworld.grid.Location;

public class ZBug extends BoxBug {

	private boolean moveable = true;
	private int sideCounter = 0;
	
	public ZBug(int length) {
		super(length);
		setDirection(Location.EAST);
	}
	
	public void turn() {
		if (moveable) {
			if (sideCounter == 0) {
				super.turn();
				setDirection(Location.SOUTHWEST);
				sideCounter++;
				return;
			}
			else if (sideCounter == 1) {
				super.turn();
				setDirection(Location.EAST);
				sideCounter++;
				return;
			}
			else {
				moveable = false;
			}
		}
	}
	
	public boolean canMove() {
		if (moveable) {
			return super.canMove();
		}
		return false;
	}
	
	
}
