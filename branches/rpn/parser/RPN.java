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

import org.apache.log4j.Logger;

import dataStructures.Stack;
import defIntegral.Calculate;
import defIntegral.CalculateException;


/**
 * A stack based implementation of parsing a equation.  Converts an infix expression into an
 * RPN expression to then perform calculations on the equation.
 * 
 * @author Ryan Moore
 */
public final class RPN implements Calculate {
	static final private Logger logger = Logger.getLogger(RPN.class);
	private final Stack<EquationToken> exprStack;
	/**
	 * Constructs the RPN expression based on of the infix expression.
	 * @param expression The expression to parse into reverse polish notation.
	 */
	public RPN(String expression) throws ParseException
	{
		exprStack = new Stack<EquationToken>();
		infixToRPN(expression);
		exprStack.reverse();//need to reverse the order of the stack since the infixToRPN process leaves it in reverse order.
	}
	
	/**
	 * Constructs the stack based off of the shunting-yard algorithm.
	 * @param expression The math express to parse.
	 */
	//http://en.wikipedia.org/wiki/Shunting-yard_algorithm
	private void infixToRPN(String expression)//tokenize string.
	{
		final EquationLexer lexer = new EquationLexer(expression);
		final Stack<EquationToken> tempStack = new Stack<EquationToken>();
		while(lexer.hasMoreElements())
		{
			EquationToken currToken = lexer.nextElement();
			ExpressionType currType = currToken.getType();
			if(currType.isTerm())
			{
				exprStack.push(currToken);		
				logger.debug("Pushing " + currToken.toString() + " onto the output stack.");
				
			}
			else if(currType.isFunction())
			{
				tempStack.push(currToken);
				logger.debug("Pushing " + currToken.toString() + " onto temp stack.");
			}
			else
			{
				while(tempStack.size() > 0 && ExpressionType.comparePrecedence(currToken.getType(), tempStack.peek().getType()) != 1)
				{
					exprStack.push(tempStack.pop());
					logger.debug("Pushing " + exprStack.peek() + " from the tempStack onto the output stack.");
				}
				tempStack.push(currToken);
				logger.debug("Pushing " + currToken + " onto tempStack.");
			}
					
		}
		
		Iterator<EquationToken> iter = tempStack.iterator();
		while(iter.hasNext())
		{
			exprStack.push(iter.next());
			logger.debug("Pushing " + exprStack.peek().toString() + " onto the output stack from the temp stack,");			
		}
	}
	
	/**
	 * Calculates a value in the expression at value equal index.
	 * @param index A value to evaluate the expression at.  IE x = index.
	 * @return Returns the value of the expression at index.
	 */
	@Override
	public double eval(double index) throws CalculateException {
		final Stack<Double> calcStack = new Stack<Double>();
		final Stack<EquationToken> currExprStack = new Stack<EquationToken>();
		while(exprStack.size() > 0)
		{
			EquationToken curr = exprStack.pop();
			currExprStack.push(curr);//push onto currExprStack to reconstruct stack after error.
			if(curr.getType().isTerm() || curr.getType().isFunction())
			{
				//push the value onto the calc stack.
				calcStack.push(ExpressionType.eval(curr, index));
			}
			else if(curr.getType().isOp())
			{
				//pop off 2 values off of the calcStack.  If there are not 2 values then through an exception.
				double left = calcStack.pop();
				double right = calcStack.pop();
				calcStack.push(ExpressionType.eval(curr,left,right, index));
			}
			else
			{
				while(currExprStack.size() > 0)
				{
					exprStack.push(currExprStack.pop());
				}
				throw new CalculateException("Invalid type in expression.");
			}
			logger.debug("Current Stack: " + calcStack);
		}

		//pop all the old values from the currExprStack back onto the exprStack.
		while(currExprStack.size() > 0)
		{
			exprStack.push(currExprStack.pop());
		}
		
		//check to make sure there is only 1 value on the stack, and that there is actually a value on the stack.
		if(calcStack.size() == 0)
		{
			throw new CalculateException("Invalid expression: too many operators in expression.");
		}
		else if(calcStack.size() != 1)
		{
			throw new CalculateException("Invalid expression: not enough operators in expression.");
		}
		return calcStack.pop();
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
