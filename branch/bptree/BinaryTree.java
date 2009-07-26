package bptree;

public class BinaryTree {
	
	private TreeNode root;
	
	public BinaryTree()
	{
		root = null;
	}
	
	public void insert(String expression, int type)
	{
		TreeNode newNode = new TreeNode(expression,type);
		if(root == null)
		{
			root = newNode;
		}
		insert(newNode,root);
	}
	
	public void insert(TreeNode newNode, TreeNode current)
	{
		
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
		
		if(type < type2 & (Math.abs(type - type2) > 1))
		{
			return -1;
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
	
	
	
	//Used when combining 2 BinaryTrees together
	private TreeNode getRoot()
	{
		return root;
	}
	/*Protected class for the implementation.
	 *This class should not be used outside of the BinaryParseTree
	 */
	protected class TreeNode
	{
		private String expression;
		private int type;
		private TreeNode left;
		private TreeNode right;
		private TreeNode root;
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
		public void setRoot(TreeNode root)
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
