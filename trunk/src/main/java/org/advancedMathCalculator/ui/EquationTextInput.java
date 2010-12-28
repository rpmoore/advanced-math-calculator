package org.advancedMathCalculator.ui;


import java.awt.Color;
import java.util.ArrayList;

import org.apache.pivot.wtk.Component;
import org.apache.pivot.wtk.ComponentKeyListener;
import org.apache.pivot.wtk.Keyboard;
import org.apache.pivot.wtk.Keyboard.KeyLocation;
import org.apache.pivot.wtk.TextInput;

public class EquationTextInput extends TextInput{
	private final EquationTextInput thisRef = this;
	private final ArrayList<EquationEntry> equationList;
	private int index;
	public EquationTextInput() {
		super();
		this.equationList = new ArrayList<EquationEntry>();
		index = -1; //starting index: -1 since there isn't any data in the ArrayList.
		//Add the code to handle the up and down key presses.
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
					if(equationList.size() > 0 )
					{
						if(index < equationList.size())
						{
							if(index == equationList.size()-1)
							{
								thisRef.setText("");//clear the list.
								index++;//set one outside of the size.
							}
							else
							{
								index++;
								thisRef.setText(equationList.get(index).getEquation());
							}	
						}
					}
					else
					{
						//clear the text
						thisRef.setText("");
					}
					System.out.println("<EquationTextInput> KeyPress: DOWN - index " + index);
					return true;
				}
				else if(arg1 == Keyboard.KeyCode.UP)
				{
					if(index > 0)
					{
						index--;
						thisRef.setText(equationList.get(index).getEquation());
					}
					System.out.println("<EquationTextInput> KeyPress: UP - index " + index);
					return true;
				}
				else if(arg1 != Keyboard.KeyCode.ENTER)
				{
					index = equationList.size();//this means we are editing the current item and this is a place holder for when color gets enabled.
					System.out.println("<EquationTextInput> KeyPress: OTHER - index " + index);
				}
				return false;
			}
		});
	}

	public void addExpression()
	{
		//when adding an expression be sure to re-set the index to the end of the list.
		//only add an expression if it isn't already at the top of the list.
		if(equationList.size() == 0 || !equationList.get(equationList.size()-1).getEquation().equals(this.getText()))
		{
			equationList.add(new EquationEntry(this.getText()));
			index = equationList.size()-1;//sub 1 for correct indexing.
			System.out.println("<EquationTextInput> Adding new equation: index " + index);
		}
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
