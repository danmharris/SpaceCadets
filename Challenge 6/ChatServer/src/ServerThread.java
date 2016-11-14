import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ServerThread extends Thread{
	Server server;
	
	public ServerThread(Server s){
		this.server = s;
	}
	
	
	public void run(){
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		while (true){ //Will constantly accept command line input and send to all clients
			try {
				String input = in.readLine();
				if (input.equals("/close")){
					this.server.stopExec();
					break;
				} else {
					System.out.println("Server: " + input);
					server.sendToAll("Server: "+input);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
}
	