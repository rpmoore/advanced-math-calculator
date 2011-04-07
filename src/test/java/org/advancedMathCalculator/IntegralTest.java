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
import java.io.StringReader;

import junit.framework.TestCase;

import org.advancedMathCalculator.computation.Calculate;
import org.advancedMathCalculator.computation.CalculateException;
import org.advancedMathCalculator.computation.defIntegral.SimpsonsRule;
import org.advancedMathCalculator.parser.cc.EquationParserCC;
import org.advancedMathCalculator.parser.cc.ParseException;
import org.advancedMathCalculator.parser.cc.RPNCC;

public class IntegralTest extends TestCase {
	public static junit.framework.Test suite() {
		return new junit.framework.JUnit4TestAdapter(IntegralTest.class);
	}

	public void testBasicDiagnalSimpsionsRuleTwo() throws CalculateException {
		final DiagnalLine line = new DiagnalLine();

		final double answer = SimpsonsRule.compute(line, -.5, 4.5);

		assertEquals(122.5, answer, 1);
		assertEquals(122.5, answer, 0);
	}

	public void testBasicDiagnalSimpsionsRuleTwoRPN() throws CalculateException {
		final DiagnalLine line = new DiagnalLine();

		final double answer = SimpsonsRule.compute(line, -.5, 4.5);

		assertEquals(122.5, answer, 1);
		assertEquals(122.5, answer, 0);
	}

	public void testBasicSimpsionsRuleOne() throws CalculateException {
		final Line2 line = new Line2();

		final double answer = SimpsonsRule.compute(line, 2, 4);

		assertEquals(4, answer, 0);
	}

	public void testBasicSimpsionsRuleOneRPN() throws CalculateException {
		final Line2 line = new Line2();

		final double answer = SimpsonsRule.compute(line, 2, 4);

		assertEquals(4, answer, 0);
	}

	public void testEquivalentStatements() throws CalculateException, ParseException {
			double result1 = SimpsonsRule.compute(
					new RPNCC(new EquationParserCC(new StringReader("3*x^2")).parseEquation()), 2, 3);
			assertEquals(19.0, result1, 0);
			result1 = SimpsonsRule.compute(new RPNCC(new EquationParserCC(new StringReader("(3*x)^2")).parseEquation()),
					2, 3);
			assertEquals(57.0, result1, 0);
			result1 = SimpsonsRule.compute(new RPNCC(new EquationParserCC(new StringReader("3x^2")).parseEquation()), 2,
					3);
			assertEquals(19.0, result1, 0);
	}

	public void testIntegrationSimpsionsParsefunction() throws CalculateException, ParseException {
		Calculate function = new RPNCC(new EquationParserCC(new StringReader("12*x+.5")).parseEquation());


		final double answer = SimpsonsRule.compute(function, .5, 5.5);
		assertEquals(182.5, answer, 1);
		assertEquals(182.5, answer, 0);
	}

	public void testIntegrationSimpsionsRPN() throws CalculateException, ParseException {
		Calculate expr = new RPNCC(new EquationParserCC(new StringReader("12*x+.5")).parseEquation());

		final double answer = SimpsonsRule.compute(expr, .5, 5.5);
		assertEquals(182.5, answer, 1);
		assertEquals(182.5, answer, 0);
	}
}