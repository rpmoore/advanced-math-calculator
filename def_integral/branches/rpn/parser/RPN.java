package parser;

import java.text.ParseException;

import DataStructures.Stack;

import defIntegral.Calculate;

/**
 * @author Ryan Moore
 */
public class RPN implements Calculate {

	private String rpnExpr = null; 
	private final Stack exprStack;
	/**
	 * Constructs the RPN expression based on of the infix expression.
	 * @param expression The expression to parse into reverse polish notation.
	 */
	public RPN(String expression) throws ParseException
	{
		exprStack = new Stack();
		infixToRPN(expression);
	}
	
	public void infixToRPN(String expression)
	{
		
	}
	
	private String printStack()
	{
		
		return "";
	}
	
	@Override
	public double eval(double index) {
		return 0;
	}

}
