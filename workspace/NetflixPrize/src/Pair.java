
public class Pair {
	
	private User u1, u2;
	
	public Pair(User u1, User u2) {
		this.u1 = u1;
		this.u2 = u2;
	}
	
	public User getFront() {
		return u1;
	}
	
	public User getBack() {
		return u2;
	}
	
}
