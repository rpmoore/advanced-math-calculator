package bptree;

import java.text.ParseException;

public class ParseTree {


	public ParseTree() {

	}
	
	/**
	 * 
	 * @param expression The mathematical expression to be parsed into a binary tree.
	 */
	public BinaryTree parse(String expression) throws ParseException
	{
		BinaryTree tree = new BinaryTree();	
		System.out.println("Parsing the equation: " + expression);
		
		EquationTokenizer tokenizer = new EquationTokenizer(expression);
		
		int counter = 0;
		while(tokenizer.hasMoreElements())
		{
			EquationToken token = tokenizer.nextElement();
			if(token.getType() == ExpressionType.OTHER)
			{
				throw new ParseException("Encountered an unknown symbol", counter);
			}
			tree.insert(token.getToken(),token.getType());
			counter++;
		}
		
		
		System.out.println("I have read in all of the tokens");
		
		return tree;
	}	
}