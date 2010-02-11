package graph;

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
public class IntegralGraph<T extends Calculate> extends Graph {
	
	private double left;
	private double right;
	
	@SuppressWarnings("unchecked")
	public IntegralGraph(T equation,double left, double right)
	{
		super(equation);
		this.left = left;
		this.right = right;
	}

}
