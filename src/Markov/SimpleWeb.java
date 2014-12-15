package Markov;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Random;

public class SimpleWeb {
    ArrayList<Node> nodeList;
    public static int maxNodes;
    private double[][] matrix;

    /****************************/
    /** INSTANCING AND CONTROL **/
    /****************************/

    public SimpleWeb(int max) {
	nodeList=new ArrayList<Node>(max);
	for (int i =0; i<max;i++) 
	    nodeList.add(new Node(i));
	maxNodes=max;
	matrix=genMatrix();
    }

    // Add arc between node id=head and another node id=tail
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
    
    // Checks if node is present in nodeList
    public void contains(Arc a) throws ArcException {
	for (Node n : nodeList) 
	    if (n.getOutArcs().contains(a)) {
		throw new ArcException(a);
	    }
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

    // Matrix getter
    public double[][] getMatrix() { return matrix; }

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

    // Print method
    public void printMatrix() {
	double[][] mat = genMatrix();
	for(int i = 0 ; i<maxNodes ; i++) {
	    for(int j = 0 ; j<maxNodes ; j++)
		System.out.format("%.4f | ", mat[i][j]);
	    System.out.println();
	}
    }

    // Static version: matrix print
    public static void printMatrix(double[][] d) {
	for(int i = 0 ; i<d.length ; i++) {
	    for(int j = 0 ; j<d.length ; j++)
		System.out.format("%.4f | ", d[i][j]);
	    System.out.println();
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
    
    // Matrix power method
    public double[][] matrixPow(int pow) {
	double[][] mat = genMatrix();
	double[][] ret = genMatrix();
	for (int i = 1 ; i<pow ; i++) {
	    ret = multiply(ret, mat);
	}
	return ret;
    }
    
    // Multiply vector by matrix
    public static double[] vectMatrix(double [] a , double [][] b ){
	double[] ret = new double[a.length];
	int columnsInB = b[0].length;
	for (int i = 0 ; i<columnsInB ; i++) { // Columns
	    for (int j = 0 ; j<a.length ; j++) { // Lines
		ret[i] += b[j][i]*a[j];
	    }
	}
	return ret;
    }

    // Matrix multiplication method
    private static double[][] multiply(double[][] a, double[][] b) {
	int rowsInA = a.length;
	int columnsInA = a[0].length; // same as rows in B
	int columnsInB = b[0].length;
	double[][] c = new double[rowsInA][columnsInB];
	for (int i = 0; i < rowsInA; i++) {
	    for (int j = 0; j < columnsInB; j++) {
		for (int k = 0; k < columnsInA; k++) {
		    c[i][j] = c[i][j] + a[i][k] * b[k][j];
		}
	    }
	}
	return c;
    }

}
