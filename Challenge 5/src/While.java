
import java.util.ArrayList;
import java.util.Iterator;

public class While implements Executable{
	protected Interpreter interpreter;
	protected ArrayList<Line> block;
	protected Condition cond;
	
	public While(Interpreter i, ArrayList<Token> params) throws SyntaxException{
		interpreter = i;
		this.block = new ArrayList<Line>();
		cond = new Condition(this.interpreter, params);
		Line line;		
		do {
			line = interpreter.nextLine();
			if (line == null){
				throw new SyntaxException("Missing END statement for WHILE");
			}
			if (line.getCommand().getKeyword() != Keyword.END){
				this.block.add(line);
			} else {
				break;
			}
		} while (line != null);
		
	}
	
	public void execute(){
		while (cond.conditionTrue()){
			Iterator<Line> it = block.iterator();
			while (it.hasNext()){
				it.next().execute();
			}
		}
	}
}
