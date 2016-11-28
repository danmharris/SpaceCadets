import java.util.ArrayList;

public class Set implements Executable{
	protected Interpreter interpreter;
	protected String varName;
	protected Token newValue;
	
	public Set(Interpreter i, ArrayList<Token> params) throws SyntaxException{
		this.interpreter = i;

		if (params.get(0).getKeyword() != Keyword.VAR){
			throw new SyntaxException("Invalid arguments for SET");
		}
		if (params.get(1).getKeyword() != Keyword.NUMBER && params.get(1).getKeyword() != Keyword.VAR){
			throw new SyntaxException("Invalid arguments for SET");
		}
		
		this.varName = params.get(0).getValue();
		this.newValue = params.get(1);
	}
	
	public void execute(){
		
		Variable var = interpreter.getVariable(varName);
		
		if (var != null){
			if (newValue.getKeyword() == Keyword.VAR){
				var.setValue(interpreter.getVariable(newValue.getValue()).getValue());
			} else if (newValue.getKeyword() == Keyword.NUMBER){
				var.setValue(Integer.parseInt(newValue.getValue()));
			}
		}
		
	}
}
