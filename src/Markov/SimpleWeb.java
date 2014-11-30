package Markov;
import java.util.ArrayList;

public class SimpleWeb {
    ArrayList<Node> nodeList;
    public static int maxNodes;

    public SimpleWeb(int max) {
	nodeList=new ArrayList<Node>(max);
	for (int i =0; i<max;i++) 
	    nodeList.add(new Node(i));
	maxNodes=max;
    }

    public void addArc(int head, int tail) {
	if (head < maxNodes && tail < maxNodes) {
	    try {
		Arc a = new Arc(head, tail);
		contains(a);
		Node out = nodeList.get(head);
		out.addOutArc(a);
		updateProbas();
	    } catch (ArcException e) { }
	}
    }
    
    public void updateProbas() {
	for (Node n : nodeList)
	    n.updateProbas();
    }
    
    // Checks if node is present in nodeList
    public void contains(Arc a) throws ArcException {
	for (Node n : nodeList) 
	    if (n.getOutArcs().contains(a)) {
		throw new ArcException(a);
	    }
    }
    
    // Prints outgoing arcs from each Node, as such, with the probability for that arc
    // as such: Outgoing arcs from node [0] | 1 (0.87). Which means that there exists
    // an arc between nodes 0 and 1, with probability of 0.87.
    void showTransitionTable() {
	for (Node n : nodeList) {
	    String s=("Outgoing arcs from node ["+n.getID()+"] |");
	    for (Arc a : n.getOutArcs()) {
		String pro = String.format("%.2f", a.getProba());
		s+=" "+a.getTail()+" ("+pro+") |";
	    }
	    System.out.println(s.substring(0, (s.length()-2)));
	}
    }

    public Node getNode(int i) {
	try {
	    return nodeList.get(i);
	} catch (IndexOutOfBoundsException e) {
	    System.out.println("[getNode] Index "+i+" not available.");
	} finally {
	    return new Node(-1);
	}
    }
}
