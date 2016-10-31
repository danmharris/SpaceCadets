import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Stack;

public class Command {	
	protected HashMap<String,Variable> variables; //Reference to variables HashMap
	protected String command; //String containing the command to run
	protected int lineNumber; //Line number currently being read
	protected HashMap<Integer,Integer> whileBlocks; //HashMap containing start and end lines of loops
	protected Stack<While> loopStack; //Stack of loops being run
	protected String log; //Log data

	public Command(HashMap<String,Variable> variables, String command,HashMap<Integer,Integer> whileBlocks, int lineNumber, Stack<While> loopStack){
		this.variables = variables; //Initialises Command attributes passed through in this constructor
		this.command = command;
		this.whileBlocks = whileBlocks;
		this.lineNumber = lineNumber;
		this.loopStack = loopStack;
		this.log = "";
	}

	public void run(){
		if (!command.matches("^.*;$")){ //Ensures that command ends with ;
			log+= "Line " + this.lineNumber + ": Invalid Syntax\n";
		} else {

			command = command.replaceAll("\\t", ""); //removes any tabs from the string
			command = command.trim(); //removes leading and trailing spacces
			ArrayList<String> wordList = new ArrayList<String>(Arrays.asList(command.split("[\\s;]"))); //creates an array of each word in command

			if (wordList.get(0).matches("^(incr)|(decr)|(clear)$")){ //if a variable is to be altered it runs the AlterVariable constructor
				AlterVariable alter = new AlterVariable(wordList, variables);
				if (alter.getLog() != null){ //This checks for any log data and prints it if present
					log+="Line " + this.lineNumber + ": " + alter.getLog();
				}
				if (alter.getVariable() != null){ //If a variable has been changed it prints the new information
					log+="Line " + this.lineNumber + ": Name: " + alter.getVariable().getName() + "; Value: " + alter.getVariable().getValue()+"\n";
				}
			}  else if (wordList.get(0).matches("^while$")){ //If a while loop is to be performed a constructor is run
				While loop = new While(wordList,variables);
				loop.setBlock(this.lineNumber, whileBlocks.get(this.lineNumber)); //Its start and end points are added as found previously
				if (loop.conditionMet()){ //If the condition is met the command updates its line number for where to go next
					this.lineNumber = loop.getEnd();
				} else {
					loopStack.push(loop); //If the loop runs, it is added to the stack so the interpreter knows the last loop ran
				}
				if (loop.getLog()!=null){
					this.log+="Line " + this.lineNumber + ": " + loop.getLog();
				}
			} else if (wordList.get(0).matches("^end")){ //If the end keyword is found it goes to the line of the while loop at the top of the stack
				this.lineNumber = loopStack.pop().getStart()-1;
			}
		}




	}

	public int getLineNumber(){ //returns the new line number if changed
		return this.lineNumber;
	}

	public String getLog(){ //returns log data
		return this.log;
	}
}
