package org.beginprogramming.web.client.widgets;

import org.beginprogramming.web.client.services.CalculationResponse;
import org.beginprogramming.web.client.services.CalculationService;
import org.beginprogramming.web.client.services.CalculationServiceAsync;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class CalculationWidget extends Composite {
	private  CalculationServiceAsync CalculationService = GWT.create(CalculationService.class);
	private final PushButton sendButton;
	private final TextBox equationText;
	private final HTML resultLabel;
	private final CalculationServiceProcess eventProcessing = new CalculationServiceProcess();
	private final EquationHelpWidget help = new EquationHelpWidget();
	public CalculationWidget() {
		final VerticalPanel verticalPane = new VerticalPanel();
		final HorizontalPanel horizontalPanel = new HorizontalPanel();
		horizontalPanel.setStyleName("equationArea");
		sendButton= new PushButton("Compute Calculation");
		equationText = new TextBox();
		equationText.setText("Equation");
		equationText.addFocusHandler(new FirstVisitFocusHandler());
		horizontalPanel.add(equationText);
		horizontalPanel.add(sendButton);
		horizontalPanel.add(help);
		verticalPane.add(horizontalPanel);
		

		resultLabel = new HTML("Result Area", true);
		resultLabel.setStyleName("CalculationTableResult");
		verticalPane.add(resultLabel);
		equationText.addKeyDownHandler(eventProcessing);
		sendButton.addMouseDownHandler(eventProcessing);
		initWidget(verticalPane);
	}
	
	private class CalculationServiceProcess implements MouseDownHandler, KeyDownHandler
	{
		final private DialogBox processing;
		final Timer t = new Timer() 
		{
			public void run()
			{
				processing.center();
			}
		};
		
		public CalculationServiceProcess()
		{
			processing = new DialogBox();
			processing.setModal(true);
			processing.setGlassEnabled(true);
			processing.setTitle("Processing");
			processing.setText("Processing");
			processing.add(new Label("Processing Calculation Calculation..."));
			
		}
		
		@Override
		public void onKeyDown(KeyDownEvent event) {
			if(event.getNativeKeyCode() == KeyCodes.KEY_ENTER && validate())
			{
				processRequest();				
			}
		}

		@Override
		public void onMouseDown(MouseDownEvent event) {
			if(event.getSource() == sendButton && validate())
			{
				processRequest();
			}
		}
		
		private boolean validate()
		{
			if(equationText.getText().isEmpty())
			{
				return false;
			}
			return true;
		}
		
		private void processRequest()
		{
			if(CalculationService == null)
			{
				CalculationService = GWT.create(CalculationService.class);
			}
			t.schedule(750);
			CalculationService.calculateEquation(equationText.getText(), new AsyncCallback<CalculationResponse>() {
				@Override
				public void onSuccess(CalculationResponse result) {
					resultLabel.setText(result.getResultMessage());
					t.cancel();
					processing.hide();
				}
				@Override
				public void onFailure(Throwable caught) {
					t.cancel();
					processing.hide();
				}
			});
		}
	}
}