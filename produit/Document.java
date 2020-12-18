package produit;

import application.Abonne;

public interface Document {
	int numero();
	void reservationPour(Abonne ab) throws ReservationException ;
	void empruntPar(Abonne ab) throws EmpruntException;
	
	// retour document ou annulation réservation
	void retour();
}
