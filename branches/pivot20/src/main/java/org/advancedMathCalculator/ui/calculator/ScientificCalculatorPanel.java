package org.advancedMathCalculator.ui.calculator;

import java.net.URL;
import java.text.ParseException;

import org.advancedMathCalculator.defIntegral.CalculateException;
import org.advancedMathCalculator.parser.generators.RPNGenerator;
import org.advancedMathCalculator.ui.EquationTextInput;
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
import org.apache.pivot.wtk.MessageType;
import org.apache.pivot.wtk.Prompt;
import org.apache.pivot.wtk.PushButton;
import org.apache.pivot.wtk.TablePane;

public class ScientificCalculatorPanel extends TablePane implements Bindable {

	private EquationTextInput calc_equation;
	private Label returnLabel;
	
	public void initialize(Map<String, Object> arg0, URL arg1, Resources arg2) {
	calc_equation = (EquationTextInput) arg0.get("sciCalcExpr");
	returnLabel = (Label) arg0.get("returnLabel");
	calc_equation.getComponentKeyListeners().add(new ComponentKeyListener() {
		
		public boolean keyTyped(Component arg0, char arg1) {
			if(arg1 == Keyboard.KeyCode.ENTER)
			{
				processCalc();
			}
			return false;
		}
		
		public boolean keyReleased(Component arg0, int arg1, KeyLocation arg2) {
			return false;
		}
		
		public boolean keyPressed(Component arg0, int arg1, KeyLocation arg2) {
			return false;
		}
	});
		
	final PushButton button = (PushButton) arg0.get("sciCalcButton");
	button.getButtonPressListeners().add(new ButtonPressListener() {
		
		public void buttonPressed(Button arg0) {
			processCalc();
		}
	});
	}
	
	
	private void processCalc()
	{		
		boolean valid = false;
		//check equation to make sure there are no x's in the equation.
		if(calc_equation.getText().contains("x"))
		{
			Prompt.prompt(MessageType.ERROR,"There cannot be an x in the equation at index: " + calc_equation.getText().indexOf('x'),this.getWindow());
			returnLabel.setText("");
		}
		else
		{
			try {
				returnLabel.setText("The answer to 'f(x) = " + calc_equation.getText() + "' is: " + (new RPNGenerator().generate(calc_equation.getText()).eval(0)));
				valid = true;
			} catch (ParseException e) {
				Prompt.prompt(MessageType.ERROR, e.getMessage()
						+ " at position " + e.getErrorOffset() + ".", this.getWindow());
				returnLabel.setText("");
			} catch (CalculateException e) {
				Prompt.prompt(MessageType.ERROR, e.getMessage(), this.getWindow());
				returnLabel.setText("");
			}
		}
		calc_equation.addExpression();
		calc_equation.setCurrentValid(valid);
	}
}
