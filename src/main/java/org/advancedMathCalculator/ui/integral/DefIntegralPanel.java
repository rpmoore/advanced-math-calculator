package org.advancedMathCalculator.ui.integral;

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

import java.net.URL;
import java.text.ParseException;
import java.util.EmptyStackException;

import org.advancedMathCalculator.defIntegral.Calculate;
import org.advancedMathCalculator.defIntegral.CalculateException;
import org.advancedMathCalculator.defIntegral.SimpsonsRule;
import org.advancedMathCalculator.parser.generators.ParserGenerator;
import org.advancedMathCalculator.ui.EquationTextInput;
import org.apache.pivot.beans.Bindable;
import org.apache.pivot.collections.Map;
import org.apache.pivot.util.Resources;
import org.apache.pivot.wtk.ListButton;
import org.apache.pivot.wtk.MessageType;
import org.apache.pivot.wtk.Prompt;
import org.apache.pivot.wtk.PushButton;
import org.apache.pivot.wtk.TablePane;
import org.apache.pivot.wtk.TextInput;
import org.apache.pivot.wtk.Window;

public class DefIntegralPanel extends TablePane implements Bindable{

	private ParserGenerator calcMethodGen = null;
	private Calculate calcMethod = null;
	
	private EquationTextInput def_equation = null;
	private TextInput def_upperBound = null;
	private TextInput def_lowerBound = null;
	private PushButton def_Button = null;
	private ListButton listButton = null;
	private PushButton calc_Button = null;
	private EquationTextInput calc_equation = null;

	

	private void processIntegral() {
		// graph.clear();
		double lower, upper;
		if (def_equation.getText().isEmpty()) {
			Prompt.prompt(MessageType.ERROR,
					"The definite integral equation is empty.", this.getWindow());
		} else if (def_lowerBound.getText().isEmpty()) {
			Prompt.prompt(MessageType.ERROR,
					"The definite integral lower bound is empty.", this.getWindow());
		} else if (def_upperBound.getText().isEmpty()) {
			Prompt.prompt(MessageType.ERROR,
					"The definite integral upper bound is empty.", this.getWindow());
		} else {
			// check bounds, make sure they are doubles.
			try {
				lower = Double.parseDouble(def_lowerBound.getText());
			} catch (final NumberFormatException e) {
				return;
			}
			try {
				upper = Double.parseDouble(def_upperBound.getText());
			} catch (final NumberFormatException e) {
				return;
			}
			try {
				calcMethod = calcMethodGen.generate(def_equation.getText());
				// graph.addEquation(pTree, Color.RED);
				// graph.generatePoints(lower, upper);
				Prompt.prompt(
						MessageType.INFO,
						"The answer to 'f(x)="
						+ def_equation.getText()
						+ "' is: "
						+ SimpsonsRule
						.compute(calcMethod, lower, upper),
						this.getWindow());
			} catch (final ParseException e) {
				Prompt.prompt(MessageType.ERROR, e.getMessage()
						+ " at position " + e.getErrorOffset() + ".", this.getWindow());
			} catch (final CalculateException e) {
				Prompt.prompt(MessageType.ERROR, e.getMessage(), this.getWindow());
			} catch (final EmptyStackException e) {
				Prompt.prompt(MessageType.ERROR, e.getMessage(), this.getWindow());
			} catch (final NullPointerException e) {
				Prompt.prompt(MessageType.ERROR,
						"Please select a Calculation Type", this.getWindow());
			}

		}
	}
	
	public void initialize(Map<String, Object> arg0, URL arg1, Resources arg2) {
		System.out.println("Started DefIntegral Panel");
		
	}

}
