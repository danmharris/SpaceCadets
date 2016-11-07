import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class Line {
	protected Interpreter interpreter;
	protected Token command;
	protected Executable e;
	
	public Line(Interpreter i){
		this.interpreter = i;
	}
	
	public void parse(String lineString) {
		ArrayList<Token> tokens = new ArrayList<Token>();
		ArrayList<String> words = new ArrayList<String>(Arrays.asList(lineString.split("[\\s]")));
		
		Iterator<String> it = words.iterator();
		while (it.hasNext()){
			boolean br = false;
			String current = it.next();
			switch(current){
			case "":
				break;
			case "clear":
				tokens.add(new Token(Keyword.CLEAR, current));
				break;
			case "set":
				tokens.add(new Token(Keyword.SET,current));
				break;
			case "incr":
				tokens.add(new Token(Keyword.INCR, current));
				break;
			case "decr":
				tokens.add(new Token(Keyword.DECR,current));
				break;
			case "run":
				tokens.add(new Token(Keyword.RUN,current));
				break;
			case "if":
				tokens.add(new Token(Keyword.IF,current));
				break;
			case "else":
				tokens.add(new Token(Keyword.ELSE,current));
				break;
			case "while":
				tokens.add(new Token(Keyword.WHILE,current));
				break;
			case "def":
				tokens.add(new Token(Keyword.DEF,current));
				break;
			case "end":
				tokens.add(new Token(Keyword.END,current));
				break;			
			case "not":
				tokens.add(new Token(Keyword.NOT,current));
				break;
			case "==":
				tokens.add(new Token(Keyword.IFEQUALS,current));
				break;
			case ">":
				tokens.add(new Token(Keyword.GT,current));
				break;
			case "<":
				tokens.add(new Token(Keyword.LT,current));
				break;
			case ">=":
				tokens.add(new Token(Keyword.GE,current));
				break;
			case "<=":
				tokens.add(new Token(Keyword.LE,current));
				break;
			case ";":
				tokens.add(new Token(Keyword.SEMICOLON,current));
				break;
			default:
				if (current.matches("^[0-9]*$")){
					tokens.add(new Token(Keyword.NUMBER, current));
				} else if (current.matches("^[a-z,A-Z]*$")){
					tokens.add(new Token(Keyword.VAR, current));
				} else if (current.matches("^//.*$")){
					br = true;
				}
				break;
			}
			if (br){
				break;
			}
		}
		
		try {
			if (tokens.get(tokens.size()-1).getKeyword() != Keyword.SEMICOLON){
				throw new SyntaxException("Missing Semicolon");
			}
			this.command = tokens.get(0);
			tokens.remove(0);
			ArrayList<Token> parameters = tokens;
			switch(this.command.getKeyword()){
			case CLEAR:
				e = new Clear(this.interpreter, parameters);
				break;
			case SET:
				e = new Set(this.interpreter,parameters);
				break;
			case INCR:
				e = new Incr(this.interpreter,parameters);
				break;
			case DECR:
				e = new Decr(this.interpreter, parameters);
				break;
			case WHILE:
				e = new While(this.interpreter, parameters);
				break;
			case IF:
				e = new If(this.interpreter,parameters);
				break;
			case RUN:
				e = new Run(this.interpreter,parameters);
				break;
			case DEF:
				e = new Def(this.interpreter,parameters);
				break;
			}
		} catch(SyntaxException e){
			interpreter.appendLog("Syntax Error on Line "+interpreter.getLineNumber() +": " + e.getMessage());
			interpreter.stopRun();
		}
	}
	
	public Token getCommand(){
		return this.command;
	}
	
	
	public void execute(){
		this.e.execute();
		
	}

}
