package cz.braza.leetcode;

/*
951. Flip Equivalent Binary Trees
Medium
Topics
Companies
For a binary tree T, we can define a flip operation as follows: choose any node, and swap the left and right child subtrees.

A binary tree X is flip equivalent to a binary tree Y if and only if we can make X equal to Y after some number of flip operations.

Given the roots of two binary trees root1 and root2, return true if the two trees are flip equivalent or false otherwise.
 */
public class L951FlipBinaryTrees {
    /*
    Intuition first:
    To be equivalent, trees have to have:
    - same root value
    - same number of nodes
    - same set of values
    - same values in same level
    - even same values in children (left/right is only thing we can flip)
     */
    public boolean flipEquiv(TreeNode root1, TreeNode root2) {
        if (root1 == null) return root2 == null;
        if (root2 == null) return false; // root1 is not null
        // now both are valid nodes
        if (root1.val != root2.val) return false;

        return (flipEquiv(root1.left, root2.left) && flipEquiv(root1.right, root2.right))
                ||
               (flipEquiv(root1.left, root2.right) && flipEquiv(root1.right, root2.left));
    }
}
