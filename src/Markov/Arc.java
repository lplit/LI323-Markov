package Markov;

public class Arc {

    private int head; // Identifies start node
    private int tail; // Identifies end node
    private int id; // Arc's ID
    private double proba; // Probability of the arc
    private static int cnt=0;

    /** Constructors **/
    public Arc() {
	head=-1;
	tail=-1;
	id=-1;
	proba=0;
	cnt++;
    }

    public Arc(int h, int t, int i) {
	head=h;
	tail=t;
	id=i;
	cnt++;
    }


    /** Getters **/
    public int getHead() { return head; }
    public int getTail() { return tail; }
    public int getID() { return id; }
    public String toString() {return ("ID: "+id+" head: "+head+" tail: "+tail); }
    
}