package unitTesting;
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
import parser.RPN;


public class RPNTesting extends TestCase {

	public void testConstruction()
	{
		try
		{
		
			@SuppressWarnings("unused")
			RPN rpnExpr = new RPN("x+x");
			
			assertTrue(true);
		}
		catch(ParseException exception)
		{
			//if we got here we have a bad expression
			assertTrue(false);
		}
	
	}
	
	public void testEval1()
	{
		try {
			RPN rpnExpr = new RPN("2+3");
			assertTrue(true);
			assertEquals(5.0, rpnExpr.eval(0),0);//this should eval exactly.
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			assertTrue(false);
		}
		
	}
	
	public void testEval2()
	{
		try
		{
			RPN rpnExpr = new RPN("x");
			assertTrue(true);
			assertEquals(3, rpnExpr.eval(3),0);
		}
		catch(ParseException e)
		{
			assertTrue(false);
		}
	}
	public void testEval3()
	{
		try
		{
			RPN rpnExpr = new RPN("x+1");
			assertTrue(true);
			assertEquals(4, rpnExpr.eval(3),0);
		}
		catch(ParseException e)
		{
			assertTrue(false);
		}
	}
	public void testEval4()
	{
		try
		{
			RPN rpnExpr = new RPN("x*2");
			assertTrue(true);
			assertEquals(6, rpnExpr.eval(3),0);
		}
		catch(ParseException e)
		{
			assertTrue(false);
		}
	}
	public void testEval5()
	{
		try
		{
			RPN rpnExpr = new RPN("5-x");
			assertTrue(true);
			assertEquals(2, rpnExpr.eval(3),0);
		}
		catch(ParseException e)
		{
			assertTrue(false);
		}
	}
	public void testEval6()
	{
		try
		{
			RPN rpnExpr = new RPN("x+2*5");
			assertTrue(true);
			assertEquals(12, rpnExpr.eval(2),0);
		}
		catch(ParseException e)
		{
			assertTrue(false);
		}
	}
}
