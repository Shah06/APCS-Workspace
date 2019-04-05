import java.util.ArrayList;

public class MVector {
	
	private ArrayList<Boolean> data;
	
	public MVector() {
		data = new ArrayList<Boolean>();
	}
	
	public void addSpace() {
		data.add(new Boolean(false));
	}
	
	public void nextOn() {
		data.add(new Boolean(true));
	}
	
	public int size() {
		return data.size();
	}
	
	public int get(int index) {
		if (data.get(index)) {
			return 1;
		} else {
			return 0;
		}
	}
	
}
