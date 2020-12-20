package abonne;

public class AbonneClassique implements Abonne{
	private int numero;
	private String nom;
	private String dateNaissance;
	private boolean naissanceBoolean;
	
	public AbonneClassique(int numero, String nom, String dateNaissance, Boolean naissanceBoolean) {
		this.numero = numero;
		this.nom = nom;
		this.dateNaissance = dateNaissance;
		this.naissanceBoolean = naissanceBoolean;
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

	public Boolean getDateNaissanceBoolean() {
		return naissanceBoolean;
	}
	
}
