import java.util.ArrayList;

public class Clear implements Executable{
	protected Interpreter interpreter;
	protected String varName;
	
	public Clear(Interpreter i, ArrayList<Token> params) throws SyntaxException{
		this.interpreter = i;
		this.varName = params.get(0).getValue();
		if (params.get(0).getKeyword() != Keyword.VAR){
			throw new SyntaxException("Invalid arguments for CLEAR");
		}
	}
	
	public void execute(){
		Variable var = interpreter.getVariable(varName);
		if (var != null){
			var.setValue(0);
		} else {
			interpreter.addVariable(varName, new Variable(0));
		}
	}
}
