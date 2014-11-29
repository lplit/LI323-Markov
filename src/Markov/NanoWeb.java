package Markov;

public class NanoWeb extends SimpleWeb {
    public NanoWeb(int maxNode) {
	super(maxNode);
    }
    
    public static NanoWeb nanoWeb1() {
	NanoWeb n1 = new NanoWeb(10);
	n1.addArc(0,1);
	n1.addArc(0,4);
	n1.addArc(1,2);
	n1.addArc(2,3);
	n1.addArc(2,4);
	n1.addArc(3,9);
	n1.addArc(4,2);
	n1.addArc(4,5);
	n1.addArc(4,6);
	n1.addArc(5,6);
	n1.addArc(6,5);
	n1.addArc(6,7);
	n1.addArc(7,8);
	n1.addArc(8,7);
	return n1;
    }

    public static void main (String[] args) {
	NanoWeb n = nanoWeb1();
	n.updateProbas();
	n.showTransitionTable();
    }
}
