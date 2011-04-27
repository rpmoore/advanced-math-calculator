package org.beginprogramming.web.client;


import org.beginprogramming.web.client.widgets.DerivativeWidget;
import org.beginprogramming.web.client.widgets.IntegralWidget;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.dom.client.Style.Unit;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TabLayoutPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Begin_Website implements EntryPoint {
	private final IntegralWidget integral = new IntegralWidget();
	private final DerivativeWidget derivative = new DerivativeWidget();
	public void onModuleLoad() {
		final TabLayoutPanel tabPane = new TabLayoutPanel(1.5, Unit.EM);
		tabPane.add(integral,"Integral");
		tabPane.add(derivative,"Derivative");
		RootPanel.get("integralComputation").add(tabPane);
		tabPane.setSize("450px", "192px");
		
	}
	
}
