package org.beginprogramming.web.client;


import org.beginprogramming.web.client.widgets.CalculationWidget;
import org.beginprogramming.web.client.widgets.DerivativeWidget;
import org.beginprogramming.web.client.widgets.IntegralWidget;
import org.beginprogramming.web.client.widgets.MenuWidget;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.TabLayoutPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Begin_Website implements EntryPoint {
	private final IntegralWidget integral = new IntegralWidget();
	private final DerivativeWidget derivative = new DerivativeWidget();
	private final CalculationWidget calculation = new CalculationWidget();
	public void onModuleLoad() {
		DockLayoutPanel mainLayout = new DockLayoutPanel(Unit.EM);
		HTML titleArea = new HTML("<h1 id=\"titleText\">Begin Programming - Calculations</h1>");
		titleArea.setStylePrimaryName("titleArea");
		mainLayout.addNorth(titleArea, 7);
		HTML footerSpan = new HTML("<small>Copyright &copy; 2011 Ryan Moore</small>");
		footerSpan.setStylePrimaryName("footer");
		mainLayout.addSouth(footerSpan, 3.5);
		MenuWidget menu = new MenuWidget("menuLinks");
		menu.setStylePrimaryName("menu");
		mainLayout.addWest(menu,14);
		Panel tabContainer = new FlowPanel();
		TabLayoutPanel tabPane = new TabLayoutPanel(1.5, Unit.EM);
		tabContainer.setStylePrimaryName("calculationArea");
		tabPane.setStylePrimaryName("calcBox");
		tabPane.add(integral,"Integral");
		tabPane.add(derivative,"Derivative");
		tabPane.add(calculation,"Calculator");
		tabContainer.add(tabPane);
		mainLayout.add(tabContainer);
		//mainLayout.add(new HTML("Center"));
		RootLayoutPanel.get().add(mainLayout);
		tabPane.setSize("500px", "192px");
		
	}
	
}
