package ui;

import org.apache.pivot.collections.Map;
import org.apache.pivot.wtk.Application;
import org.apache.pivot.wtk.DesktopApplicationContext;
import org.apache.pivot.wtk.Display;
import org.apache.pivot.wtk.PushButton;
import org.apache.pivot.wtk.TextInput;
import org.apache.pivot.wtk.Window;
import org.apache.pivot.wtkx.WTKXSerializer;

public class PMain implements Application {
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
		def_upperBound = (TextInput) wtkxSerializer.get("def_upperBound");
		def_lowerBound = (TextInput) wtkxSerializer.get("def_lowerBound");
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
}
