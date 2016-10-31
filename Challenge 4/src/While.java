import java.util.ArrayList;
import java.util.HashMap;

public class While {
	protected Variable condition;
	protected int value;
	protected int startLine;
	protected int endLine;
	protected String log;
	
	public While(ArrayList<String> command, HashMap<String,Variable> variables){
		//Checks syntax of loop
		if (!command.get(1).matches("^[a-z,A-Z]*$") || !command.get(2).matches("^not$") || !command.get(3).matches("^\\w*$")){
			log+= "Invalid Argument";
		}
		this.condition = variables.get(command.get(1));
		
		//If the condition value is a number, it is set directly
		if (command.get(3).matches("^[0-9]*$")){
		this.value = Integer.parseInt(command.get(3));
		} else if (command.get(3).matches("^[a-z,A-Z]*")){ //If text is used, the condition value is set based on the value of that variable
			Variable variable = variables.get(command.get(3));
			if (variable == null){
				log+="Variable does not exist for condition";
			} else {
				this.value = variable.getValue();
			}
			
		}
	}
	
	public boolean conditionMet(){ //Checks if condition has been met
		if (this.condition.getValue() == this.value){
			return true;
		} else {
			return false;
		}
	}
	
	public void setBlock(int start, int end){ //Sets lines of start and end
		this.startLine = start;
		this.endLine = end;
	}
	
	public int getStart(){
		return this.startLine;
	}
	
	public int getEnd(){
		return this.endLine;
	}	
	
	public String getLog(){
		return this.log;
	}
}
