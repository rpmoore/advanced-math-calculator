package bptree;

public class EquationToken {
	private String token;
	private ExpressionType type;
	
	protected EquationToken( String token, ExpressionType type)
	{
		this.token = token;
		this.type = type;
	}

	public String getToken() {
		return token;
	}

	public ExpressionType getType() {
		return type;
	}
}
