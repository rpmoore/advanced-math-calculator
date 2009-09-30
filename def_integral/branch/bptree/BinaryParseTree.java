package bptree;

import java.text.ParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BinaryParseTree {

	private Pattern parser = Pattern.compile("\\(.+\\) | \\^ | \\+ | \\- | \\* | / | \\d+\\.+\\d+ | \\d+ | x{1}+ | sin | cos | tan", Pattern.COMMENTS);
	public BinaryParseTree() {

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
		Matcher matcher = parser.matcher(expression);
		while(matcher.find())
		{
			temp = matcher.group();
			if( temp.length() > 0 && temp.charAt(0) == '(')
			{
				temp = temp.substring(1,temp.length()-1);
				parse(temp);
			}
				tree.insert(temp,BinaryTree.returnType(temp));
			System.out.println("The next item in the pattern is: " + temp);
		}	
		return tree;
	}	
}