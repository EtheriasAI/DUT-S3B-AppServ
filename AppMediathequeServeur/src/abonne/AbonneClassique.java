package abonne;

public class AbonneClassique implements Abonne{
	private int numero;
	private String nom;
	private String dateNaissance;
	private boolean naissance;
	
	public AbonneClassique(int numero, String nom, String dateNaissance, Boolean naissance) {
		this.numero = numero;
		this.nom = nom;
		this.dateNaissance = dateNaissance;
		this.naissance = naissance;
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

	public Boolean getDateNaissanceV2() {
		return naissance;
	}
	
}
