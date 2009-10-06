package bptree;

import java.util.HashMap;

public class ExpressionType {

	public final static int OTHER = -1;
	
	public final static int NUMBER = 0;
	public final static int E = 1;
	public final static int PI = 2;
	public final static int VARIABLE = 3;
	

	public final static int SIN = 6;
	public final static int COS = 7;
	public final static int TAN = 8;
	public final static int SINH = 9;
	public final static int COSH = 10;
	public final static int TANH = 11;
	public final static int LOG = 12;
	public final static int LN = 13;
	
	public final static int MULTIPLY = 17;
	public final static int DIVIDE = 18;
	
	public final static int ADD = 19;
	public final static int SUBTRACT = 20;
	
	private static HashMap<String,Integer> expressionToType;
	
	static {
		expressionToType = new HashMap<String, Integer>();
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
	}
	
	public static int compType(int type, int type2)
	{
		if(type == type2)
		{
			return 0;
		}
		if(isOp(type) && !isOp(type2))
		{
			return 1;
		}
		if(type > ExpressionType.DIVIDE && type2 > ExpressionType.DIVIDE)
		{
			return 0;
		}
		if(type > ExpressionType.DIVIDE && !(type2 > ExpressionType.DIVIDE))
		{
			return 1;
		}
		if(type <=ExpressionType.DIVIDE && type2 > ExpressionType.DIVIDE)
		{
			return -1;
		}
		if(type > ExpressionType.LN && type2 > ExpressionType.LN)
		{
			return 0;
		}
		if(type > ExpressionType.LN && type2 <= ExpressionType.LN)
		{
			return 1;
		}
		return -1;
	}
	
	public static boolean isOp(int type)
	{
		if(type>ExpressionType.VARIABLE)
		{
			return true;
		}
		return false;
	}
	
	/**
	 * Checks an subset of an expression to validate that it is a valid mathematical symbol.
	 * 
	 * @param expression A portion of the total expression to check to see if it is a valid symbol.
	 * @return true if the expression is valid, false otherwise.
	 */
	private static boolean checkValue(String expression)
	{
		if(getType(expression) < -1)
		{
			return true;
		}
		return false;
	}


	public static int getType(String expression)
	{
		Integer value = expressionToType.get(expression);
		if(value != null)
		{
			return value;
		}
		try
		{
			Integer.parseInt(expression);
			return ExpressionType.NUMBER; 
		}
		catch(NumberFormatException e)
		{
			try
			{
				Double.parseDouble(expression);
				return ExpressionType.NUMBER;
			}
			catch(NumberFormatException e2)
			{
				return -1;
			}
		}
	}

}
