import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.color.*;

public class HoughTransform {
	int[][] sobelArray;
	
	public HoughTransform(int[][] sobelArray){
		this.sobelArray = sobelArray;
	}
	
	public Circle detectCircle(){
		Circle c = new Circle();
		ArrayList<HoughThread> threads = new ArrayList<HoughThread>();
		
		threads.add(new HoughThread(100,500,c,sobelArray));
		//threads.add(new HoughThread(60,120,c,sobelArray));
		//threads.add(new HoughThread(120,180,c,sobelArray));
		//threads.add(new HoughThread(180,240,c,sobelArray));
		//threads.add(new HoughThread(240,300,c,sobelArray));
		//threads.add(new HoughThread(300,360,c,sobelArray));
		//threads.add(new HoughThread(360,420,c,sobelArray));
		//threads.add(new HoughThread(420,480,c,sobelArray));

		for (HoughThread t : threads){
			t.run();
		}
		try {
			for (HoughThread t: threads){
				t.join();
			}
			//threads.get(threads.size()-1).join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("X: "+ c.getCentreX());
		System.out.println("Y: "+c.getCentreY());
		System.out.println("D: "+c.getDiameter());
		return c;
	}
}
