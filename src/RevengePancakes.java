import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.HashSet;
import java.util.Scanner;

/**
 * https://code.google.com/codejam/contest/dashboard?c=6254486#s=p1
 * 
 * @author farzo
 *
 */

public class RevengePancakes {
//could be done much much better by going only once through the list and keeping the last status
	public static void main(String[] args) throws FileNotFoundException {
		 Scanner reader = new Scanner(new File("data/B-large-practice.in"));
		 PrintStream out = new PrintStream(new
		 FileOutputStream("data/B-large-practice.out"));
		 System.setOut(out);
		//Scanner reader = new Scanner(System.in);
		int testNum = reader.nextInt();

		for (int t = 0; t < testNum; t++) {
			char[] pancakes = reader.next().toCharArray();
			int count = 0;
			while (true) {
				int index = 1;
				while (index < pancakes.length
						&& pancakes[index - 1] == pancakes[index]) {
					index++;
				}
				if (index < pancakes.length) {
					count++;
					for (int i = 0; i < index; i++) {
						if (pancakes[i] == '+') {
							pancakes[i] = '-';
						} else {
							pancakes[i] = '+';
						}
					}
				} else {
					if (pancakes.length > 0 && pancakes[0] == '-') {
						count++;
					}
					break;
				}
			}
			printOutput(t + 1, count);
		}
	}


	private static void printOutput(int caseNumber, int output) {
		System.out.println("Case #" + caseNumber + ": " + output);
	}

}
