package mediatheque;

public class Abonne {

	private boolean peut; //reserver ou emprunter
	private boolean adulte;
	private String name;
	private int numero;
	
	public Abonne(String name,int numero) {
		this.name=name;
		this.peut=true;
		this.numero = numero;
		this.adulte=true;
	}
	
	public boolean peut() {
		return peut || adulte;
	}
	
}
