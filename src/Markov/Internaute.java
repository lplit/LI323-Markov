package Markov;
public interface Internaute {
    public void trace(String filename);
    public double getEpsiMax();
    public void walk(int n, double e);
    public void showEpsi();
}
