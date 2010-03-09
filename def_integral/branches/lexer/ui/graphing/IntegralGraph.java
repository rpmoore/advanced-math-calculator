package ui.graphing;

import org.apache.pivot.wtk.media.drawing.Canvas;

import defIntegral.Calculate;

/**
 *Creates a graph of the integral passed into it.  Automaticlly sets bounds, and allows for
 *scrolling of the graph and printing out values.
 * 
 * @author Ryan
 *
 * @param <T> An equation to graph the integral of.
 */
public class IntegralGraph<T extends Calculate> extends LineGraph<T> {
	
	private double left;
	private double right;
	
	/**
	 * 
	 * @param equation
	 * @param left
	 * @param right
	 */
	public IntegralGraph(double left, double right)
	{
		this.left = left;
		this.right = right;
	}
	
	/**
	 * Creates a graph without the area filled in for the integral.  Would be the same as
	 * creating a LineGraph.
	 * @param equation
	 */
	public IntegralGraph(T equation)
	{
		this(0,0);
	}
}
