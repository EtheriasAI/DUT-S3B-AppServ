package serveur;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import application.Abonne;


public class ServiceEmprunt implements Runnable {
	private static List<Abonne> lesAbonnes = new ArrayList<Abonne>();
	
	public static void setLesAbonnes(List<Abonne> lesAbonnes) {
		ServiceEmprunt.lesAbonnes = lesAbonnes;
	}

	private static Abonne getAbonne(int id) {
		for (Abonne ab : lesAbonnes)
			if (ab.getNumero() == id)
				return ab;
		return null;
	}
	
	private final Socket client;

	ServiceEmprunt(Socket socket) {
		this.client = socket;
	}
	
	
	@Override
	public void run() {
		String reponse = null;
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
			PrintWriter out = new PrintWriter(client.getOutputStream(), true);

			out.println("Tapez le numéro du DVD ");
			int noDVD = Integer.parseInt(in.readLine());
			out.println("Tapez votre numero ");
			int noAbonne = Integer.parseInt(in.readLine());
			
			System.out.println("Requète de " + this.client.getInetAddress() + " DVD n°" + noDVD + " Abonne n° " + noAbonne);
			Abonne abonne = getAbonne(noAbonne);
			if(getAge() == true) {
				
			}
		}
		catch (IOException e) {
			// Fin du service d'inversion
		}
	}

	private boolean getAge() {
		// TODO Auto-generated method stub
		return true;
	}
	
}
