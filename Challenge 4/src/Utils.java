import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

public class Utils {	
	public static HashMap<Integer,Integer> findBlocks(ArrayList<String> code){
		HashMap<Integer,Integer> blocks = new HashMap<Integer,Integer>();
		Stack<Integer> stack = new Stack<Integer>(); //Uses a stack to find the last while loop encountered
		for (int i = 0; i < code.size(); i++){
			String line = code.get(i).trim();
			line = line.replace("\\t", "");
			if (line.matches("^while.*")){ 
				blocks.put(i, -1); //It adds the start line of the while and adds to stack
				stack.push(i);
			} else if (line.matches("^end.*")){ //When end encountered it updates the loop at the top of the stack
				blocks.put(stack.pop(), i);
			}
		}
		return blocks;
	}
}
