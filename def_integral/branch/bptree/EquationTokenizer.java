package bptree;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The EquationTokenizer class takes a Mathimatical Expression and will create tokens representing
 * each part of the expression.
 * 
 * @author Ryan Moore
 *
 */
public class EquationTokenizer implements Lexar<EquationToken> {

	private Pattern parser = Pattern.compile("\\( | \\) | \\^ | \\+ | \\- | \\* | / | \\d*\\.+\\d+ | \\d+ | x{1}+ | e{1}+ | (?i)pi | (?i)sin | (?i)cos | (?i)tan | \\.+ ", Pattern.COMMENTS);
	
	private String leftOver;
	private EquationToken nextToken;
	
	public EquationTokenizer(String equation)
	{
		this.leftOver = equation;
	}
	
	@Override
	public boolean hasMoreElements() {
		if(leftOver.length() < 1)
		{
			return false;
		}
		return true;
	}

	/**
	 * @return EquationToken - returns an EquationToken if there is another token in the equation, and consumes it.
	 * If there are no more elements in the equation, null is returned.
	 */
	@Override
	public EquationToken nextElement() {
		EquationToken retToken;
		if(nextToken != null)
		{	
			retToken = nextToken;
			nextToken = null;
		}
		else
		{
			retToken = getNext();
		}
		return retToken;


	}
	/**
	 * 
	 * @return Returns the next EquationToken, but does not consume the token.
	 */
	public EquationToken peek()
	{
		if(nextToken == null)
		{
			nextToken = getNext();
		}
		return nextToken;
	}
	
	private EquationToken getNext()
	{
		if(hasMoreElements()==false)//Checks to make sure that the equation is not empty before continuing on.
		{
			return null;
		}
		String temp = null;
		Matcher matcher = parser.matcher(leftOver);
		
		if(matcher.find())
		{
			temp = matcher.group();
			EquationToken ret = new EquationToken(temp,ExpressionType.getType(temp),matcher.start());
			int index = leftOver.indexOf(temp);
			leftOver = leftOver.substring(index+temp.length());
			return ret;
		}
		return null;//This should not happen.
	}
}