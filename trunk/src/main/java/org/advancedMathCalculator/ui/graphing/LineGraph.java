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

import org.advancedMathCalculator.defIntegral.Calculate;
import org.apache.pivot.collections.HashMap;

import org.apache.pivot.wtk.Component;



public class LineGraph<T extends Calculate> extends Component {
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


	
}