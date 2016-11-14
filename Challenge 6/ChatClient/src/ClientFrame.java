import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ClientFrame extends JFrame implements ActionListener{
	Client client;
	JTextField input;
	
	public ClientFrame(){
		//Obtains connection information from dialog boxes
		String address = JOptionPane.showInputDialog(null,"Enter IP Address of server:");
		int port = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter port of server (default 10241): "));
		
		//Standard JFrame initialisation
		this.setSize(500,500);
		this.setLayout(new FlowLayout());
		this.setResizable(false);
		this.setTitle("Chat Client");
		this.addWindowListener(new WindowListener(){

			@Override
			public void windowActivated(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowClosed(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowClosing(WindowEvent arg0) {
				//Overriden the X method so that it closes the socket safely and sends a disconnect message
				client.sendText("$INFO User " + client.getName() + " disconnected.");
				client.closeSocket();
				System.exit(0);
			}

			@Override
			public void windowDeactivated(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowDeiconified(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowIconified(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowOpened(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		
		//Sets up objects contained within the frame
		JTextArea chat = new JTextArea();
		chat.setEditable(false);
		chat.setPreferredSize(new Dimension(450, 350));
		input = new JTextField();
		input.setPreferredSize(new Dimension(450,50));
		input.requestFocus();
		input.addKeyListener(new KeyListener(){

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				if (e.getKeyCode() == KeyEvent.VK_ENTER){
					send(); //Sends the message if enter key pressed within input box
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyTyped(KeyEvent e) {
				
			}
			
		});
		JButton submit = new JButton("Submit");
		submit.addActionListener(this);
		submit.setActionCommand("submitClick");
		this.add(chat);
		this.add(input);
		this.add(submit);
		
		try {
			client = new Client(address,port, chat); //Attempts to connect, otherwise error displayed and client exits
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Could not connect to host server!");
			System.exit(0);
		}
		//If connection successful user is prompted for a name
		String name = JOptionPane.showInputDialog(null,"Enter a name to connect:");
		client.setName(name); 
		
		this.setVisible(true);
		
	}
	
	public void send(){
		//If the input contains text that does not start with a dollar send it through the socket
		if (!input.getText().equals("") && !input.getText().matches("^\\$.*$")){
			client.sendText(input.getText());
			input.setText("");
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getActionCommand().equals("submitClick")){
			send();
		}
	}
}
