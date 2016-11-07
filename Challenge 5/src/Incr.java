import java.util.ArrayList;

public class Incr implements Executable{
	protected Interpreter interpreter;
	protected String varName;
	
	public Incr(Interpreter i, ArrayList<Token> params) throws SyntaxException{
		this.interpreter = i;
		this.varName = params.get(0).getValue();
		if (params.get(0).getKeyword() != Keyword.VAR){
			throw new SyntaxException("Invalid arguments for INCR");
		}
	}
	
	public void execute(){
		Variable var = interpreter.getVariable(varName);
		
		if (var != null){
			var.setValue(var.getValue()+1);
		}
	}
}
