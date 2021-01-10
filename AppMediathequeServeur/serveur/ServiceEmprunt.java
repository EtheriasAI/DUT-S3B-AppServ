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
import produit.EmpruntException;

public class ServiceEmprunt implements Runnable {

	private Socket socket;		//socket utilise pour le service
	//listes des documents et abonnes
	private static List<Abonne> lesAbonnes = new ArrayList<Abonne>();
	private static List<Document> lesDocuments = new ArrayList<Document>();
	
	/*
	 * @param in Socket socket : utilise pour le service
	 */
	public ServiceEmprunt(Socket socket) {
		this.socket = socket;
	}
	//initialise les listes des documents et des abonnes
	public static void setLesListes(List<Abonne> lesAbonnes, List<Document> doc) {
		ServiceEmprunt.lesAbonnes = lesAbonnes;
		ServiceEmprunt.lesDocuments = doc;
	}
	/*
	 * @param in int id : id de l'abonne selectionne
	 * @return l'abonne qui correspond ou null
	 * si l'abonne n'existe pas
	 */
	private static Abonne getAbonne(int id) {
		for (Abonne ab : lesAbonnes) {
			if (ab.getNumero() == id) {
				return ab;
			}
		}
		return null;
	}
	/*
	 * @param in int id : id du document selectionne
	 * @return le document qui correspond ou null
	 * si le document n'existe pas
	 */
	private static Document getDocument(int id) {
		for (Document ab : lesDocuments) {
			if (ab.numero() == id) {
				return ab;
			}
		}
		return null;
	}
	/*
	 * run du service
	 * prend le numero d'un document et d'un abonne entres par l'utilisateur
	 * et emprunte le document si EmpruntException e ne se lance pas
	 * EmpruntException e renvoie au client la raison d'une erreur
	 */
	@Override
	public void run() {
		synchronized(this) {
			try {
				//pour lire le message du client
				BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				//pour repondre au client
				PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
				
				int idAbonne = Integer.parseInt(in.readLine());			
				int idDocument = Integer.parseInt(in.readLine());
				
				Abonne ab = getAbonne(idAbonne);
				Document doc = getDocument(idDocument);
				
				doc.empruntPar(ab);
				out.println("Emprunt effectue : "+ab.getNom() + " a emprunte " + doc.numero());
				
					
			} catch (IOException | EmpruntException e) {
				try{
					PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
					out.println(e);
				
				}catch(IOException e1) {}
			}
		}
	}

}
	