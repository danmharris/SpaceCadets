import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Client {
	BufferedReader in;
	PrintWriter out;
	Socket socket;
	String name;
	
	public Client(String host, int port, JTextArea output) throws IOException{
		socket = new Socket(host,port); //Sets up new connection to server
		
		try {
			out = new PrintWriter(socket.getOutputStream(),true);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		ReaderThread t = new ReaderThread(in); //Initialises the looping thread
		t.setOutput(output);
		t.start();
	}
	
	public void setName(String name){ //Sets the name for the client
		this.name = name;
		out.println("$USER " + name);
		out.println("$INFO User " + name + " connected.");
	}
	
	public String getName(){
		return this.name;
	}
	
	public void sendText(String text){
		out.println(text);
	}
	
	public void closeSocket(){
		try {
			this.socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
