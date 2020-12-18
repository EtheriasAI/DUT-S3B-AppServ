package IDocument;

import abonne.Abonne;
import produit.EmpruntException;
import produit.ReservationException;

public interface Document {
	int numero();
	void reservationPour(Abonne ab) throws ReservationException ;
	void empruntPar(Abonne ab) throws EmpruntException;
	
	// retour document ou annulation réservation
	void retour();
}
