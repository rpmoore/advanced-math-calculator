package org.advancedMathCalculator.ui;

/*
 * Copyright 2010 Ryan Moore
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *  
 */

import org.apache.pivot.beans.BXMLSerializer;
import org.apache.pivot.collections.Map;
import org.apache.pivot.wtk.Application;
import org.apache.pivot.wtk.DesktopApplicationContext;
import org.apache.pivot.wtk.Display;
import org.apache.pivot.wtk.Window;

public class PMain implements Application {

	// private LineGraph<ParseTree> graph = null;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DesktopApplicationContext.main(PMain.class, args);
	}

	private Window window = null;

	

	public boolean shutdown(boolean arg0) throws Exception {
		if (window != null) {
			window.close();
		}
		return false;
	}

	public void startup(Display display, Map<String, String> arg1)
	throws Exception {
		final BXMLSerializer bxmlSerializer = new BXMLSerializer();
		bxmlSerializer.getNamespace().put("application", this);
		window = (Window)bxmlSerializer.readObject(PMain.class,"pMain.bxml");

		window.open(display);
	}

	public void suspend() throws Exception {
	}

	public void resume() throws Exception {
		// TODO Auto-generated method stub
		
	}
}
