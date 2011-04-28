package org.beginprogramming.web.client.widgets;

import org.beginprogramming.web.client.services.CalculationResponse;
import org.beginprogramming.web.client.services.DerivativeService;
import org.beginprogramming.web.client.services.DerivativeServiceAsync;

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
import com.google.gwt.user.client.ui.DoubleBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class DerivativeWidget extends Composite {
	private  DerivativeServiceAsync DerivativeService = GWT.create(DerivativeService.class);
	private final PushButton sendButton;
	private final TextBox equationText;
	private final DoubleBox leftBounds;
	private final HTML resultLabel;
	private final DerivativeServiceProcess eventProcessing = new DerivativeServiceProcess();

	public DerivativeWidget() {
		
		final VerticalPanel verticalPane = new VerticalPanel();
		final HorizontalPanel horizontalPanel = new HorizontalPanel();
		horizontalPanel.setStyleName("DerivativeEquation");
		sendButton= new PushButton("Compute Derivative");
		equationText = new TextBox();
		equationText.setText("Equation");
		horizontalPanel.add(equationText);
		horizontalPanel.add(sendButton);
		verticalPane.add(horizontalPanel);
		
		HorizontalPanel horizontalPanel_1 = new HorizontalPanel();
		horizontalPanel_1.setStyleName("DerivativeLowerBounds");
		
		InlineLabel nlnlblLowerBounds = new InlineLabel("Eval at x = ");
		horizontalPanel_1.add(nlnlblLowerBounds);
		
		leftBounds = new DoubleBox();
		horizontalPanel_1.add(leftBounds);
		verticalPane.add(horizontalPanel_1);
		
		resultLabel = new HTML("Result Area", true);
		
		resultLabel.setStyleName("DerivativeTableResult");
		verticalPane.add(resultLabel);
		equationText.addKeyDownHandler(eventProcessing);
		leftBounds.addKeyDownHandler(eventProcessing);
		sendButton.addMouseDownHandler(eventProcessing);
		initWidget(verticalPane);
	}
	
	private class DerivativeServiceProcess implements MouseDownHandler, KeyDownHandler
	{
		final private DialogBox processing;
		final Timer t = new Timer() 
		{
			public void run()
			{
				processing.center();
			}
		};
		
		public DerivativeServiceProcess()
		{
			processing = new DialogBox();
			processing.setModal(true);
			processing.setGlassEnabled(true);
			processing.setTitle("Processing");
			processing.setText("Processing");
			processing.add(new Label("Processing Derivative Calculation..."));
			
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
			String left = leftBounds.getText();
			if(equationText.getText().isEmpty())
			{
				return false;
			}
			if(left.isEmpty())
			{
				return false;
			}
			return true;
		}
		
		private void processRequest()
		{
			if(DerivativeService == null)
			{
				DerivativeService = GWT.create(DerivativeService.class);
			}
			t.schedule(750);
			DerivativeService.derivativeService(equationText.getText(), leftBounds.getText(), new AsyncCallback<CalculationResponse>() {
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
