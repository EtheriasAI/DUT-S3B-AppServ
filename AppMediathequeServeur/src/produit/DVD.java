package produit;

import IDocument.Document;
import abonne.Abonne;

public class DVD implements Document{
	private int numero;
	private Boolean disponibilite = true;
	Abonne ab;
	public DVD(int numero) {
		this.numero = numero;
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
	public void retour() {
		this.setDisponibilite(true);
		
	}
	
	public Boolean getDisponibilite() {
		return disponibilite;
	}
	private void setDisponibilite(Boolean disponibilite) {
		this.disponibilite = disponibilite;
	}
	
}