package bptree;

public class EquationToken {
	private String token;
	private int type;
	
	protected EquationToken( String token, int type)
	{
		this.token = token;
		this.type = type;
	}

	public String getToken() {
		return token;
	}

	public int getType() {
		return type;
	}
}
