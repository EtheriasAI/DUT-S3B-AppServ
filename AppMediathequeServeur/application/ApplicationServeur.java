package application;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import IDocument.Document;
import abonne.Abonne;
import abonne.AbonneClassique;
import produit.DVD;
import serveur.ServeurEmprunt;
import serveur.ServeurReservation;
import serveur.ServeurRetour;
import serveur.ServiceEmprunt;
import serveur.ServiceReservation;
import serveur.ServiceRetour;

public class ApplicationServeur {
	private final static int PORTReserver  = 3000;	//numero de port de reservation
	private final static int PORTEmprunter = 4000;	//numero de port d'emprunt
	private final static int PORTRetourer  = 5000;	//numero de port de retour
	
	public static void main(String[] args) throws IOException{
		//cree les listes d'abonnes et de documents
		List<Abonne> lesAbonnes = initAbonne();
		List<Document> doc = initDocument();
		
		//envoie les a tous les services
		ServiceEmprunt.setLesListes(lesAbonnes, doc);
		ServiceReservation.setLesListes(lesAbonnes, doc);
		ServiceRetour.setLesListes(lesAbonnes, doc);

		//lance le serveur de reservation
		new Thread(new ServeurReservation(PORTReserver)).start();
		System.out.println("Serveur Reservation lance sur le port " + PORTReserver);
		
		//lance le serveur d'emprunt
		new Thread(new ServeurEmprunt(PORTEmprunter)).start();
		System.out.println("Serveur Emprunt lance sur le port " + PORTEmprunter);
		
		//lance le serveur de retour
		new Thread(new ServeurRetour(PORTRetourer)).start();
		System.out.println("Serveur Retour lance sur le port " + PORTRetourer);
		
		
	}
	//cree une liste d'abonne
	public static List<Abonne> initAbonne() {
		List<Abonne> abonnes = new ArrayList<Abonne>();
		abonnes.add(new AbonneClassique(1, "Choeurtis", "18/01/2002", true));
		abonnes.add(new AbonneClassique(2, "Farah", "04/07/2001", true));
		abonnes.add(new AbonneClassique(3, "Justine", "06/12/2005", false));
		
		return abonnes;
	}
	//cree une liste de DVD
	public static List<Document> initDocument() {
		List<Document> doc = new ArrayList<Document>();
		doc.add(new DVD(1, false));
		doc.add(new DVD(2, false));
		doc.add(new DVD(3, true));
		
		return doc;
	}
	
}