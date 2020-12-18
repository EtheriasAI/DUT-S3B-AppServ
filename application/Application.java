package application;

import java.util.ArrayList;
import java.util.List;

import serveur.ServeurEmprunt;
import serveur.ServeurReservation;
import serveur.ServeurRetour;
import serveur.ServiceEmprunt;
import serveur.ServiceReservation;
import serveur.ServiceRetour;

public class Application {
	private final static int PORTReserver  = 3000;
	private final static int PORTEmprunter = 4000;
	private final static int PORTRetourer  = 5000;
	
	public static void main(String[] args) throws Exception {
		List<Abonne> lesAbonnes = initAbonne();
		ServiceEmprunt.setLesAbonnes(lesAbonnes);
		ServiceReservation.setLesAbonnes(lesAbonnes);
		ServiceRetour.setLesAbonnes(lesAbonnes);
		
		new Thread(new ServeurEmprunt(PORTEmprunter)).start();
		System.out.println("Serveur Emprunt lance sur le port " + PORTEmprunter);
		
		new Thread(new ServeurReservation(PORTReserver)).start();
		System.out.println("Serveur Reservation lance sur le port " + PORTReserver);
		
		new Thread(new ServeurRetour(PORTRetourer)).start();
		System.out.println("Serveur Retour lance sur le port " + PORTRetourer);
	}
	
	public static List<Abonne> initAbonne() {
		List<Abonne> abonnes = new ArrayList<Abonne>();
		abonnes.add(new AbonneClassique(1, "Choeurtis", "18/01/2002"));
		abonnes.add(new AbonneClassique(2, "Farah", "04/07/2005"));
		abonnes.add(new AbonneClassique(3, "Justine", "11/12/2001"));
		
		return abonnes;
	}
	
}
