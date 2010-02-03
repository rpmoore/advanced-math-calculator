package unitTesting;

import bptree.ParseTree;
import bptree.ParseTreeGenerationException;
import bptree.ParseTreeNewLexer;

public class RunTimeDifferences {
	public static final String equation = "3+4*x/(5^(6*x))-(10*x-5^(x+7))";
	public static final int upperBound = 1000000;
	public static void main(String args[])
	{

		Thread a = new Thread(new NewParseRunner(false));
		Thread b = new Thread(new OldParseRunner(false));
		Thread c = new Thread(new NewParseRunner(true));
		Thread d = new Thread(new OldParseRunner(true));		

		a.start();
		b.start();

		try {
			a.join();
			b.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		c.start();
		d.start();

		try {
			c.join();
			d.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}

class NewParseRunner implements Runnable
{
	boolean optimize = false;
	long startTime;
	public NewParseRunner(boolean optimize)
	{
		this.optimize = optimize;
	}
	@Override
	public void run() {
		startTime = System.currentTimeMillis();
		for(int i = 0; i < RunTimeDifferences.upperBound ; ++i)
		{
			try {
				ParseTreeNewLexer parser = ParseTreeNewLexer.makeTree(RunTimeDifferences.equation, false);
				parser.eval(5);
			} catch (ParseTreeGenerationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		System.out.println("NewParseRunner: Total Time " + (System.currentTimeMillis() - startTime)/60);
	}
}

class OldParseRunner implements Runnable
{
	boolean optimize = false;
	long startTime;
	public OldParseRunner(boolean optimize)
	{
		this.optimize = optimize;
	}
	@Override
	public void run() {
		startTime = System.currentTimeMillis();
		// TODO Auto-generated method stub
		for(int i = 0; i < RunTimeDifferences.upperBound; ++i)
		{
			try {
				ParseTree parser = ParseTree.makeTree(RunTimeDifferences.equation, optimize);
				parser.eval(5);
			} catch (ParseTreeGenerationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("OldParseRunner: Total Time " + (System.currentTimeMillis() - startTime)/60);
	}		
}
