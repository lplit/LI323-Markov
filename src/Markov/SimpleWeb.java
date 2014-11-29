package Markov;
import java.util.ArrayList;

public class SimpleWeb {
    ArrayList<Node> nodeList;
    public static int maxNodes;

    public SimpleWeb(int max) {
	nodeList=new ArrayList<Node>(max);
	for (int i =0; i<max;i++) 
	    nodeList.add(new Node());
	maxNodes=max;
    }

    public void addArc(int head, int tail) {
	if (head < maxNodes && tail < maxNodes) {
	    Arc a = new Arc(head, tail);
	    Node out = nodeList.get(head);
	    Node in = nodeList.get(tail);
	    out.addOutArc(a);
	    in.addInArc(a);
	}
    }

    public void updateProbas() {
	for (Node n : nodeList)
	    n.updateProbas();
    }

    public void showTransitionTable() {
	for (Node n : nodeList) {
	    String s=("Node "+n.getID());
	    for (Arc a : n.getOutArcs())
		s+=a.getTail()+" ";
	    System.out.println(s);
	}

    }
}
