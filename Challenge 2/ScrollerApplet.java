import java.applet.Applet;
import java.awt.Button;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class ScrollerApplet extends Applet implements Runnable{
	int posX, posY; //Position variables
	int step, delay; //Movement speed variables
	Thread t; //Thread for running animation
	boolean running; //Whether the text is moving0
	String dir; //Direction of movement
	TextField userString; //What user wants to be displayed
	
	public void init(){
		
		//Movement variables
		posX = -50;
		posY = 100;
		dir = "right";
		step = 5;
		delay = 200;
				
		this.setSize(500,250);
		this.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent e) {
				//Changes position of text to mouse position
				posY = e.getY();
				posX = e.getX();
			}

			@Override
			public void mouseEntered(MouseEvent e) {}

			@Override
			public void mouseExited(MouseEvent e) {}

			@Override
			public void mousePressed(MouseEvent e) {}

			@Override
			public void mouseReleased(MouseEvent e) {}
			
		});
				
		t = new Thread(this);
		Button start = new Button("Start");
		Button stop = new Button("Stop");
		Button dirChange = new Button("Direction");
		userString = new TextField("Hello World");
		start.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				running = true; //allows position to change
			}
			
		});
		
		stop.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				running = false; //prevents position being changed
			}
			
		});
		
		dirChange.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {				
				if (dir.equals("right") || dir.equals("left")){ //Changes the direction
					dir = "down";
				} else {
					dir = "right";
				}
			}
			
		});
		
		this.add(start);
		this.add(stop);
		this.add(dirChange);
		this.add(userString);
		
		running = true;
		t.start();
	}
	
	public void paint(Graphics g){
		int pos = 0;
		
		//Obtains common position based on direction so colour logic can be applied to that direction
		if (dir.equals("left") || dir.equals("right")){
			pos = posX;
		} else if (dir.equals("up") || dir.equals("down")){
			pos = posY;
		}
		switch (pos / 100){ //Depending on position (either X or Y as shown above) the colour will change
		case (0):
			g.setColor(Color.BLUE);		
			break;
		case (1):
			g.setColor(Color.RED);		
			break;
		case (2):
			g.setColor(Color.GREEN);		
			break;
		case (3):
			g.setColor(Color.CYAN);		
			break;
		case (4):
			g.setColor(Color.ORANGE);		
			break;
		default:
			g.setColor(Color.BLACK);
			break;
		}
		g.drawString(userString.getText(), posX, posY);
		this.showStatus("PosX: " + posX + "; PosY: " + posY + "; Direction: " + dir + "; Step: " + step + "; Delay: " + delay); //Allows variables to be tracked
		
	}
	
	public void run(){
		while(true){
			//Only runs if value is true (start button pressed)
			if (running){
				
				//Control logic for bouncing
				if (dir.equals("right") && posX > this.getWidth()){
					dir = "left";
				} else if (dir.equals("left") && posX < 0){
					dir = "right";
				} else if (dir.equals("down") && posY > this.getHeight()){
					dir = "up";
				} else if (dir.equals("up") && posY < 0){
					dir = "down";
				}
				
				//Determines which direction to move text
				if (dir.equals("right")){
					posX = posX + step;
				} else if (dir.equals("left")){
					posX = posX - step;
				} else if (dir.equals("down")){
					posY = posY + step;
				} else if (dir.equals("up")){
					posY = posY - step;
				}
				
				repaint(); //redraws entire applet
			}
			try{
				Thread.sleep(delay);
			} catch (Exception e){
				
			}
		}
	}

}
