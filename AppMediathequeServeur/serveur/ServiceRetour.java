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

public class ServiceRetour implements Runnable {
	
	private Socket socket;		//socket utilise pour le service
	//liste des documents
	private static List<Document> lesDocuments = new ArrayList<Document>();
	
	/*
	 * @param in Socket socket : utilise pour le service
	 */
	public ServiceRetour(Socket socket) {
		this.socket = socket;
	}
	//initialise la liste des documents
	public static void setLesListes(List<Abonne> lesAbonnes, List<Document> doc) {
		ServiceRetour.lesDocuments = doc;
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
	 * prend le numero d'un document entre par l'utilisateur
	 * et rend le document qui y correspond
	 */
	@Override
	public void run() {
		synchronized(this) {
			try {
				BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
						
				int idDocument = Integer.parseInt(in.readLine());
				Document doc = getDocument(idDocument);
				
				doc.retour();
				out.println("retour du produit : (" + doc.numero() + ")");
		
				
			} catch (IOException e) {
				try {
					PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
					
					out.println("Le produit est deja dispo");
					
				} catch (IOException e1) {}
			}
		}
	}
}
