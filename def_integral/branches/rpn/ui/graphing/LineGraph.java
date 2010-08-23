package ui.graphing;

import java.awt.Color;
import java.util.Iterator;

import org.apache.pivot.collections.HashMap;
import org.apache.pivot.wtk.media.drawing.Canvas;
import org.apache.pivot.wtk.media.drawing.Ellipse;

import defIntegral.Calculate;

public class LineGraph <T extends Calculate> extends Canvas {
	int leftBound, rightBound;
	
	HashMap<T,Color> equationList = null;
	
	public LineGraph()
	{
		equationList = new HashMap<T,Color>();
		leftBound = 0;
		rightBound = 0;
	}
	
	public void addEquation(T equation, Color color)
	{
		equationList.put(equation, color);
	}
	
	public Color removeEquation(T equation)
	{
		Color retValue = equationList.remove(equation);		
		clearShapes();
		this.generatePoints(leftBound, rightBound);
		return retValue;
	}
	
	
	private void clearShapes()
	{
		this.remove(0, this.getLength());		
	}
	
	public void clear()
	{
		equationList.clear();
		clearShapes();
	}
	
	public void generatePoints(double leftBound, double rightBound)
	{
		clearShapes();
		int intLeftBound = (int) Math.round(Math.floor(leftBound));
		int intRightBound = (int)Math.round(Math.floor(rightBound));
		
		this.leftBound = intLeftBound;
		this.rightBound = intRightBound;
		
		for(int i = intLeftBound; i <= intRightBound; ++i)
		{
			addPoint(i);
		}
	}
	
	public void addPoint(int x)
	{
		Ellipse point;
		Iterator<T> iter = equationList.iterator();
		while(iter.hasNext())
		{
			T equation = iter.next();
			point = new Ellipse();
			point.setSize(3,3);
			point.setX(x);
			point.setY((int)Math.round(equation.eval(x)));
			point.setStroke(equationList.get(equation));
			this.add(point);
		}
	}
}