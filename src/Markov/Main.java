package Markov; 
public class Main {
    public static void main(String[] args) {
	NanoWeb[] webs = {NanoWeb.nanoWeb1(), NanoWeb.nanoWeb2(), NanoWeb.nanoWeb3()};
		
	int steps = 400, 
	    go = 5,
	    i=1;
	double epsi = 0.001;
	for (NanoWeb n : webs) {
	    System.out.println("/************************************NanoWEB : "+i);
	    String simu = "nano"+(i)+"_simu.txt";
	    String vect = "nano"+(i)+"_vect.txt";
	    String matr = "nano"+(i++)+"_matrix.txt";
	    InternauteSimulation bob = new InternauteSimulation(n, simu);
	    InternauteMath bobTheMathGuy = new InternauteMath(n, vect, matr);

	    bob.goTo(go);
	    bobTheMathGuy.goTo(go);

	    bob.walk(steps, epsi);
	    bobTheMathGuy.walk(steps, epsi);

	    bobTheMathGuy.showEpsi();
	    System.out.println("Matrix:");
	    bobTheMathGuy.printMatrix();
	    System.out.println("\n\n\n");
	}
	
	SimpleWeb sw = SimpleWeb.generateRandomWeb(10);
	InternauteSimulation is = new InternauteSimulation(sw, "random_simu.txt");
	InternauteMath im = new InternauteMath(sw, "random_vect.txt", "random_matrix.txt");

	is.goTo(go);
	im.goTo(go);
	im.walk(steps, epsi);
	is.walk(steps, epsi);
	
	sw.showTransitionTable();
    }
}
