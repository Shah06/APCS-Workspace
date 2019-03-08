
public class ShelbyTester {

	public static void main(String[] args) {
		RecursiveStringTools runner = new RecursiveStringTools();
		
		System.out.println("matches");
		System.out.println("true : " + runner.matches("MOON", "MOON"));
		System.out.println("true : " + runner.matches("MOON", "M??N"));
		System.out.println("true : " + runner.matches("W?zar?", "?izard"));
		System.out.println("true : " + runner.matches("???", "???"));
		System.out.println("true : " + runner.matches("??????snake", "rattle?????"));
		System.out.println("false : " + runner.matches("MOON", "M??N?"));
		System.out.println("false : " + runner.matches("????", "???"));
		System.out.println("false : " + runner.matches("MANGO", "MONGO"));
		System.out.println();
		
		System.out.println("palindrome");
		System.out.println("true = " + runner.isPalindrome("racecar"));
		System.out.println("false = " + runner.isPalindrome("bananas"));
		System.out.println("true = " + runner.isPalindrome("\"Madam, I’m Adam?\""));
		System.out.println("false = " + runner.isPalindrome("Madam, I am Adam."));
		System.out.println("true = " + runner.isPalindrome("Are we not pure? \"No sir!\" Panama's moody Noriega brags. \"It is garbage!\" Irony dooms a man; a prisoner up to new era."));
		System.out.println();
		
//		System.out.println("permute");
//		runner.printPermutations("a","");
//		System.out.println();
//		runner.printPermutations("abc","");
//		System.out.println();
//		runner.printPermutations("duck","");
	}

}
