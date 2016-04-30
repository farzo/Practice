import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * https://code.google.com/codejam/contest/4304486/dashboard
 * 
 * @author farzo
 *
 */

public class TheLastWord {
	public static void main(String[] args) throws FileNotFoundException {
		 Scanner reader = new Scanner(new File("data/A-large-practice.in"));
		 PrintStream out = new PrintStream(new
		 FileOutputStream("data/A-large-practice.out"));
		 System.setOut(out);
	//	Scanner reader = new Scanner(System.in);
		int testNum = reader.nextInt();

		for (int t = 0; t < testNum; t++) {
			char[] chars = reader.next().toCharArray();
			String lastWord = "";
			for (char c : chars) {
				if (lastWord.length() == 0 || lastWord.charAt(0) > c) {
					lastWord = lastWord + c;
				} else {
					lastWord = c + lastWord;
				}
			}
			
			printOutput(t + 1, lastWord);
		}
	}


	private static void printOutput(int caseNumber, String output) {
		System.out.println("Case #" + caseNumber + ": " + output);
	}

}
