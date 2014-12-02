package Markov; 

public class Main {
    public static void main(String[] args) {
	NanoWeb w = NanoWeb.nanoWeb2();
	Internaute bob = new Internaute(w);
	bob.trace("exec.txt");
	bob.goTo(4);
	bob.walk(1000, 0.1);
	bob.showFrequences();
    }
}
