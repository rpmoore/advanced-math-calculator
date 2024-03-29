package org.advancedMathCalculator.parser.cc;

import org.advancedMathCalculator.computation.Calculate;
import org.advancedMathCalculator.computation.CalculateException;
import org.advancedMathCalculator.dataStructures.Queue;
import org.advancedMathCalculator.dataStructures.Stack;
import org.advancedMathCalculator.parser.EquationToken;
import org.advancedMathCalculator.parser.ExpressionType;

public class RPNCC implements Calculate{

	final private Queue<EquationToken> equation;
	final private Queue<EquationToken> usedTokens;
	public RPNCC(Queue<EquationToken> equation)
	{
		this.equation = equation;
		this.usedTokens = new Queue<EquationToken>();
	}
	
	@Override
	public double eval(double index) throws CalculateException {
		final Stack<Double> evalStack = new Stack<Double>();
		
		while(!equation.isEmpty())
		{
			final EquationToken curr = equation.dequeue();
			usedTokens.enqueue(curr);
			if(curr.getType().isTerm())
			{
				evalStack.push(ExpressionType.eval(curr, index));
			}
			else if(curr.getType().isFunction())
			{
				evalStack.push(ExpressionType.eval(curr,0,evalStack.pop(), index));
			}
			else if(curr.getType() == ExpressionType.UNARYMINUS)
			{
				evalStack.push(ExpressionType.eval(curr,0,evalStack.pop(), index));
			}
			else 
			{
				final double right = evalStack.pop();
				final double left = evalStack.pop();
				evalStack.push(ExpressionType.eval(curr, left,right,index));		
			}
		}		
		if(evalStack.size() > 1)
		{
			throw new CalculateException("Missing an operator.  There is an extra element.");
		}
		
		//reload all the used operators.
		while(!usedTokens.isEmpty()){
			equation.enqueue(usedTokens.dequeue());			
		}

		return evalStack.pop();
	}	
}