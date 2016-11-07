import java.util.ArrayList;
import java.util.Iterator;

public class If implements Executable{
	protected Interpreter interpreter;
	protected ArrayList<Line> block;
	protected ArrayList<Line> elseBlock;
	protected Condition cond;
	
	public If(Interpreter i, ArrayList<Token> params) throws SyntaxException{
		interpreter = i;
		this.block = new ArrayList<Line>();
		this.elseBlock = new ArrayList<Line>();
		cond = new Condition(this.interpreter, params);
		Line line;		
		do {
			line = interpreter.nextLine();
			if (line == null){
				throw new SyntaxException("Missing END statement for IF");
			}
			if (line.getCommand().getKeyword() != Keyword.END || line.getCommand().getKeyword() != Keyword.ELSE){
				this.block.add(line);
			} else {
				break;
			}
		} while (line != null);
		
		if (line.getCommand().getKeyword() == Keyword.ELSE){
			do {
				line = interpreter.nextLine();
				if (line == null){
					throw new SyntaxException("Missing END statement for IF");
				}
				if (line.getCommand().getKeyword() != Keyword.END){
					this.elseBlock.add(line);
				} else {
					break;
				}
			} while (line != null);
		}
		
		
	}
	
	public void execute() {
		if (cond.conditionTrue()){
			Iterator<Line> it = block.iterator();
			while (it.hasNext()){
				it.next().execute();
			}
		} else {
			Iterator<Line> it = elseBlock.iterator();
			while (it.hasNext()){
				it.next().execute();
			}
		}
		
	}

}
