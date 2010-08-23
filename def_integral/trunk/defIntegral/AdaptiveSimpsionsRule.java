package defIntegral;

public final class AdaptiveSimpsionsRule {
	public static <T extends Calculate> double compute(T toCompute, double a, double b)
	{
		double midpoint = (a + b)/2.0;
		double start = (b - a)/6.0;
		double end = toCompute.eval(a)+4.0*toCompute.eval(midpoint) + toCompute.eval(b);
		
		return (start*end);
	}
}
