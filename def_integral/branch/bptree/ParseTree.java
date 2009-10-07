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
		tree.clear();//empty the tree if there is anything in it.		
		System.out.println("Parsing the equation: " + expression);
		
		EquationTokenizer tokenizer = new EquationTokenizer(expression);
		
		while(tokenizer.hasMoreElements())
		{
			
			
		}
		
		
		
		
		return tree;
	}	
}