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
	
	/**
	 * Constructs the stack based off of the shunting-yard algorithm.
	 * @param expression The math express to parse.
	 */
	
	private void infixToRPN(String expression)//tokenize string.
	{
		final EquationLexer lexer = new EquationLexer(expression);
		final Stack<EquationToken> tempStack = new Stack<EquationToken>();
		while(lexer.hasMoreElements())
		{
			EquationToken currToken = lexer.nextElement();
			switch(currToken.getType())
			{
				//these are all number cases.
				case NUMBER:
				case PI:
				case E:
				case VARIABLE:
				{
					exprStack.push(currToken);
				}
				break;
				case COS:
				case COSH:
				case LN:
				case LOG:
				case POW:
				case SIN:
				case SINH:
				case TAN:
				case TANH:
				{
					tempStack.push(currToken);
				}
				break;
				case ADD:
				case DIVIDE:
				case MULTIPLY:
				case SUBTRACT:
				{
					
				}
				break;
			}
		}
		
		Iterator<EquationToken> iter = tempStack.iterator();
		while(iter.hasNext())
		{
			exprStack.push(iter.next());
		}
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
