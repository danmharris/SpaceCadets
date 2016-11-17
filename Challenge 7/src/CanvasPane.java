import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class CanvasPane extends JPanel{	
	BufferedImage image;
	
	/*public CanvasPane(ImageHandler ih){
		this.ih = ih;
	}*/
	
	public void setImage(BufferedImage i){
		this.image = i;
	}
	
	public void paint(Graphics g){
		g.drawImage(image, 500, 500, null);
	}
}
