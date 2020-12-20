package IDocument;

import abonne.Abonne;
import produit.EmpruntException;
import produit.ReservationException;

public interface Document {
	int numero();
	String getNom();
	void reservationPour(Abonne ab) throws ReservationException ;
	void empruntPar(Abonne ab) throws EmpruntException;
	
	//verifie si l'abonne a reserver le produit
	Boolean verifReservation(Abonne ab);
	
	//verifie si le produit est pour les adultes
	Boolean getPourAdulte();
	
	//verifie si le produit est dispo
	Boolean getDisponibilite();
	
	// retour document ou annulation réservation
	void retour();
}
