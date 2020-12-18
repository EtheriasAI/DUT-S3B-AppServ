package produit;

import java.io.IOException;

import action.EmpruntException;
import action.ReservationException;
import mediatheque.Abonne;
import serveur.ApplicationClient;

public class DVD implements Document{
	
	private int numero;

	@Override
	public int numero() {
		return numero;
	}

	@Override
	public void reservationPour(Abonne ab) throws ReservationException {
		//retirertrycatchpourreservatonexception
		if(ab.peut())
			try {
				ApplicationClient.lancerServeur();
			} catch (IOException e) {e.printStackTrace();}
		
	}

	@Override
	public void empruntPar(Abonne ab) throws EmpruntException {
		
	}

	@Override
	public void retour() {
		
	}

}
