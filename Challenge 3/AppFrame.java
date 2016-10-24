import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class AppFrame extends JFrame implements ActionListener{
	protected CanvasPane canvas;
	
	public AppFrame(){
		this.setTitle("Spirograph");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setBackground(Color.WHITE);
		
		canvas = new CanvasPane();
		
		JMenuBar menuBar = new JMenuBar();
		JMenu file = new JMenu("File");
		JMenuItem newGraph = new JMenuItem("New Graph");
		JMenuItem clearCanvas = new JMenuItem("Clear Canvas");
		
		JMenu help = new JMenu("Help");
		JMenuItem about = new JMenuItem("About");
		
		newGraph.setActionCommand("newGraph");
		clearCanvas.setActionCommand("clearCanvas");
		about.setActionCommand("about");
		
		newGraph.addActionListener(this);
		clearCanvas.addActionListener(this);
		about.addActionListener(this);
		
		file.add(newGraph);
		file.add(clearCanvas);
		help.add(about);
		menuBar.add(file);
		menuBar.add(help);
		this.setJMenuBar(menuBar);
				
		this.setSize(500,500);
		this.add(canvas);
		this.setVisible(true);
		this.setResizable(false);
	
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		switch (e.getActionCommand()){
		case "newGraph":
			new OptionsFrame(canvas);
			break;
		case "clearCanvas":
			canvas.clear();
			break;
		case "about":
			JOptionPane.showMessageDialog(null, "Draws Spirographs!\n Made by Dan Harris");
			break;
		}
	}
}
