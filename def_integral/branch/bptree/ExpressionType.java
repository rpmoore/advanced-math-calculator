package bptree;

import java.util.HashMap;

public enum ExpressionType {

	OTHER,NUMBER,E,PI,VARIABLE,SIN,COS,TAN,SINH,
	COSH,TANH,LOG,LN,POW,MULTIPLY,DIVIDE,ADD,SUBTRACT,
	LEFTPAREN,RIGHTPAREN;
	
	private static HashMap<String, ExpressionType> expressionToType;
	
	/**
	 * Static constructor used to initaize the hashmap.
	 */
	static {
		expressionToType = new HashMap<String, ExpressionType>();
		//Fill the hashmap
		expressionToType.put("e", ExpressionType.E);
		expressionToType.put("pi", ExpressionType.PI);
		expressionToType.put("x", ExpressionType.VARIABLE);
		expressionToType.put("sin", ExpressionType.SIN);
		expressionToType.put("cos", ExpressionType.COS);
		expressionToType.put("tan", ExpressionType.TAN);
		expressionToType.put("sinh", ExpressionType.SINH);
		expressionToType.put("cosh", ExpressionType.COSH);
		expressionToType.put("tanh", ExpressionType.TANH);
		expressionToType.put("log", ExpressionType.LOG);
		expressionToType.put("ln", ExpressionType.LN);
		expressionToType.put("*", ExpressionType.MULTIPLY);
		expressionToType.put("/", ExpressionType.DIVIDE);
		expressionToType.put("+", ExpressionType.ADD);
		expressionToType.put("-",ExpressionType.SUBTRACT);	
		expressionToType.put("(", ExpressionType.LEFTPAREN);
		expressionToType.put(")", ExpressionType.RIGHTPAREN);
		expressionToType.put("^", ExpressionType.POW);
	}
	
	/**
	 * 
	 * @param 
	 * @return
	 */
	public static boolean isOp(ExpressionType type)
	{
		if(type == ExpressionType.NUMBER || type == ExpressionType.VARIABLE || type == ExpressionType.E || type == ExpressionType.PI)
		{
			return false;
		}
		return true;
	}
	

	/**
	 * 
	 * @param expression 
	 * @return
	 */
	public static ExpressionType getType(String expression)
	{
		ExpressionType value = expressionToType.get(expression);
		if(value != null)
		{
			return value;
		}
		try
		{
			Integer.parseInt(expression);
			return NUMBER; 
		}
		catch(NumberFormatException e)
		{
			try
			{
				Double.parseDouble(expression);
				return NUMBER;
			}
			catch(NumberFormatException e2)
			{
				return OTHER;
			}
		}
	}

}
