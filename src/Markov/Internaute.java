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
    private HashMap<Node, Double> epsilons;
    private Writer w;

    public Internaute(SimpleWeb w) {
	web=w;
	freq=new HashMap<Node, Double>();
	visits=new HashMap<Node, Integer>();
	epsilons=new HashMap<Node, Double>();
	w = null;
	currentNode=null;
	steps=0;
    }
    
    public void trace(String filename) {
	try {
	    w = new FileWriter("./"+filename);
	} catch (IOException e) {
	    System.err.println("Error writing to file : ");
	    e.printStackTrace();
	}
    }

    // Go to node #n    
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
	updateFreq();
    }

    // Updates the freq hashtab with frequency (technically the probability)
    // of visiting each node.
    private  void updateFreq() {
	for (Map.Entry<Node, Integer> en : visits.entrySet()) {
	    Node no = en.getKey();
	    Integer i = en.getValue();
	    Double put = ((double)i)/steps;
	    if (epsilons.containsKey(no)) {
		Double epsi= Math.abs(epsilons.get(no)-put);
		epsilons.put(no, epsi);
		System.out.print("Epsilon "+epsi+" putting ");
	    } else epsilons.put(no, put);
	    System.out.println(put+" node "+no.getID()+" - "+i+" of "+steps);
	    freq.put(no, put);
	}
    }

    public void walk(int n, double e) {
	int st=0;
	double epsi=999999.;
	boolean write=false;
	if (w!=null) write=true;
	while ( st < n && epsi>e && currentNode!=null) {
	    // Statistically pick way to go
	    // System.out.println(currentNode);
	    currentNode=web.getRandomOutNodeFrom(currentNode);
	    increment(currentNode);
	    epsi = epsilons.get(currentNode);
	    System.out.println("Epsilon: "+epsi);
	    steps++;
	    st++;
	}
	System.out.print("Walk done ("+st+" steps). Attempting to write to file...");
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
