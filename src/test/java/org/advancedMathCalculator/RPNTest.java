package org.advancedMathCalculator;

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

import junit.framework.TestCase;

import org.advancedMathCalculator.defIntegral.CalculateException;
import org.advancedMathCalculator.parser.RPN;


public class RPNTest extends TestCase {

	public void testConstruction()
	{
		try
		{
			@SuppressWarnings("unused")
			RPN rpnExpr = RPN.generate("x+x");

			assertTrue(true);
		}
		catch(ParseException exception)
		{
			//if we got here we have a bad expression
			assertTrue(false);
		}

	}

	public void testEval1() throws CalculateException, ParseException
	{
		RPN rpnExpr = RPN.generate("2+3");
		assertTrue(true);
		assertEquals(5.0, rpnExpr.eval(0),0);//this should eval exactly.

	}

	public void testEval2() throws CalculateException
	{
		try
		{
			RPN rpnExpr = RPN.generate("x");
			assertTrue(true);
			assertEquals(3, rpnExpr.eval(3),0);
		}
		catch(ParseException e)
		{
			assertTrue(false);
		}
	}
	public void testEval3() throws CalculateException
	{
		try
		{
			RPN rpnExpr = RPN.generate("x+1");
			assertTrue(true);
			assertEquals(4, rpnExpr.eval(3),0);
		}
		catch(ParseException e)
		{
			assertTrue(false);
		}
	}
	public void testEval4() throws CalculateException
	{
		try
		{
			RPN rpnExpr = RPN.generate("x*2");
			assertTrue(true);
			assertEquals(6, rpnExpr.eval(3),0);
		}
		catch(ParseException e)
		{
			assertTrue(false);
		}
	}
	public void testEval5() throws CalculateException
	{
		try
		{
			RPN rpnExpr = RPN.generate("5-x");
			assertTrue(true);
			assertEquals(2, rpnExpr.eval(3),0);
		}
		catch(ParseException e)
		{
			assertTrue(false);
		}
	}
	public void testEval6() throws CalculateException
	{
		try
		{
			RPN rpnExpr = RPN.generate("x+2*5");
			assertTrue(true);
			assertEquals(12, rpnExpr.eval(2),0);
		}
		catch(ParseException e)
		{
			assertTrue(false);
		}
	}
	public void testEval7() throws CalculateException, ParseException
	{

		RPN rpnExpr = RPN.generate("x+2*5");
		assertTrue(true);
		assertEquals(12, rpnExpr.eval(2),0);
		assertEquals(11, rpnExpr.eval(1),0);
		assertEquals(10, rpnExpr.eval(0),0);
		assertEquals(9, rpnExpr.eval(-1),0);
	}
	public void testEval8() throws ParseException, CalculateException
	{

		RPN	rpnExpr = RPN.generate("(12-8)^2/2");


		assertEquals(8, rpnExpr.eval(0), 0);
	}

	public void testEval9() throws ParseException, CalculateException
	{

		RPN rpnExpr = RPN.generate("-2+3/x");		
		assertEquals(-1.0, rpnExpr.eval(3),0);
	}


	public void testEval10() throws ParseException, CalculateException
	{
		RPN rpnExpr = RPN.generate("12x + 4");
		assertEquals(16.0, rpnExpr.eval(1),0);
	}



	public void testEval11() throws ParseException, CalculateException
	{
		RPN rpnExpr = RPN.generate("3pix");
		assertEquals(3 * Math.PI * 2, rpnExpr.eval(2),0);
	}

	public void testEval12() throws ParseException, CalculateException
	{
		RPN rpnExpr = RPN.generate("2+(-3+x)");
		assertEquals(2+(-3+1),rpnExpr.eval(1),0);
	}

	public void testEval13() throws ParseException, CalculateException
	{
		RPN rpnExpr = RPN.generate("2+(-3x)");
		assertEquals(2+(-3*2),rpnExpr.eval(2),0);
	}
	public void testEval14() throws ParseException, CalculateException
	{
		RPN rpnExpr = RPN.generate("2*-3x");
		assertEquals(2*-3*2, rpnExpr.eval(2),0);
	}
	public void testEval15() throws ParseException, CalculateException
	{
		RPN rpnExpr = RPN.generate("2+(2*-3x)");
		assertEquals(2+(2*-3*2),rpnExpr.eval(2),0);
	}
	public void testEval16() throws ParseException, CalculateException
	{
		RPN rpnExpr = RPN.generate("2+(2*(-3x))");
		assertEquals(2+(2*(-3*2)),rpnExpr.eval(2),0);
	}

	public void testEval17() throws ParseException, CalculateException
	{
		RPN rpnExpr = RPN.generate("ln(x)");
		assertEquals(1,rpnExpr.eval(Math.E),0);
	}
}
