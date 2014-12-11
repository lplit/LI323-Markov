package Markov;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Random;

public class SimpleWeb {
    ArrayList<Node> nodeList;
    public static int maxNodes;
    private double[][] matrix;

    public SimpleWeb(int max) {
	nodeList=new ArrayList<Node>(max);
	for (int i =0; i<max;i++) 
	    nodeList.add(new Node(i));
	maxNodes=max;
	matrix=genMatrix();
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
	//System.out.println(n.getID()+"->"+outNode.getID());
	return outNode;
    }
    
    // Prints outgoing arcs from each Node, with the probability for that arc
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

    
    // Generates transitions matrix
    public double[][] genMatrix() {
	double[][] ret = new double[maxNodes][maxNodes];
	for ( double[] d : ret) 
	    Arrays.fill(d, 0.);
	for (Node n : nodeList) {
	    int nID = n.getID();
	    for (Arc a : n.getOutArcs()) {
		int tail = a.getTail();		
		ret[nID][tail]=a.getProba();
	    }
	}
	return ret;
    }

    public void printMatrix() {
	double[][] mat = genMatrix();
	for(int i = 0 ; i<maxNodes ; i++) {
	    for(int j = 0 ; j<maxNodes ; j++)
		System.out.print(mat[i][j]+" | ");
	    System.out.println();
	}
    }

    public static void printMatrix(double[][] d) {
	for(int i = 0 ; i<d.length ; i++) {
	    for(int j = 0 ; j<d.length ; j++)
		System.out.print(d[i][j]+" | ");
	    System.out.println();
	}
    }

    public double[][] matrixPow(int pow) {
	int N = maxNodes;
	double[][]mat = genMatrix();
	double[][] ret = new double[N][N];
	for (int p = 0 ; p < pow ; p++)
	    for (int i = 0; i < N; i++)
		for (int k = 0; k < N; k++)
		    for (int j = 0; j < N; j++)
			ret[i][j] += mat[i][k] * mat[k][j];
	return ret;
    }

    public Node getNode(int i) {
	try {
	    return nodeList.get(i);
	} catch (IndexOutOfBoundsException e) {
	    System.out.println("[getNode] Index "+i+" not available.");
	    return new Node(-1);
	}
    }
    
    public double[][] getMatrix() { return matrix; }
}
