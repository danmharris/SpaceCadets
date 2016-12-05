
public class Weapon extends Item{
	private int durability;
	private int damage;
	
	public Weapon(String name,int durability, int damage){
		super(name);
		this.durability = durability;
		this.damage = damage;
	}
	
	public void use(Character c){
		int damageDealt = (int) Math.round(Math.random()*this.damage);
		c.decreaseHealth(damageDealt);
		System.out.println(this.getName() + " dealt " + damageDealt + " to " + c.getName() + ". New health: " + c.getHealth());
		
	}
}
