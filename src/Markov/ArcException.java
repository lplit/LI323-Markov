package Markov;

public class ArcException extends Exception {
    public ArcException(Arc a) {
	super();
	int h= a.getHead();
	int t= a.getTail();
	System.out.println("[ArcException] Arc "+h+"->"+t+" exists already!");
    }
}
