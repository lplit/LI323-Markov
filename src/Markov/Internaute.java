package Markov;
public interface Internaute {
    
	/**
	 * File output occurs while iterations%saveStep==0. 
	 */
	public final int saveStep=1;
    
	/**
     * Returns max from array
     * @param ep Array to analyse
     * @return Max value stored in array
     */
    public double maxArray(double[] ep);
    
	/**
     * Performs simulation({@link InternauteSimulation}) / calculation ({@link InternauteMath}).
     * @param n Maximal number of steps/iterations.
     * @param e Epsilon cap value. Function stops if current epsilon is lower than "e".
     */
    public void walk(int n, double e);
       
    /**
     * Prints vector.
     */
    public void showEpsi();
    
    /**
     * Manually override movement
     * @param n Go-to node ID
     */
    public void goTo(int n);
}
