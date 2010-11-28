package unitTesting;
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
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import junit.framework.TestSuite;

public class MainTestSuite {

	private final static Logger logger = Logger.getLogger(MainTestSuite.class);
	public static junit.framework.Test suite()
	{
		PropertyConfigurator.configure("logger.conf");
		logger.info("Started main test suite.");
		@SuppressWarnings("rawtypes")
		Class testClass[] = {StackTest.class,QueueTest.class,ExpressionTypePrecedenceTesting.class,ExpressionLexerTest.class,ParseTreeTesting.class,TrigFunctionTesting.class,IntegralTesting.class,RPNTesting.class};
		TestSuite suite = new TestSuite(testClass);
		return suite;
	}
}
