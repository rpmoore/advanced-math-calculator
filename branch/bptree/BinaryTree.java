package bptree;

import java.util.HashMap;


public class BinaryTree {

	private TreeNode root;
	HashMap<String,Integer> expressionToType;
	public BinaryTree()
	{
		root = null;
		expressionToType = new HashMap<String, Integer>();
		//Fill the hashmap
		expressionToType.put("e", ExpressionTypes.E);
		expressionToType.put("pi", ExpressionTypes.PI);
		expressionToType.put("x", ExpressionTypes.VARIABLE);
		expressionToType.put("sin", ExpressionTypes.SIN);
		expressionToType.put("cos", ExpressionTypes.COS);
		expressionToType.put("tan", ExpressionTypes.TAN);
		expressionToType.put("sinh", ExpressionTypes.SINH);
		expressionToType.put("cosh", ExpressionTypes.COSH);
		expressionToType.put("tanh", ExpressionTypes.TANH);
		expressionToType.put("log", ExpressionTypes.LOG);
		expressionToType.put("ln", ExpressionTypes.LN);
		expressionToType.put("*", ExpressionTypes.MULTIPLY);
		expressionToType.put("/", ExpressionTypes.DIVIDE);
		expressionToType.put("+", ExpressionTypes.ADD);
		expressionToType.put("-",ExpressionTypes.SUBTRACT);

	}

	public void insert(String expression, int type)
	{
		TreeNode newNode = new TreeNode(expression,type);
		if(root == null)
		{
			root = newNode;
		}
		else
		{
			insert(newNode,root);
		}
	}

	public void insert(TreeNode newNode, TreeNode current)
	{
		int ret = compType(current.getType(), newNode.getType());
		if(ret > 0)
		{
			if(current.getRight() == null)
			{
				current.setRight(newNode);
			}
			if(current.getLeft() == null)
			{
				current.setLeft(newNode);
			}
		}
		else if(ret < 0)
		{
			TreeNode parent = current.getParent();
			if(parent != null)
			{

				if(parent.getLeft().equals(current))
				{
					parent.setLeft(newNode);
				}
				else
				{
					parent.setRight(newNode);
				}
			}
			if(current.equals(root))
			{
				root = newNode;
			}
			newNode.setLeft(current);
		}
		else if(ret == 0)
		{
			
		}

	}

	public boolean isOp(int type)
	{
		if(type>ExpressionTypes.VARIABLE)
		{
			return true;
		}
		return false;
	}



	public int compType(int type, int type2)
	{
		if(type == type2)
		{
			return 0;
		}
		if(isOp(type) && !isOp(type2))
		{
			return 1;
		}
		if(type > ExpressionTypes.DIVIDE && type2 > ExpressionTypes.DIVIDE)
		{
			return 0;
		}
		if(type > ExpressionTypes.DIVIDE && !(type2 > ExpressionTypes.DIVIDE))
		{
			return 1;
		}
		if(type <=ExpressionTypes.DIVIDE && type2 > ExpressionTypes.DIVIDE)
		{
			return -1;
		}
		if(type > ExpressionTypes.LN && type2 > ExpressionTypes.LN)
		{
			return 0;
		}
		if(type > ExpressionTypes.LN && type2 <= ExpressionTypes.LN)
		{
			return 1;
		}
		return -1;
	}

	/**
	 * Checks an subset of an expression to validate that it is a valid mathematical symbol.
	 * 
	 * @param expression A portion of the total expression to check to see if it is a valid symbol.
	 * @return true if the expression is valid, false otherwise.
	 */
	private boolean checkValue(String expression)
	{
		if(returnType(expression) < -1)
		{
			return true;
		}
		return false;
	}

	public int returnType(String expression)
	{
		Integer value = expressionToType.get(expression);
		if(value != null)
		{
			return value;
		}
		try
		{
			Integer.parseInt(expression);
			return ExpressionTypes.NUMBER; 
		}
		catch(NumberFormatException e)
		{
			try
			{
				Double.parseDouble(expression);
				return ExpressionTypes.NUMBER;
			}
			catch(NumberFormatException e2)
			{
				return -1;
			}
		}
	}




	//Used when combining 2 BinaryTrees together
	public TreeNode getRoot()//TODO make this private for release
	{
		return root;
	}
	/*Protected class for the implementation.
	 *This class should not be used outside of the BinaryParseTree
	 */
	public class TreeNode//TODO make this private for release
	{
		private String expression;
		private int type;
		private TreeNode left;
		private TreeNode right;
		private TreeNode parent;
		public TreeNode(String expression, int type)
		{
			this.expression = expression;
			this.type = type;
		}
		public void setLeft(TreeNode left)
		{
			this.left = left;
		}
		public void setRight(TreeNode right)
		{
			this.right = right;
		}
		public TreeNode getRight()
		{
			return right;
		}
		public TreeNode getLeft()
		{
			return left;
		}
		public TreeNode getParent()
		{
			return parent;
		}
		public void setParent(TreeNode parent)
		{
			this.parent = parent;
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
