import java.util.ArrayList;
import java.util.HashMap;

public class AlterVariable {
	protected Variable variable; //Variable that is being altered
	protected String log; //Log data
	public AlterVariable(ArrayList<String> command, HashMap<String,Variable> variables){
		if (!command.get(1).matches("^[a-z,A-Z]*$")){ //Checks that syntax of statement is correct
			this.log+="Invalid Argument!";
		} else {
			String operation = command.get(0); //Obtains the operation to run on the variable and the variable to run it on
			String name = command.get(1);
			this.variable = variables.get(name);
			
			if (this.variable == null && operation.equals("clear")){ //If the variable does not exist and keyword is clear, a new variable is made
				this.variable = new Variable(name);
				variables.put(name, this.variable);
			} else if(this.variable == null){ //If other option attempted an error is presented
				log+="Variable not defined\n";
			} else {
				switch (operation){ //If the variable exists the operation is performed on it
				case "incr":
					variable.incVariable();
					break;
				case "decr":
					variable.decVariable();
					break;
				case "clear":
					variable.clearVariable();
					break;
				}
			}
		}
	}
	
	public Variable getVariable(){
		return this.variable;
	}
	
	public String getLog(){
		return this.log;
	}
}
