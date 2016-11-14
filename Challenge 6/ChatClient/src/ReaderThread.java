import java.io.BufferedReader;
import java.io.IOException;

import javax.swing.JTextArea;

public class ReaderThread extends Thread{
	BufferedReader in;
	JTextArea output;
	
	public ReaderThread(BufferedReader in){
		this.in = in;
	}
	
	//Sets the object to be used for output (in this case JTextArea)
	public void setOutput(JTextArea area){
		this.output = area;
	}
	
	public void run(){
		//Will infinitely append the chat with anything received through the socket
		String input;
		try {
			while (true){
				input = in.readLine();
				if (!input.equals("")){
					this.output.append(input+"\n");
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Exiting");
		}
	}
	
}
