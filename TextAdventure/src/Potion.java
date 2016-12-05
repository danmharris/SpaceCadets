
public class Potion extends Item{

	public Potion() {
		super("Potion");
	}

	@Override
	public void use(Character c) {
		c.increaseHealth(20);
	}
	
}
