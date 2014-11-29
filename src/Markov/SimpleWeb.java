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
		Node in = nodeList.get(tail);
		out.addOutArc(a);
		in.addInArc(a);
		updateProbas();
	    } catch (ArcException e) {
	    }
	}
    }
	
    public void updateProbas() {
	for (Node n : nodeList)
	    n.updateProbas();
    }
    
    public void contains(Arc a) throws ArcException {
	for (Node n : nodeList) 
	    if ( (n.getOutArcs().contains(a)) || (n.getInArcs().contains(a))) {
		throw new ArcException(a);
	    }
    }
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
}
