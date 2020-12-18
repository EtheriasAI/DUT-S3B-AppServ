package serveur;

import java.io.IOException;
import java.net.ServerSocket;

public class ServeurEmprunt implements Runnable {
	private ServerSocket listen_socket;
	
	public ServeurEmprunt(int portemprunter) throws IOException {
		listen_socket = new ServerSocket(portemprunter);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

}
