package defIntegral;

/**
 * A basic implementation of Simpsion's rule.
 * @author Ryan
 *
 */
public class SimpsonsRule {
	public static <T extends Calculate> double compute(T toCompute, double a, double b)
	{
		double midpoint = (a + b)/2;
		double start = (b - a)/6;
		double end = toCompute.eval(a)+4*toCompute.eval(midpoint) + toCompute.eval(b);
		
		return (start*end);
	}
}
