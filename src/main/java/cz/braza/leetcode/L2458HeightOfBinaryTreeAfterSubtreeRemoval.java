package cz.braza.leetcode;

import java.util.HashMap;
import java.util.Map;

/*
2458. Height of Binary Tree After Subtree Removal Queries
Hard
Topics
Companies
Hint
You are given the root of a binary tree with n nodes. Each node is assigned a unique value from 1 to n. You are also given an array queries of size m.

You have to perform m independent queries on the tree where in the ith query you do the following:

Remove the subtree rooted at the node with the value queries[i] from the tree. It is guaranteed that queries[i] will not be equal to the value of the root.
Return an array answer of size m where answer[i] is the height of the tree after performing the ith query.

Note:

The queries are independent, so the tree returns to its initial state after each query.
The height of a tree is the number of edges in the longest simple path from the root to some node in the tree.
 */
public class L2458HeightOfBinaryTreeAfterSubtreeRemoval {
    /*
    First try, no optimization, TLE on testcase 36 (passed 35/40)
     */
    public int[] treeQueries(TreeNode root, int[] queries) {
        int[] answers = new int[queries.length];
        for (int index = 0; index < queries.length; index++)
            answers[index] = treeHeightWithAStopper(root, queries[index]) - 1;
        return answers;
    }

    /**
     * DFS to find out the height of a tree, but will not go through node
     * with a specified value (that subtree is considered removed)
     * @param node
     * @param notHere
     * @return
     */
    public static int treeHeightWithAStopper(TreeNode node, int notHere) {
        return (node == null || node.val == notHere) ? 0 : 1 + Math.max(treeHeightWithAStopper(node.left, notHere), treeHeightWithAStopper(node.right, notHere));
    }

    /**
     * From Editorial, no 10k+ array to store precomputed values
     */
    public int[] treeQueriesSolution(TreeNode root, int[] queries) {
        Map<Integer, Integer> resultMap = new HashMap<>();
        Map<TreeNode, Integer> heightCache = new HashMap<>();

        // Run DFS to fill resultMap with maximum heights after each query
        dfs(root, 0, 0, resultMap, heightCache);

        int[] result = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            result[i] = resultMap.get(queries[i]);
        }
        return result;
    }

    // Function to calculate the height of the tree
    private int height(TreeNode node, Map<TreeNode, Integer> heightCache) {
        if (node == null) return -1;
        // Return cached height if already calculated
        if (heightCache.containsKey(node))
            return heightCache.get(node);
        int h = 1 + Math.max(height(node.left, heightCache), height(node.right, heightCache));
        heightCache.put(node, h);
        return h;
    }

    // DFS to precompute the maximum values after removing the subtree
    private void dfs(TreeNode node, int depth, int maxVal, Map<Integer, Integer> resultMap, Map<TreeNode, Integer> heightCache) {
        if (node == null) return;
        resultMap.put(node.val, maxVal);

        // Traverse left and right subtrees while updating max values
        dfs(node.left, depth + 1, Math.max(maxVal, depth + 1 + height(node.right, heightCache)), resultMap, heightCache);
        dfs(node.right, depth + 1, Math.max(maxVal, depth + 1 + height(node.left, heightCache)), resultMap, heightCache);
    }
}
