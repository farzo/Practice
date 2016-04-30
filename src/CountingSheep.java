import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.HashSet;
import java.util.Scanner;
/**
 * https://code.google.com/codejam/contest/dashboard?c=6254486#s=p0
 * @author farzo
 *
 */

public class CountingSheep {

	private static final String INSOMNIA = "INSOMNIA";

	public static void main(String[] args) throws FileNotFoundException {
		Scanner reader = new Scanner(new File("data/A-large-practice.in"));
		PrintStream out = new PrintStream(new FileOutputStream("data/A-large-practice.out"));
		System.setOut(out);
		//Scanner reader = new Scanner(System.in);
		int testNum = reader.nextInt();
		
		for (int t = 0; t < testNum ; t++) {
			int n = reader.nextInt();
			int lastNumber = n;
			HashSet<Integer> seen = new HashSet<Integer>();
			int i = 1;
			while (seen.size() < 10) {
				int nextNumber = i * n;
				if (i > 1 && nextNumber == lastNumber) {
					break;
				}
				lastNumber = nextNumber;
				addDigitsToSet(seen, lastNumber);
				i++;
			}
			if (seen.size() == 10){
				printOutput(t+1, String.valueOf(n * (i-1)));
			}else {
				printOutput(t+1, INSOMNIA);
			}
			
		}
	}

	private static void addDigitsToSet(HashSet<Integer> seen, int lastNumber) {
		String str = String.valueOf(lastNumber);
		for (char c : str.toCharArray()) {
			seen.add(Character.getNumericValue(c));
		}
	}

	private static void printOutput(int caseNumber, String lastNumber) {
		System.out.println("Case #"+ caseNumber +": "+lastNumber);
	}
	
	
}
