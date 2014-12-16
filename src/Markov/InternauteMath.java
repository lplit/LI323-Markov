package Markov;
import java.util.Arrays;
import java.util.Random;
import java.io.FileWriter;
import java.io.Writer;
import java.io.IOException;

public class InternauteMath implements Internaute {
    private int steps, maxNodes;
    private SimpleWeb web;
    private double[][] matrix;
    private double[] pi, epsilons;
    private Writer w;
    
    public InternauteMath(SimpleWeb w) {
	web=w;
	maxNodes=web.getMaxNodes();
	pi=new double[web.maxNodes];
	epsilons=new double[web.maxNodes];
	Arrays.fill(epsilons,9999.);
	matrix = genMatrix();
	w = null;
	steps=-1;
    }

    public InternauteMath(SimpleWeb w, String file) {
	this(w);
	trace(file);
    }


    public void goTo(int i) { pi[i]=1; }
    public void trace(String filename) {
	try {
	    w = new FileWriter("./Results/"+filename);
	} catch (IOException e) {
	    System.err.println("Error writing to file : ");
	    e.printStackTrace();
	}
    }

    public int getSteps() { return steps; }
    
    /******************/
    /** MATH METHODS **/
    /******************/
    public double getEpsiMax() {
	double ret = 0.;
	for (double d : epsilons)
	    if (d>ret) ret=d;
	return ret;
    }

    public void walk(int n, double e) {
	int st=0;
	boolean write=false;
	double epsi = 9999.;
	if (w!=null) write=true;
	while ( st++ < n && epsi>e) {
	    if (epsi<e) System.out.println("Epsilon stop @ "+epsi);
	    double [] curr =pi;
	    pi=vectMatrix(pi, matrixPow(st));
	    for (int i = 0 ; i<epsilons.length ; i++)
		epsilons[i] = Math.abs(curr[i]-pi[i]);
	    epsi = getEpsiMax();
	    steps++;
	    if (st%1==0) {
		try {
		    if (write) w.write(st+ " "+epsi+"\n");
		} catch (IOException ioe) {
		    System.err.println("Write error!");
		    ioe.printStackTrace();
		}
	    }
	}
	System.out.print("[Math] Walk done ("+st+" steps). Attempting to write to file...");
	try {
	    w.close();
	    System.out.println("\tOK");
	} catch (IOException es) {
	    System.err.println("\tFAIL");
	    es.printStackTrace();
	}
    }

    // Matrix power method
    public double[][] matrixPow(int pow) {
	double[][] mat = genMatrix();
	double[][] ret = genMatrix();
	for (int i = 1 ; i<pow ; i++) {
	    ret = multiply(ret, mat);
	}
	return ret;
    }
    
    // Multiply vector by matrix
    public static double[] vectMatrix(double [] a , double [][] b ){
	double[] ret = new double[a.length];
	int columnsInB = b[0].length;
	for (int i = 0 ; i<columnsInB ; i++) { // Columns
	    for (int j = 0 ; j<a.length ; j++) { // Lines
		ret[i] += b[j][i]*a[j];
	    }
	}
	return ret;
    }

    // Matrix multiplication method
    private static double[][] multiply(double[][] a, double[][] b) {
	int rowsInA = a.length;
	int columnsInA = a[0].length; // same as rows in B
	int columnsInB = b[0].length;
	double[][] c = new double[rowsInA][columnsInB];
	for (int i = 0; i < rowsInA; i++) {
	    for (int j = 0; j < columnsInB; j++) {
		for (int k = 0; k < columnsInA; k++) {
		    c[i][j] = c[i][j] + a[i][k] * b[k][j];
		}
	    }
	}
	return c;
    }

    // Generates transitions matrix
    public double[][] genMatrix() {
	double[][] ret = new double[maxNodes][maxNodes];
	for ( double[] d : ret) 
	    Arrays.fill(d, 0.);
	for (Node n : web.getNodes()) {
	    int nID = n.getID();
	    for (Arc a : n.getOutArcs()) {
		int tail = a.getTail();		
		ret[nID][tail]=a.getProba();
	    }
	}
	return ret;
    }


    /*******************/
    /** PRINT METHODS **/
    /*******************/

    public void showEpsi() {
	System.out.println("Vector: ");
	for (double d : pi)
	    System.out.format("%.4f | ", d);
	System.out.println();
    }

    // Print method
    public void printMatrix() {
	double[][]hueh=matrixPow(steps);
	for(int i = 0 ; i<maxNodes ; i++) {
	    for(int j = 0 ; j<maxNodes ; j++)
		System.out.format("%.4f | ", hueh[i][j]);
	    System.out.println();
	}
    }
}
