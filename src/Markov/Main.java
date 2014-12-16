package Markov; 
public class Main {
    public static void main(String[] args) {
	NanoWeb bobWeb = NanoWeb.nanoWeb2();
	NanoWeb bobMathWeb = NanoWeb.nanoWeb2();
	InternauteSimulation bob = new InternauteSimulation(bobWeb, "simulation.txt");
	InternauteMath bobTheMathGuy = new InternauteMath(bobMathWeb, "math.txt");
	bob.goTo(4);
	int steps = 10000;
	double epsi = 0.001;

	bob.walk(steps, epsi);
	bobTheMathGuy.walk(steps, epsi);

	bob.showFrequences();
	bob.showEpsi();

	int pow = bob.getSteps();
	System.out.println("^this to power of "+pow);
	bob.showPi();
    }
}
