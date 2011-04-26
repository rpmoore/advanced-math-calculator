package org.beginprogramming.web.client.services;

import java.io.Serializable;

public class CalculationResponse implements Serializable {

	private static final long serialVersionUID = 3390687205945470541L;
	private final static int success = 0;
	private final static int error = 1;
	private double result;
	private String errorMessage;
	private int resultType = -1;

	public void setErrorMessage(String message)
	{
		this.errorMessage = message;
		resultType = error;
	}

	public void setResult(double retVal)
	{
		this.result = retVal;
		resultType = success;
	}

	public int getResultType()
	{
		return resultType;
	}
	
	public String getResultMessage()
	{
		if(resultType == success)
		{
			return "Result: " + result;
		}
		else if(resultType == error)
		{	
			return "ERROR: " +errorMessage;
		}
		else
		{
			return "Nothing has been set.";
		}

	}
}
