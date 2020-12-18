package serveur;

import java.io.IOException;
import java.net.ServerSocket;

public class ServeurReservation implements Runnable{

	private ServerSocket listen_socket;
	
	public ServeurReservation(int portreservation) throws IOException {
		listen_socket = new ServerSocket(portreservation);
	}
	

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
	
}
