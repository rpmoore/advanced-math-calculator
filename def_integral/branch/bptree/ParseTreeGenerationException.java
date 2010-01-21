package bptree;

public class ParseTreeGenerationException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2668756992271005480L;
	private String message;
	
	public ParseTreeGenerationException(String token, ParseTreeGenerationExceptionEnum type)
	{
		switch(type)
		{
			case EXPECTED:
				this.message = "Expected a different token after " + token;
			break;
			case UNKNOWNTOKEN:
				this.message = "Unknowntoken " + token + " in the equation";
			break;
			case END:
				this.message = "Expected more tokens at end of equation";
			break;
			case NONE:
				this.message = "Could not find a token";
			break;
		}
	}
	
	public String getMessage()
	{
		return message;
	}
}
