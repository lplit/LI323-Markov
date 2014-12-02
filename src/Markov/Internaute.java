package Markov;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.io.FileWriter;
import java.io.Writer;
import java.io.IOException;

public class Internaute {
    private int steps;
    private SimpleWeb web;
    private Node currentNode;
    private HashMap<Node, Integer> visits;
    private HashMap<Node, Double> freq;
    private Writer w;

    public Internaute(SimpleWeb w) {
	web=w;
	freq=new HashMap<Node, Double>();
	visits=new HashMap<Node, Integer>();
	w = null;
    }
    
    public void trace(String filename) {
	try {
	    w = new FileWriter("./"+filename);
	} catch (IOException e) {
	    System.err.println("Error writing to file : ");
	    e.printStackTrace();
	}
    }
    
    public void goTo(int n) {
	currentNode=web.getNode(n);
	steps++;
	increment(currentNode);
	updateFreq();
    }

    // Used within goTo as a way to update the visits hashmap
    private void increment(Node n) {
	if (visits.containsKey(n))
	    visits.put(n, (visits.get(n)+1));
	else 
	    visits.put(n, 1);
    }

    // Updates the freq hashtab with frequency (technically the probability)
    // of visiting each node.
    private  void updateFreq() {
	for (Map.Entry<Node, Integer> en : visits.entrySet()) {
	    Node no = en.getKey();
	    Integer i = en.getValue();
	    Double put = ((double)i)/steps;
	    freq.put(no, put);
	}
    }
    
    public void walk(int n, double e) {
	int st=0;
	double epsiT=99., epsiT1=99., epsi=999999.;
	boolean write=false;
	if (w!=null) write=true;
	
	while (steps++ < n && epsi>e && currentNode!=null) {
	    /** Epsilon calc
	    epsiT1=epsiT;
	    epsiT=0 ; // Difference between current % and theorethical
	
	    if (write)
	    if (steps%100==0)
	    w.write(epsilon);

	    //Convergence
	    double diff=Math.abs(epsiT1-epsiT);
	    if (diff<epsi) epsi=diff;
	    **/
	    // Statistically pick way to go
	    // System.out.println(currentNode);
	    currentNode=web.getRandomOutNodeFrom(currentNode);
	    System.out.println("Step: "+steps);
	    updateFreq();

	}
	System.out.print("Walk done. Attempting to write to file...");
	/**	try {
	    w.close();
	    System.out.println("\tOK");
	} catch (IOException es) {
	    System.err.println("\tFAIL");
	    es.printStackTrace();
	    }*/
    }

    public void showFrequences() {
	for (Map.Entry<Node, Double> en : freq.entrySet()) 
	    System.out.println("Node "+en.getKey().getID()+" frequency: "+en.getValue());
    }
}
