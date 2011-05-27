package org.beginprogramming.web.client.widgets;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Image;

public class MenuWidget extends Composite {

	final FlowPanel panel = new FlowPanel(); 
	public MenuWidget(String style) {
		//add all the hyperlinks that I will use.
		panel.add(new HTML("<a style=\""+ style +"\" href=\"http://blog.begin-programming.com\">Blog</a>"));
		panel.add(new HTML("<a style=\""+ style +"\"href=\"http://code.google.com/p/advanced-math-calculator/\">Google Code Site</a>"));		
		panel.add(new Image("/images/IPv6-test-flight-blue-128-trans.png"));
		initWidget(panel);
	}

}
