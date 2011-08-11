package org.beginprogramming.web.client.widgets;

import com.google.gwt.event.dom.client.FocusEvent;
import com.google.gwt.event.dom.client.FocusHandler;
import com.google.gwt.user.client.ui.TextBox;

public class FirstVisitFocusHandler implements FocusHandler {
	boolean firstVisit = false;
	@Override
	public void onFocus(FocusEvent event) {
		TextBox parentObject;
		if(event.getSource() instanceof TextBox)
		{
			parentObject = (TextBox) event.getSource();
			if(!firstVisit)
			{
				parentObject.setText("");
			}
		}
	}
}
