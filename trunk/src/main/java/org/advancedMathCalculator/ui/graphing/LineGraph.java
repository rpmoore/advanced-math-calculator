package org.advancedMathCalculator.ui.graphing;

/*
 * Copyright 2010 Ryan Moore
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *  
 */
import java.awt.Color;
import java.util.Iterator;

import org.advancedMathCalculator.defIntegral.Calculate;
import org.advancedMathCalculator.defIntegral.CalculateException;
import org.apache.pivot.collections.HashMap;
import org.apache.pivot.wtk.Bounds;
import org.apache.pivot.wtk.media.drawing.Canvas;
import org.apache.pivot.wtk.media.drawing.Ellipse;


public class LineGraph<T extends Calculate> extends Canvas {
	int leftBound, rightBound;

	HashMap<T, Color> equationList = null;

	public LineGraph() {
		equationList = new HashMap<T, Color>();
		leftBound = 0;
		rightBound = 0;
	}

	public void addEquation(T equation, Color color) {
		equationList.put(equation, color);
	}

	public void addPoint(int x) throws CalculateException {
		Ellipse point;
		final Iterator<T> iter = equationList.iterator();
		while (iter.hasNext()) {
			final T equation = iter.next();
			point = new Ellipse();
			point.setSize(3, 3);
			point.setX(x);
			point.setY((int) Math.round(equation.eval(x)));
			point.setStroke(equationList.get(equation));
			this.add(point);
		}
	}

	public void clear() {
		equationList.clear();
		clearShapes();
	}

	private void clearShapes() {
		this.remove(0, this.getLength());
	}

	public void generatePoints(double leftBound, double rightBound)
			throws CalculateException {
		clearShapes();
		// get the bounds of the canvas
		final Bounds canvasSize = getBounds();
		//assuming length is in pixels.

		final int intLeftBound = (int) Math.round(Math.floor(leftBound));
		final int intRightBound = (int) Math.round(Math.floor(rightBound));

		this.leftBound = intLeftBound;
		this.rightBound = intRightBound;

		for (int i = intLeftBound; i <= intRightBound; ++i) {
			addPoint(i);
		}
	}

	public Color removeEquation(T equation) throws CalculateException {
		final Color retValue = equationList.remove(equation);
		clearShapes();
		this.generatePoints(leftBound, rightBound);
		return retValue;
	}
}