package Markov; 
public class Main {
    public static void main(String[] args) {
	NanoWeb bobWeb = NanoWeb.nanoWeb2();
	NanoWeb bobMathWeb = NanoWeb.nanoWeb2();
	InternauteSimulation bob = new InternauteSimulation(bobWeb, "nano2_simu.txt");
	InternauteMath bobTheMathGuy = new InternauteMath(bobMathWeb, "nano2_vect.txt", "nano2_matrix.txt");
	
	int steps = 400, 
	    go = 1;
	double epsi = 0.001;

	bob.goTo(go);
	bobTheMathGuy.goTo(go);


	bob.walk(steps, epsi);
	bobTheMathGuy.walk(steps, epsi);

	bob.showFrequences();
	bob.showEpsi();

	int pow = bobTheMathGuy.getSteps();
	System.out.println("Matrix to the power of "+pow);
	bobTheMathGuy.printMatrix();
	bobTheMathGuy.showEpsi();

	SimpleWeb sw = SimpleWeb.generateRandomWeb(4);
	InternauteSimulation is = new InternauteSimulation(sw, "random_simu.txt");
	InternauteMath im = new InternauteMath(sw, "random_vect.txt", "random_matrix.txt");

	is.goTo(go);
	im.goTo(go);
	im.walk(steps, epsi);
	is.walk(steps, epsi);
	
	sw.showTransitionTable();
    }
}
