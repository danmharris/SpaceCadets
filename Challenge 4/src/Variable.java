
public class Variable {
	protected int value;
	protected String name;
	
	public Variable(String name){
		this.value = 0;
		this.name = name;
	}
	
	public void clearVariable(){
		this.value = 0;
	}
	
	public void incVariable(){
		this.value++;
	}
	
	public void decVariable(){
		this.value--;
	}
	
	public int getValue(){
		return this.value;
	}
	
	public String getName(){
		return this.name;
	}
}
