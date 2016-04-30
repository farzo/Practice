import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.sun.xml.internal.ws.util.StringUtils;

/**
 * https://code.google.com/codejam/contest/4304486/dashboard#s=p1
 * 
 * @author farzo
 *
 */

public class RankAndFile {
	public static void main(String[] args) throws FileNotFoundException {
		 Scanner reader = new Scanner(new File("data/B-large-practice.in"));
		 PrintStream out = new PrintStream(new
		 FileOutputStream("data/B-large-practice.out"));
		 System.setOut(out);
	//	Scanner reader = new Scanner(System.in);
		int testNum = reader.nextInt();

		for (int t = 0; t < testNum; t++) {
			int n = reader.nextInt();
			HashMap<Integer, Integer> counts = new HashMap<Integer, Integer>();
			for (int i = 0; i < n * (2 * n - 1); i++) {
				
				int height = reader.nextInt();
				Integer count = counts.get(height);
				if(count == null) {
					counts.put(height, 1);
				} else {
					counts.put(height, count + 1);
				}
			}
			ArrayList<Integer> missing = new ArrayList<Integer>();
			for (Map.Entry<Integer, Integer> entry : counts.entrySet()) {
				if (entry.getValue()%2 != 0) {
					missing.add(entry.getKey());
				}
			}
			Collections.sort(missing);
			printOutput(t + 1, missing);
		}
	}


	private static void printOutput(int caseNumber, ArrayList<Integer> output) {
		String elements = "";
		for (Integer i : output) {
			elements += i + " ";
		}
		System.out.println("Case #" + caseNumber + ": " + elements);
	}

}
