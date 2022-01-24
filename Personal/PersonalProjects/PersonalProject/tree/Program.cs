using System;
using System.Collections.Generic;
using System.Text;

namespace tree
{
    class Tree
    {
        public class TreeNode
        {
            public int val;
            public TreeNode left;
            public TreeNode right;
            public TreeNode(int val = 0, TreeNode left = null, TreeNode right = null)
            {
                this.val = val;
                this.left = left;
                this.right = right;
            }
        }
        static void Main(string[] args)
        {
            Tree program = new Tree();
            TreeNode root = new TreeNode()
            {
                val = 5,
                left = null,
                right = null
            };

            program.AddToTree(root, 1);
            program.AddToTree(root, 4);
            program.AddToTree(root, 7);
            program.AddToTree(root, 9);
            program.AddToTree(root, 5);
            program.AddToTree(root, 2);
            program.AddToTree(root, 10);
            program.AddToTree(root, 11);

            int maxDepth = program.FindMaxDepth(root);
        }

        public int FindMaxDepth(TreeNode root)
        {
            if (root == null) return 0;
            return Math.Max(FindMaxDepth(root.left), FindMaxDepth(root.right)) + 1;
        }

        public void AddToTree(TreeNode root, int value)
        {
            Tree program = new Tree();
            if (root.val > value)
            {
                if (root.left == null)
                {
                    TreeNode nodeToAdd = new TreeNode()
                    {
                        val = value,
                        left = null,
                        right = null
                    };
                    root.left = nodeToAdd;
                }
                else
                {
                    program.AddToTree(root.left, value);
                }
            }
            else
            {
                if (root.right == null)
                {
                    TreeNode nodeToAdd = new TreeNode()
                    {
                        val = value,
                        left = null,
                        right = null
                    };
                    root.right = nodeToAdd;
                }
                else
                {
                    program.AddToTree(root.right, value);
                }
            }
        }
    }
}
