package org.beginprogramming.web.client.widgets;

import org.beginprogramming.web.client.services.CalculationResponse;
import org.beginprogramming.web.client.services.IntegralService;
import org.beginprogramming.web.client.services.IntegralServiceAsync;

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
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.TextBox;

public class IntegralWidget extends Composite {
	private  IntegralServiceAsync integralService = GWT.create(IntegralService.class);
	private final PushButton sendButton;
	private final TextBox equationText;
	private final DoubleBox leftBounds;
	private final DoubleBox rightBounds;
	private final HTML resultLabel;
	private final EquationHelpWidget help = new EquationHelpWidget();
	private final IntegralServiceProcess eventProcessing = new IntegralServiceProcess();

	public IntegralWidget() {
		
		final FlexTable flexTable = new FlexTable();
		final HorizontalPanel horizontalPanel = new HorizontalPanel();
		horizontalPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		horizontalPanel.setStyleName("equationArea");
		final PushButton pshbtnNewButton = new PushButton("Compute Integral");
		final TextBox txtbxEquation = new TextBox();
		equationText = txtbxEquation;
		equationText.addFocusHandler(new FirstVisitFocusHandler());
		equationText.setText("Equation");
		equationText.setStyleName("equationBox");
		horizontalPanel.add(txtbxEquation);
		sendButton = pshbtnNewButton;
		horizontalPanel.add(pshbtnNewButton);
		horizontalPanel.add(help);
		
		flexTable.setWidget(0, 0, horizontalPanel);
		
		HorizontalPanel horizontalPanel_1 = new HorizontalPanel();
		horizontalPanel_1.setStyleName("integralLowerBounds");
		flexTable.setWidget(1, 0, horizontalPanel_1);
		
		InlineLabel nlnlblLowerBounds = new InlineLabel("Lower Bounds");
		horizontalPanel_1.add(nlnlblLowerBounds);
		
		DoubleBox doubleBox = new DoubleBox();
		leftBounds = doubleBox;
		horizontalPanel_1.add(doubleBox);
		
		HorizontalPanel horizontalPanel_2 = new HorizontalPanel();
		horizontalPanel_2.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
		horizontalPanel_2.setStyleName("integralUpperBounds");
		flexTable.setWidget(1, 1, horizontalPanel_2);
		
		InlineLabel nlnlblUpperBounds = new InlineLabel("Upper Bounds");
		horizontalPanel_2.add(nlnlblUpperBounds);
		
		DoubleBox doubleBox_1 = new DoubleBox();
		rightBounds = doubleBox_1;
		horizontalPanel_2.add(doubleBox_1);
		flexTable.getFlexCellFormatter().setColSpan(0, 0, 2);
		
		HTML htmlNewHtml = new HTML("Result Area", true);
		resultLabel = htmlNewHtml;
		htmlNewHtml.setStyleName("integralTableResult");
		flexTable.setWidget(2, 0, htmlNewHtml);
		flexTable.getFlexCellFormatter().setColSpan(2, 0, 2);
		flexTable.getCellFormatter().setHorizontalAlignment(1, 0, HasHorizontalAlignment.ALIGN_CENTER);
		
		equationText.addKeyDownHandler(eventProcessing);
		leftBounds.addKeyDownHandler(eventProcessing);
		rightBounds.addKeyDownHandler(eventProcessing);
		sendButton.addMouseDownHandler(eventProcessing);
		initWidget(flexTable);
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
