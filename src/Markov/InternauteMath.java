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
    private double[] pi, epsilons, epsiVect;
    private Writer vectMat, matPow;
    
    
    public InternauteMath(SimpleWeb w) {
	web=w;
	maxNodes=web.getMaxNodes();
	pi=new double[web.maxNodes];
	epsilons=new double[web.maxNodes];
	epsiVect=new double[web.maxNodes];
	Arrays.fill(epsilons,9999.);
	matrix = genMatrix();
	w = null;
	steps=-1;
    }

    public InternauteMath(SimpleWeb sw, String vector, String power) {
	this(sw);
	traceMat(vector);
	tracePow(power);
    }


    public void goTo(int i) { pi[i]=1; }
    public void traceMat(String filename) {
	try {
	    vectMat = new FileWriter("./Results/"+filename);
	} catch (IOException e) {
	    System.err.println("Error writing to file : ");
	    e.printStackTrace();
	}
    }

    public void tracePow(String filename) {
	try {
	    matPow = new FileWriter("./Results/"+filename);
	} catch (IOException e) {
	    System.err.println("Error writing to file : ");
	    e.printStackTrace();
	}
    }

    public int getSteps() { return steps; }
    
    /******************/
    /** MATH METHODS **/
    /******************/
    public double maxArray(double[] ep) {
	double ret = 0.;
	for (double d : ep)
	    if (d>ret) ret=d;
	return ret;
    }

    public void walk(int n, double e) {
	int st=0;
	double [] vect = new double[pi.length];
	System.arraycopy(pi, 0, vect, 0, pi.length);
	boolean wMat=false, wPow=false;
	double eVect = 9999., epsiPow=9999.;
	if (vectMat!=null) wMat=true;
	if (matPow!=null) wPow=true;
	while ( st++ < n || (eVect>e && epsiPow>e)) {
	    double [] curr=new double[pi.length], currVect=new double[vect.length];
	    System.arraycopy(pi, 0, curr, 0, pi.length);
	    System.arraycopy(vect, 0, currVect, 0, vect.length);
	    vect=vectMatrix(vect, matrix);
	    pi=vectMatrix(pi, matrixPow(st));
	    for (int i = 0 ; i<epsilons.length ; i++) {
		epsilons[i] = Math.abs(curr[i]-pi[i]);
		epsiVect[i] = Math.abs(currVect[i]-vect[i]);
	    }
	    eVect = maxArray(epsiVect);
	    epsiPow = maxArray(epsilons);
	    steps++;
	    if (st%5==0) {
		try {
		    if (wMat) vectMat.write(st+ " "+eVect+"\n");
		    if (wPow) matPow.write(st+ " "+epsiPow+"\n");
		} catch (IOException ioe) {
		    System.err.println("Write error!");
		    ioe.printStackTrace();
		}
	    }
	}
	System.out.print("[Math] Walk done ("+st+" steps). Attempting to write to file...");
	try {
	    vectMat.close();
	    System.out.print("\tVector: OK");
	    matPow.close();
	    System.out.println(", Matrix: OK");
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
