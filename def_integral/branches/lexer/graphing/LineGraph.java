package graphing;

import org.apache.pivot.wtk.media.drawing.Canvas;

import defIntegral.Calculate;

public class LineGraph <T extends Calculate> extends Canvas {
	
	private T equation;
	
	public LineGraph(T equation)
	{
		this.equation = equation;
		
	}

}
