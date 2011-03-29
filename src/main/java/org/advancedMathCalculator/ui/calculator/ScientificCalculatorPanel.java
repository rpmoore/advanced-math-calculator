package org.advancedMathCalculator.ui.calculator;

import java.net.URL;

import org.apache.pivot.beans.Bindable;
import org.apache.pivot.collections.Map;
import org.apache.pivot.util.Resources;
import org.apache.pivot.wtk.BoxPane;
import org.apache.pivot.wtk.Label;

public class ScientificCalculatorPanel extends BoxPane implements Bindable {

	
	public void initialize(Map<String, Object> arg0, URL arg1, Resources arg2) {
		System.out.println("Started Calculator Panel");
		Label label = (Label) arg0.get("label");
		if(label == null)
		{
			System.out.println("Wtf is my label!");
		}
		else
		{
			System.out.println("Label isVisiable: " + label.isVisible());
			System.out.println("Label, " + label.getX());
			System.out.println("Label, " + label.getY());
		}
		this.setVisible(true);
	}

	
}
