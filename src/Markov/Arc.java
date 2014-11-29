package Markov;

public class Arc {

    private int head; // Identifies start node
    private int tail; // Identifies end node
    private int id; // Arc's ID
    private double proba; // Probability of the arc
    private static int cnt=0; // Total count of arcs

    /** Constructors **/
    public Arc() {
	cnt++;
	head=-1;
	tail=-1;
	id=cnt;
    }

    public Arc(int h, int t) {
	cnt++;
	head=h;
	tail=t;
	id=cnt;
    }
    
    public void updateProbas(int nb) {
	if (nb==0) proba=0;
	else proba=1.0/nb;
    }

    /** Getters **/
    public int getHead() { return head; }
    public int getTail() { return tail; }
    public int getID() { return id; }
    public String toString() {return ("ID: "+id+" head: "+head+" tail: "+tail); }


}
