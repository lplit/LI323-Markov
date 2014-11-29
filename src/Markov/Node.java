package Markov;
import java.util.ArrayList;

public class Node {
    private ArrayList<Arc> inArcs;
    private ArrayList<Arc> outArcs;
    private int id;
    private int nbOutgoing;

    /** Constructors **/
    public Node() {
	inArcs=null;
	outArcs=null;
	id=-1;
    }

    public Node(ArrayList<Arc> in, ArrayList<Arc> out, int i) {
	inArcs=in;
	outArcs=out;
	id=i;
    }

    public void addInArc(Arc a) {
	int id = a.getID();
	inArcs.ensureCapacity(id);
	inArcs.add(id, a);
    }

    public void addOutArc(Arc a) {
	int id = a.getID();
	outArcs.ensureCapacity(id);
	outArcs.add(id, a);
	nbOutgoing++;
    }

    public  void updateProbas() {
	for (Arc a : outArcs) 
	    a.updateProbas(nbOutgoing);
    }

    /** Getters **/
    public Arc getOutArcID(int ind) { return outArcs.get(ind);}
    public Arc getInArcID(int ind) { return inArcs.get(ind);}
    public ArrayList<Arc> getOutArcs() { return outArcs;}
    public int getID() { return id;}

    public String toString() { 
	String ret=""+id+"\nINCOMING ARCS";
	for (Arc a:inArcs) 
	    ret+=a.toString();
	ret+="\nOUTGOING ARCS\n";
	for (Arc a:outArcs) 
	    ret+=a.toString();
	ret+="*******\n**END**\n*******";
     	return ret;
    }
}
