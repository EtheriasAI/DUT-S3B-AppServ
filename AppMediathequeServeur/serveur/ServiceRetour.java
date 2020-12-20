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
	
	private Socket socket;
	private static List<Document> lesDocuments = new ArrayList<Document>();
	

	public ServiceRetour(Socket socket) {
		this.socket = socket;
	}
	
	public static void setLesListes(List<Abonne> lesAbonnes, List<Document> doc) {
		ServiceRetour.lesDocuments = doc;
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
					
			int idDocument = Integer.parseInt(in.readLine());
			Document doc = getDocument(idDocument);
			
			System.out.print("ok " + doc);
			out.println(doc.numero());

			doc.retour();
			
		} catch (IOException e) {
			
		}
	}
}
