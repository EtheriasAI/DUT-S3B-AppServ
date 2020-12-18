package application;

public class AbonneClassique implements Abonne{
	private int numero;
	private String nom;
	private String dateNaissance;
	
	public AbonneClassique(int numero, String nom, String dateNaissance) {
		this.numero = numero;
		this.nom = nom;
		this.dateNaissance = dateNaissance;
	}


	public int getNumero() {
		return numero;
	}
	
	public String getNom() {
		return nom;
	}
	
	public String getDateNaissance() {
		return dateNaissance;
	}
	
}
