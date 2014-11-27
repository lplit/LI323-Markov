package Markov;

public class ArcException extends Exception {
    public ArcException() {
	super();
	System.out.println("This arc exists already!");
    }
}
