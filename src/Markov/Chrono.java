package Markov;
import java.util.Date;

/**
 * @author sigaud
 * Cette classe permet de chronomÃ©trer le temps de calcul d'un logiciel
 * Le temps renvoyÃ© est le temps Ã©coulÃ© entre l'appel du constructeur et
 * l'appel de la mÃ©thode stop()
 */
public class Chrono {
    private long time;
 
    public Chrono(){
	Date d = new Date();
	time = d.getTime();
    }
    public void stop(){
	Date d = new Date();
	long timeFin = d.getTime();
	long interv = timeFin - time;
	affiche(interv);
    }
    public void affiche(long millis){
	long ms,secondes, s, minutes, m, heures;
	if (millis>=1000){
	    secondes = millis/1000;
	    ms = millis - secondes*1000;
	}
	else {
	    System.out.println("Time : "+millis + " ms");
	    return;
	}
	if (secondes>=60){
	    minutes = secondes/60;
	    s = secondes - 60*minutes;
	}
	else{
	    System.out.println("Time : "+secondes + " s "+ms + " ms");
	    return;
	}
	if (minutes>=60){
	    heures = minutes/60;
	    m = minutes - 60*heures;
	    System.out.println("Time : " + " h "+m + " mn "+s + " s "+ms + " ms");
	    return;
	}
	else  {
	    System.out.println("Time : "+minutes + " mn "+s + " s "+ms + " ms");
	    return;
	}
    }
}
