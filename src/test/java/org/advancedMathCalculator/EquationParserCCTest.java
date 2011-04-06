package org.advancedMathCalculator;

import java.io.StringReader;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.advancedMathCalculator.computation.Calculate;
import org.advancedMathCalculator.computation.CalculateException;
import org.advancedMathCalculator.computation.defIntegral.SimpsonsRule;
import org.advancedMathCalculator.dataStructures.Queue;
import org.advancedMathCalculator.parser.EquationToken;
import org.advancedMathCalculator.parser.ExpressionType;
import org.advancedMathCalculator.parser.RPN;
import org.advancedMathCalculator.parser.cc.EquationParserCC;
import org.advancedMathCalculator.parser.cc.ParseException;
import org.advancedMathCalculator.parser.cc.RPNCC;


public class EquationParserCCTest extends TestCase {

	public void testBasicAdd() throws ParseException
	{
		EquationParserCC parser = new EquationParserCC(new StringReader("3+2"));
		Queue<EquationToken> queue = parser.parseEquation();
		Assert.assertEquals("3", queue.dequeue().getToken());
		Assert.assertEquals("2", queue.dequeue().getToken());
		Assert.assertEquals("+", queue.dequeue().getToken());
	}
	
	public void testBasicAddEval() throws ParseException, CalculateException
	{
		EquationParserCC parser = new EquationParserCC(new StringReader("3+2"));
		RPNCC eval = new RPNCC(parser.parseEquation());
		Assert.assertEquals(5.0, eval.eval(0),0.0);
	}
	
	public void testBasicSub() throws ParseException
	{
		EquationParserCC parser = new EquationParserCC(new StringReader("5-12"));
		Queue<EquationToken> queue = parser.parseEquation();
		Assert.assertEquals("5",queue.dequeue().getToken());
		Assert.assertEquals("12",queue.dequeue().getToken());
		Assert.assertEquals("-",queue.dequeue().getToken());
	}
	
	public void testBasicSubEval() throws ParseException, CalculateException
	{
		EquationParserCC parser = new EquationParserCC(new StringReader("5-12"));
		RPNCC eval = new RPNCC(parser.parseEquation());
		Assert.assertEquals(-7.0,eval.eval(0),0.0);
	}
	
	public void testBasicUnaryMinus() throws ParseException
	{
		EquationParserCC parser = new EquationParserCC(new StringReader("-2"));
		Queue<EquationToken> queue = parser.parseEquation();
		Assert.assertEquals("2", queue.dequeue().getToken());
		Assert.assertEquals(ExpressionType.UNARYMINUS, queue.dequeue().getType());
	}
	
	public void testBasicUnaryMinusEval() throws ParseException, CalculateException
	{
		EquationParserCC parser = new EquationParserCC(new StringReader("-2"));
		RPNCC eval = new RPNCC(parser.parseEquation());
		Assert.assertEquals(-2,eval.eval(0),0);
	}
	
	public void testAddMult() throws ParseException
	{
		EquationParserCC parser = new EquationParserCC(new StringReader("5+12*2"));
		Queue<EquationToken> queue = parser.parseEquation();
		Assert.assertEquals("5",queue.dequeue().getToken());
		Assert.assertEquals("12",queue.dequeue().getToken());
		Assert.assertEquals("2",queue.dequeue().getToken());
		Assert.assertEquals("*",queue.dequeue().getToken());
		Assert.assertEquals("+",queue.dequeue().getToken());
	}
	
	public void testAddMultEval() throws ParseException, CalculateException
	{
		EquationParserCC parser = new EquationParserCC(new StringReader("5+12*2"));
		RPNCC eval = new RPNCC(parser.parseEquation());
		Assert.assertEquals(29, eval.eval(0),0);
	}
	
	public void testTrigPow() throws ParseException
	{
		EquationParserCC parser = new EquationParserCC(new StringReader("2+3^sin(90)-4"));
		Queue<EquationToken> queue = parser.parseEquation();
		Assert.assertEquals("2", queue.dequeue().getToken());
		Assert.assertEquals("3", queue.dequeue().getToken());
		Assert.assertEquals("90", queue.dequeue().getToken());
		Assert.assertEquals("SIN", queue.dequeue().getToken());
		Assert.assertEquals("^", queue.dequeue().getToken());
		Assert.assertEquals("+", queue.dequeue().getToken());
		Assert.assertEquals("4", queue.dequeue().getToken());
		Assert.assertEquals("-", queue.dequeue().getToken());
	}
	
	public void testTrigPowEval() throws ParseException, CalculateException
	{
		EquationParserCC parser = new EquationParserCC(new StringReader("2+3^sin(90)-4"));
		RPNCC eval = new RPNCC(parser.parseEquation());
		Assert.assertEquals(.6702062608, eval.eval(0),.000000001);
	}	
	
	public void testDefect2() throws ParseException, CalculateException
	{
		EquationParserCC parser = new EquationParserCC(new StringReader("log(1.5)^-1"));
		RPNCC eval = new RPNCC(parser.parseEquation());
		Assert.assertEquals(1/Math.log10(1.5), eval.eval(0),0);
	}
	
	public void testCalcWithVar() throws ParseException, CalculateException
	{
		EquationParserCC parser = new EquationParserCC(new StringReader("2+x"));
		RPNCC eval = new RPNCC(parser.parseEquation());
		Assert.assertEquals(3, eval.eval(1),0);
	}
	
	public void testCalcWithVarTwice() throws ParseException, CalculateException
	{
		EquationParserCC parser = new EquationParserCC(new StringReader("2-x"));
		RPNCC eval = new RPNCC(parser.parseEquation());
		Assert.assertEquals(1, eval.eval(1),0);
		Assert.assertEquals(-1, eval.eval(3),0);
	}
	
	public void testIntegrationSimpsionsRPN() throws CalculateException, ParseException {
		Calculate expr = new RPNCC(new EquationParserCC(new StringReader(("12*x+.5"))).parseEquation());

		final double answer = SimpsonsRule.compute(expr, .5, 5.5);
		assertEquals(182.5, answer, 1);
		assertEquals(182.5, answer, 0);
	}
}