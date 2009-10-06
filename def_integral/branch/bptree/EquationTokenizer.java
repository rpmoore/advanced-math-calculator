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
	private int stringPointer;
	
	public EquationTokenizer(String equation)
	{
		this.equation = equation;
		this.stringPointer = 0;
	}
	
	@Override
	public boolean hasMoreElements() {
		if(stringPointer > equation.length() || equation.length() == 0)
		{
			return false;
		}
		return true;
	}

	@Override
	public EquationToken nextElement() {
		// TODO Auto-generated method stub
		
		if(stringPointer > equation.length() || equation.length() == 0)//Checks to make sure that the equation is not empty before continuing on.
		{
			return null;
		}
		String temp = null;
		Matcher matcher = parser.matcher(equation.substring(stringPointer));
		
		if(matcher.find())
		{
			temp = matcher.group();
			EquationToken ret = new EquationToken(temp,ExpressionType.getType(temp));
			return ret;
		}
		else
		{	//This should only get hit if the equation is empty.
			return null;
		}
	}
}