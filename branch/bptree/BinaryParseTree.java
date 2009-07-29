package bptree;

import java.text.ParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BinaryParseTree {

	private Pattern parser = Pattern.compile("\\(.+\\) | \\+ | \\- | \\* | / | \\d+\\.+\\d+ | \\d+ | x{1}+", Pattern.COMMENTS);
	public BinaryTree tree = null; //TODO make this private for release
	public BinaryParseTree() {
		tree = new BinaryTree();
	}
	
	/**
	 * 
	 * @param expression The mathematical expression to be parsed into a binary tree.
	 */
	public void parse(String expression) throws ParseException
	{
		tree.clear();//empty the tree if there is anything in it.
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
				tree.insert(temp,BinaryTree.returnType(temp));
			System.out.println("The next item in the pattern is: " + temp);
		}	
	}	
}