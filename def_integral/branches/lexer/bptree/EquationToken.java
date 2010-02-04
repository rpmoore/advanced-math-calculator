package bptree;

public class EquationToken {
	private String token;
	private ExpressionType type;
	private int index;
	
	protected EquationToken( String token, ExpressionType type, int index)
	{
		this.token = token;
		this.type = type;
		this.index = index;
	}
	protected EquationToken( String token, ExpressionType type)
	{
		this(token,type,-1);
	}
	
	public String getToken() {
		return token;
	}

	public ExpressionType getType() {
		return type;
	}
	
	public int getPosition()
	{
		return index;
	}
	
	public String toString()
	{
		return "" + type + " - " + token + " - " + index;
		}
}
