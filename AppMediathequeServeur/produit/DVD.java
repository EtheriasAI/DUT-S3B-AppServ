package produit;

import java.util.Timer;

import IDocument.Document;
import abonne.Abonne;
import produit.RetardReservation;

public class DVD implements Document{
	private int numero;						//numero du DVD
	//disponibilite du DVD si il est reserver emprunter ou disponible
	//a sa creation il est disponible
	private Boolean disponibilite = true;	
	private Boolean pourAdulte;				//si le DVD est pour adulte

	//timer pour les TimerTask
	Timer timer;
	
	//abonne qui va reserver
	Abonne ab;
	
	/*
	 * @param in int numero : id du DVD
	 * @param in Boolean pourAdulte : si le DVD est pour adulte ou pas
	 */
	public DVD(int numero, Boolean pourAdulte) {
		this.numero = numero;
		this.pourAdulte = pourAdulte;
	}
	//@return id du DVD
	@Override
	public int numero() {
		return this.numero;
	}
	/*
	 * teste si l'abonne peut reserver ou pas
	 * si l'abonne peut alors un timer se declenche
	 * temps pour emprunter le DVD sinon le DVD redevient disponible
	 * @param in Abonne ab : Abonne qui souhaite reserver
	 * @throw ReservationException selon le cas
	 */
	@Override
	public void reservationPour(Abonne ab) throws ReservationException{
		
		//pas disponible
		if(!this.getDisponibilite()) {
			String erreur_message = "Désolé, le produit (" + this.numero() + ") est deja reserve";
			throw new ReservationException(1,erreur_message);
		}
		//si l'abonne est banni
		else if(ab.getBan()){
			String erreur_message = "Desole vous ne pouvez pas reserver car vous etes banni";
			throw new ReservationException(2,erreur_message);
		}
		//disponible mais age non requis
		else if(this.getPourAdulte() && !ab.getDateNaissanceBoolean()) {
				String erreur_message = "Désolé, vous n'avez pas l'age requis pour le produit (" + this.numero() + ")";
				throw new ReservationException(3,erreur_message);
		}
		//reservation
		else {
			this.ab = ab;
			this.setDisponibilite(false);
			this.timer= new Timer();
			this.timer.schedule(new RetardReservation(this, timer), 15000);
		}
		
	}
	/*
	 * teste si l'abonne peut reserveremprunter ou pas
	 * si l'abonne peut alors un timer se declenche
	 * temps pour retourner le DVD sinon l'abonne est banni
	 * @param in Abonne ab : Abonne qui souhaite emprunter
	 * @throw EmpruntException selon le cas
	 */
	@Override
	public void empruntPar(Abonne ab) throws EmpruntException {
		
		//on teste si le produit a ete reserver
		if(this.ab == ab) {
			timer.cancel();
			timer= new Timer();
			timer.schedule(new Geronimo(ab, timer), 30000);
		}
		//si l'abonne est banni
		else if(ab.getBan()){
			String erreur_message = "Desole vous ne pouvez pas emprunter car vous etes banni";
			throw new EmpruntException(erreur_message);
		}
		//si le produit n'est pas disponible
		else if(!this.getDisponibilite()) {
			String erreur_message = "Desole le produit ("+this.numero+") a deja ete emprunte";
			throw new EmpruntException(erreur_message);
		}
		//disponible mais age non requis
		else if(
				(this.getPourAdulte()) && 
				(!ab.getDateNaissanceBoolean())) {
			String erreur_message = "Desole vous n'avez pas l'age requis pour le produit ("+this.numero+")";
			throw new EmpruntException(erreur_message);
		}
		//emprunter
		else{
			this.ab = ab;
			this.setDisponibilite(false);
			timer= new Timer();
			timer.schedule(new Geronimo(ab, timer), 30000);
		}
		
	}
	//@return si le DVD est pour adulte ou non
	public Boolean getPourAdulte() {
		return pourAdulte;
	}
	//@return si le DVD est disponible ou pas
	public Boolean getDisponibilite() {
		return disponibilite;
	}
	/*
	 * @param in Boolean disponibilite
	 * met disponibilite libre ou pas
	 * si le DVD est maintenant disponible alors notifyAll() 
	 * previendra tout les utilisateurs souhaitant une alerte
	 */
	private void setDisponibilite(Boolean disponibilite) {
		this.disponibilite = disponibilite;
		if(disponibilite)
			synchronized(this){this.notifyAll();}
	}
	/*
	 * permet de rendre le DVD et d'annuler
	 * l'Abonne ab qui avait soit reserver et/ou
	 * emprunter le DVD et annule le timer lancer
	 * pour les retard ou annulation
	 */
	@Override
	public void retour() {
		if(!disponibilite) {
		this.setDisponibilite(true);
		this.ab=null;
		timer.cancel();
		}
		
	}
	

}