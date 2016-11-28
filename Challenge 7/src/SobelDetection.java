import java.awt.Color;
import java.awt.image.BufferedImage;

public class SobelDetection {
	BufferedImage image;
	int[][] sobelArray;
	
	public SobelDetection(BufferedImage image){
		this.image = image;
		this.sobelArray = new int[500][500];
	}
	
	public int[][] getKernel(int x, int y){
		int[][] kernel = {
				{image.getRGB(x-1, y-1), image.getRGB(x,y-1), image.getRGB(x+1, y-1)},
				{image.getRGB(x-1, y), image.getRGB(x,y-1), image.getRGB(x+1, y)},
				{image.getRGB(x-1, y+1), image.getRGB(x,y+1), image.getRGB(x+1, y+1)}
		};
		return kernel;
	}
	
	public int doConvolution(int[][] kernel){
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
		return (int)g;
	}
	
	public void doDetection(){
		for (int y = 1; y < image.getHeight()-1; y++){
			for (int x = 1; x < image.getWidth()-1; x++){
				this.sobelArray[x][y] = this.doConvolution(this.getKernel(x, y));
			}
		}
	}
	
	public int[][] getSobelArray(){
		return this.sobelArray;
	}
}
