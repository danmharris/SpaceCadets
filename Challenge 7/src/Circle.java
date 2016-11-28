
public class Circle {
	int centreX;
	int centreY;
	int diameter;
	
	public Circle(){
		
	}
	
	public Circle(int centreX,int centreY,int diameter){
		this.centreX = centreX;
		this.centreY =centreY;
		this.diameter = diameter;
	}
	
	public void setCentreX(int centreX){
		this.centreX = centreX;
	}
	
	public void setCentreY(int centreY){
		this.centreY = centreY;
	}
	
	public void setDiameter(int diameter){
		this.diameter = diameter;
	}
	
	public int getCentreX(){
		return this.centreX;
	}
	
	public int getCentreY(){
		return this.centreY;
	}
	
	public int getDiameter(){
		return this.diameter;
	}
	
	public int getTopLeftX(){
		return this.centreX - this.diameter/2;
	}
	
	public int getTopLeftY(){
		return this.centreY - this.diameter/2;
	}
}
