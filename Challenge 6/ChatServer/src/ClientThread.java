import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientThread extends Thread{
	Socket socket;
	PrintWriter out = null;
	BufferedReader in = null;
	Server server;
	String name;

	public ClientThread(Socket socket, Server server){
		//When new socket is created log is output
		System.out.println("Accepting connection from " + socket.getRemoteSocketAddress());
		this.server = server;
		try {
			out = new PrintWriter(socket.getOutputStream(),true);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//sends this message to the newly connected client
		out.println("Welcome to the Chat Server!");
	}
	
	public void sendToClient(String text){
		//Sends text to client over buffer
		out.println(text);
	}
	
	public String getClientName(){
		return this.name;
	}

	@Override
	public void run() {
		try {
			String name = "User";
			String input;
			while ((input = in.readLine()) != null){
				if (input.matches("^\\$.*$")){
					//If the text received is $USER the user variable is set
					if (input.split(" ")[0].equals("$USER")){
						name = input.split(" ")[1];
						System.out.println("USER set to " + name);
					} else if (input.split(" ")[0].equals("$INFO")){
						String info = "Info: " + input.replaceAll("\\$INFO", "");
						System.out.println(info);
						server.sendToAll(info);
					} //Whereas if starts with $INFO an information message is sent

				} else {
					System.out.println(name + ": " + input);
					server.sendToAll(name + ": " + input);
				}
			}
		} catch (Exception e){

		}
	}

}
