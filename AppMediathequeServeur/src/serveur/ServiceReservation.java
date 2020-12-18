package serveur;

import java.util.ArrayList;
import java.util.List;

import IDocument.Document;
import abonne.Abonne;

public class ServiceReservation implements Runnable{


	private static List<Abonne> lesAbonnes = new ArrayList<Abonne>();
	
	public static void setLesAbonnes(List<Abonne> lesAbonnes, List<Document> doc) {
		ServiceReservation.lesAbonnes = lesAbonnes;
	}

	private static Abonne getAbonne(int id) {
		for (Abonne ab : lesAbonnes)
			if (ab.getNumero() == id)
				return ab;
		return null;
	}

	
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
}
