import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Paths;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainFrame extends JFrame implements ActionListener{
	JPanel panel;
	
	public MainFrame(){
		this.setSize(600,600);
		this.setTitle("Circle Detector");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		
		panel = new JPanel();
		panel.setPreferredSize(new Dimension(500,500));
		
		JButton load = new JButton("Load Image");
		load.setActionCommand("loadImage");
		load.addActionListener(this);
		
		this.setLayout(new FlowLayout());
		this.add(panel);
		this.add(load);
		
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()){
		case "loadImage":
			JFileChooser fc = new JFileChooser();
			fc.showOpenDialog(this);
			File file = new File(fc.getSelectedFile().getPath());
			ImageHandler ih = new ImageHandler(file);
			ih.createGrayscale();
			ih.createSobel();
			ih.createFinal();
			//BufferedImage temp = new BufferedImage(500,500,BufferedImage.TYPE_BYTE_GRAY);
			//temp.getGraphics().drawOval(0, 0, 300, 300);
			panel.getGraphics().drawImage(ih.getFinal(),0,0,500,500,null);
			//temp.getGraphics().fillRect(0, 0, 500, 500);
			//panel.getGraphics().drawImage(temp,0,0,500,500,null);
			//System.out.println(temp.getRGB(300,300));
			
			break;
		}
		
	}
}
