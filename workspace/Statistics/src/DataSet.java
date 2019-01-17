import java.util.Map;
import java.util.Arrays;
import java.util.HashMap;

public class DataSet {
	
	private final int MAX_DATA = 10000;
	private int[] data;
	private int realLength;
	
	public void readData(String filename) {
		ArrayReader reader = new ArrayReader(filename);
		int[] data = new int[MAX_DATA];
		realLength = reader.fillArray(data);
		this.data = data;
	}
	
	public double calcMean() {
		long sum = 0;
		for (int i = 0; i < realLength; i++) {
			sum += data[i];
		}
		return ((double)sum/realLength);
	}
	
	public double calcStdDev() {
		double avg = calcMean();
		double sum = 0;
		for (int i = 0; i < realLength; i++) {
			sum += Math.pow(avg-data[i], 2);
		}
		sum /= realLength-1;
		return Math.sqrt(sum);
	}
	
	public int[] calcMode() {
		
		
		// returns one mode, sorry for the spaghetti code
		int[] arr = new int[realLength];
		for (int i = 0; i < realLength; i++) {
			arr[i] = data[i];
		}
		Arrays.sort(arr);
		
		int last = arr[0];
		int modeCount = 0;
		int mcount = 0;
		int mode = 0;
		for (int i = 1; i < arr.length; i++) {
			if (arr[i] == last) {
				modeCount++;
			} else {
				if (modeCount > mcount) {
					mcount = modeCount;
					mode = last;
				}
				last = arr[i];
				modeCount = 0;
			}
		}
		
		return new int[] {mode};
		
		
		
		// returns two modes but HashMap is outside requirement; feel free to uncomment and run if you want
//		Map<Integer, Integer> modeData = new HashMap<Integer, Integer>();
//		
//		for (int i = 0; i < realLength; i++) {
//			if (!modeData.containsKey(data[i])) {
//				modeData.put(data[i], 1);
//			} else {
//				int val = modeData.get(data[i]);
//				modeData.put(data[i], val+1);
//			}
//		}
//		
//		int tempMode = 0;
//		int tempMax = 0;
//		int anotherMode = 0;
//		int anotherMax = 0;
//		for (int j = 0; j < realLength; j++) {
//			if (modeData.get(data[j]) > tempMax) {
//				tempMax = modeData.get(data[j]);
//				tempMode = data[j];
//			} else if (modeData.get(data[j]) > anotherMax) {
//				anotherMax = modeData.get(data[j]);
//				anotherMode = data[j];
//			}
//		}
//		
//		// TODO print greatest value in map
//		if (tempMode == anotherMode) {
//			return new int[] {tempMode};
//		}
//		return new int[] {tempMode, anotherMode};
//		
	}
	
	// Precondition: data is non-null int array, size is the number of legitimate 
		// elements in data
		// Postcondition: Moves all integers that are not equal to val to the front of the array data, leaving the 
		// order of integers otherwise unchanged. Returns the new number of legitimate data 
		// elements.
		public int compact( int val ) {
			int count = 0;
			for (int i = 0; i < realLength; i++) {
				int j = data[i];
				if (val == j) {
					count++;
					realLength--;
					for (int k = i; k < realLength; k++) {
						data[k] = data[k+1];
					}
				}
			}
			return count;
		}
		
		public int[] getData() {
			int[] arr = new int[realLength];
			for (int i = 0; i < realLength; i++) {
				arr[i] = data[i];
			}
			return arr;
		}
	
	
	
	
}
