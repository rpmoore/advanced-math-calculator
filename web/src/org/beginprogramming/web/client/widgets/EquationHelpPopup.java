package org.beginprogramming.web.client.widgets;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.PopupPanel;

public class EquationHelpPopup extends PopupPanel {

	private final HTML text;
	public EquationHelpPopup() {
		super(true);
		setTitle("Equation Help");
		text = new HTML("List of supported operations are:"+
				"<ul><li>+ - * / (add subtract multiply divide)</li>"+
				"<li>^ - (power unary-minus)</li>" +
				"<li>sin cos tan</li>" +
				"<li>sinh cosh tanh</li>" +
				"<li>asin acos atan</li>" +
				"<li>log ln</li>" +
				"<li>pi e x (x is a variable)</li>" +
				"</ul>" +
				"<br>" +
				"Examples:" +
				"<ul><li>2+cos(pi)</li>" +
				"" +
				"<li>3log(2x*e)^(3sin(pi/2))</li></ul>");
		this.add(text);
	}

}
