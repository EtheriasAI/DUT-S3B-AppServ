package produit;

import IDocument.Document;

public class EmpruntException extends Exception {

	private static final long serialVersionUID = 1L;

	private Document produit;

	public EmpruntException(Document produit, int nbPlaces) {
		this.produit = produit;
	}

	@Override
	public String toString() {
		return "Désolé, le produit " + produit.numero() + "est deja emprunte";
	}
	
}
