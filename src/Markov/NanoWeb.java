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
	n1.addArc(4,7);
	n1.addArc(5,6);
	n1.addArc(6,5);
	n1.addArc(6,7);
	n1.addArc(7,8);
	n1.addArc(8,7);
	return n1;
    }

    public static NanoWeb nanoWeb2() {
	NanoWeb n1 = new NanoWeb(10);
	n1.addArc(0,9);
	n1.addArc(1,0);
	n1.addArc(1,5);
	n1.addArc(2,1);
	n1.addArc(2,4);
	n1.addArc(3,2);
	n1.addArc(4,3);
	n1.addArc(5,4);
	n1.addArc(6,5);
	n1.addArc(7,6);
	n1.addArc(7,3);
	n1.addArc(8,7);
	n1.addArc(9,8);
	n1.addArc(9,2);
	return n1;
    }

    public static NanoWeb nanoWeb3() {
	NanoWeb n1 = new NanoWeb(10);
	n1.addArc(0,1);
	n1.addArc(1,2);
	n1.addArc(1,3);
	n1.addArc(2,3);
	n1.addArc(2,9);
	n1.addArc(4,5);
	n1.addArc(5,4);
	n1.addArc(6,7);
	n1.addArc(7,8);
	n1.addArc(8,7);
	n1.addArc(8,7); // Here for the sake of testing the exceptions behaviour
	return n1;
    }

    public static void main (String[] args) {
	System.out.println("\n\nnanoWeb1");
	NanoWeb n = NanoWeb.nanoWeb1();
	n.updateProbas(); // Redundant as probas get updated with every addArc call
	n.showTransitionTable();
	System.out.println("\n\nnanoWeb2:");
	NanoWeb n2 = NanoWeb.nanoWeb2();
	n2.updateProbas();
	n2.showTransitionTable();

	System.out.println("\n\nnanoWeb3:");
	NanoWeb n3 = NanoWeb.nanoWeb3();
	n3.updateProbas();
	n3.showTransitionTable();

    }
}
