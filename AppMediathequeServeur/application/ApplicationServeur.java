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
	private final static int PORTReserver  = 3000;
	private final static int PORTEmprunter = 4000;
	private final static int PORTRetourer  = 5000;
	
	public static void main(String[] args) throws IOException{
		List<Abonne> lesAbonnes = initAbonne();
		List<Document> doc = initDocument();
		
		ServiceEmprunt.setLesListes(lesAbonnes, doc);
		ServiceReservation.setLesListes(lesAbonnes, doc);
		ServiceRetour.setLesListes(lesAbonnes, doc);

		new Thread(new ServeurReservation(PORTReserver)).start();
		System.out.println("Serveur Reservation lance sur le port " + PORTReserver);
		
		new Thread(new ServeurEmprunt(PORTEmprunter)).start();
		System.out.println("Serveur Emprunt lance sur le port " + PORTEmprunter);
		
		new Thread(new ServeurRetour(PORTRetourer)).start();
		System.out.println("Serveur Retour lance sur le port " + PORTRetourer);
	}
	public static List<Abonne> initAbonne() {
		List<Abonne> abonnes = new ArrayList<Abonne>();
		abonnes.add(new AbonneClassique(1, "Choeurtis", "18/01/2002", true));
		abonnes.add(new AbonneClassique(2, "Farah", "04/07/2001", true));
		abonnes.add(new AbonneClassique(3, "Justine", "06/12/2005", false));
		
		return abonnes;
	}
	public static List<Document> initDocument() {
		List<Document> doc = new ArrayList<Document>();
		doc.add(new DVD(1, "Le Labyrinthe", false));
		doc.add(new DVD(2, "Avengers", false));
		doc.add(new DVD(3, "Sex Tape", true));
		
		return doc;
	}
}