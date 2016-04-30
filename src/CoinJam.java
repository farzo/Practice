import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;

import com.sun.xml.internal.ws.api.pipe.NextAction;

/**
 * https://code.google.com/codejam/contest/dashboard?c=6254486#s=p2
 * 
 * @author farzo
 *
 */

public class CoinJam {
	static int nextCandidate = 0;
	public static void main(String[] args) throws FileNotFoundException {
		 //Scanner reader = new Scanner(new File("data/B-large-practice.in"));
		 PrintStream out = new PrintStream(new
		 FileOutputStream("data/C-large-practice.out"));
		 System.setOut(out);
		Scanner reader = new Scanner(System.in);
		int testNum = reader.nextInt();

		for (int t = 0; t < testNum; t++) {
			nextCandidate = 0;
			System.out.println("Case #" + testNum + ": ");
			int n = reader.nextInt();
			int j = reader.nextInt();
			HashSet<String> candidates = new HashSet<String>();
			ArrayList<String> jamCoins = new ArrayList<String>();
			while (jamCoins.size() != j) {
				String candidate = generateRandomCandidate(n, candidates);
				ArrayList<BigInteger> devisors = isLegitimate(candidate);
				if (devisors != null && devisors.size() == 9) {
					jamCoins.add(candidate);
					printOutput(candidate, devisors);
				}
			}
		}
	}


	private static ArrayList<BigInteger> isLegitimate(String candidate) {
		ArrayList<BigInteger> devisors = new ArrayList<BigInteger>();
		for (int base = 2; base < 11; base++) {
			//System.out.println("checking "+candidate+ "in base "+base);
			BigInteger value = getValueInBase(candidate, base);
			BigInteger devisor = getDevisor(value);
			if (devisor == null) {
				//System.out.println("no devisor found");
				return null;
			}
			devisors.add(devisor);
		}
		return devisors;
	}


	private static BigInteger getDevisor(BigInteger value) {
		BigInteger j = new BigInteger("2");
		while (j.pow(2).compareTo(value) <= 0) {
			if (value.mod(j).compareTo(BigInteger.ZERO) == 0) {
				return j;
			}
			j = j.add(BigInteger.ONE);
		}
		return null;
	}


	private static BigInteger getValueInBase(String candidate, int base) {
		BigInteger sum = BigInteger.ZERO;
		BigInteger bigBase = new BigInteger(String.valueOf(base));
		char[] digits = candidate.toCharArray();
		for (int i = 0; i < digits.length; i++) {
			
			sum = sum.add((bigBase.pow(i)).multiply(new BigInteger(String.valueOf(digits[i]))));
		}
		//System.out.println(candidate + " has value = " + sum + "in base " + base);
		return sum;
	}


	private static String generateRandomCandidate(int n,
			HashSet<String> candidates) {
		//int max = (int) (Math.pow(2, n-2));
		//Random r = new Random();
		//int random = r.nextInt(max);
		while (true) {
		String str = Integer.toBinaryString(nextCandidate);
		while (str.length() != n-2) {
			str = "0" + str;
		}
		str = "1" + str + "1";
		nextCandidate++;
		
		if (candidates.add(str)){
			//System.out.println("checking candidate: " + str);
			return str;
		}
		}
	}


	private static void printOutput(String candidate, ArrayList<BigInteger> devisors) {
		System.out.print(candidate + " ");
		for (BigInteger devisor : devisors) {
			System.out.print(devisor + " ");
		}
		System.out.println();
		
	}

}
