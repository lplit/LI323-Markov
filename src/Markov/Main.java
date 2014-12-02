package Markov; 

public class Main {
    public static void main(String[] args) {
	NanoWeb w = NanoWeb.nanoWeb1();
	Internaute bob = new Internaute(w);
	bob.goTo(1);
	bob.walk(100, 0.1);
    }
}
