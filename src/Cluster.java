import java.util.ArrayList;
import java.util.logging.Logger;

public class Cluster {
	private static Logger LOG = Logger.getLogger(Cluster.class.getName(), null);
	ArrayList<Student> students;
	boolean loop;
	int maxSize = 0;
	boolean merged = false;

	public Cluster(Student start) {
		LOG.info("building cluster with start" + start.getId());
		students = new ArrayList<Student>();
		start.setDepth(1);
		start.setCluster(this);
		students.add(start);
		maxSize = 1;
		buildCluster(start);
	}

	private void buildCluster(Student start) {
		Student s = start;

		while (true) {
			Student nextCandidate = s.getBestFriend();

			if (students.contains(nextCandidate)) {
				if (nextCandidate.getBestFriend().equals(s)) {
					// open cluster
					loop = false;
					nextCandidate.setWeight(nextCandidate.getDepth() - 1);
					LOG.info("open cluster. setting student weight for student "
							+ nextCandidate.getId()
							+ "to "
							+ nextCandidate.getWeight());
					return;
				} else {
					// we are in a loop now
					loop = true;
					maxSize = s.getDepth() - nextCandidate.getDepth() + 1;
					LOG.info("loop detected with maximum size " + maxSize);
					return;
				}
			}

			if (nextCandidate.getCluster() != null) {
				// we should not be creating a new cluster, we should merge
				// clusters
				LOG.info(nextCandidate.getId()
						+ " is already in a cluster. we need to merge ");
				nextCandidate.getCluster().merge(this);
				return;
			}

			nextCandidate.setDepth(s.getDepth() + 1);
			students.add(nextCandidate);
			nextCandidate.setCluster(this);
			LOG.info("adding student " + nextCandidate.getId() + "with depth "
					+ nextCandidate.getDepth());
			maxSize++;
			LOG.info("max size is " + maxSize);
			s = nextCandidate;
		}
	}

	public boolean addStudent(Student s) {
		if (!students.contains(s.getBestFriend()) || students.contains(s)) {
			// error: we can not add it
			return false;
		}
		s.setDepth(1);
		students.add(s);
		s.setCluster(this);
		LOG.info("adding student " + s.getId() + "with depth " + s.getDepth());
		if (loop) {
			return true;
		}
		int lastDepth = 1;
		while (s.getBestFriend().getDepth() <= s.getDepth() + 1
				&& !s.getBestFriend().getBestFriend().equals(s)) {

			s.getBestFriend().setDepth(s.getDepth() + 1);
			LOG.info("updating student " + s.getBestFriend().getId()
					+ "with depth " + s.getBestFriend().getDepth());
			s = s.getBestFriend();
			lastDepth++;
		}

		if (s.getBestFriend().getBestFriend().equals(s)) {
			if (s.getWeight() < lastDepth) {
				s.setWeight(lastDepth - 1);
				LOG.info("updating student " + s.getId() + "with weight "
						+ s.getWeight());

			}

			s.getBestFriend().setDepth(s.getDepth() + 1);
			LOG.info("updating student " + s.getBestFriend().getId()
					+ "with depth " + s.getBestFriend().getDepth());
			maxSize = s.getBestFriend().getDepth()
					+ s.getBestFriend().getWeight();
			LOG.info("max size is updated to " + maxSize);
		} else if (s.getBestFriend().getBestFriend().getBestFriend()
				.equals(s.getBestFriend())) {
			Student border = s.getBestFriend();
			if (border.getWeight() < s.getDepth()) {
				border.setWeight(s.getDepth());
				LOG.info("updating student " + border.getId() + "with weight "
						+ border.getWeight());
			}
			if (border.getDepth() > border.getBestFriend().getDepth()) {
				maxSize = border.getDepth() + border.getWeight();
				LOG.info("max size is updated to " + maxSize);
			}
		}
		return true;
	}

	private void merge(Cluster cluster) {

		for (int i = cluster.students.size() - 1; i >= 0; i--) {
			this.addStudent(cluster.students.get(i));
		}
		cluster.setMerged(true);
	}

	public boolean isMerged() {
		return merged;
	}

	public void setMerged(boolean merged) {
		this.merged = merged;
	}

	public static Logger getLOG() {
		return LOG;
	}

	public static void setLOG(Logger lOG) {
		LOG = lOG;
	}

	public ArrayList<Student> getStudents() {
		return students;
	}

	public void setStudents(ArrayList<Student> students) {
		this.students = students;
	}

	public boolean isLoop() {
		return loop;
	}

	public void setLoop(boolean loop) {
		this.loop = loop;
	}

	public int getMaxSize() {
		return maxSize;
	}

	public void setMaxSize(int maxSize) {
		this.maxSize = maxSize;
	}

}
