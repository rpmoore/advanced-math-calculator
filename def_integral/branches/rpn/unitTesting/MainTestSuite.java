package unitTesting;

import junit.framework.TestSuite;

public class MainTestSuite {

	public static junit.framework.Test suite()
	{
		@SuppressWarnings("rawtypes")
		Class testClass[] = {ParseTreeTesting.class,TrigFunctionTesting.class,IntegralTesting.class};
		TestSuite suite = new TestSuite(testClass);
		return suite;
	}
}
