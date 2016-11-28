import java.awt.image.BufferedImage;

public class HoughThread extends Thread implements Runnable{
	int minD;
	int maxD;
	int[][] sobelArray;
	Circle c;
	
	public HoughThread(int minD, int maxD, Circle c, int[][] sobelArray){
		this.minD = minD;
		this.maxD = maxD;
		this.c = c;
		this.sobelArray = sobelArray;
	}
	
	
	@Override
	public void run() {
		BufferedImage circles = new BufferedImage(500,500,BufferedImage.TYPE_BYTE_GRAY);
		int[][] counts = new int[500][500];
		
		int largestVal = 0;
		int largestX = 0;
		int largestY = 0;
		int largestD = 0;
		for (int d = minD; d < maxD ; d= d+10){
			counts = new int[500][500];
			for (int y = 0; y < this.sobelArray[0].length; y++){
				for (int x = 0; x < this.sobelArray.length; x++){
					if (sobelArray[x][y] > 3000){
						/*circles.getGraphics().clearRect(0, 0, 500, 500);
						circles.getGraphics().drawOval(x-(d/2), y-(d/2), d, d);
						for (int circleY = 0 ; circleY < 500; circleY++){
							for (int circleX = 0; circleX < 500; circleX++){
								if(circles.getRGB(circleX, circleY) == -1){
									counts[circleX][circleY]++;
									
									if(counts[circleX][circleY] > largestVal){
										largestVal = counts[circleX][circleY];
										largestX = circleX;
										largestY = circleY;
										largestD = d;
									}
								}
							}
						}*/
						for (double t = 0; t < 2*Math.PI; t=t+(Math.PI)/100){
							int circleX = (int)(Math.round((d/2)*Math.sin(t)))+x;
							int circleY = (int)(Math.round((d/2)*Math.cos(t)))+y;
							
							if (circleX >= 0 && circleX < 500 && circleY >=0 && circleY <500){
								counts[circleX][circleY]++;
								if(counts[circleX][circleY] > largestVal){
									largestVal = counts[circleX][circleY];
									largestX = circleX;
									largestY = circleY;
									largestD = d;
								}
							}
						}
					}
				}
			}		
		}
		c.setCentreX(largestX);
		c.setCentreY(largestY);
		c.setDiameter(largestD);
		
		/*for (int y = 0 ; y<500;y++){
			for (int x = 0; x <500; x++){
				System.out.println(x+","+y+","+counts[x][y]);
			}
		}*/
	}

	
}
