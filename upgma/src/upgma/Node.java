package upgma;

public class Node {
	private String nameNode = null;
	private Node nodeLeft = null;
	private Node nodeRight = null;
	private double distance = 0;
	
	public Node(Node nodeLeft, Node nodeRight, double distance){
		this.nodeLeft = nodeLeft;
		this.nodeRight = nodeRight;
		this.distance = distance;
	}
	
	public Node(String nameNode){
		this.nameNode = nameNode;
	}
	
	public String getNameNode() {
		return nameNode;
	}

	public void setNameNode(String nameNode) {
		this.nameNode = nameNode;
	}

	public Node getNodeLeft() {
		return nodeLeft;
	}
	public void setNodeLeft(Node nodeLeft) {
		this.nodeLeft = nodeLeft;
	}
	public Node getNodeRight() {
		return nodeRight;
	}
	public void setNodeRight(Node nodeRight) {
		this.nodeRight = nodeRight;
	}
	public double getDistance() {
		return distance;
	}
	public void setDistance(double distance) {
		this.distance = distance;
	}	
}
