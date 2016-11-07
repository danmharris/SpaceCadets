import java.util.ArrayList;

public class Condition {
	protected Interpreter i;
	protected Token variable;
	protected Token operand;
	protected int value;
	
	public Condition(Interpreter i, ArrayList<Token> params) throws SyntaxException{
		if (params.get(0).getKeyword() != Keyword.VAR){
			throw new SyntaxException("Invalid Argument for Condition");
		} else if (params.get(2).getKeyword() != Keyword.VAR && params.get(2).getKeyword() != Keyword.NUMBER){
			throw new SyntaxException("Invalid Argument for Condition");
		}
		
		this.i = i;
		this.variable = params.get(0);
		this.operand = params.get(1);
		this.value = Integer.parseInt(params.get(2).getValue());
	}
	public boolean conditionTrue(){
		int varVal = i.getVariable(variable.getValue()).getValue();
		switch (this.operand.getKeyword()){
		case NOT:
			if (varVal != value){
				return true;
			}
			break;
		case IFEQUALS:
			if (varVal == value){
				return true;
			}
			break;
		case GT:
			if (varVal > value){
				return true;
			}
			break;
		case LT:
			if (varVal < value){
				return true;
			}
			break;
		case GE:
			if (varVal >= value){
				return true;
			}
			break;
		case LE:
			if (varVal <= value){
				return true;
			}
			break;
		}
		
		
		return false;
	}

}
