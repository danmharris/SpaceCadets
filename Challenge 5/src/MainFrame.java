import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class MainFrame extends JFrame implements ActionListener{
	protected JTextArea textArea;
	protected JTextArea console;
	
	public MainFrame(){
		//Sets basic parameters for window
		this.setSize(500,500);
		this.setTitle("Barebones Interpreter");
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		//Sets up text area for code input
		textArea = new JTextArea();
		JScrollPane scroll = new JScrollPane(textArea);
		scroll.setPreferredSize(new Dimension(this.getWidth()-50,200));
		this.add(scroll);
		
		//Sets up text area for console output
		console = new JTextArea();
		console.setEditable(false);
		JScrollPane consoleScroll = new JScrollPane(console);
		consoleScroll.setPreferredSize(new Dimension(this.getWidth()-50, 150));
		this.add(consoleScroll);
		
		//Sets up buttons and their commands
		JButton open = new JButton("Open File");
		open.setActionCommand("openFile");
		open.addActionListener(this);
		this.add(open);
		
		JButton run = new JButton("Run");
		run.setActionCommand("runCode");
		run.addActionListener(this);
		this.add(run);		
		
		this.setLayout(new FlowLayout());
		this.setVisible(true);
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()){
		case "runCode": //This extracts the code and goes through each line running the command
			console.setText("");
			ArrayList<String> code = new ArrayList<String>(Arrays.asList(textArea.getText().split("[\\n]")));
			Interpreter i = new Interpreter(code);
			i.parse();
			console.append(i.getLog());
			i.execute();
			
			System.out.println("Done!");
			
			Iterator<String> it = i.getVariableNameIterator();
			while (it.hasNext()){
				String s = it.next();
				console.append(s + ": " + i.getVariable(s).getValue() + "\n");
			}
			break;
		case "openFile": //Opens a file dialog that reads a text file and places its contents in the code editor
			JFileChooser fc = new JFileChooser();
			int returnVal = fc.showOpenDialog(this);
			if (returnVal == JFileChooser.APPROVE_OPTION){
				Path file = Paths.get(fc.getSelectedFile().getPath());
				try {
					List<String> text = Files.readAllLines(file);
					Iterator<String> fileIt = text.iterator();
					textArea.setText("");
					while (fileIt.hasNext()){
						textArea.append(fileIt.next()+"\n");
					}
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
			}
			break;	
		}
		
	}
}
