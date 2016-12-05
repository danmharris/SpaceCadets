
public class Character {
	private String name;
	private int health;
	private boolean isAlive;
	
	public Character(String name, int health){
		this.name = name;
		this.health = health;
		this.isAlive = true;
	}
	
	public String getName(){
		return this.name;
	}
	
	public void decreaseHealth(int value){
		this.health-= value;
		if (this.health <= 0){
			this.health = 0;
			kill();
		}
	}
	
	public void increaseHealth(int value){
		this.health+= value;
	}
	
	public int getHealth(){
		return this.health;
	}
	
	public void kill(){
		this.isAlive = false;
	}
	
	public boolean isAlive(){
		return this.isAlive;
	}
}
