package produit;

import java.util.Timer;
import java.util.TimerTask;

import abonne.Abonne;

public class Geronimo extends TimerTask{
    private Timer timer;//timer utilise
    private Abonne a;	//abonne qui a emprunte
    
    /*
     * @param in Abonne a : abonne qui a emprunter
     * @param in Timer timer : timer de l'emprunt
     */
    public Geronimo(Abonne a, Timer timer) {
        this.timer = timer;
        this.a =a;
    }

    /*
     * run du TimerTask
     * il banni l'abonne et lance un banissement
     * qui prendra fin a la fin du timer
     */
    @Override
    public void run() {
    	this.a.setBan(true);
        this.timer.cancel(); 
        timer= new Timer();
		timer.schedule(new DeBan(a, timer), 60000);
    }
    
    /*
     * classe qui gere le banissement de l'abonne
     */
    private class DeBan extends TimerTask{
    	private Timer timer;//timer utilise
        private Abonne a;	//abonne banni
    	
        /*
         * @param in Abonne a : abonne banni
         * @param in Timer timer : timer de l'emprunt
         */
    	public DeBan(Abonne a, Timer timer) {
            this.timer = timer;
            this.a =a;
        }

    	/*
         * run du TimerTask
         * debanni l'abonne a la fin du temps
         */
        @Override
        public void run() {
        	this.a.setBan(false);
            this.timer.cancel(); 
        }
    }
}
