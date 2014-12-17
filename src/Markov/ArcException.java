package Markov;

public class ArcException extends Exception {
	private static final long serialVersionUID = 1L;

	public ArcException(Arc a) {
	super();
	int h= a.getHead();
	int t= a.getTail();
	System.out.println("[ArcException] Arc "+h+"->"+t+" exists already!");
    }
}
