package org.advancedMathCalculator.ui;

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
//import java.awt.Color;
import java.text.ParseException;
import java.util.EmptyStackException;

import org.advancedMathCalculator.defIntegral.Calculate;
import org.advancedMathCalculator.defIntegral.CalculateException;
import org.advancedMathCalculator.defIntegral.SimpsonsRule;
import org.advancedMathCalculator.parser.generators.ParserGenerator;
import org.advancedMathCalculator.parser.generators.RPNGenerator;
import org.advancedMathCalculator.parser.generators.TreeGenerator;
import org.apache.pivot.collections.Map;
import org.apache.pivot.wtk.Application;
import org.apache.pivot.wtk.Button;
import org.apache.pivot.wtk.ButtonPressListener;
import org.apache.pivot.wtk.Component;
import org.apache.pivot.wtk.ComponentKeyListener;
import org.apache.pivot.wtk.DesktopApplicationContext;
import org.apache.pivot.wtk.Display;
import org.apache.pivot.wtk.Keyboard;
import org.apache.pivot.wtk.Keyboard.KeyLocation;
import org.apache.pivot.wtk.ListButton;
import org.apache.pivot.wtk.ListButtonSelectionListener;
import org.apache.pivot.wtk.MessageType;
import org.apache.pivot.wtk.Prompt;
import org.apache.pivot.wtk.PushButton;
import org.apache.pivot.wtk.TextInput;
import org.apache.pivot.wtk.Window;
import org.apache.pivot.wtkx.WTKXSerializer;

