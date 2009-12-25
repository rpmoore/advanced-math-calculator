package bptree;
public class TreeNode
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