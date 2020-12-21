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

	private Socket socket;
	private static List<Abonne> lesAbonnes = new ArrayList<Abonne>();
	private static List<Document> lesDocuments = new ArrayList<Document>();
	
	public ServiceEmprunt(Socket socket) {
		this.socket = socket;
	}
	
	public static void setLesListes(List<Abonne> lesAbonnes, List<Document> doc) {
		ServiceEmprunt.lesAbonnes = lesAbonnes;
		ServiceEmprunt.lesDocuments = doc;
	}

	private static Abonne getAbonne(int id) {
		for (Abonne ab : lesAbonnes) {
			if (ab.getNumero() == id) {
				return ab;
			}
		}
		return null;
	}
	
	private static Document getDocument(int id) {
		for (Document ab : lesDocuments) {
			if (ab.numero() == id) {
				return ab;
			}
		}
		return null;
	}
	
	@Override
	public void run() {

		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			
			int idAbonne = Integer.parseInt(in.readLine());			
			int idDocument = Integer.parseInt(in.readLine());
			
			Abonne ab = getAbonne(idAbonne);
			Document doc = getDocument(idDocument);
			
			if((doc.getPourAdulte() == true) && (ab.getDateNaissanceBoolean() == true) && 
					(doc.getDisponibilite() == true)
				) {
				out.println(ab.getNom() + " a emprunte " + doc.getNom());
				doc.empruntPar(ab);	
			}
			
			else if((doc.getDisponibilite() == true) && (doc.getPourAdulte() == false)) {
				out.println(ab.getNom() + " a emprunte " + doc.getNom());
				doc.empruntPar(ab);
			}
			
			else if((doc.getPourAdulte() == true) && (ab.getDateNaissanceBoolean() == false) && 
					(doc.getDisponibilite() == true)
				) {
				out.println("Vous n'avez pas l'age requit pour cet article :(");
			}
			
			else if(doc.getDisponibilite() == false) {
				out.println("le produit " + doc.getNom() + " n'est pas dispo pour le moment");
			}
			
			else {
				out.println("Ce cas n'est pas enregistre");
			}
				
		} catch (IOException | EmpruntException e) {
			
		}
		
	}

}
	