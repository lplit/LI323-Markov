package Markov;
import java.util.ArrayList;

public class Node {
    private ArrayList<Arc> outArcs;
    private int id;
    private int nbOutgoing;

    /** Constructors **/
    /**
     * Constructor, creates new node with id.
     * @param i Node's ID
     */
    public Node(int i) {
	outArcs=new ArrayList<Arc>();
	id=i; 
    }

    /**
     * Adds outgoing arc to current node
     * @param a Arc to add
     */
    public void addOutArc(Arc a) {
	outArcs.add(a);
	nbOutgoing++; 
    }

    /**
     * Updates probabilities for nodes
     */
    public void updateProbas() {
	for (Arc a : outArcs) 
	    a.updateProbas(nbOutgoing); 
    }

    /** Getters **/
    public Arc getOutArcID(int ind) { return outArcs.get(ind);}
    public ArrayList<Arc> getOutArcs() { return outArcs;}
    public int getID() { return id;}
    public int[] getOutNodesIDs() {
	int[] ret = new int[outArcs.size()];
	for (int i = 0; i< ret.length ; i++) {
	    Arc a = outArcs.get(i);
	    ret[i] = a.getTail();
	}
	return ret;
    }
    
    /*******************/
    /** PRINT METHODS **/
    /*******************/
    
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    public String toString() { 
	String ret=("Node "+id)+" ";
	for (Arc a:outArcs) 
	    ret+=a.toString()+"\n";
     	return ret;
    }
}
