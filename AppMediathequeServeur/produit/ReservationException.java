package produit;


public class ReservationException  extends Exception{
	private static final long serialVersionUID = 1L;
	
	private String message;	//message d'erreur
	private int n;			//numero d'erreur

	/*
	 * @param in String m : message d'erreur
	 */
	public ReservationException(int n,String m) {
		this.message = m;
		this.n=n;
	}

	//@return message d'erreur
	@Override
	public String toString() {
		return  "Erreur reservation n°"+ n +" <-- " + message;
	}
	
}
