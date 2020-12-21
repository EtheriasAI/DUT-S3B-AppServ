package application;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ApplicationClient {
	private final static int PORTReserver  = 3000;
	private final static int PORTEmprunter = 4000;
	private final static int PORTRetourer  = 5000;
	private final static String HOST = "localhost"; 
	
	public static void main(String[] args) throws IOException {
		//Scanner pour le choix du service
		Scanner clavierChoix = new Scanner(System.in);
		Integer choixApp;
		
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
		
		System.out.print("Choix du service [ emprunter(1) | reserver(2) | retourner(3) ] : ");
		choixApp = clavierChoix.nextInt();
		
		switch(choixApp) {
		  case 1:
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
			  messageServeur = sin.readLine();
			  
			  // Ecrit la ligne envoyee par le client
			  System.out.println("Reponse du serveur :\n" + messageServeur);
			  socket.close();
			  
			  // Refermer dans tous les cas la socket
			  try { 
				  if (socket != null) 
					  socket.close(); 
			  } catch (IOException e2) {}
			  
			  break;
		  case 2:
			  // Cree une socket pour communiquer avec le service se trouvant sur la
			  // machine host au port PORT
			  socket = new Socket(HOST, PORTReserver);
			  
			  // Cree les streams pour lire et ecrire du texte dans cette socket
			  sin = new BufferedReader (new InputStreamReader(socket.getInputStream ( )));
			  sout = new PrintWriter (socket.getOutputStream ( ), true);
			  		
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
			  messageServeur = sin.readLine();
			  // Ecrit la ligne envoyee par le serveur
			  System.out.println("Reponse du serveur\n" + messageServeur);
			  socket.close();
			  
			  // Refermer dans tous les cas la socket
			  try { 
				  if (socket != null) 
					  socket.close(); 
			  } catch (IOException e2) {}
			  
			  		  
			  break;
		  case 3:  
			  // Cree une socket pour communiquer avec le service se trouvant sur la
			  // machine host au port PORT
			  socket = new Socket(HOST, PORTRetourer);
			  
			  // Cree les streams pour lire et ecrire du texte dans cette socket
			  sin = new BufferedReader (new InputStreamReader(socket.getInputStream ( )));
			  sout = new PrintWriter (socket.getOutputStream ( ), true);
			  		
			  // Informe l'utilisateur de la connection
			  System.out.println("Connecté au serveur " + socket.getInetAddress() + ":"+ socket.getPort());
			  
			  System.out.println("Tapez ID du document");
			  System.out.print("->");
			  idDoc = clavierClient.readLine();
			  
			  // envoie au serveur
			  sout.println(idDoc);
			  
			  // lit la réponse provenant du serveur
			  messageServeur = sin.readLine();
			  // Ecrit la ligne envoyee par le serveur
			  System.out.println("Reponse du serveur\n" + messageServeur);
			  socket.close();
			  
			  // Refermer dans tous les cas la socket
			  try { 
				  if (socket != null) 
					  socket.close(); 
			  } catch (IOException e2) {}
			  
			  break;
			  
		  default:
			  clavierClient.close();
			  System.out.print("Service non reconnue ");
		}
		
		clavierChoix.close();
	}
}