public class PMain implements Application, ButtonPressListener,
ComponentKeyListener, ListButtonSelectionListener {

	// private LineGraph<ParseTree> graph = null;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DesktopApplicationContext.main(PMain.class, args);
	}

	private ParserGenerator calcMethodGen = null;
	private Calculate calcMethod = null;
	private Window window = null;
	private EquationTextInput def_equation = null;
	private TextInput def_upperBound = null;
	private TextInput def_lowerBound = null;
	private PushButton def_Button = null;
	private ListButton listButton = null;
	private PushButton calc_Button = null;
	private EquationTextInput calc_equation = null;
	public void buttonPressed(Button arg0) {
		processIntegral();
	}
	

	public boolean keyPressed(Component arg0, int arg1, KeyLocation arg2) {
		if (arg1 == Keyboard.KeyCode.ENTER) {
			processIntegral();
		}

		return true;
	}

	public boolean keyReleased(Component arg0, int arg1, KeyLocation arg2) {
		return false;
	}

	public boolean keyTyped(Component arg0, char arg1) {
		return false;
	}

	private void processIntegral() {
		// graph.clear();
		double lower, upper;
		if (def_equation.getText().isEmpty()) {
			Prompt.prompt(MessageType.ERROR,
					"The definite integral equation is empty.", window);
		} else if (def_lowerBound.getText().isEmpty()) {
			Prompt.prompt(MessageType.ERROR,
					"The definite integral lower bound is empty.", window);
		} else if (def_upperBound.getText().isEmpty()) {
			Prompt.prompt(MessageType.ERROR,
					"The definite integral upper bound is empty.", window);
		} else {
			// check bounds, make sure they are doubles.
			try {
				lower = Double.parseDouble(def_lowerBound.getText());
			} catch (final NumberFormatException e) {
				Prompt.prompt(
						MessageType.ERROR,
						"The definite integral lower bound ("
						+ def_lowerBound.getText()
						+ ") is not a number.", window);
				return;
			}
			try {
				upper = Double.parseDouble(def_upperBound.getText());
			} catch (final NumberFormatException e) {
				Prompt.prompt(
						MessageType.ERROR,
						"The definite integral upper bound ("
						+ def_upperBound.getText()
						+ ") is not a number.", window);
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
						window);
			} catch (final ParseException e) {
				Prompt.prompt(MessageType.ERROR, e.getMessage()
						+ " at position " + e.getErrorOffset() + ".", window);
			} catch (final CalculateException e) {
				Prompt.prompt(MessageType.ERROR, e.getMessage(), window);
			} catch (final EmptyStackException e) {
				Prompt.prompt(MessageType.ERROR, e.getMessage(), window);
			} catch (final NullPointerException e) {
				Prompt.prompt(MessageType.ERROR,
						"Please select a Calculation Type", window);
			}

		}
	}

	private void processCalc()
	{		
		boolean valid = false;
		//check equation to make sure there are no x's in the equation.
		if(calc_equation.getText().contains("x"))
		{
			Prompt.prompt(MessageType.ERROR,"There cannot be an x in the equation at index: " + calc_equation.getText().indexOf('x'), window);
		}
		else
		{
			try {
				Prompt.prompt("The answer to 'f(x) = " + calc_equation.getText() + "' is: " + (new RPNGenerator().generate(calc_equation.getText()).eval(0)), window);
				valid = true;
			} catch (ParseException e) {
				Prompt.prompt(MessageType.ERROR, e.getMessage()
						+ " at position " + e.getErrorOffset() + ".", window);
			} catch (CalculateException e) {
				Prompt.prompt(MessageType.ERROR, e.getMessage(), window);
			}
		}
		calc_equation.addExpression();
		calc_equation.setCurrentValid(valid);
	}

	public void resume() throws Exception {
	}

	public void selectedIndexChanged(ListButton list, int previousIndex) {
		final int index = list.getSelectedIndex();
		if (index != previousIndex) {
			final String item = (String) list.getSelectedItem();
			if (item.equals("Stack")) {
				calcMethodGen = new RPNGenerator();
			} else if (item.equals("Tree")) {
				calcMethodGen = new TreeGenerator();
			} else {
				// Unknown calc type.
				Prompt.prompt(MessageType.ERROR,
						"Invalid Calculation Type. Please select again.",
						window);
			}
		}
	}

	public boolean shutdown(boolean arg0) throws Exception {
		if (window != null) {
			window.close();
		}
		return false;
	}

	public void startup(Display display, Map<String, String> arg1)
	throws Exception {
		final WTKXSerializer wtkxSerializer = new WTKXSerializer();
		window = (Window) wtkxSerializer
		.readObject(this, "hello.wtkx");
		def_equation = (EquationTextInput) wtkxSerializer.get("def_equation");
		def_equation.getComponentKeyListeners().add(this);
		def_upperBound = (TextInput) wtkxSerializer.get("def_upperBound");
		def_upperBound.getComponentKeyListeners().add(this);
		def_lowerBound = (TextInput) wtkxSerializer.get("def_lowerBound");
		def_lowerBound.getComponentKeyListeners().add(this);
		def_Button = (PushButton) wtkxSerializer.get("def_solve");
		def_Button.getButtonPressListeners().add(this);
		def_Button.getComponentKeyListeners().add(this);
		listButton = (ListButton) wtkxSerializer.get("calcType");
		listButton.getListButtonSelectionListeners().add(this);
		listButton.setSelectedIndex(0);
		// LineGraph<ParseTree> lineGraph = (LineGraph<ParseTree>)
		// wtkxSerializer.get("graph");
		// graph = lineGraph;

		calc_equation = (EquationTextInput) wtkxSerializer.get("sciCalcExpr");
		calc_equation.getComponentKeyListeners().add(new ComponentKeyListener() {

			public boolean keyTyped(Component arg0, char arg1) {
				if (arg1 == Keyboard.KeyCode.ENTER) {
					processCalc();
				}
				return true;
			}

			public boolean keyReleased(Component arg0, int arg1, KeyLocation arg2) {
				return false;
			}

			public boolean keyPressed(Component arg0, int arg1, KeyLocation arg2) {
				return false;
			}
		});
		calc_Button = (PushButton) wtkxSerializer.get("sciCalcButton");
		calc_Button.getComponentKeyListeners().add(new ComponentKeyListener() {

			public boolean keyTyped(Component arg0, char arg1) {
				if(arg1 == Keyboard.KeyCode.ENTER)
				{
					processCalc();
					
				}
				return true;
			}

			public boolean keyReleased(Component arg0, int arg1, KeyLocation arg2) {
				return false;
			}

			public boolean keyPressed(Component arg0, int arg1, KeyLocation arg2) {
				return false;
			}
		});
		calc_Button.getButtonPressListeners().add(new ButtonPressListener() {

			public void buttonPressed(Button arg0) {
				processCalc();
			}
		});
		window.open(display);
	}

	public void suspend() throws Exception {
	}
}
