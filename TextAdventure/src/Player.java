import java.util.ArrayList;
import java.util.Iterator;

public class Player extends Character{
	private Room currentRoom;
	private Weapon weapon;
	private ArrayList<Item> items;
	
	public Player(String name){
		super(name, 100);
		this.items = new ArrayList<Item>();
		this.weapon = new Weapon("Dagger",-1, 10);
	}
	
	public void setCurrentRoom(Room currentRoom){
		this.currentRoom = currentRoom;
	}
	
	public Room getCurrentRoom(){
		return this.currentRoom;
	}
	
	public void moveInDirection(Direction direction){
		this.currentRoom = this.currentRoom.getRoomAtLink(direction);
	}
	
	public void use(String item){
		Iterator<Item> it = this.items.iterator();
		while (it.hasNext()){
			Item current = it.next();
			if (current.getName().equalsIgnoreCase(item)){
				current.use(this);
			}
		}
	}
	
	public void attack(){
		Enemy e = this.currentRoom.getEnemy();
		if (e == null){
			System.out.println("There is nothing to attack");
		} else {
			this.weapon.use(e);
			e.attackPlayer(this);
		}
	}
	
	public void printInventory(){
		System.out.println("You have the following items:");
		for (Item i : this.items){
			System.out.println(i.getName() + "\n");
		}
	}
	
	public void pickupItem(String item){
		Item i = this.currentRoom.takeItem(item);
		if (i!=null){
			System.out.println("You take "+i.getName());
			this.items.add(i);
		} else {
			System.out.println("There is nothing to take");
		}
	}
}
