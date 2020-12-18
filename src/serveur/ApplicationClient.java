package serveur;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.file.Files;

public class ApplicationClient implements Runnable{
	
	private final static int PORT_RESERVATION = 3000;
	private final static int PORT_EMPRUNT = 4000;
	private final static int PORT_RETOUR = 5000;
    private final static String HOST = "localhost"; 
            
	@Override
	public void run(){
		//Make a ServerSocket to listen for message
    	try {
			new Thread(new Serveur(PORT_RESERVATION)).start();
		
    	Socket socket =null;       
    	socket = new Socket(HOST, PORT_RESERVATION);
    	
        PrintWriter sout = new PrintWriter (socket.getOutputStream ( ), true);
        BufferedReader clavier = new BufferedReader(new InputStreamReader(System.in));  
        System.out.println("Connecté au serveur " + socket.getInetAddress() + ":"+ socket.getPort());
        
        String line;
        
        System.out.println("Tapez un texte à inverser");
        System.out.print("->");
        line = clavier.readLine();
        sout.println(line);
        System.out.println("Votre chaîne inversée\n" + line);
        socket.close();
        socket.close();
        try { if (socket != null) socket.close(); } 
    	catch (IOException e2) { ; } 
    	} catch (IOException e) {
		}
        
		
	}
}
