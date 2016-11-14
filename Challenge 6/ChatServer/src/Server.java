import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;

public class Server{
	ArrayList<ClientThread> clients;
	int port;
	boolean exec;
	
	public Server(int port){ //Sets up parameters for the server
		this.port = port;
		this.clients = new ArrayList<ClientThread>();
		this.exec = true;
	}
	
	public void sendToAll(String text){
		Iterator<ClientThread> it = this.clients.iterator();
		while (it.hasNext()){ //Goes through each connected client and sends message to them
			it.next().sendToClient(text);
		}
	}
	
	public void stopExec(){
		this.exec = false;
	}

	public void run(){
		try {
			ServerSocket serverSocket = new ServerSocket(port);
			new ServerThread(this).start(); //Creates a thread to get console input
			while (exec){ //Will constantly accept new socket connections
				ClientThread t = new ClientThread(serverSocket.accept(),this);
				this.clients.add(t);
				t.start();
			}
			serverSocket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
