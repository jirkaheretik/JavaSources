package cz.braza.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
2641. Cousins in Binary Tree II
Medium
Topics
Companies
Hint
Given the root of a binary tree, replace the value of each node in the tree with the sum of all its cousins' values.

Two nodes of a binary tree are cousins if they have the same depth with different parents.

Return the root of the modified tree.

Note that the depth of a node is the number of edges in the path from the root node to it.
 */
public class L2641CousinsInBinaryTree2 {
    /*
    First attempt. Use L2583 and find level sum of all levels.
    Then traverse it again, this time replacing values
    Maybe I can get it through parent, to find the sum of its children and replace with total-sum
    Runs 26ms, beats 54.7 % (
     */
    public TreeNode replaceValueInTree(TreeNode root) {
        List<Integer> levelSums = new ArrayList<>();
        Queue<TreeNode> bfsQueue = new LinkedList<>();
        bfsQueue.add(root);
        while (!bfsQueue.isEmpty()) {
            //level order traversal
            int size = bfsQueue.size();
            int sum = 0;
            for (int i = 0; i < size; i++) {
                TreeNode poppedNode = bfsQueue.remove();
                sum += poppedNode.val;
                if (poppedNode.left != null)
                    //add left child
                    bfsQueue.add(poppedNode.left);
                if (poppedNode.right != null)
                    // add right child
                    bfsQueue.add(poppedNode.right);
            }
            levelSums.add(sum);
        }
        // root does not have cousins:
        root.val = 0;
        traverseAndUpdate(root, 0, levelSums);
        return root; // updated tree
    }

    public static void traverseAndUpdate(TreeNode node, int level, List<Integer> sums) {
        boolean hasLeftNode = node.left != null;
        boolean hasRightNode = node.right != null;
        if (!hasLeftNode && !hasRightNode)
            return; // nothing to do here
        // get sum of child nodes:
        int sum = (hasLeftNode ? node.left.val : 0) + (hasRightNode ? node.right.val : 0);
        // update their values with total (from sums) - sum value
        if (hasLeftNode)
            node.left.val = sums.get(level + 1) - sum;
        if (hasRightNode)
            node.right.val = sums.get(level + 1) - sum;
        // traverse them as well
        if (node.left != null)
            traverseAndUpdate(node.left, level + 1, sums);
        if (node.right != null)
            traverseAndUpdate(node.right, level + 1, sums);
    }
}
