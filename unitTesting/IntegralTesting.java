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

import org.apache.log4j.Logger;

import parser.ParseTree;
import parser.RPN;
import defIntegral.Calculate;
import defIntegral.CalculateException;
import defIntegral.SimpsonsRule;

public class IntegralTesting extends TestCase {
	private static final Logger logger = Logger.getLogger(IntegralTesting.class);
	public static junit.framework.Test suite() {
        return new junit.framework.JUnit4TestAdapter(IntegralTesting.class);
    }
	
	public void testEquivalentStatements() throws CalculateException
	{
		try {
			double result1 = SimpsonsRule.compute(ParseTree.generate("3*x^2", true), 2, 3);
			assertEquals(19.0,result1,0);
			result1 = SimpsonsRule.compute(ParseTree.generate("(3*x)^2", true), 2, 3);
			assertEquals(57.0,result1,0);
			result1 = SimpsonsRule.compute(ParseTree.generate("3x^2", true), 2, 3);
			assertEquals(19.0,result1,0);
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			assertTrue(false);
		}
		
	}
	
	public void testBasicSimpsionsRuleOne() throws CalculateException
	{
		Line2 line = new Line2();
		
		double answer = SimpsonsRule.compute(line,2,4);
		
		assertEquals(4,answer,0);
	}
	
	
	public void testBasicDiagnalSimpsionsRuleTwo() throws CalculateException
	{
		DiagnalLine line = new DiagnalLine();
		
		double answer = SimpsonsRule.compute(line, -.5, 4.5);
		
		assertEquals(122.5,answer,1);
		assertEquals(122.5,answer,0);
	}
	
	
	public void testIntegrationSimpsionsParseTree() throws CalculateException
	{		
		Calculate tree = null;
		try
		{
			tree = ParseTree.generate("12*x+.5", false);
		}
		catch (ParseException e) {
			e.printStackTrace();
		}	
		
		double answer = SimpsonsRule.compute(tree, .5, 5.5);		
		assertEquals(182.5,answer,1);
		assertEquals(182.5,answer,0);
	}
	
	public void testEquivalentStatementsRPN() throws CalculateException, ParseException
	{
			logger.debug("Beginning RPN eval tests.");
			double result1 = SimpsonsRule.compute(RPN.generate("3*x^2"), 2, 3);
			assertEquals(19.0,result1,0);
			result1 = SimpsonsRule.compute(RPN.generate("(3*x)^2"), 2, 3);
			assertEquals(57.0,result1,0);
			result1 = SimpsonsRule.compute(RPN.generate("3x^2"), 2, 3);
			assertEquals(19.0,result1,0);
		
	}
	
	public void testBasicSimpsionsRuleOneRPN() throws CalculateException
	{
		Line2 line = new Line2();
		
		double answer = SimpsonsRule.compute(line,2,4);
		
		assertEquals(4,answer,0);
	}
	
	
	public void testBasicDiagnalSimpsionsRuleTwoRPN() throws CalculateException
	{
		DiagnalLine line = new DiagnalLine();
		
		double answer = SimpsonsRule.compute(line, -.5, 4.5);
		
		assertEquals(122.5,answer,1);
		assertEquals(122.5,answer,0);
	}
	
	
	public void testIntegrationSimpsionsRPN() throws CalculateException
	{		
		Calculate expr = null;
		try
		{
			expr = RPN.generate("12*x+.5");
		}
		catch (ParseException e) {
			e.printStackTrace();
		}	
		
		double answer = SimpsonsRule.compute(expr, .5, 5.5);		
		assertEquals(182.5,answer,1);
		assertEquals(182.5,answer,0);
	}
}
