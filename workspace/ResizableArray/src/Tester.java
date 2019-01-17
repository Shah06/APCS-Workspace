import java.util.Arrays;

public class Tester {
	public static void main(String[] args) {
		ResizableArray<String> a1 = new ResizableArray<>();
		a1.add("something");
		a1.add("asdfas dfkjash dkfjah sdkjf haskjdf aksdf");
		a1.add("another string");
		a1.add("last value");
		a1.add("last value");
		System.out.println(a1.toString());
		System.out.println(a1.size());
		a1.reverse();
		System.out.println(a1.toString());
		System.out.println(a1.get(0).equals(a1.get(1)));
	}
}
