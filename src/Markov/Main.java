package Markov; 
public class Main {
    public static void main(String[] args) {
	NanoWeb bobWeb = NanoWeb.nanoWeb2();
	NanoWeb bobMathWeb = NanoWeb.nanoWeb2();
	InternauteSimulation bob = new InternauteSimulation(bobWeb, "simu.txt");
	InternauteMath bobTheMathGuy = new InternauteMath(bobMathWeb, "math.txt");
	
	int steps = 10000, 
	    go = 4;
	double epsi = 0.001;

	bob.goTo(go);
	bobTheMathGuy.goTo(go);


	bob.walk(steps, epsi);
	bobTheMathGuy.walk(steps, 0.00000000000001);

	bob.showFrequences();
	bob.showEpsi();

	int pow = bobTheMathGuy.getSteps();
	System.out.println("Matrix to the power of "+pow);
	bobTheMathGuy.printMatrix();
	bobTheMathGuy.showEpsi();
    }
}
