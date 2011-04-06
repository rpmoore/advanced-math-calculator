package org.advancedMathCalculator.ui.calculator;

import java.io.StringReader;
import java.net.URL;
import java.text.ParseException;

import org.advancedMathCalculator.computation.CalculateException;
import org.advancedMathCalculator.parser.cc.EquationParserCC;
import org.advancedMathCalculator.parser.cc.RPNCC;
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
import org.apache.pivot.wtk.MessageType;
import org.apache.pivot.wtk.Prompt;
import org.apache.pivot.wtk.PushButton;
import org.apache.pivot.wtk.TablePane;
import org.apache.pivot.wtk.validation.Validator;

public class ScientificCalculatorPanel extends TablePane implements Bindable {

	private EquationTextInput calc_equation;
	private Label returnLabel;
	
	public void initialize(Map<String, Object> arg0, URL arg1, Resources arg2) {
	calc_equation = (EquationTextInput) arg0.get("sciCalcExpr");
	returnLabel = (Label) arg0.get("returnLabel");
	calc_equation.setValidator(new Validator() {
		
		public boolean isValid(String arg0) {
			if(arg0.indexOf('x') == -1)
			{
				return true;
			}
			return false;
		}
	});
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
				returnLabel.setText("The answer to 'f(x) = " + calc_equation.getText() + "' is: " + (new RPNCC(new EquationParserCC(new StringReader(calc_equation.getText())).parseEquation()).eval(0)));
				valid = true;
			} catch (CalculateException e) {
				Prompt.prompt(MessageType.ERROR, e.getMessage(), this.getWindow());
				returnLabel.setText("");
			} catch (org.advancedMathCalculator.parser.cc.ParseException e) {
				Prompt.prompt(MessageType.ERROR, e.getMessage()
						+ " at position " + e.currentToken.beginColumn + ".", this.getWindow());
				returnLabel.setText("");
			}
		}
		calc_equation.addExpression();
		calc_equation.setCurrentValid(valid);
	}
}
