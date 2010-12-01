package org.advancedMathCalculator;


import org.advancedMathCalculator.parser.ExpressionType;

import junit.framework.TestCase;


public class ExpressionTypePrecedenceTest extends TestCase {

	public void testFunc()
	{
		assertEquals(0, ExpressionType.comparePrecedence(ExpressionType.COS, ExpressionType.COS));
		assertEquals(0, ExpressionType.comparePrecedence(ExpressionType.COS, ExpressionType.COSH));
		assertEquals(0, ExpressionType.comparePrecedence(ExpressionType.LN, ExpressionType.COS));
	}
	
	public void testOP()
	{
		assertEquals(-1, ExpressionType.comparePrecedence(ExpressionType.ADD, ExpressionType.COS));
		assertEquals(1,ExpressionType.comparePrecedence(ExpressionType.SIN, ExpressionType.DIVIDE));
		assertEquals(0,ExpressionType.comparePrecedence(ExpressionType.ADD, ExpressionType.ADD));
		assertEquals(-1,ExpressionType.comparePrecedence(ExpressionType.ADD, ExpressionType.SUBTRACT));
		assertEquals(0,ExpressionType.comparePrecedence(ExpressionType.DIVIDE, ExpressionType.MULTIPLY));
		assertEquals(-1,ExpressionType.comparePrecedence(ExpressionType.ADD, ExpressionType.MULTIPLY));
		assertEquals(1,ExpressionType.comparePrecedence(ExpressionType.DIVIDE, ExpressionType.ADD));
	}
	
	public void testParans()
	{
		assertEquals(-1, ExpressionType.comparePrecedence(ExpressionType.ADD, ExpressionType.LEFTPAREN));
		assertEquals(0, ExpressionType.comparePrecedence(ExpressionType.RIGHTPAREN, ExpressionType.LEFTPAREN));
		assertEquals(1, ExpressionType.comparePrecedence(ExpressionType.RIGHTPAREN, ExpressionType.MULTIPLY));
		assertEquals(1, ExpressionType.comparePrecedence(ExpressionType.LEFTPAREN, ExpressionType.POW));
	}
}
