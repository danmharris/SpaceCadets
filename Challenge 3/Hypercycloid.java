import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Hypercycloid {
	protected double R;
	protected double r;
	protected double O;
	protected Color colour;
	
	public Hypercycloid(double initR, double initr, double initO, Color c){
		this.R = initR;
		this.r = initr;
		this.O = initO;
		this.colour = c;
	}
	
	public void paint(Graphics g, JPanel panel){
		//Sets initial position to that of when t = 0
		int lastX = - (int)R - (int)O+250; 
		int lastY = (int)R + (int)r + 250;
		for (double t = 0; t < 100; t+= 0.1){
			g.setColor(this.colour);
			int x = (int) Math.round((R+r)*Math.sin(t) - (r+O)*Math.cos(((R+r)/r)*t))+250; //Calculates parametric equations for co-ordinates
			int y = (int) Math.round((R+r)*Math.cos(t) - (r+O)*Math.sin(((R+r)/r)*t))+250;	
			g.drawLine(lastX, lastY, x, y);
			lastX = x;
			lastY = y;
		}
	}
	
	public double getR(){
		return this.R;
	}
	
	public double getr(){
		return this.r;
	}
	
	public double getO(){
		return this.O;
	}
}
