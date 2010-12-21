package org.advancedMathCalculator.ui;


import java.awt.Color;
import java.util.ArrayList;

import org.advancedMathCalculator.dataStructures.Stack;
import org.apache.pivot.wtk.Component;
import org.apache.pivot.wtk.ComponentKeyListener;
import org.apache.pivot.wtk.Keyboard;
import org.apache.pivot.wtk.Keyboard.KeyLocation;
import org.apache.pivot.wtk.TextInput;

public class EquationTextInput extends TextInput{

	private final Stack<EquationEntry> pastExpressionsUp;
	private final Stack<EquationEntry> pastExpressionsDown;
	private final Color defaultColor;
	private final StyleDictionary style = this.getStyles();
	private final EquationTextInput thisRef = this;
	public EquationTextInput() {
		super();
		this.pastExpressionsUp = new Stack<EquationEntry>();
		this.pastExpressionsDown = new Stack<EquationEntry>();
		this.defaultColor = (Color) style.get("backgroundColor");
	}

	public void addExpression(String expression)
	{
		
		this.getComponentKeyListeners().add(new ComponentKeyListener() {
			
			public boolean keyTyped(Component arg0, char arg1) {
				return false;
			}
			
			public boolean keyReleased(Component arg0, int arg1, KeyLocation arg2) {
				return false;
			}
			
			public boolean keyPressed(Component arg0, int arg1, KeyLocation arg2) {
				if(arg1 == Keyboard.KeyCode.DOWN)
				{

					

					return true;
				}
				else if(arg1 == Keyboard.KeyCode.UP)
				{
					
					
					return true;
				}
					
				return false;
			}
		});
	}	
	
	public void setCurrentValid(boolean isValid)
	{

	}
	
	private class EquationEntry
	{
		private final String equation;
		private boolean isValid = false;
		public EquationEntry(String equation) {
			this.equation = equation;
		}
		
		public void setValid(boolean isValid)
		{
			this.isValid = isValid;
		}
		
		public String getEquation()
		{
			return equation;
		}
		
		public Color returnColor()
		{
			if(this.isValid)
			{
				return Color.getHSBColor(0x00, 0xdd, 0x77);
			}
			else
			{
				return Color.getHSBColor(0xff, 0x44, 0x44);
			}
		}
	}
}
