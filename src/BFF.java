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
import java.util.Stack;
import java.util.logging.Logger;


/**
 * https://code.google.com/codejam/contest/4304486/dashboard#s=p2 * 
 * @author farzo
 *
 */

public class BFF {
	private static Logger LOG = Logger.getLogger(BFF.class.getName(), null);
	
	public static void main(String[] args) throws FileNotFoundException {
		 Scanner reader = new Scanner(new File("data/C-large-practice.in"));
		 PrintStream out = new PrintStream(new
		 FileOutputStream("data/C-large-practice.out"));
		 System.setOut(out);
//		Scanner reader = new Scanner(System.in);
		int testNum = reader.nextInt();

		for (int t = 0; t < testNum; t++) {
			LOG.info("#######case " + t);
			int n = reader.nextInt();
			int[] f = new int[n];
			for (int i = 0; i < n ; i++) {
				f[i] = reader.nextInt();
			}
			int max = computeBiggestCircle(f);
			printOutput(t + 1, max);
		}
	}


	private static int computeBiggestCircle(int[] f) {
		// build students
		Stack<Student> students = new Stack<Student>();
		for (int i = 0; i<f.length; i++) {
			Student s = new Student(i);
			students.push(s);
		}
		for (int i = 0; i<f.length; i++) {
			students.get(i).setBestFriend(students.get(f[i]-1));
		}
		//build clusters
		ArrayList<Cluster> clusters = new ArrayList<Cluster>();
		while (!students.isEmpty()) {
			Student s = students.pop();
			boolean added = false;
			for (int i=0; i<clusters.size(); i++) {
				if (clusters.get(i).addStudent(s)){
					LOG.info("student "+ s.getId() + "added to cluser "+i);
					added = true;
					break;
				}
			}
			if (!added) {
				LOG.info("student "+ s.getId() + "added to new cluser "+ clusters.size());
				Cluster c = new Cluster(s);
				
				if (c.isMerged()) {
					LOG.info("new cluster has been merged");
				} else {
					clusters.add(c);
				}
				//remove all that were added
				students.removeAll(c.getStudents());
			}
		}
		LOG.info("clusters.size = "+ clusters.size());
		return findBiggestLoop(clusters);
	}


	private static int findBiggestLoop(ArrayList<Cluster> clusters) {
		// clustesr with the biggest loop
		int maxLoop = 0;
		// sum in clusters with the biggest already ok chain
		int maxOpen = 0;
		for (int i = 0; i<clusters.size(); i++) {
			if (clusters.get(i).isLoop()) {
				if (clusters.get(i).getMaxSize() > maxLoop) {
					maxLoop = clusters.get(i).getMaxSize();
				}
			} else {
				maxOpen += clusters.get(i).getMaxSize();
				LOG.info("found open cluster "+ i + " with size "+ clusters.get(i).getMaxSize());
			}
		}
		LOG.info("maxLoop = "+ maxLoop + " maxOpen = "+ maxOpen);
		return maxLoop > maxOpen? maxLoop : maxOpen;
	}


	private static void printOutput(int caseNumber, int output) {
		
		System.out.println("Case #" + caseNumber + ": " + output);
	}

}
