package Markov;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

public class InternauteSimulation implements Internaute {
    private int steps;
    private SimpleWeb web;
    private Node currentNode;
    private HashMap<Node, Integer> visits;
    private HashMap<Node, Double> freq,	epsilons;
    private Writer w;

    /****************************/
    /** INSTANCING AND OPTIONS **/
    /****************************/

    /**
     * Constructor
     * @param w SimpleWeb
     */
    public InternauteSimulation(SimpleWeb w) {
	web=w;
	freq=new HashMap<Node, Double>();
	visits=new HashMap<Node, Integer>();
	epsilons=new HashMap<Node, Double>();
	/**	for (int i = 0 ; i <web.getMaxNodes();i++) {
	    freq.put(new Node(-1), 0.);
	    visits.put(new Node(-1), 0);
	    epsilons.put(new Node(-1), 999999.);
	    }**/
	w = null;
	currentNode=null;
	steps=-1;
    }

    /**
     * Alternative Constructor with file output enabled
     * @param w SimpleWeb
     * @param file File to save 
     */
    public InternauteSimulation(SimpleWeb w, String file) {
	this(w);
	trace(file);
    }

    /**
     * Enables file output for simulations results, used by constructor, or separately.
     * @param filename Filename to save
     */
    public void trace(String filename) {
	try {
	    w = new FileWriter("./Results/"+filename);
	} catch (IOException e) {
	    System.err.println("Error writing to file : ");
	    e.printStackTrace();
	}
    }


    /**
     * Steps field getter
     * @return steps
     */
    public int getSteps() { return steps; }

    /************************/
    /** SIMULATION METHODS **/
    /************************/

    // Go to node #n    
    /* (non-Javadoc)
     * @see Markov.Internaute#goTo(int)
     */
    public void goTo(int n) {
	currentNode=web.getNode(n);
	steps++;
	increment(currentNode);
	updateFreq();
    }

    
    /**
     * Calculates the maximum value from epsilons hashmap
     * @return Highest value stored in epsilons map
     */
    public double getEpsiMax() {
	double ret = 0.;
	for (Map.Entry<Node, Double> m : epsilons.entrySet()) {
	    if (m.getValue() > ret)
		ret=m.getValue();
	}
	return ret;
    }

    /* (non-Javadoc)
     * @see Markov.Internaute#maxArray(double[])
     */
    public double maxArray(double[] ep) {
	double ret = 0.;
	for (double d : ep)
	    if (d>ret) ret=d;
	return ret;
    }

    /**
     * Used within goTo as a way to update the visits, epsilons and freq hashmaps
     * @param n Node which values will be updated
     */
    private void increment(Node n) {
	if (visits.containsKey(n))
	    visits.put(n, (visits.get(n)+1));
	else 
	    visits.put(n, 1);
	updateFreq();
    }


    /**
     * Updates the freq hashmap with frequency (technically the probability)
     * of visiting each node.
     */
    private  void updateFreq() {
	for (Map.Entry<Node, Integer> en : visits.entrySet()) {
	    Node no = en.getKey();
	    Integer i = en.getValue();
	    Double put = ((double)i)/steps;
	    if (epsilons.containsKey(no)) {
		Double epsi= Math.abs(freq.get(no)-put);
		epsilons.put(no, epsi);
	    } else
		epsilons.put(no, put);
	    freq.put(no, put);
	}
    }

    /* (non-Javadoc)
     * @see Markov.Internaute#walk(int, double)
     */
    public void walk(int n, double e) {
	int st=0;
	double epsi=999999.;
	boolean write=false;
	if (w!=null) write=true;
	while ( st < n && epsi>e && currentNode!=null) {
	    currentNode=web.getRandomOutNodeFrom(currentNode);
	    increment(currentNode);
	    epsi = getEpsiMax();
	    steps++;
	    st++;
	    if (steps%saveStep==0) {
		try {
		    if (write) w.write(st+ " "+getEpsiMax()+"\n");
		} catch (IOException ioe) {
		    System.err.println("Write error!");
		    ioe.printStackTrace();
		}
	    }
	}
	System.out.print("[Simulation] Walk done ("+st+" steps). Attempting to write to file...");
	try {
	    w.close();
	    System.out.println("\tOK");
	} catch (IOException es) {
	    System.err.println("\tFAIL");
	    es.printStackTrace();
	}
    }

    /*******************/
    /** PRINT METHODS **/
    /*******************/

    public void showEpsi() {
	if (epsilons.isEmpty()) 
	    System.out.println("[InternauteSimulation:showEpsi] Empty hashmap!"); 
	else 
	    for (Map.Entry<Node, Double> en : epsilons.entrySet()) 
		System.out.println("Node "+en.getKey().getID()+" epsilon: "+en.getValue());
    }
    
    public void showFrequences() {
	for (Map.Entry<Node, Double> en : freq.entrySet()) 
	    System.out.println("Node "+en.getKey().getID()+" frequency: "+en.getValue()+" visits: "+ visits.get(en.getKey()));
    }
}
