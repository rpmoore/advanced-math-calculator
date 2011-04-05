package org.advancedMathCalculator.ui.components;


import java.awt.Color;
import java.util.ArrayList;

import org.apache.pivot.wtk.Component;
import org.apache.pivot.wtk.ComponentKeyListener;
import org.apache.pivot.wtk.Keyboard;
import org.apache.pivot.wtk.Keyboard.KeyLocation;
import org.apache.pivot.wtk.TextInput;

public class EquationTextInput extends TextInput{
	private final ArrayList<EquationEntry> equationList;
	private int index;
	private final Color defaultColor;
	public EquationTextInput() {
		super();
		this.equationList = new ArrayList<EquationEntry>();
		index = -1; //starting index: -1 since there isn't any data in the ArrayList.
		defaultColor = (Color) this.getStyles().get("backgroundColor");
		//Add the code to handle the up and down key presses.
		this.getComponentKeyListeners().add(new ComponentKeyListener() {

			public boolean keyTyped(Component arg0, char arg1) {
				return false;
			}

			public boolean keyReleased(Component arg0, int arg1, KeyLocation arg2) {
				return false;
			}

			public boolean keyPressed(Component arg0, int arg1, KeyLocation arg2) {
				return processKeyPress(arg1);
			}
		});
	}

	private boolean processKeyPress(int arg1) {
		if(arg1 == Keyboard.KeyCode.DOWN)
		{
			if(equationList.size() > 0 )
			{
				if(index < equationList.size())
				{
					if(index == equationList.size()-1)
					{
						this.setText("");//clear the list.
						index++;//set one outside of the size.
						this.updateColor(defaultColor);
					}
					else
					{
						index++;
						final EquationEntry entry = equationList.get(index);
						this.setText(entry.getEquation());
						this.updateColor(entry.getColor());
					}	
				}
			}
			else
			{
				//clear the text
				this.setText("");
				this.updateColor(defaultColor);
			}
			return true;
		}
		else if(arg1 == Keyboard.KeyCode.UP)
		{
			if(index > 0)
			{
				index--;
				final EquationEntry entry = equationList.get(index);
				this.setText(entry.getEquation());
				this.updateColor(entry.getColor());
			}
			return true;
		}
		else if(arg1 != Keyboard.KeyCode.ENTER)
		{
			index = equationList.size();//this means we are editing the current item and this is a place holder for when color gets enabled.
		}
		return false;
	}
	
	public void addExpression()
	{
		//when adding an expression be sure to re-set the index to the end of the list.
		//only add an expression if it isn't already at the top of the list.
		if(equationList.size() == 0 || !equationList.get(equationList.size()-1).getEquation().equals(this.getText()))
		{
			equationList.add(new EquationEntry(this.getText()));
			index = equationList.size()-1;//sub 1 for correct indexing.
		}
	}	

	public void updateColor(Color newcolor)
	{
		this.getStyles().put("backgroundColor", newcolor);
	}
	
	/**
	 * Set weather the current equation is valid or not.
	 * @param isValid
	 */
	public void setCurrentValid(boolean isValid)
	{
		final EquationEntry entry = equationList.get(index);
		entry.setValid(isValid);
		updateColor(entry.getColor());
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

		public Color getColor()
		{
			if(this.isValid)
			{
				return new Color(0x76,0xfb,0x6c);//light green
			}
			else
			{
				return new Color(0xff, 0x44, 0x44);//light red
			}
		}
	}
}
