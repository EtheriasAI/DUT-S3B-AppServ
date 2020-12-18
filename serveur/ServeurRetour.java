package serveur;

import java.io.IOException;
import java.net.ServerSocket;

public class ServeurRetour implements Runnable{
	private ServerSocket listen_socket;
	
	public ServeurRetour(int portretour) throws IOException {
		listen_socket = new ServerSocket(portretour);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
}
