import java.util.ArrayList;

public class Node {
	public char value;
	public Node parent;
	public ArrayList<Node> children;
	public Node(Node parent, char value) {
		this.parent = parent;
		this.value = value;
		if (this.parent != null) {
			this.parent.children.add(this);
		}
	}
}
