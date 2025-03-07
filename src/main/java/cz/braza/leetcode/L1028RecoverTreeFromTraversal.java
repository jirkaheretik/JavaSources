package cz.braza.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
Daily 22.2.2025

1028. Recover a Tree From Preorder Traversal
Hard
Topics
Companies
Hint
We run a preorder depth-first search (DFS) on the root of a binary tree.

At each node in this traversal, we output D dashes (where D is the depth of this node), then we output the value of this node.  If the depth of a node is D, the depth of its immediate child is D + 1.  The depth of the root node is 0.

If a node has only one child, that child is guaranteed to be the left child.

Given the output traversal of this traversal, recover the tree and return its root.



Example 1:


Input: traversal = "1-2--3--4-5--6--7"
Output: [1,2,5,3,4,6,7]
Example 2:


Input: traversal = "1-2--3---4-5--6---7"
Output: [1,2,5,3,null,6,null,4,null,7]
Example 3:


Input: traversal = "1-401--349---90--88"
Output: [1,401,null,349,88,90]


Constraints:

The number of nodes in the original tree is in the range [1, 1000].
1 <= Node.val <= 10^9
 */
public class L1028RecoverTreeFromTraversal {
    /*
    First parse the string into array of integers (level, value), then recursively build a tree from that.

    Runs 7ms, beats 42.5%, 105 testcases
     */
    public static TreeNode recoverFromPreorder(String traversal) {
        // parse string into integer pairs array, pair being number of dashes and value
        Queue<Integer> pairs = parseString(traversal);
        // then recursively build the tree from the array and return the root
        TreeNode root = new TreeNode();
        buildTreeHelper(root, pairs, 0);
        return root;
    }

    public static Queue<Integer> parseString(String traversal) {
        Queue<Integer> pairs = new LinkedList<>();
        int i = 0;
        while (i < traversal.length()) {
            int count = 0;
            while (i < traversal.length() && traversal.charAt(i) == '-') {
                count++;
                i++;
            }
            int value = 0;
            while (i < traversal.length() && Character.isDigit(traversal.charAt(i))) {
                value = value * 10 + Character.getNumericValue(traversal.charAt(i));
                i++;
            }
            pairs.add(count);
            pairs.add(value);
        }
        return pairs;
    }

    public static void buildTreeHelper(TreeNode node, Queue<Integer> pairs, int level) {
        if (pairs.isEmpty() || pairs.peek() < level) return; // cannot go any deeper
        // is current value for me? (I mean, it SHOULD BE)
        if (pairs.peek() == level) {
            pairs.poll();
            node.val = pairs.poll();
            if (!pairs.isEmpty() && pairs.peek() > level) {
                node.left = new TreeNode();
                buildTreeHelper(node.left, pairs, level + 1);
            }
            if (!pairs.isEmpty() && pairs.peek() > level) {
                node.right = new TreeNode();
                buildTreeHelper(node.right, pairs, level + 1);
            }
        } else {
            System.out.println("Error: invalid level in traversal");
        }
    }

    public static void main(String[] args) {
        // testing:
        TreeNode result = recoverFromPreorder("1-2--3--4-5--6--7");
        System.out.println("Should be 1: " + result.val);
        System.out.println("Should be 3: " + result.left.left.val);
        System.out.println("Should be 6: " + result.right.left.val);
        System.out.println("Should be 7: " + result.right.right.val);
        System.out.println("Should be null: " + result.left.left.left);
    }
}
