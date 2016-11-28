import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class Interpreter {
	protected ArrayList<String> code;
	protected ArrayList<Line> lines;
	protected HashMap<String,Def> methods;
	protected HashMap<String,Variable> variables;
	protected Iterator<String> it;
	protected int lineNumber;
	protected String log;
	protected boolean runnable;
	
	public Interpreter(ArrayList<String> code){
		this.code = code;
		this.variables = new HashMap<String,Variable>();
		this.methods = new HashMap<String,Def>();
		this.lines = new ArrayList<Line>();
		this.lineNumber = 0;
		this.log = "";
		this.runnable = true;
	}
	
	public Line nextLine() {
		Line line = new Line(this);
		if (!it.hasNext()){
			return null;
		} else {
		String lineString = it.next().trim().replaceAll(";", " ;");
		lineNumber++;
		it.remove();
		if (lineString.equals("")){
			return null;
		} else {
			line.parse(lineString);
			return line;
		}
		}
		
	}
	
	public Iterator<String> getVariableNameIterator(){
		return this.variables.keySet().iterator();
	}
	
	public Variable getVariable(String name){
		if (variables.containsKey(name)){
			return variables.get(name);
		} else {
			return null;
		}
	}
	
	public Def getMethod(String name){
		if (methods.containsKey(name)){
			return methods.get(name);
		} else {
			return null;
		}
	}
	
	public void addVariable(String name, Variable var){
		this.variables.put(name, var);
	}
	
	public void addMethod(String name, Def def){
		this.methods.put(name, def);
	}
	
	public int getLineNumber(){
		return this.lineNumber;
	}
	
	public void appendLog(String text){
		this.log+=text + "\n";
	}
	
	public String getLog(){
		return this.log;
	}
	
	public void stopRun(){
		this.runnable = false;
	}
	
	public void parse() {
		System.out.println("Parsing");
		it = this.code.iterator();
		while (it.hasNext()){
			Line line = nextLine();
			if (line != null){
				lines.add(line);
			}
		}
	}
	

	public void execute(){
		if (this.runnable){
		System.out.println("Executing");
		Iterator<Line> it = this.lines.iterator();
		while (it.hasNext()){
			it.next().execute();
		}
		}
	}
}
