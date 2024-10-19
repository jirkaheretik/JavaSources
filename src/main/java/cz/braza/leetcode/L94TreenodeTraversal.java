package cz.braza.leetcode;

import java.util.ArrayList;
import java.util.List;

public class L94TreenodeTraversal {
    public static void traverseNode(List<Integer> list, TreeNode node) {
        if (node.left != null) traverseNode(list, node.left);
        list.add(node.val);
        if (node.right != null) traverseNode(list, node.right);
    }

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root != null) traverseNode(result, root);
        return result;
    }

}
