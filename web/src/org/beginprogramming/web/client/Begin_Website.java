package org.beginprogramming.web.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.TextBox;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Begin_Website implements EntryPoint {

	private  IntegralServiceAsync integralService = GWT.create(IntegralService.class);
	
	private final Button sendButton = new Button("Calculate");
	private final TextBox equationText = new TextBox();
	private final TextBox leftBounds = new TextBox();
	private final TextBox rightBounds = new TextBox();
	private final HTML resultLabel = new HTML();
	private final IntegralServiceProcess eventProcessing = new IntegralServiceProcess();
	public void onModuleLoad() {
		equationText.setText("Equation");
		equationText.setTabIndex(1);
		leftBounds.setTabIndex(2);
		rightBounds.setTabIndex(3);
		sendButton.setTabIndex(4);
		// We can add style names to widgets
		sendButton.addStyleName("sendButton");
		equationText.setWidth("200px");
		leftBounds.setWidth("50px");
		rightBounds.setWidth("50px");
		// Add the nameField and sendButton to the RootPanel
		// Use RootPanel.get() to get the entire body element
		final HorizontalPanel boundrayPaneLeft = new HorizontalPanel();
		final HorizontalPanel boundrayPaneRight = new HorizontalPanel();
		final HorizontalPanel topPanel = new HorizontalPanel();
		topPanel.add(equationText);
		topPanel.add(sendButton);
		topPanel.setSpacing(3);
		RootPanel.get("nameFieldContainer").add(topPanel);
		boundrayPaneLeft.add(new Label("Left Bounds:"));
		boundrayPaneLeft.add(leftBounds);
		boundrayPaneRight.add(new Label("Right Bounds:"));
		boundrayPaneRight.add(rightBounds);
		RootPanel.get("leftBoundrayConditions").add(boundrayPaneLeft);
		RootPanel.get("rightBoundrayConditions").add(boundrayPaneRight);
		ScrollPanel sPanel = new ScrollPanel();
		sPanel.add(resultLabel);
		resultLabel.setText("Result Area");
		RootPanel.get("labelContainer").add(sPanel);

		// Focus the cursor on the name field when the app loads
		equationText.setFocus(true);
		equationText.selectAll();
	
		equationText.addKeyDownHandler(eventProcessing);
		leftBounds.addKeyDownHandler(eventProcessing);
		rightBounds.addKeyDownHandler(eventProcessing);
		sendButton.addMouseDownHandler(eventProcessing);
		
	}
	
	private class IntegralServiceProcess implements MouseDownHandler, KeyDownHandler
	{
		final private DialogBox processing;
		final Timer t = new Timer() 
		{
			public void run()
			{
				processing.center();
			}
		};
		
		public IntegralServiceProcess()
		{
			processing = new DialogBox();
			processing.setModal(true);
			processing.setGlassEnabled(true);
			processing.setTitle("Processing");
			processing.setText("Processing");
			processing.add(new Label("Processing Integral Calculation..."));
			
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
			String right = rightBounds.getText();
			if(equationText.getText().isEmpty())
			{
				return false;
			}
			if(left.isEmpty())
			{
				return false;
			}
			if(right.isEmpty())
			{
				return false;
			}
			return true;
		}
		
		private void processRequest()
		{
			if(integralService == null)
			{
				integralService = GWT.create(IntegralService.class);
			}
			t.schedule(750);
			integralService.integralService(equationText.getText(), leftBounds.getText(), rightBounds.getText(), new AsyncCallback<CalculationResponse>() {
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
