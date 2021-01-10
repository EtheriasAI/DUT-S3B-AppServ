package abonne;

public interface Abonne {
	
	int getNumero();					//@eturn id abonne
	String getNom();					//@return nom de l'abonne
	String getDateNaissance();			//@return date de naissance de l'abonne
	Boolean getDateNaissanceBoolean();	//@return si l'abonne est mineur ou majeur
	Boolean getBan();					//@return si l'abonne est banni ou pas
	void setBan(boolean a);				//@param in boolean a : banni ou debanni l'abonne
}
