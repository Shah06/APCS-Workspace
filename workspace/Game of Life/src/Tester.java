import processing.core.*;


public class Tester{
	
	public static void main(String[] args) {
		Life board = new Life("data\\life100.txt");
//		PApplet.main("DrawingSurface"); // TODO
		board.step(5);
		System.out.println(board);
		
	}
}
