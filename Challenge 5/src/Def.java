import java.util.ArrayList;
import java.util.Iterator;

public class Def implements Executable{
	protected Interpreter interpreter;
	protected ArrayList<Line> block;
	protected Condition cond;
	
	public Def(Interpreter i, ArrayList<Token> params) throws SyntaxException{
		interpreter = i;
		this.block = new ArrayList<Line>();
		String name = params.get(0).getValue();
		if (i.getMethod(name) != null){
			throw new SyntaxException("Method already defined");
		}
		
		Line line;		
		do {
			line = interpreter.nextLine();
			if (line.getCommand().getKeyword() != Keyword.END){
				this.block.add(line);
			} else {
				break;
			}
		} while (line != null);
		if (line == null){
			throw new SyntaxException("Missing END statement for DEF");
		}
		i.addMethod(name, this);
	}
	@Override	
	public void execute() {
		
	}
	
	public void run(){
		Iterator<Line> it = block.iterator();
		while (it.hasNext()){
			it.next().execute();
		}
	}

}
