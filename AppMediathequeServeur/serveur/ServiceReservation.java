package serveur;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import produit.Document;
import abonne.Abonne;

public class ServiceReservation implements Runnable{

	private Socket socket;
	private static List<Abonne> lesAbonnes = new ArrayList<Abonne>();
	private static List<Document> lesDocuments = new ArrayList<Document>();
	
	public ServiceReservation(Socket socket) {
		this.socket = socket;
	}
	
	public static void setLesListes(List<Abonne> lesAbonnes, List<Document> doc) {
		ServiceReservation.lesAbonnes = lesAbonnes;
	}

	private static Abonne getAbonne(int id) {
		for (Abonne ab : lesAbonnes)
			if (ab.getNumero() == id)
				return ab;
		return null;
	}
	
	private static Document getDocument(int id) {
		for (Document ab : lesDocuments)
			if (ab.numero() == id)
				return ab;
		return null;
	}
	
	@Override
	public void run() {
		
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

			
			int i = Integer.parseInt(in.readLine());			
			//int j = Integer.parseInt(in.readLine());
			
			Abonne a = getAbonne(i);
			//Document b = getDocument(j);
			
			
			out.println(a.getNom());
			//out.println(s);
			
			
		} catch (IOException e) {
			
		}
		
	}
}