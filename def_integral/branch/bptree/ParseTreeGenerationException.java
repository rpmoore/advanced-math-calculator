package bptree;

public class ParseTreeGenerationException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2668756992271005480L;

	
	public ParseTreeGenerationException(String token)
	{
		super("The equation has encountered a parse expcetion after " + token);
	}
}
