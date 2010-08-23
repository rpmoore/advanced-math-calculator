package ui;

import org.apache.pivot.collections.Map;
import org.apache.pivot.wtk.Application;
import org.apache.pivot.wtk.Button;
import org.apache.pivot.wtk.ButtonPressListener;
import org.apache.pivot.wtk.Component;
import org.apache.pivot.wtk.ComponentKeyListener;
import org.apache.pivot.wtk.DesktopApplicationContext;
import org.apache.pivot.wtk.Display;
import org.apache.pivot.wtk.Keyboard;
import org.apache.pivot.wtk.MessageType;
import org.apache.pivot.wtk.Prompt;
import org.apache.pivot.wtk.PushButton;
import org.apache.pivot.wtk.TextInput;
import org.apache.pivot.wtk.Window;
import org.apache.pivot.wtk.Keyboard.KeyLocation;
import org.apache.pivot.wtkx.WTKXSerializer;

import defIntegral.SimpsonsRule;

import bptree.ParseTree;
import bptree.ParseTreeGenerationException;

public class PMain implements Application, ButtonPressListener, ComponentKeyListener{
	private ParseTree pTree = null;
	private Window window = null;
	private TextInput def_equation = null;
	private TextInput def_upperBound = null;
	private TextInput def_lowerBound = null;
	private PushButton def_Button = null;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DesktopApplicationContext.main(PMain.class, args);
	}



	@Override
	public void startup(Display display, Map<String, String> arg1)
			throws Exception {
		WTKXSerializer wtkxSerializer = new WTKXSerializer();
		window = (Window)wtkxSerializer.readObject(this,"hello.wtkx");
		def_equation = (TextInput) wtkxSerializer.get("def_equation");
		def_equation.getComponentKeyListeners().add(this);
		def_upperBound = (TextInput) wtkxSerializer.get("def_upperBound");
		def_upperBound.getComponentKeyListeners().add(this);
		def_lowerBound = (TextInput) wtkxSerializer.get("def_lowerBound");
		def_lowerBound.getComponentKeyListeners().add(this);
		def_Button = (PushButton) wtkxSerializer.get("def_solve");
		def_Button.getButtonPressListeners().add(this);
		window.open(display);
	}
	
	
	@Override
	public boolean shutdown(boolean arg0) throws Exception {
		if(window != null)
		{
			window.close();
		}
		return false;
	}
	@Override
	public void resume() throws Exception {
	}
	@Override
	public void suspend() throws Exception {
	}

	public void buttonPressed(Button arg0) {
		processIntegral();
	}



	@Override
	public boolean keyPressed(Component arg0, int arg1, KeyLocation arg2) {
		if(arg1 == Keyboard.KeyCode.ENTER)
		{
			processIntegral();
		}
		return true;
	}



	@Override
	public boolean keyReleased(Component arg0, int arg1, KeyLocation arg2) {
		// TODO Auto-generated method stub
		return false;
	}



	@Override
	public boolean keyTyped(Component arg0, char arg1) {
		// TODO Auto-generated method stub
		return false;
	}
	
	private void processIntegral()
	{
		double lower,upper;
		if(def_equation.getText().isEmpty())
		{
			Prompt.prompt(MessageType.ERROR, "The definite integral equation is empty.", window);
		}
		else if(def_lowerBound.getText().isEmpty())
		{
			Prompt.prompt(MessageType.ERROR, "The definite integral lower bound is empty.", window);
		}
		else if(def_upperBound.getText().isEmpty())
		{
			Prompt.prompt(MessageType.ERROR, "The definite integral upper bound is empty.", window);					
		}
		else
		{
			//check bounds, make sure they are doubles.
			try{
				lower = Double.parseDouble(def_lowerBound.getText());
			}
			catch(NumberFormatException e){
				Prompt.prompt(MessageType.ERROR, "The definite integral lower bound (" + def_lowerBound.getText() + ") is not a number.", window);
				return;
			}
			try{
				upper = Double.parseDouble(def_upperBound.getText());
			}
			catch(NumberFormatException e){
				Prompt.prompt(MessageType.ERROR, "The definite integral upper bound (" + def_upperBound.getText() + ") is not a number.", window);
				return;
			}
			try {
				pTree = ParseTree.makeTree(def_equation.getText(), true);
				Prompt.prompt(MessageType.INFO,"The answer to 'f(x)=" + def_equation.getText() + "' is: " + SimpsonsRule.compute(pTree, lower, upper),window);
			} catch (ParseTreeGenerationException e) {
				// TODO Auto-generated catch block
				Prompt.prompt(MessageType.ERROR, e.getMessage(), window);
			}
		}
	}
}
