package Markov; 

public class Main {
    public static void main(String[] args) {
	NanoWeb w = NanoWeb.nanoWeb2();
	Internaute bob = new Internaute(w);
	bob.trace("epsilons.txt");
	bob.goTo(4);
	bob.walk(10000, 0.001);
	bob.showFrequences();
	bob.showEpsi();
	w.showTransitionTable();
	w.printMatrix();
	int pow = 10;
	System.out.println("^this to power of "+pow);
	w.printMatrix(w.matrixPow(pow));
	bob.showPi();
    }
}
