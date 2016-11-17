import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public class ImageHandler {
	protected BufferedImage image;
	protected BufferedImage gray;
	
	public ImageHandler(File file){
		try {
			BufferedImage tmp = ImageIO.read(file);
			tmp.getScaledInstance(500, 500, Image.SCALE_SMOOTH);
			
			this.image = new BufferedImage(500,500,BufferedImage.TYPE_INT_ARGB);
			this.image.getGraphics().drawImage(tmp,0,0,500,500,null);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		}
	
	public BufferedImage getImage(){
		return this.image;
	}
	
	public BufferedImage getGrayscaleImage(){
		return this.gray;
	}
	
	public void createGrayscale(){
		gray = new BufferedImage(500,500,BufferedImage.TYPE_BYTE_GRAY);
		
		gray.getGraphics().drawImage(this.image, 0,0,null);
	}

}
