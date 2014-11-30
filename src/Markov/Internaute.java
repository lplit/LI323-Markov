package Markov;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Internaute {
    private int steps;
    private SimpleWeb web;
    private Node currentNode;
    private HashMap<Node, Integer> visits;
    private HashMap<Node, Double> freq;

    public Internaute(SimpleWeb w) {
	web=w;
	freq=new HashMap<Node, Double>();
	visits=new HashMap<Node, Integer>();
    }

    public void goTo(int n) {
	currentNode=web.getNode(n);
	steps++;
	increment(currentNode);
    }

    private void increment(Node n) {
	if (visits.containsKey(n)) 
	    visits.put(n, (visits.get(n)+1));
	else 
	    visits.put(n, 1);
    }

    public void updateFreq() {
	for (Map.Entry<Node, Integer> en : visits.entrySet()) {
	    Node no = en.getKey();
	    Integer i = en.getValue();
	    Double put = ((double)i)/steps;
	    freq.put(no, put);
	}
    }
    
    public void walk(int n, double e) {
	int st=0;
	double epsiT=999999., epsiT1=999999., epsi=999999.;
	
	while (steps++ < n || epsi>e) {
	    // Epsilon calc
	    epsiT1=epsiT;
	    epsiT=0 ; // Difference between current % and theorethical

	    // Convergence
	    double diff=Math.abs(epsiT1-epsiT);
	    if (diff<epsi) epsi=diff;

	    // Randomly pick way to go
	    int go =1 ;
	    goTo(go);
	    updateFreq();
	}
    }

    public void showFrequences() {
	for (Map.Entry<Node, Double> en : freq.entrySet()) 
	    System.out.println("Node "+en.getKey().getID()+" frequency: "+en.getValue());
    }
}
