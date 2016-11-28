import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public class ImageHandler {
	protected BufferedImage image;
	protected BufferedImage gray;
	protected BufferedImage sobel;
	protected BufferedImage finalImage;
	int[][] sobelArray;
	
	public ImageHandler(File file){
		try {
			BufferedImage tmp = ImageIO.read(file);
			tmp.getScaledInstance(500, 500, Image.SCALE_SMOOTH);
			
			this.image = new BufferedImage(500,500,BufferedImage.TYPE_INT_RGB);
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
	
	public void createSobel(){
		sobel = new BufferedImage(500,500,BufferedImage.TYPE_BYTE_GRAY);
		SobelDetection detect = new SobelDetection(gray);
		detect.doDetection();
		sobelArray = detect.getSobelArray();
		
		
		for (int y = 1; y < gray.getHeight()-1; y++){
			for (int x = 1; x < gray.getWidth()-1; x++){
				sobel.setRGB(x, y, Color.HSBtoRGB(0, 0, sobelArray[x][y]/1000));		
			}
		}
		
	}
	
	
	public BufferedImage getSobel(){
		return this.sobel;
	}
	
	public void createFinal(){
		this.finalImage = this.image;
		HoughTransform hough = new HoughTransform(sobelArray);
		Circle c = hough.detectCircle();
		Graphics g = finalImage.getGraphics();
		g.setColor(Color.CYAN);
		g.drawOval(c.getTopLeftX(), c.getTopLeftY(), c.getDiameter(), c.getDiameter());		
	}
	
	public BufferedImage getFinal(){
		return this.finalImage;
	}

}
