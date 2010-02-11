package graph;

import org.apache.pivot.wtk.media.drawing.Canvas;

import defIntegral.Calculate;

public class Graph <T extends Calculate> extends Canvas {
	
	private T equation;
	
	public Graph(T equation)
	{
		this.equation = equation;
		
	}

}
