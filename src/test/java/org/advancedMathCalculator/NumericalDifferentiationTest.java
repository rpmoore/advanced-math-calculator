package org.advancedMathCalculator;

import java.io.StringReader;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.advancedMathCalculator.computation.CalculateException;
import org.advancedMathCalculator.computation.defDerivative.CentralDifference;
import org.advancedMathCalculator.parser.cc.EquationParserCC;
import org.advancedMathCalculator.parser.cc.ParseException;
import org.advancedMathCalculator.parser.cc.RPNCC;


public class NumericalDifferentiationTest extends TestCase {

	public void testDerivative() throws ParseException, CalculateException
	{
		RPNCC equation = new RPNCC(new EquationParserCC(new StringReader("x^2")).parseEquation());
		Assert.assertEquals(4.0, CentralDifference.DerivativeOh4Central1(equation, 2, 0.1),0.0000000000001);
		
	}
}
