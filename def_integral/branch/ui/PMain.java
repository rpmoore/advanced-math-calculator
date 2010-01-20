package ui;

import org.apache.pivot.collections.Map;
import org.apache.pivot.wtk.Application;
import org.apache.pivot.wtk.DesktopApplicationContext;
import org.apache.pivot.wtk.Display;
import org.apache.pivot.wtk.Window;
import org.apache.pivot.wtkx.WTKXSerializer;

public class PMain implements Application {
	private Window window = null;
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
