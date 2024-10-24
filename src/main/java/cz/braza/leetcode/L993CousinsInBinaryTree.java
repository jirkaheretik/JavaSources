package cz.braza.leetcode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/*
993. Cousins in Binary Tree
Easy
Topics
Companies
Given the root of a binary tree with unique values and the values of two different nodes of the tree x and y, return true if the nodes corresponding to the values x and y in the tree are cousins, or false otherwise.

Two nodes of a binary tree are cousins if they have the same depth with different parents.

Note that in a binary tree, the root node is at the depth 0, and children of each depth k node are at the depth k + 1.
 */
public class L993CousinsInBinaryTree {
    /*
    First try, using BFS from L2583 and L2641
    If we find our node, we can only return true if the other is in the same queue and has a different parent
     */
    public boolean isCousins(TreeNode root, int x, int y) {
        // deal with root first, so that at any time we know x and y nodes have parent:
        if (root.val == x || root.val == y) return false;

        boolean firstFound = false;
        int firstLevel = 0;
        Queue<TreeNode> bfsQueue = new LinkedList<>();
        Map<Integer, TreeNode> parents = new HashMap<>();
        bfsQueue.add(root);
        int level = 0;
        while (!bfsQueue.isEmpty()) {
            //level order traversal
            int size = bfsQueue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = bfsQueue.remove();
                if (node.val == x || node.val == y) {
                    if (firstFound) {
                        // we have second node, compare
                        // check level! return false if different
                        if (level != firstLevel) return false;
                        // then return true if different parent
                        TreeNode xParent = parents.get(x);
                        TreeNode yParent = parents.get(y);
                        return xParent != null && yParent != null && xParent.val != yParent.val;
                    } else {
                        firstFound = true;
                        firstLevel = level;
                    }
                }
                if (node.left != null) {
                    //add left child
                    bfsQueue.add(node.left);
                    parents.put(node.left.val, node);
                }
                if (node.right != null) {
                    // add right child
                    bfsQueue.add(node.right);
                    parents.put(node.right.val, node);
                }
            }
            level++;
        }
        return false;
    }

}
