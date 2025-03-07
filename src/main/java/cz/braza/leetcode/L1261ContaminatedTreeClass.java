package cz.braza.leetcode;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

/*
Daily 21.2.2025

1261. Find Elements in a Contaminated Binary Tree
Medium
Topics
Companies
Hint
Given a binary tree with the following rules:

root.val == 0
For any treeNode:
If treeNode.val has a value x and treeNode.left != null, then treeNode.left.val == 2 * x + 1
If treeNode.val has a value x and treeNode.right != null, then treeNode.right.val == 2 * x + 2
Now the binary tree is contaminated, which means all treeNode.val have been changed to -1.

Implement the FindElements class:

FindElements(TreeNode* root) Initializes the object with a contaminated binary tree and recovers it.
bool find(int target) Returns true if the target value exists in the recovered binary tree.


Example 1:


Input
["FindElements","find","find"]
[[[-1,null,-1]],[1],[2]]
Output
[null,false,true]
Explanation
FindElements findElements = new FindElements([-1,null,-1]);
findElements.find(1); // return False
findElements.find(2); // return True
Example 2:


Input
["FindElements","find","find","find"]
[[[-1,-1,-1,-1,-1]],[1],[3],[5]]
Output
[null,true,true,false]
Explanation
FindElements findElements = new FindElements([-1,-1,-1,-1,-1]);
findElements.find(1); // return True
findElements.find(3); // return True
findElements.find(5); // return False
Example 3:


Input
["FindElements","find","find","find","find"]
[[[-1,null,-1,-1,null,-1]],[2],[3],[4],[5]]
Output
[null,true,false,false,true]
Explanation
FindElements findElements = new FindElements([-1,null,-1,-1,null,-1]);
findElements.find(2); // return True
findElements.find(3); // return False
findElements.find(4); // return False
findElements.find(5); // return True


Constraints:

TreeNode.val == -1
The height of the binary tree is less than or equal to 20
The total number of nodes is between [1, 10^4]
Total calls of find() is between [1, 10^4]
0 <= target <= 10^6
 */
public class L1261ContaminatedTreeClass {
}

/* When invoking constructor, build a set of values it containes, based
on its position in a tree, not the actual value, which is contaminated.
Find() method simply checks if the target value is in the set.

Runs 21ms, beats 72.2%, 31 testcases - HashSet
23ms - TreeSet
399ms - ArrayList
 */

class FindElements {
    Set<Integer> numbers = new HashSet<>();
    public FindElements(TreeNode root) {
        if (root != null) addBranch(root, numbers, 0);
    }

    private void addBranch(TreeNode root, Set<Integer> numbers, int value) {
        numbers.add(value);
        if (root.left != null) addBranch(root.left, numbers, 2 * value + 1);
        if (root.right != null) addBranch(root.right, numbers, 2 * value + 2);
    }

    public boolean find(int target) {
        return numbers.contains(target);
    }
}
