package org.advancedMathCalculator.dataStructures;
/*
 * Copyright 2010 Ryan Moore
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *  
 */
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
			if(leftTree==null)
				return;
			this.left = leftTree.getRoot();	
		}
		public void setRight(BinaryTree<T> rightTree)
		{
			if(rightTree==null)
				return;
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