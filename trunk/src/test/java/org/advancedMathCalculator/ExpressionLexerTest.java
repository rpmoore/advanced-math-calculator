package org.advancedMathCalculator;


import org.advancedMathCalculator.parser.ExpressionLexer;

import junit.framework.TestCase;


public class ExpressionLexerTest extends TestCase{

	public void testMult()
	{
		ExpressionLexer lex = new ExpressionLexer("2*3");
		assertEquals("2", lex.nextElement().getToken());
		assertEquals("*",lex.nextElement().getToken());
		assertEquals("3",lex.nextElement().getToken());
	}
	
	public void testImplicitMult()
	{
		ExpressionLexer lex = new ExpressionLexer("3x");
		assertEquals("3", lex.nextElement().getToken());
		assertEquals("*", lex.nextElement().getToken());
		assertEquals("x", lex.nextElement().getToken());
	}
	
	public void testImplicitSub()
	{
		ExpressionLexer lex = new ExpressionLexer("3-x");
		assertEquals("3", lex.nextElement().getToken());
		assertEquals("+", lex.nextElement().getToken());
		assertEquals("-", lex.nextElement().getToken());
		assertEquals("x", lex.nextElement().getToken());
	}
}
