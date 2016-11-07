
public class Token {
	protected Keyword type;
	protected String value;
	public Token(Keyword type, String value){
		this.type = type;
		this.value = value;
	}
	
	public Keyword getKeyword(){
		return this.type;
	}
	
	public String getValue(){
		return this.value;
	}
}
