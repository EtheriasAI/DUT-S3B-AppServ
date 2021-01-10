package produit;

import java.util.Timer;
import java.util.TimerTask;

public class RetardReservation extends TimerTask{

    private DVD doclouer;	//le document qui a ete reserver
    private Timer timer;	//timer utilise
    
    /*
     * @param in DVD doclouer : DVD reserve
     * @param in Timer timer : timer de la reservation
     */
    public RetardReservation(DVD doclouer, Timer timer) {
        this.doclouer = doclouer;
        this.timer = timer;
    }
    /*
     * run du TimerTask
     * il retour le DVD a la vente et annule le timer
     */
    @Override
    public void run() {
    	this.doclouer.retour();
        this.timer.cancel();    
    }

}