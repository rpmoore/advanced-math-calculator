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
import org.advancedMathCalculator.parser.generators.RPNGenerator;
import org.advancedMathCalculator.parser.generators.TreeGenerator;
import org.advancedMathCalculator.ui.components.EquationTextInput;
import org.apache.pivot.beans.Bindable;
import org.apache.pivot.collections.Map;
import org.apache.pivot.util.Resources;
import org.apache.pivot.wtk.Button;
import org.apache.pivot.wtk.ButtonPressListener;
import org.apache.pivot.wtk.Component;
import org.apache.pivot.wtk.ComponentKeyListener;
import org.apache.pivot.wtk.Keyboard;
import org.apache.pivot.wtk.Keyboard.KeyLocation;
import org.apache.pivot.wtk.Label;
import org.apache.pivot.wtk.ListButton;
import org.apache.pivot.wtk.ListButtonSelectionListener;
import org.apache.pivot.wtk.MessageType;
import org.apache.pivot.wtk.Prompt;
import org.apache.pivot.wtk.PushButton;
import org.apache.pivot.wtk.TablePane;
import org.apache.pivot.wtk.TextInput;

public class DefIntegralPanel extends TablePane implements Bindable{

	private ParserGenerator calcMethodGen = null;
	private Calculate calcMethod = null;
	
	private EquationTextInput def_equation = null;
	private TextInput def_upperBound = null;
	private TextInput def_lowerBound = null;
	private PushButton def_Button = null;
	private Label resultVal = null;
	

	public void initialize(Map<String, Object> arg0, URL arg1, Resources arg2) {
		final ListButton listButton = (ListButton) arg0.get("listButton");
		def_equation = (EquationTextInput) arg0.get("def_equation");
		def_upperBound = (TextInput) arg0.get("upperBound");
		def_lowerBound = (TextInput) arg0.get("lowerBound");
		resultVal = (Label) arg0.get("resultVal");
		def_Button = (PushButton) arg0.get("defIntEval");
		def_upperBound.getComponentKeyListeners().add(new DefIntKeyboardListener());
		def_lowerBound.getComponentKeyListeners().add(new DefIntKeyboardListener());
		def_equation.getComponentKeyListeners().add(new DefIntKeyboardListener());
		def_Button.getButtonPressListeners().add(new ButtonPressListener() {
			
			public void buttonPressed(Button arg0) {
				processIntegral();
			}
		});
		listButton.getListButtonSelectionListeners().add(new ListButtonSelectionListener.Adapter() {
			@Override
            public void selectedItemChanged(ListButton listButton, Object previousSelectedItem) {
				processRepresentationChange(listButton);
			}
		});
		listButton.setSelectedIndex(0);
		
	}
	
	private void processRepresentationChange(ListButton listButton)
	{
		Object selectedItem= listButton.getSelectedItem();
		
		if(selectedItem != null && selectedItem instanceof String)
		{
			String representation = (String)selectedItem;
			
			if(representation.equalsIgnoreCase("stack"))
			{
				calcMethodGen = new RPNGenerator();
			}
			else if(representation.equalsIgnoreCase("tree"))
			{
				calcMethodGen = new TreeGenerator();
			}
			else
			{
				Prompt.prompt(MessageType.ERROR, "Unknown calculation backend.", getWindow());
			}
		}
		else
		{
			Prompt.prompt(MessageType.ERROR,"Fatal Error in processing calculation backend.",getWindow());
		}
	}
	
	private void processIntegral() {
		boolean valid = false;
		double lower, upper;
		resultVal.setText("");
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
				resultVal.setText("The answer to 'f(x)="
						+ def_equation.getText()
						+ "' is: "
						+ SimpsonsRule
						.compute(calcMethod, lower, upper));
				valid = true;
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
			def_equation.addExpression();
			def_equation.setCurrentValid(valid);
		}
	}
	
	private class DefIntKeyboardListener implements ComponentKeyListener
	{

		public boolean keyPressed(Component arg0, int arg1, KeyLocation arg2) {
				
			return false;
		}

		public boolean keyReleased(Component arg0, int arg1, KeyLocation arg2) {
			return false;
		}

		public boolean keyTyped(Component arg0, char arg1) {
			if(arg1 == Keyboard.KeyCode.ENTER)
			{
				processIntegral();
			}
			return false;
		}
		
	}

}