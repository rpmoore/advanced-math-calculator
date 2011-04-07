package org.advancedMathCalculator.computation.defDerivative;

import org.advancedMathCalculator.computation.Calculate;
import org.advancedMathCalculator.computation.CalculateException;

/**
 * These numerical computations are taken from: 
 * Numerical Mehtods, Algortithms and Tools in C#
 * by Waldemar Dos Passos ISBN 978-0-8493-7479-1
 * 
 * The methods have been adapted to run in Java.
 * @author Ryan Moore
 *
 */
public class CentralDifference {

	public static double DerivativeOh4Central1(Calculate function, double x, double h) throws CalculateException
	{
		h=(h==0?0.01:h);
		return (function.eval(x-2*h)-8*function.eval(x-h)+8*function.eval(x+h)-function.eval(x+2*h))/12/h;
	}
}
