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
import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.Iterator;

import org.apache.log4j.Logger;

import dataStructures.Queue;
import dataStructures.Stack;
import defIntegral.Calculate;
import defIntegral.CalculateException;


/**
 * A stack based implementation of parsing an equation.  Converts an infix expression into an
 * RPN expression to then perform calculations on the equation.
 * 
 * @author Ryan Moore
 */
public final class RPN implements Calculate {
	static final private Logger logger = Logger.getLogger(RPN.class);
	private final Queue<EquationToken> exprQueue;

	/**
	 * Constructs the RPN expression based on of the infix expression.
	 * @param expression The expression to parse into reverse polish notation.
	 */
	private RPN(String expression) throws ParseException
	{
		logger.debug("Parsing Equation: " + expression);
		exprQueue = new Queue<EquationToken>();
		infixToRPN(expression);
	}

	/**
	 * Constructs the stack based off of the shunting-yard algorithm.
	 * @param expression The math express to parse.
	 * @throws ParseException 
	 */
	//http://en.wikipedia.org/wiki/Shunting-yard_algorithm
	private void infixToRPN(String expression) throws ParseException//tokenize string.
	{
		final Lexer<EquationToken> lexer = new ExpressionLexer(expression);
		final Stack<EquationToken> tempStack = new Stack<EquationToken>(new ArrayList<EquationToken>());
		while(lexer.hasMoreElements())
		{
			EquationToken currToken = lexer.nextElement();
			ExpressionType currType = currToken.getType();
			if(currType.isTerm())
			{
				exprQueue.enqueue(currToken);		
				logger.debug("Pushing " + currToken.toString() + " onto the output stack.");

			}
			else if(currType.isFunction())
			{
				tempStack.push(currToken);
				logger.debug("Pushing " + currToken.toString() + " onto temp stack.");
			}
			else if(currType == ExpressionType.LEFTPAREN)
			{
				tempStack.push(currToken);
				logger.debug("Pushing "  + currToken.toString()+" onto the temp stack.");
			}
			else if(currType == ExpressionType.RIGHTPAREN)
			{
				logger.debug("Popping " + currToken + " from temp stack.");
				logger.debug("Current Temp Stack :" + tempStack.toString());
				try
				{
					EquationToken currTempToken = tempStack.pop();
					while(currTempToken.getType() != ExpressionType.LEFTPAREN)
					{
						exprQueue.enqueue(currTempToken);
						logger.debug("Pushing " + currTempToken+ " onto output queue.");
						currTempToken = tempStack.pop();
					}
				}
				catch (EmptyStackException e)
				{
					throw new ParseException("Parse Error: Mismatched Parenthesis.",currToken.getPosition());
				}
			}
			else
			{
				while(tempStack.size() > 0 && tempStack.peek().getType().isOp() && ExpressionType.comparePrecedence(currToken.getType(), tempStack.peek().getType()) != 1)
				{
					exprQueue.enqueue(tempStack.pop());
					logger.debug("Pushing " + exprQueue.peek() + " from the tempStack onto the output stack.");
				}
				tempStack.push(currToken);
				logger.debug("Pushing " + currToken + " onto tempStack.");
			}

		}

		Iterator<EquationToken> iter = tempStack.iterator();
		while(iter.hasNext())
		{
			exprQueue.enqueue(iter.next());
			logger.debug("Pushing " + exprQueue.peekLast().toString() + " onto the output stack from the temp stack,");			
		}
	}

	/**
	 * Calculates a value in the expression at value equal index.
	 * @param index A value to evaluate the expression at.  IE x = index.
	 * @return Returns the value of the expression at index.
	 */
	@Override
	public double eval(double index) throws CalculateException {
		final Stack<Double> calcStack = new Stack<Double>(new ArrayList<Double>());
		final Queue<EquationToken> currExprQueue = new Queue<EquationToken>();
		while(!exprQueue.isEmpty())
		{
			EquationToken curr = exprQueue.dequeue();
			if(curr.getType().isTerm() || curr.getType().isFunction())
			{
				//push the value onto the calc stack.
				calcStack.push(ExpressionType.eval(curr, index));
			}
			else if(curr.getType().isOp())
			{
				//pop off 2 values off of the calcStack.  If there are not 2 values then throw an exception.
				double right = calcStack.pop();//pop off in reverse order.
				//do a check to see if the next value is either empty or if it is another term.
				double left = 0;
				
				if(calcStack.size() > 0 && curr.getType() != ExpressionType.SUBTRACT)
				{
					left = calcStack.pop();
				}

				calcStack.push(ExpressionType.eval(curr,left,right, index));
			}
			else
			{
				while(!currExprQueue.isEmpty())
				{
					exprQueue.enqueue(currExprQueue.dequeue());
				}
				throw new CalculateException("Invalid type in expression.");
			}
			logger.debug("Current Stack: " + calcStack);
			currExprQueue.enqueue(curr);//push onto currExprStack to reconstruct stack after error.
		}

		//pop all the old values from the currExprStack back onto the exprStack.
		while(!currExprQueue.isEmpty())
		{
			exprQueue.enqueue(currExprQueue.dequeue());
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
		Iterator<EquationToken> iter = exprQueue.iterator();
		while(iter.hasNext())
		{
			string.append(iter.next().getToken());
			string.append(" ");
		}
		return string.toString();
	}

	public static RPN generate(String expr) throws ParseException {
		return new RPN(expr);
	}
}
