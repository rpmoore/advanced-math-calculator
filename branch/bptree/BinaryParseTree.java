package bptree;

import java.text.ParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BinaryParseTree {
	private Pattern parser = Pattern.compile("\\(.+\\) | \\+ | \\- | \\d+\\.+\\d+ | \\d+ | x{1}+", Pattern.COMMENTS);
	public BinaryParseTree() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 
	 * @param expression The mathmatical expression to be parsed into a binary tree.
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
	
	
	
	
	
	
	
	/*Private class for the implementation.
	 *This class should not be used outside of the BinaryParseTree
	 */
	private class BPTreeNode
	{
		private String expression;
		private BPTreeNode left;
		private BPTreeNode right;
		private BPTreeNode root;
		
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
		
		public String getExpression()
		{
			return this.expression;
		}
	}	
}