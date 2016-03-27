import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Scanner;
/**
 * https://code.google.com/codejam/contest/dashboard?c=6224486
 * @author farzo
 *
 */

public class StandingOvation {

	public static void main(String[] args) throws FileNotFoundException {
		Scanner reader = new Scanner(new File("data/A-small-practice.in"));
		PrintStream out = new PrintStream(new FileOutputStream("data/A-small-practice.out"));
		System.setOut(out);
		
		int testNum = reader.nextInt();
		
		for (int i = 0; i < testNum ; i++) {
			int maxShyness = reader.nextInt();
			String shynesses = reader.next();
			// to check the correct input
			char[] digits = shynesses.toCharArray();
			
			if (digits.length != maxShyness + 1) {
				//EROOR
				System.out.println("ERROR");
				continue;
			}
			int count = 0;
			int needed = 0;
			for (int j = 0 ; j < digits.length - 1 ; j++) {
				count += Character.getNumericValue(digits[j]);
				if (count < j+1 && digits[j+1] != 0) {
					needed += j+1 - count;
					count += j+1 - count;
				}
			}
			System.out.println("Case #"+ (i+1) +": "+needed);
		}
	}
	
}
