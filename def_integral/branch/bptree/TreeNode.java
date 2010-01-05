package bptree;
/**
 * The TreeNode class used by the BinaryTree class.  The BinaryTree class 
 * uses this class extensively so changes to this class will affect the
 * BinaryTree class, or any classes that use the BinaryTree extensively. 
 * @author Ryan
 *
 * @param <T>
 */
public class TreeNode<T>
	{
		private T item;
		private TreeNode<T> left;
		private TreeNode<T> right;
		private TreeNode<T> parent;
		public TreeNode(T newitem)
		{
			this.item = newitem;
		}
		public void setLeft(TreeNode<T> left)
		{
			this.left = left;
		}
		public void setRight(TreeNode<T> right)
		{
			this.right = right;
		}
		public void setLeft(BinaryTree<T> leftTree)
		{
			this.left = leftTree.getRoot();	
		}
		public void setRight(BinaryTree<T> rightTree)
		{
			this.right = rightTree.getRoot();
		}
		public TreeNode<T> getRight()
		{
			return right;
		}
		public TreeNode<T> getLeft()
		{
			return left;
		}
		public TreeNode<T> getParent()
		{
			return parent;
		}
		public void setParent(TreeNode<T> parent)
		{
			this.parent = parent;
		}
		public void setExpression(T newItem)
		{
			this.item = newItem;
		}
		public T getItem()
		{
			return this.item;
		}
	}