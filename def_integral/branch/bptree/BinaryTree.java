package bptree;

public class BinaryTree {

	private TreeNode root;
	private String lastInsert;
	public BinaryTree()
	{//This is empty right now.  Don't see a need to have it.
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
		lastInsert = expression;
	}

	public void insert(TreeNode newNode, TreeNode current)
	{
		int ret = ExpressionType.compType(current.getType(), newNode.getType());
		if(ret > 0)
		{
			if(current.getLeft() == null)
			{
				current.setLeft(newNode);
				newNode.setParent(current);
			}
			else if(current.getRight() == null)
			{
				current.setRight(newNode);
				newNode.setParent(current);
			}
			else
			{
				insert(newNode, current.getRight());
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
