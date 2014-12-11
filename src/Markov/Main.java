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
    }
}
