public class Student {
	private int id;
	private Student bestFriend;
	private Cluster cluster;
	private int depth;
	private int weight;

	public Student(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Student getBestFriend() {
		return bestFriend;
	}

	public void setBestFriend(Student bestFriend) {
		this.bestFriend = bestFriend;
	}

	public Cluster getCluster() {
		return cluster;
	}

	public void setCluster(Cluster cluster) {
		this.cluster = cluster;
	}

	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	@Override
	public boolean equals(Object obj) {

		if (!(obj instanceof Student)) {
			return false;
		}
		return this.id == ((Student) obj).id;

	}
}
