package Markov;
import java.util.ArrayList;

public class Node {
    private ArrayList<Arc> inArcs;
    private ArrayList<Arc> outArcs;
    private int id;
    private int nbOutgoing;

    /** Constructors **/
    public Node(int i) {
	inArcs=new ArrayList<Arc>();
	outArcs=new ArrayList<Arc>();
	id=i;
    }

    public void addInArc(Arc a) {
	inArcs.add(a);
    }

    public void addOutArc(Arc a) {
	outArcs.add(a);
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
