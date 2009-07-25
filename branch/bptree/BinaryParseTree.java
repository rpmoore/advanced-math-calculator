package bptree;

import java.text.ParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BinaryParseTree {
	private BPTreeNode root = null;
	private Pattern parser = Pattern.compile("\\(.+\\) | \\+ | \\- | \\* | / | \\d+\\.+\\d+ | \\d+ | x{1}+", Pattern.COMMENTS);
	public BinaryParseTree() {
		
	}
	
	/**
	 * 
	 * @param expression The mathematical expression to be parsed into a binary tree.
	 */
	public void parse(String expression) throws ParseException
	{
		String temp = null;
		System.out.println("Parsing the equation: " + expression);
		Matcher matcher = parser.matcher(expression);
		while(matcher.find())
		{
			temp = matcher.group();
			if(temp.charAt(0) == '(')
			{
				temp = temp.substring(1,temp.length()-1);
				parse(temp);
			}
			System.out.println("The next item in the pattern is: " + temp);
		}
		
	}

	
	//Binary tree implementation code.
	
	public void insert(String value, int type)
	{
		BPTreeNode newNode = new BPTreeNode(value,type);
		if(root == null)
		{
			newNode = root;
		}
		else
		{
			insert(newNode,root);
		}
	}
	
	private void insert(BPTreeNode newNode, BPTreeNode current)
	{
//		if()
//		{
//			
//		}
	}

	
	private boolean isOp(int type)
	{
		if(type>ExpressionTypes.VARIABLE)
		{
			return true;
		}
		return false;
	}
	
	private int compType(int type, int type2)
	{
		if(type == type2)
		{
			return 0;
		}
		
		
		
		
		
		return 1;
	}
	
	/**
	 * Checks an subset of an expression to validate that it is a valid mathematical symbol.
	 * 
	 * @param expression A portion of the total expression to check to see if it is a valid symbol.
	 * @return true if the expression is valid, false otherwise.
	 */
	private boolean checkValue(String expression)
	{
		
		return false;
	}
	
	/*Private class for the implementation.
	 *This class should not be used outside of the BinaryParseTree
	 */
	private class BPTreeNode
	{
		private String expression;
		private int type;
		private BPTreeNode left;
		private BPTreeNode right;
		private BPTreeNode root;
		
		public BPTreeNode(String expression, int type)
		{
			this.expression = expression;
			this.type = type;
		}
		
		public void setLeft(BPTreeNode left)
		{
			this.left = left;
		}
		
		public void setRight(BPTreeNode right)
		{
			this.right = right;
		}
		
		public void setRoot(BPTreeNode root)
		{
			this.root = root;
		}
		
		public void setExpression(String expression)
		{
			this.expression = expression;
		}
		
		public void setType(int type)
		{
			this.type = type;
		}
		
		public String getExpression()
		{
			return this.expression;
		}
		
		public int getType()
		{
			return type;
		}
	}	
}