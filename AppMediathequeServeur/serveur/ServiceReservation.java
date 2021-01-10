package serveur;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import IDocument.Document;
import abonne.Abonne;
import produit.ReservationException;

public class ServiceReservation implements Runnable{

	private Socket socket;		//socket utilise pour le service
	//listes des documents et abonnes
	private static List<Abonne> lesAbonnes = new ArrayList<Abonne>();
	private static List<Document> lesDocuments = new ArrayList<Document>();
	
	/*
	 * @param in Socket socket : utilise pour le service
	 */
	public ServiceReservation(Socket socket) {
		this.socket = socket;
	}
	//initialise les listes des documents et des abonnes
	public static void setLesListes(List<Abonne> lesAbonnes, List<Document> doc) {
		ServiceReservation.lesAbonnes = lesAbonnes;
		ServiceReservation.lesDocuments = doc;
	}
	/*
	 * @param in int id : id de l'abonne selectionne
	 * @return l'abonne qui correspond ou null
	 * si l'abonne n'existe pas
	 */
	private static Abonne getAbonne(int id) {
		for (Abonne ab : lesAbonnes)
			if (ab.getNumero() == id)
				return ab;
		return null;
	}
	/*
	 * @param in int id : id du document selectionne
	 * @return le document qui correspond ou null
	 * si le document n'existe pas
	 */
	private static Document getDocument(int id) {
		for (Document ab : lesDocuments)
			if (ab.numero() == id)
				return ab;
		return null;
	}
	/*
	 * run du service
	 * prend le numero d'un document et d'un abonne entres par l'utilisateur
	 * et reserve le document si ReservationException e ne se lance pas
	 * ReservationException e renvoie au client la raison d'une erreur
	 */
	@Override
	public void run() {
		synchronized(this) {
			Abonne ab=null;
			Document doc=null; 
			try {
				//pour lire le message du client
				BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				//pour repondre au client
				PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
	
				int idAbonne = Integer.parseInt(in.readLine());			
				int idDocument = Integer.parseInt(in.readLine());
				
				ab = getAbonne(idAbonne);
				doc = getDocument(idDocument); 
				
				doc.reservationPour(ab);
				
				out.println("Reservation effectuee : "+ab.getNom() + " a reserve " + doc.numero());			
				
			} catch (IOException | ReservationException e) {
				try {
					BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
					PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
					
					out.println(e);
					//si l'erreur 1 est trouver et que le client souhaite une alerte lorsque le produit sera disponible
					String test =in.readLine();
					if(test==null)
						return;
					else {
					if(test.contentEquals("oui")) {
						try
					    {
					      synchronized(doc) {
					    	doc.wait();
					       	out.println("Votre DVD est disponible");
					      }
					    } catch (InterruptedException e2) {}
					}
					else
						out.println("Dommage");}
				
				} catch (IOException e1) {}
			}
		}
		
	}
}