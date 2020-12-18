import java.io.IOException;

import action.ReservationException;
import serveur.ApplicationClient;
import mediatheque.Abonne;
import produit.DVD;
import produit.Document;

public class main {

	public static void main(String[] args) {
		
		ApplicationClient test = new ApplicationClient();
		test.run();
		
		Abonne a = new Abonne("Brette",1);
		
		Document b = new DVD();
		
		try {
			b.reservationPour(a);
		} catch (ReservationException e1) {e1.printStackTrace();}

		System.out.println("fini");
		
		try {
			b.reservationPour(a);
		} catch (ReservationException e1) {e1.printStackTrace();}

		
	}

}
