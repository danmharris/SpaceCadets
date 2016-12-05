import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Room {
	private String name;
	private String description;
	private ArrayList<Item> itemsPresent;
	private Map<Direction,Room> links;
	private Enemy enemy;
	
	public Room(String name, String description){
		this.name= name;
		this.description = description;
		this.itemsPresent = new ArrayList<Item>();
		this.links = new HashMap<Direction,Room>();
		this.enemy = null;
	}
	
	public void setEnemy(Enemy e){
		this.enemy = e;
	}
	
	public Enemy getEnemy(){
		if (this.enemy.isAlive()){
			return this.enemy;
		} else {
			return null;
		}
	}
	
	public String getName(){
		return this.name;
	}
	
	public String getDescription(){
		String output;
		output= this.description + "\n";
		
		output+="There are the following items: \n";
		for (Item i : this.itemsPresent){
			output+=i.getName()+"\n";
		}
		
		output+= "You have the following options: \n";
		Iterator<Direction> it= this.links.keySet().iterator();
		while (it.hasNext()){
			Direction direction = it.next();
			output+="* To the " + direction.toString() + " lies " + this.links.get(direction).getName() + "\n";
		}
		
		
		if (this.enemy!= null){
			if (this.enemy.isAlive()){
				output+="There is an enemy!\n";
			}
		}
		return output;		
	}
	
	public void addBidirectionalLink(Direction direction, Room room){
		this.links.put(direction, room);
		room.addLink(calculateOpposite(direction), this);
	}
	
	public void addLink(Direction direction, Room room){
		this.links.put(direction, room);
	}
	
	public Room getRoomAtLink(Direction direction){
		if (this.links.containsKey(direction)){
		return this.links.get(direction);
		} else {
			return this;
		}
	}
	
	public Direction calculateOpposite(Direction direction){
		switch(direction){
		case NORTH:
			return Direction.SOUTH;
		case SOUTH:
			return Direction.NORTH;
		case EAST:
			return Direction.WEST;
		case WEST:
			return Direction.EAST;
		default:
			return null;
		}
	}
	
	public void addItem(Item i){
		this.itemsPresent.add(i);
	}
	
	public Item takeItem(String item){
		Iterator<Item> it = this.itemsPresent.iterator();
		while (it.hasNext()){
			Item current = it.next();
			if (current.getName().equalsIgnoreCase(item)){
				it.remove();
				return current;
			}
		}
		return null;
	}
}
