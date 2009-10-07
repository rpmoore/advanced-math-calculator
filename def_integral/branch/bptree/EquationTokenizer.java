package bptree;

import java.util.Enumeration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The EquationTokenizer class takes a Mathimatical Expression and will create tokens representing
 * each part of the expression.
 * 
 * @author Ryan Moore
 *
 */
public class EquationTokenizer implements Enumeration<EquationToken> {

	private Pattern parser = Pattern.compile("\\( | \\) | \\^ | \\+ | \\- | \\* | / | \\d+\\.+\\d+ | \\d+ | x{1}+ | sin | cos | tan | \\.+ ", Pattern.COMMENTS);
	
	private String equation;
	private String leftOver;

	
	public EquationTokenizer(String equation)
	{
		this.equation = equation;
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

	@Override
	public EquationToken nextElement() {
		// TODO Auto-generated method stub
		
		if(hasMoreElements()==false)//Checks to make sure that the equation is not empty before continuing on.
		{
			return null;
		}
		String temp = null;
		Matcher matcher = parser.matcher(leftOver);
		
		if(matcher.find())
		{
			temp = matcher.group();
			EquationToken ret = new EquationToken(temp,ExpressionType.getType(temp));
			
			stringPointer++;
			return ret;
		}
		else
		{	//This should only get hit if the equation is empty.
			return null;
		}
	}
}