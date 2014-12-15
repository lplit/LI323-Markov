package Markov;

public class Arc {
    private int 
	head, // Identifies start node
	tail; // Identifies end node
    private double proba; // Probability of the arc

    /** Constructors **/
    public Arc() {
	head=-1;
	tail=-1;
    }

    public Arc(int h, int t) {
	head=h;
	tail=t;
    }

    @Override
    public boolean equals(Object b) {
	if (b instanceof Arc) {
	    Arc a = (Arc) b;
	    int aT=a.getTail();
	    int aH=a.getHead();
	    if (head==aH && tail==aT) return true;
	    else return false;
	} else return false;
    }

    public void updateProbas(int nb) {
	if (nb==0) proba=0;
	else proba=1.0/nb;
    }

    /** Getters **/
    public double getProba() {return proba;}
    public int getHead() { return head; }
    public int getTail() { return tail; }
    public String toString() {return ("Head: "+head+" tail: "+tail+" proba: "+proba); }
}
