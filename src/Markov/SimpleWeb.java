package Markov;
import java.util.ArrayList;
import java.util.Random;

public class SimpleWeb {
    ArrayList<Node> nodeList;
    public static int maxNodes;

    /****************************/
    /** INSTANCING AND CONTROL **/
    /****************************/

    /**
     * Constructor, creates a {@link SimpleWeb} with given max number of nodes in it. 
     * @param max Maximum number of nodes in this web.
     */
    public SimpleWeb(int max) {
	nodeList=new ArrayList<Node>(max);
	for (int i =0; i<max;i++) 
	    nodeList.add(new Node(i));
	maxNodes=max;
    }

    /**
     * Add arc between node id=head and another node id=tail
     * @param head Node ID from
     * @param tail Node ID to
     */
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
    

    /**
     * To avoid double additions
     * @param a Arc to check
     * @throws ArcException Rises exception if this arc is already present in the web 
     */
    public void contains(Arc a) throws ArcException {
	for (Node n : nodeList) 
	    if (n.getOutArcs().contains(a)) {
		throw new ArcException(a);
	    }
    }

    /**
     * Random {@link SimpleWeb} generator, with number of nodes.
     * @param nb How many nodes will the web have
     * @return {@link SimpleWeb} instance 
     */
    public static SimpleWeb generateRandomWeb(int nb) {
	Random r = new Random();
	SimpleWeb sw = new SimpleWeb(nb);
	for (Node n : sw.getNodes()) {
	    int head = n.getID();
	    int outArcs = r.nextInt(nb)+1;
	    while (outArcs == head) outArcs=r.nextInt(nb); // Avoid self loop
	    for (int i = 0 ; i<outArcs ; i++) {
		int tail = r.nextInt(nb);
		sw.addArc(head, tail);
	    }
	}
	return sw;
    }

    /*************/
    /** GETTERS **/
    /*************/

    // Node getter by id
    public Node getNode(int i) {
	try {
	    return nodeList.get(i);
	} catch (IndexOutOfBoundsException e) {
	    System.out.println("[getNode] Index "+i+" not available.");
	    return new Node(-1);
	}
    }

    public ArrayList<Node> getNodes() { return nodeList; }

    public int getMaxNodes() { return maxNodes; }

    // Used by Internaute to walk(). 
    public Node getRandomOutNodeFrom(Node n) {
	int[] outNodes = n.getOutNodesIDs();
	if (outNodes.length==0) {
	    System.err.println("[getRandomOutNodeFrom] End of the road! @ "+n);
	    return null;
	}
	Random r = new Random(); 
	int index = r.nextInt(outNodes.length);
	Node outNode=nodeList.get(outNodes[index]);
	return outNode;
    }
    

    /*******************/
    /** PRINT METHODS **/
    /*******************/

    // Prints outgoing arcs from each Node, with the probability for that arc
    // as such: "Outgoing arcs from node [0] | 1 (0.87)". Which means that there exists
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

    /******************/
    /** MATH METHODS **/
    /******************/

    // Updates probabilities on nodes
    public void updateProbas() {
	for (Node n : nodeList)
	    n.updateProbas();
    }
}
