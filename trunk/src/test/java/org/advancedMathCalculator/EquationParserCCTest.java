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
	
	public void testParens() throws ParseException, CalculateException
	{
		EquationParserCC parser = new EquationParserCC(new StringReader("(5+2)*3"));
		RPNCC eval = new RPNCC(parser.parseEquation());
		Assert.assertEquals((5+2)*3, eval.eval(0),0);
	}
	
	public void testNestedParens()throws ParseException, CalculateException
	{
		EquationParserCC parser = new EquationParserCC(new StringReader("(2*(5+2))^2"));
		RPNCC eval = new RPNCC(parser.parseEquation());
		Assert.assertEquals(Math.pow((2*(5+2)),2), eval.eval(0),0);		
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

	public void testLexLogEval() throws ParseException, CalculateException
	{
		RPNCC function = new RPNCC(new EquationParserCC(new StringReader("log(x)")).parseEquation());
		assertEquals(Math.log10(5), function.eval(5),0);
	}

	public void testLexSinEval() throws ParseException, CalculateException
	{
		RPNCC function = new RPNCC(new EquationParserCC(new StringReader("sin(x)")).parseEquation());
		assertEquals(Math.sin(5), function.eval(5),0);
	}

	public void testLexCosEval() throws ParseException, CalculateException
	{
		RPNCC function = new RPNCC(new EquationParserCC(new StringReader("cos(x)")).parseEquation());
		assertEquals(Math.cos(5), function.eval(5),0);
	}

	public void testLexTanEval() throws ParseException, CalculateException
	{
		RPNCC function = new RPNCC(new EquationParserCC(new StringReader("tan(x)")).parseEquation());
		assertEquals(Math.tan(5), function.eval(5),0);
	}

	public void testLexLNEval() throws ParseException, CalculateException
	{
		RPNCC function = new RPNCC(new EquationParserCC(new StringReader("ln(x)")).parseEquation());
		assertEquals(Math.log(5), function.eval(5),0);
	}

	public void testEval8() throws ParseException, CalculateException
	{
		RPNCC	function = new RPNCC(new EquationParserCC( new StringReader("(12-8)^2/2")).parseEquation());
		assertEquals(8, function.eval(0), 0);
	}

	public void testEval9() throws ParseException, CalculateException
	{
		RPNCC function = new RPNCC(new EquationParserCC(new StringReader("-2+3/x")).parseEquation());		
		assertEquals(-1.0, function.eval(3),0);
	}

	public void testEvalImplicitMult() throws ParseException, CalculateException
	{
		RPNCC function = new RPNCC(new EquationParserCC(new StringReader("2x")).parseEquation());
		assertEquals(4, function.eval(2),0);
	}
	
	public void testEval10() throws ParseException, CalculateException
	{
		RPNCC function = new RPNCC(new EquationParserCC(new StringReader("12x + 4")).parseEquation());
		assertEquals(16.0, function.eval(1),0);
	}

	public void testEval11() throws ParseException, CalculateException
	{
		RPNCC function = new RPNCC(new EquationParserCC(new StringReader("3pix")).parseEquation());
		assertEquals(3 * Math.PI * 2, function.eval(2),0);
	}

	public void testEval12() throws ParseException, CalculateException
	{
		RPNCC function = new RPNCC(new EquationParserCC(new StringReader("2+(-3+x)")).parseEquation());
		assertEquals(2+(-3+1),function.eval(1),0);
	}

	public void testEval13() throws ParseException, CalculateException
	{
		RPNCC function = new RPNCC(new EquationParserCC(new StringReader("2+(-3x)")).parseEquation());
		assertEquals(2+(-3*2),function.eval(2),0);
	}
	public void testEval14() throws ParseException, CalculateException
	{
		RPNCC function = new RPNCC(new EquationParserCC(new StringReader("2*-3x")).parseEquation());
		assertEquals(2*-3*2, function.eval(2),0);
	}
	public void testEval15() throws ParseException, CalculateException
	{
		RPNCC function = new RPNCC(new EquationParserCC(new StringReader("2+(2*-3x)")).parseEquation());
		assertEquals(2+(2*-3*2),function.eval(2),0);
	}
	public void testEval16() throws ParseException, CalculateException
	{
		RPNCC function = new RPNCC(new EquationParserCC(new StringReader("2+(2*(-3x))")).parseEquation());
		assertEquals(2+(2*(-3*2)),function.eval(2),0);
	}

}