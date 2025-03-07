package cz.braza.leetcode;

/*
Daily 23.2.2025

889. Construct Binary Tree from Preorder and Postorder Traversal
Medium
Topics
Companies
Given two integer arrays, preorder and postorder where preorder is the preorder traversal of a binary tree of distinct values and postorder is the postorder traversal of the same tree, reconstruct and return the binary tree.

If there exist multiple answers, you can return any of them.



Example 1:


Input: preorder = [1,2,4,5,3,6,7], postorder = [4,5,2,6,7,3,1]
Output: [1,2,3,4,5,6,7]
Example 2:

Input: preorder = [1], postorder = [1]
Output: [1]


Constraints:

1 <= preorder.length <= 30
1 <= preorder[i] <= preorder.length
All the values of preorder are unique.
postorder.length == preorder.length
1 <= postorder[i] <= postorder.length
All the values of postorder are unique.
It is guaranteed that preorder and postorder are the preorder traversal and postorder traversal of the same binary tree.
 */
public class L889ConstructBinaryTree {
    /*
    Recursive function to construct binary tree from preorder and postorder traversals.
    In each node, we find the position of next left node in the postorder array, and process. If anything left for right tree, process as well.

    Runs 0ms, beats 100%, 306 testcases
     */
    public TreeNode constructFromPrePost(int[] preorder, int[] postorder) {
        TreeNode root = new TreeNode();
        constructTree(root, preorder, postorder, 0, preorder.length - 1, 0, postorder.length - 1);
        return root;
    }

    public static void constructTree(TreeNode root, int[] preorder, int[] postorder, int preStart, int preEnd, int postStart, int postEnd) {
        // set value:
        root.val = preorder[preStart++];
        if (preStart > preEnd || postStart > postEnd) return;
        // find where to split left/right parts - left node is first in preorder, last in the left part of postorder:
        int index = postStart;
        while (postorder[index] != preorder[preStart]) index++;

        int leftSize = index - postStart;
        root.left = new TreeNode();
        constructTree(root.left, preorder, postorder, preStart, preStart + leftSize, postStart, index);
        // anything left for the right tree?
        if (preStart + leftSize + 1 <= preEnd) {
            root.right = new TreeNode();
            constructTree(root.right, preorder, postorder, preStart + leftSize + 1, preEnd, index + 1, postEnd - 1);
        }
    }
}
