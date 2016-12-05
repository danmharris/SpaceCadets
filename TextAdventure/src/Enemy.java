
public class Enemy extends Character{
	private Weapon w;
	
	public Enemy(String name, int damage, int health){
		super(name, health);
		this.w = new Weapon("Bite",-1, damage);
	}
	
	public void attackPlayer(Player p){
		this.w.use(p);
	}
}
