import java.util.Arrays;

public class StatisticsTester {
	
	public static void main(String[] args) {
		
		DataSet d1 = new DataSet();
		d1.readData("data\\numbers.txt");
		System.out.println("Mean: " + d1.calcMean());
		System.out.println("Standard dev: " + d1.calcStdDev());
		System.out.println("mode: " + Arrays.toString(d1.calcMode()));
		System.out.println();
		
		DataSet d2 = new DataSet();
		d2.readData("data\\numbers2.txt");
		System.out.println("Mean: " + d2.calcMean());
		System.out.println("Standard dev: " + d2.calcStdDev());
		System.out.println("mode: " + Arrays.toString(d2.calcMode()));
		System.out.println();
		
		DataSet d3 = new DataSet();
		d3.readData("data\\numbers3.txt");
		System.out.println("Mean: " + d3.calcMean());
		System.out.println("Standard dev: " + d3.calcStdDev());
		System.out.println("mode: " + Arrays.toString(d3.calcMode()));
		System.out.println();
		
		DataSet d4 = new DataSet();
		d4.readData("data\\numbers4.txt");
		System.out.println("Mean: " + d4.calcMean());
		System.out.println("Standard dev: " + d4.calcStdDev());
		System.out.println("mode: " + Arrays.toString(d4.calcMode()));
		System.out.println();
		
		DataSet d5 = new DataSet();
		d5.readData("data\\numbers3.txt");
		d5.compact(0);
		System.out.println(Arrays.toString(d5.getData()));
		
	}
	
}
