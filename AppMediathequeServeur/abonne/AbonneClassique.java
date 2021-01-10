package abonne;

public class AbonneClassique implements Abonne{
	private int numero;					//id de l'abonne
	private String nom;					//nom de l'abonne
	private String dateNaissance;		//date de naissance de l'abonne
	private boolean naissanceBoolean;	//si l'abonne est majeur ou mineur
	private boolean isBan;				//si l'abonne est banni ou non
	
	/*
	 * @param in int numero : id de l'abonne
	 * @param in String nom : nom de l'abonne
	 * @param in String dateNaissance : date de naissance de l'abonne
	 * @param in boolean naissanceBoolen : si l'abonne est mineur ou majeur
	 * par defaut lorqu'un abonne est inscrit il n'est pas banni
	 */
	public AbonneClassique(int numero, String nom, String dateNaissance, Boolean naissanceBoolean) {
		this.numero = numero;
		this.nom = nom;
		this.dateNaissance = dateNaissance;
		this.naissanceBoolean = naissanceBoolean;
		this.isBan = false;
	}

	//@return l'id de l'abonne
	@Override
	public int getNumero() {
		return numero;
	}
	//@return le nom de l'abonne
	@Override
	public String getNom() {
		return nom;
	}
	//@return la date de naissance d'un abonne
	@Override
	public String getDateNaissance() {
		return dateNaissance;
	}
	//@return si l'abonne est mineur ou majeur
	@Override
	public Boolean getDateNaissanceBoolean() {
		return naissanceBoolean;
	}
	//@return si l'abonne est banni ou non
	@Override
	public Boolean getBan() {
		return isBan;
	}
	//@param in boolean a : banni ou debanni l'abonne
	@Override
	public void setBan(boolean a) {
		isBan = a;
	}
	
}
