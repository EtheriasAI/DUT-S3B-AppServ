package serveur;

import java.net.ServerSocket;
import java.io.IOException;

public class Serveur implements Runnable {

	 private ServerSocket listen_socket;
	    
	 Serveur(int port) throws IOException {
	    listen_socket = new ServerSocket(port);
	 }
	 
	@Override
	public void run() {
        try {
            System.err.println("Lancement du serveur au port "+this.listen_socket.getLocalPort());
            while(true)
                new Thread(new ServiceInversion(listen_socket.accept())).start();
        }
        catch (IOException e) { 
            try {this.listen_socket.close();} catch (IOException e1) {}
            System.err.println("Arrêt du serveur au port "+this.listen_socket.getLocalPort());
        }
    }
	
	protected void finalize() throws Throwable {
        try {this.listen_socket.close();} catch (IOException e1) {}
    }
	

}
