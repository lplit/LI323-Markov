package Markov;
import java.util.ArrayList;

public class SimpleWeb {
    ArrayList<Node> nodeList;
    public static int maxNodes;

    public SimpleWeb(int max) {
	nodeList=new ArrayList<Node>();
	maxNodes=max;
    }

    public void addArc(int head, int tail) {
	Arc a = new Arc(head, tail);
	Node out = nodeList.get(head);
	Node in = nodeList.get(tail);
	out.addOutArc(a);
	in.addInArc(a);
    }

    public void updateProbas() {
	for (Node n : nodeList)
	    n.updateProbas();
    }

    public void showTransitionTable() {
	for (Node n : nodeList) {
	    String cur=("Node "+n.getID());
	    for (Arc a : n.getOutArcs())
	}
    }
}
