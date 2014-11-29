package Markov;
import java.util.ArrayList;

public class Node {
    private ArrayList<Arc> inArcs;
    private ArrayList<Arc> outArcs;
    private int id;
    private int nbOutgoing;
    private static int cnt=0;

    /** Constructors **/
    public Node() {
	inArcs=new ArrayList<Arc>();
	outArcs=new ArrayList<Arc>();
	id=cnt++;
	System.out.println("Created new Node #"+id);
    }

    public Node(ArrayList<Arc> in, ArrayList<Arc> out) {
	inArcs=in;
	outArcs=out;
	id=cnt++;
    }

    public void addInArc(Arc a) {
	int id = a.getID();
	inArcs.add(a);
    }

    public void addOutArc(Arc a) {
	int id = a.getID();
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
