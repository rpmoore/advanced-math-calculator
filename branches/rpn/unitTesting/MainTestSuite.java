package unitTesting;

import junit.framework.TestSuite;

public class MainTestSuite {

	public static junit.framework.Test suite()
	{
		
		Class testClass[] = {ParseTreeTesting.class,TrigFunctionTesting.class,IntegralTesting.class,StackTest.class};
		TestSuite suite = new TestSuite(testClass);
		return suite;
	}
}
