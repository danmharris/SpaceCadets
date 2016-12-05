import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Game {
	private Player player;
	private Map<Integer, Room> world;
	
	public static void main(String[] args){
		Game game = new Game();
		game.play();
	}
	
	public Game(){
		this.world = new HashMap<Integer, Room>();
		
		
		//SET UP INITIAL ROOMS
		this.world.put(0, new Room("Forest","You are in a dark and creepy forest"));
		this.world.get(0).addItem(new Potion());
		this.world.put(1, new Room("River Crossing","You now stand at a wooden footbridge"));
		this.world.get(1).setEnemy(new Enemy("Skeleton", 2, 10));
		this.world.get(0).addBidirectionalLink(Direction.SOUTH, this.world.get(1));
		
	}
	
	public void play(){
		Scanner sc = new Scanner(System.in);
		
		
		System.out.println("Welcome to the Text Adventure Game!");
		System.out.println("===================================");
		System.out.println();
		
		System.out.println("What is your name?: ");
		String name = sc.nextLine();
		this.player = new Player(name);
		this.player.setCurrentRoom(this.world.get(0));
		
		do {
			System.out.println();
			System.out.println(this.player.getCurrentRoom().getDescription());
			System.out.println("Your Health: "+ this.player.getHealth());
			String input;
			System.out.print("Enter a command: ");
			input = sc.nextLine();
			ArrayList<String> words = new ArrayList(Arrays.asList(input.split(" ")));
			String command = words.get(0);
			words.remove(0);
			if (command.equals("quit")){
				break;
			} else if (command.equals("move")){
				this.player.moveInDirection(Direction.valueOf(words.get(0).toUpperCase()));
			} else if (command.equals("attack")){
				this.player.attack();
			} else if (command.equals("use")){
				this.player.use(words.get(0));
			} else if (command.equals("i")){
				this.player.printInventory();
			} else if (command.equals("pickup")){
				this.player.pickupItem(words.get(0));
			}
		} while(true);
		
	}
}
