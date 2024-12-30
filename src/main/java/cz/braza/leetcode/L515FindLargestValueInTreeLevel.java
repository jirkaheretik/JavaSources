package cz.braza.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
515. Find Largest Value in Each Tree Row
Medium
Topics
Companies
Given the root of a binary tree, return an array of the largest value in each row of the tree (0-indexed).



Example 1:


Input: root = [1,3,2,5,3,null,9]
Output: [1,3,9]
Example 2:

Input: root = [1,2,3]
Output: [1,3]


Constraints:

The number of nodes in the tree will be in the range [0, 10^4].
-2^31 <= Node.val <= 2^31 - 1
 */
public class L515FindLargestValueInTreeLevel {
    /*
    Classic BFS, copied/updated from L3203
    Start with root, and for each "level" (we know number of nodes)
    we poll as many elements, remembering maximum, then put it in the result list.
    Runs 2ms beats 83.8%
     */
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int qsize = queue.size();
            int max = Integer.MIN_VALUE;
            for (int i = 0; i < qsize; i++) {
                TreeNode current = queue.poll();
                if (current.val > max) max = current.val;
                if (current.left != null) queue.offer(current.left);
                if (current.right != null) queue.offer(current.right);
            }
            result.add(max);
        }
        return result;
    }
}
