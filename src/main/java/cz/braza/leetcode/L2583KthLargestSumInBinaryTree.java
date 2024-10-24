package cz.braza.leetcode;

import java.util.*;

/*
2583. Kth Largest Sum in a Binary Tree
Medium
Topics
Companies
Hint
You are given the root of a binary tree and a positive integer k.

The level sum in the tree is the sum of the values of the nodes that are on the same level.

Return the kth largest level sum in the tree (not necessarily distinct). If there are fewer than k levels in the tree, return -1.

Note that two nodes are on the same level if they have the same distance from the root.



Example 1:


Input: root = [5,8,9,2,1,3,7,4,6], k = 2
Output: 13
Explanation: The level sums are the following:
- Level 1: 5.
- Level 2: 8 + 9 = 17.
- Level 3: 2 + 1 + 3 + 7 = 13.
- Level 4: 4 + 6 = 10.
The 2nd largest level sum is 13.
Example 2:


Input: root = [1,2,null,3], k = 1
Output: 3
Explanation: The largest level sum is 3.


Constraints:

The number of nodes in the tree is n.
2 <= n <= 105
1 <= Node.val <= 106
1 <= k <= n
 */
public class L2583KthLargestSumInBinaryTree {
    public static final int LIMIT = 500000;
    /*
    Straightforward first solution,
    45 ms, beats 12 %
     */
    public long kthLargestLevelSum(TreeNode root, int k) {
        long[] levelSums = new long[LIMIT];
        getSum(root, 0, levelSums);
        Arrays.sort(levelSums);
        long result = levelSums[levelSums.length - k];
        return result == 0 ? -1 : result;
    }

    public static void getSum(TreeNode node, int level, long[] sums) {
        sums[level] += node.val;
        if (node.left != null)
            getSum(node.left, level + 1, sums);
        if (node.right != null)
            getSum(node.right, level + 1, sums);
    }

    // V2 - ArrayList instead of long fixed length array
    // 35 ms beats 53.4 %
    public long kthLargestLevelSumAList(TreeNode root, int k) {
        List<Long> levelSums = new ArrayList<>();
        getSumAList(root, 0, levelSums);
        Collections.sort(levelSums);
        return k > levelSums.size() ? -1 : levelSums.get(levelSums.size() - k);
    }

    public static void getSumAList(TreeNode node, int level, List<Long> sums) {
        if (sums.size() <= level)
            sums.add((long)node.val);
        else
            sums.set(level, sums.get(level) + node.val);
        if (node.left != null)
            getSumAList(node.left, level + 1, sums);
        if (node.right != null)
            getSumAList(node.right, level + 1, sums);
    }

    /*
    From Solutions/Editorial
    PriorityQueue to store level sum values,
    BFS, doing always whole level at once, summing and storing in the queue
    at the end remove k-1 values and return the next (or -1 if there are not enough levels)
     */
    public long kthLargestLevelSumEditorial(TreeNode root, int k) {
        // max heap
        PriorityQueue<Long> pq = new PriorityQueue<>(Collections.reverseOrder());

        Queue<TreeNode> bfsQueue = new LinkedList<>();
        bfsQueue.add(root);
        while (!bfsQueue.isEmpty()) {
            //level order traversal
            int size = bfsQueue.size();
            long sum = 0;
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
            pq.add(sum);
        }
        // return -1 if not enough levels
        if (pq.size() < k) return -1;
        // remove k-1 levels, return the next (k-th highest)
        for (int i = 0; i < k - 1; i++) pq.remove();
        return pq.peek();
    }
}
