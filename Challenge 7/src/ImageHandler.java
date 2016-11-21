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
	
	public void doSobelDetection(){
		sobel = new BufferedImage(500,500,BufferedImage.TYPE_BYTE_GRAY);
		
		
		
		for (int y = 1; y < gray.getHeight()-1; y++){
			for (int x = 1; x < gray.getWidth()-1; x++){
				int[][] kernel = {
						{gray.getRGB(x-1, y-1), gray.getRGB(x,y-1), gray.getRGB(x+1, y-1)},
						{gray.getRGB(x-1, y), gray.getRGB(x,y-1), gray.getRGB(x+1, y)},
						{gray.getRGB(x-1, y+1), gray.getRGB(x,y+1), gray.getRGB(x+1, y+1)}
				};
				sobel.setRGB(x, y, this.doConvolution(kernel));		
			}
		}
		
	}
	
	private int doConvolution(int[][] kernel){
		int[][] gX = {
				{-1,0,1},
				{-2,0,2},
				{-1,0,1}
		};
		
		int[][] gY = {
				{1,2,1},
				{0,0,0},
				{-1,-2,-1}	
		};
		
		int gx = 0;
		int gy = 0;
		for (int y = 0; y < 3; y++){
			for (int x = 0; x < 3; x++){
				gx+= gX[x][y] * kernel[x][y];
				gy+= gY[x][y] * kernel[x][y];
			}
		}
		
		double g = Math.sqrt(gx^2 + gy^2);
		return (int)Math.round(g)*10;
	}
	
	public BufferedImage getSobel(){
		return this.sobel;
	}

}
