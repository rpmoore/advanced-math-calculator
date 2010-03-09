package graphing;

import org.apache.pivot.collections.LinkedList;
import org.apache.pivot.wtk.media.drawing.Canvas;

import defIntegral.Calculate;

public class LineGraph <T extends Calculate> extends Canvas {
	
	LinkedList<T> equationList = null;
	
	public LineGraph()
	{
		equationList = new LinkedList<T>();
	}
	
	public void addEquation(T equation)
	{
		equationList.add(equation);
	}
	
	public int removeEquation(T equation)
	{
		return equationList.remove(equation);
	}
	
	public T removeEquation(int i)
	{
		return equationList.remove(i,1).get(0);
	}
	
	public void clear()
	{
		equationList.clear();
	}
	
	public void drawGraph()
	{
		
	}
	
	public void drawPoint(int x)
	{
		
	}
	
}