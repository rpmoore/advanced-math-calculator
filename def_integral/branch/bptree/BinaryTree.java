package bptree;

public class BinaryTree {

	private TreeNode root;
	public BinaryTree()
	{//This is empty right now.  Don't see a need to have it.
	}

	public void insert(TreeNode newNode, TreeNode current)
	{
		
		
		
	}

	public boolean isEmpty()
	{
		if(root != null)
		{	
			return false;
		}
		return true;
	}

	public void clear()
	{
		if(!isEmpty())
		{
			root = null;
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
		private ExpressionType type;
		private TreeNode left;
		private TreeNode right;
		private TreeNode parent;
		public TreeNode(String expression, ExpressionType type)
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
		public void setType(ExpressionType type)
		{
			this.type = type;
		}
		public String getExpression()
		{
			return this.expression;
		}
		public ExpressionType getType()
		{
			return type;
		}
	}	
}
