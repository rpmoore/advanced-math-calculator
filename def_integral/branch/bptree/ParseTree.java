package bptree;

import java.text.ParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
		String temp = null;
		System.out.println("Parsing the equation: " + expression);
		
		
		
		
		return tree;
	}	
}