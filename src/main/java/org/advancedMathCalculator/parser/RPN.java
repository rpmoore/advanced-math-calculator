package org.advancedMathCalculator.parser;

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

import org.advancedMathCalculator.dataStructures.Queue;
import org.advancedMathCalculator.dataStructures.Stack;
import org.advancedMathCalculator.defIntegral.Calculate;
import org.advancedMathCalculator.defIntegral.CalculateException;


/**
 * A stack based implementation of parsing an equation. Converts an infix
 * expression into an RPN expression to then perform calculations on the
 * equation.
 * 
 * @author Ryan Moore
 */
public final class RPN implements Calculate {

	public static RPN generate(String expr) throws ParseException {
		return new RPN(expr);
	}

	private final Queue<EquationToken> exprQueue;

	/**
	 * Constructs the RPN expression based on of the infix expression.
	 * 
	 * @param expression
	 *            The expression to parse into reverse polish notation.
	 */
	private RPN(String expression) throws ParseException {
		exprQueue = new Queue<EquationToken>();
		infixToRPN(expression);
	}

	/**
	 * Calculates a value in the expression at value equal index.
	 * 
	 * @param index
	 *            A value to evaluate the expression at. IE x = index.
	 * @return Returns the value of the expression at index.
	 */
	public double eval(double index) throws CalculateException {
		final Stack<Double> calcStack = new Stack<Double>(
				new ArrayList<Double>());
		final Queue<EquationToken> currExprQueue = new Queue<EquationToken>();
		while (!exprQueue.isEmpty()) {
			final EquationToken curr = exprQueue.dequeue();
			if (curr.getType().isTerm()) {
				// push the value onto the calc stack.
				calcStack.push(ExpressionType.eval(curr, index));
			}else if(curr.getType().isFunction())
			{
				//pop a value off the calc stack and eval the function
				double value = calcStack.pop();
				calcStack.push(ExpressionType.eval(curr,0,value,index));
			}
			else if (curr.getType().isOp()) {
				// pop off 2 values off of the calcStack. If there are not 2
				// values then throw an exception.
				final double right = calcStack.pop();// pop off in reverse
														// order.
				// do a check to see if the next value is either empty or if it
				// is another term.
				double left = 0;

				if ((calcStack.size() > 0)
						&& (curr.getType() != ExpressionType.SUBTRACT)) {
					left = calcStack.pop();
				}

				calcStack.push(ExpressionType.eval(curr, left, right, index));
			} else {
				while (!currExprQueue.isEmpty()) {
					exprQueue.enqueue(currExprQueue.dequeue());
				}
				throw new CalculateException("Invalid type in expression.");
			}
			currExprQueue.enqueue(curr);// push onto currExprStack to
										// reconstruct stack after error.
		}

		// pop all the old values from the currExprStack back onto the
		// exprStack.
		while (!currExprQueue.isEmpty()) {
			exprQueue.enqueue(currExprQueue.dequeue());
		}

		// check to make sure there is only 1 value on the stack, and that there
		// is actually a value on the stack.
		if (calcStack.size() == 0) {
			throw new CalculateException(
					"Invalid expression: too many operators in expression.");
		} else if (calcStack.size() != 1) {
			throw new CalculateException(
					"Invalid expression: not enough operators in expression.");
		}
		return calcStack.pop();
	}

	/**
	 * Constructs the stack based off of the shunting-yard algorithm.
	 * 
	 * @param expression
	 *            The math express to parse.
	 * @throws ParseException
	 */
	// http://en.wikipedia.org/wiki/Shunting-yard_algorithm
	private void infixToRPN(String expression) throws ParseException// tokenize
																	// string.
	{
		final Lexer<EquationToken> lexer = new ExpressionLexer(expression);
		final Stack<EquationToken> tempStack = new Stack<EquationToken>(
				new ArrayList<EquationToken>());
		while (lexer.hasMoreElements()) {
			final EquationToken currToken = lexer.nextElement();
			final ExpressionType currType = currToken.getType();
			if (currType.isTerm()) {
				exprQueue.enqueue(currToken);

			} else if (currType.isFunction()) {
				tempStack.push(currToken);
			} else if (currType == ExpressionType.LEFTPAREN) {
				tempStack.push(currToken);
			} else if (currType == ExpressionType.RIGHTPAREN) {
				try {
					EquationToken currTempToken = tempStack.pop();
					while (currTempToken.getType() != ExpressionType.LEFTPAREN) {
						exprQueue.enqueue(currTempToken);
						currTempToken = tempStack.pop();
					}
				} catch (final EmptyStackException e) {
					throw new ParseException(
							"Parse Error: Mismatched Parenthesis.",
							currToken.getPosition());
				}
			} else {
				while ((tempStack.size() > 0)
						&& tempStack.peek().getType().isOp()
						&& (ExpressionType
								.comparePrecedence(currToken.getType(),
										tempStack.peek().getType()) != 1)) {
					exprQueue.enqueue(tempStack.pop());
				}
				tempStack.push(currToken);
			}

		}

		final Iterator<EquationToken> iter = tempStack.iterator();
		while (iter.hasNext()) {
			exprQueue.enqueue(iter.next());
		}
	}

	@Override
	public String toString() {
		final StringBuilder string = new StringBuilder();
		final Iterator<EquationToken> iter = exprQueue.iterator();
		while (iter.hasNext()) {
			string.append(iter.next().getToken());
			string.append(" ");
		}
		return string.toString();
	}
}
