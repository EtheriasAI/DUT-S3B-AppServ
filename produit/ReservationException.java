package produit;

public class ReservationException  extends Exception{
	private static final long serialVersionUID = 1L;
	
	private Document produit;

	public ReservationException(DVD produit, int nbPlaces) {
		this.produit = produit;
	}

	@Override
	public String toString() {
		return "Désolé, le produit " + produit.numero() + " est deja reserve";
	}
	
}
