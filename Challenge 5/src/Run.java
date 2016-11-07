import java.util.ArrayList;

public class Run implements Executable{
	protected Token method;
	protected Interpreter interpreter;
	
	public Run(Interpreter i, ArrayList<Token> params){
		this.interpreter = i;
		this.method = params.get(0);
	}
	
	@Override
	public void execute() {
		Def def = this.interpreter.getMethod(this.method.getValue());
		
		if (def != null){
			def.run();
		}
	}

}
