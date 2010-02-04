package bptree;

import java.util.HashMap;

public enum ExpressionType {

	OTHER,NUMBER,E,PI,VARIABLE,SIN,COS,TAN,SINH,
	COSH,TANH,LOG,LN,POW,MULTIPLY,DIVIDE,ADD,SUBTRACT,
	LEFTPAREN,RIGHTPAREN, NAN,BAD_TOKEN,EOF;

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
		expressionToType.put("Nan", ExpressionType.NAN);
	}

	/**
	 * 
	 * @param 
	 * @return
	 */
	public static boolean isOp(ExpressionType type)
	{
		if(type == ExpressionType.MULTIPLY || type == ExpressionType.DIVIDE || 
				type == ExpressionType.ADD || type == ExpressionType.SUBTRACT ||
				type == ExpressionType.POW)
		{
			return true;
		}
		return false;
	}

	/**
	 * 
	 * @param type
	 * @return
	 */
	public static boolean isFunction(ExpressionType type)
	{
		if(type == ExpressionType.SIN || type == ExpressionType.SINH || 
				type == ExpressionType.COS || type == ExpressionType.COSH || 
				type == ExpressionType.TAN || type == ExpressionType.TANH || 
				type == ExpressionType.LOG || type == ExpressionType.LN)
		{
			return true;
		}
		return false;
	}

	/**
	 * 
	 * @param type
	 * @return
	 */
	public static boolean isTerm(ExpressionType type)
	{
		if(type == ExpressionType.E || type == ExpressionType.PI ||
				type == ExpressionType.NUMBER || type == ExpressionType.VARIABLE ||
				type == ExpressionType.NAN)
		{
			return true;
		}
		return false;
	}

	/**
	 * 
	 * @param expression 
	 * @return
	 */
	public static ExpressionType getType(String expression)
	{
		ExpressionType value = expressionToType.get(expression.toLowerCase());
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

	/**
	 * Performs all the arthimitic operations.  This will get called a lot.
	 * @param type
	 * @param left
	 * @param right
	 * @return
	 */
	public static double eval(EquationToken token, double left, double right, double index) 
	{
		//TODO Add the rest of the functionality for the other functions.
		double ret = 0.0;

		switch(token.getType())
		{
		case E:
			ret = Math.E;
			break;
		case PI:
			ret = Math.PI;
			break;
		case VARIABLE:
			ret = index;
			break;
		case NUMBER:
			ret = Double.parseDouble(token.getToken());
			break;
		case ADD: 
			ret = left+ right; 
			break;
		case SUBTRACT:
			if(new Double(left).isNaN())
			{
				ret = -right;
			}
			else
			{
				ret = left - right;
			}
			break;
		case MULTIPLY:
			ret = left * right;
			break;
		case DIVIDE:
			ret = left / right;
			break;
		case POW:
			ret = Math.pow(left, right);
			break;
		case COS:
			ret = Math.cos(right);
			break;
		case SIN:
			ret = Math.sin(right);
			break;
		case TAN:
			ret = Math.tan(right);
			break;
		case COSH:
			ret = Math.cosh(right);
			break;
		case SINH:
			ret = Math.sinh(right);
			break;
		case TANH:
			ret = Math.tanh(right);
			break;
		case LN:
			ret = Math.log(right);
			break;
		case LOG:
			ret = Math.log10(right);
			break;
		case NAN:
			ret = Double.NaN;
			break;
		}
		return ret;
	}

}
