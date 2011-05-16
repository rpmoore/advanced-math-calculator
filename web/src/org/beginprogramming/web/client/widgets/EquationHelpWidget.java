package org.beginprogramming.web.client.widgets;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Image;

public class EquationHelpWidget extends Composite {
	static final private EquationHelpPopup help = new EquationHelpPopup();
	final private Image questionMark = new Image("/images/question.png");
	public EquationHelpWidget() {
		questionMark.setHeight("20px");
		questionMark.setWidth("20px");
		questionMark.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				help.setPopupPosition(event.getClientX(), event.getClientY());
				help.show();
			}
		});
		initWidget(questionMark);
		
	}

}
