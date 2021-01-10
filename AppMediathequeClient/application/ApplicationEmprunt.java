package application;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ApplicationEmprunt {
	private final static int PORTEmprunter = 4000;
	private final static String HOST = "localhost"; 

	public static void main(String[] args) throws IOException {
		//Scanner pour le choix du service
		Scanner clavierChoix = new Scanner(System.in);
		
		Socket socket = null;
		// Cree les streams pour lire et ecrire du texte dans cette socket
		BufferedReader sin;
		PrintWriter sout;		
		
		//variables pour recuperer et envoyer les informarions au serveur
		String idAbonne;
		String idDoc;
		String messageServeur;
		
		// Cree le stream pour lire du texte a partir du clavier 
		BufferedReader clavierClient = new BufferedReader(new InputStreamReader(System.in));	
		
		System.out.print("Ouverture de l'application Emprunt :\n ");

		// Cree une socket pour communiquer avec le service se trouvant sur la
		// machine host au port PORT
		socket = new Socket(HOST, PORTEmprunter);
  
		// Cree les streams pour lire et ecrire du texte dans cette socket
		sin = new BufferedReader (new InputStreamReader(socket.getInputStream ()));
		sout = new PrintWriter (socket.getOutputStream (), true);
  		
		// Informe l'utilisateur de la connection
		System.out.println("Connecté au serveur " + socket.getInetAddress() + ":"+ socket.getPort());

  
		System.out.println("Tapez votre ID");
		System.out.print("->");
		idAbonne = clavierClient.readLine();
  
  
		System.out.println("Tapez ID du document");
		System.out.print("->");
		idDoc = clavierClient.readLine();
  
		// envoie au serveur
		sout.println(idAbonne);
		sout.println(idDoc);
  
		// lit la réponse provenant du serveur
		System.out.println(sin.readLine());
		messageServeur = sin.readLine();
  
		// Ecrit la ligne envoyee par le client
		System.out.println("\nReponse du serveur <-- " + messageServeur);
		socket.close();
  
		// Refermer dans tous les cas la socket
		try { 
			if (socket != null) {
				socket.close(); 
			}
		} catch (IOException e2) {}
  		  
		clavierChoix.close();
	}
}
