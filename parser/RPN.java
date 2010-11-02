package parser;

import java.text.ParseException;
import java.util.Iterator;

import DataStructures.Stack;
import defIntegral.Calculate;

/**
 * @author Ryan Moore
 */
public final class RPN implements Calculate {

	private final Stack<EquationToken> exprStack;
	/**
	 * Constructs the RPN expression based on of the infix expression.
	 * @param expression The expression to parse into reverse polish notation.
	 */
	public RPN(String expression) throws ParseException
	{
		exprStack = new Stack<EquationToken>();
		infixToRPN(expression);
	}
	
	public void infixToRPN(String expression)
	{
		
	}
	
	@Override
	public double eval(double index) {
		return 0;
	}

	public String toString()
	{
		StringBuilder string = new StringBuilder();
		Iterator<EquationToken> iter = exprStack.iterator();
		while(iter.hasNext())
		{
			string.append(iter.next().getToken());
			string.append(" ");
		}
		return string.toString();
	}
}
