package produit;


public class EmpruntException extends Exception {

	private static final long serialVersionUID = 1L;
	//message d'erreur
	private String message;

	/*
	 * @param in String m : message d'erreur
	 */
	public EmpruntException(String m) {
		this.message = m;
	}

	//@return message d'erreur
	@Override
	public String toString() {
		return "\nErreur emprut <-- " + message;
	}
		
}
