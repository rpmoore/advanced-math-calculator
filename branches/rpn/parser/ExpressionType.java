package parser;
/*
 * Copyright 2010 Ryan Moore
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *  
 */
import java.util.HashMap;

public enum ExpressionType {

	OTHER,NUMBER,E,PI,VARIABLE,SIN,COS,TAN,SINH,
	COSH,TANH,LOG,LN,POW,MULTIPLY,DIVIDE,ADD,SUBTRACT,
	LEFTPAREN,RIGHTPAREN, NAN,BAD_TOKEN,EOF;

	private static HashMap<String, ExpressionType> expressionToType;

	/**
	 * Static constructor used to initialize the hashmap.
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
	 * Determines if the expression is an operator.
	 * @param The type to determine if it is an operator.
	 * @return True if the type is an operator, false if the type is not an operator.
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
	 * Determines if the current type is an operator.
	 * @return True if the type is an operator, false if the type is not an operator.
	 */
	public boolean isOp()
	{
		return isOp(this);
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
	 */	
	public boolean isFunction()
	{
		return isFunction(this);
	}

	/**
	 * E, PI, NUMBER, VARIABLE and NAN are all terminals
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
	 * E, PI, NUMBER, VARIABLE and NAN are all terminals
	 * @return
	 */
	public boolean isTerm()
	{
		return isTerm(this);
	}

	/**
	 * Determines the Expression Type for a given string.
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
	 * Compares the precedence of the left ExpressionType to the right ExpressionType.
	 * If the left ExpressionType is less than the right it returns < 0. If the left and right are
	 * equal it returns 0. If the left is greater than the right it returns > 0.
	 * @param left
	 * @param right
	 * @return
	 */
	public static int comparePrecedence(ExpressionType left, ExpressionType right)
	{
		int leftPrec = getPrecedence(left);
		int rightPrec = getPrecedence(right);
		if(leftPrec < rightPrec)
		{
			return -1;
		}
		else if(leftPrec > rightPrec)
		{
			return 1;
		}
		else
		{
			return 0;
		}
	}

	//precedence levels are as follows.
	// 0 = terms
	// 1 = add
	// 2 = mult divide
	// 3 = sub -- doing this so that the sub is a single unary op.
	// 4 = exponent
	// 5 = functions
	// 6 = parans
	public static int getPrecedence(ExpressionType type)
	{
		if(type.isFunction())
		{
			return 5;
		}
		if(type.isTerm())
		{
			return 0;
		}
		switch(type)
		{
		case ADD:
			return 1;
		case SUBTRACT:
			return 3;
		case MULTIPLY:
		case DIVIDE:
			return 2;
		case POW:
			return 4;
		case LEFTPAREN:
		case RIGHTPAREN:
			return 6;
		default:
			return -1;
		}
	}
	
	/**
	 * Performs the arithmetic operations on expressions that only return a value. All the term values.
	 * @param token
	 * @param index
	 * @return
	 */
	public static double eval(EquationToken token,double index)
	{
		return eval(token,0,0,index);
	}
	/**
	 * Performs all the arithmetic operations.  This will get called a lot.
	 * @param type
	 * @param left
	 * @param right
	 * @return
	 */
	public static double eval(EquationToken token, double left, double right, double index) 
	{
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