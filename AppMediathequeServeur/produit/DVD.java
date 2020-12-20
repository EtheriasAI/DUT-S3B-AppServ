package produit;

import IDocument.Document;
import abonne.Abonne;

public class DVD implements Document{
	private int numero;
	private Boolean disponibilite = true;
	private String nom;
	private Boolean pourAdulte;
	
	Abonne ab;
	public DVD(int numero, String nom, Boolean pourAdulte) {
		this.numero = numero;
		this.nom = nom;
		this.pourAdulte = pourAdulte;
	}
	@Override
	public int numero() {
		return this.numero;
	}

	@Override
	public void reservationPour(Abonne ab) throws ReservationException {
		this.ab = ab;
		this.setDisponibilite(false);
	}

	@Override
	public void empruntPar(Abonne ab) throws EmpruntException {
		this.ab = ab;
		this.setDisponibilite(false);
	}

	@Override
	public Boolean verifReservation(Abonne ab) {
		if(this.ab.getNumero() == ab.getNumero()) {
			return true;
		}
		else {
			return false;
		}
	}
	
	@Override
	public String getNom() {
		return this.nom;
	}
	
	@Override
	public Boolean getPourAdulte() {
		return pourAdulte;
	}
	
	@Override
	public void retour() {
		this.setDisponibilite(true);
	}
	
	@Override
	public Boolean getDisponibilite() {
		return disponibilite;
	}
	private void setDisponibilite(Boolean disponibilite) {
		this.disponibilite = disponibilite;
	}
	
}