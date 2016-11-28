import java.util.ArrayList;

public class Decr implements Executable{
	protected Interpreter interpreter;
	protected String varName;
	
	public Decr(Interpreter i, ArrayList<Token> params) throws SyntaxException{
		this.interpreter = i;
		this.varName = params.get(0).getValue();
		if (params.get(0).getKeyword() != Keyword.VAR){
			throw new SyntaxException("Error on line " + i.getLineNumber() + ": Invalid arguments for DECR");
		}
	}
	
	public void execute(){
		Variable var = interpreter.getVariable(varName);
		
		if (var != null){
			var.setValue(var.getValue()-1);
		}
	}
}
