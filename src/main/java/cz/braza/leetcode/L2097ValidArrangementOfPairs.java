package cz.braza.leetcode;

import java.util.*;

/*
2097. Valid Arrangement of Pairs
Hard
Topics
Companies
Hint
You are given a 0-indexed 2D integer array pairs where pairs[i] = [starti, endi]. An arrangement of pairs is valid if for every index i where 1 <= i < pairs.length, we have endi-1 == starti.

Return any valid arrangement of pairs.

Note: The inputs will be generated such that there exists a valid arrangement of pairs.



Example 1:

Input: pairs = [[5,1],[4,5],[11,9],[9,4]]
Output: [[11,9],[9,4],[4,5],[5,1]]
Explanation:
This is a valid arrangement since endi-1 always equals starti.
end0 = 9 == 9 = start1
end1 = 4 == 4 = start2
end2 = 5 == 5 = start3
Example 2:

Input: pairs = [[1,3],[3,2],[2,1]]
Output: [[1,3],[3,2],[2,1]]
Explanation:
This is a valid arrangement since endi-1 always equals starti.
end0 = 3 == 3 = start1
end1 = 2 == 2 = start2
The arrangements [[2,1],[1,3],[3,2]] and [[3,2],[2,1],[1,3]] are also valid.
Example 3:

Input: pairs = [[1,2],[1,3],[2,1]]
Output: [[1,2],[2,1],[1,3]]
Explanation:
This is a valid arrangement since endi-1 always equals starti.
end0 = 2 == 2 = start1
end1 = 1 == 1 = start2


Constraints:

1 <= pairs.length <= 105
pairs[i].length == 2
0 <= starti, endi <= 109
starti != endi
No two pairs are exactly the same.
There exists a valid arrangement of pairs.
 */
public class L2097ValidArrangementOfPairs {
    /*
    Look then for problem L332, should be same or similar.
    Insight:
    Look at the problem as Eulerian path (goes through all the edges),
    numbers are nodes, pairs are connections/vertices.

    Process pairs, build adjancency list (where to go from current node),
    but we need to know also a number of incoming edges to find a starting node.
    We then pick a starting number and visit any node from there. Once we are stuck
    (no more outgoing edges), we add the node to the solution and backtrack,
    picking another edge until nothing is left and we are back at the starting node.
    Then construct a result from the list of nodes.
    Runs 116ms, beats 96.1%
     */
    public int[][] validArrangement(int[][] pairs) {
        HashMap<Integer, Integer> incoming = new HashMap<>();
        Map<Integer, LinkedList<Integer>> adjacency = new HashMap();
        // process pairs/edges:
        for (int[] pair: pairs) {
            int from = pair[0];
            int to = pair[1];
            // add incoming line:
            incoming.put(to, incoming.getOrDefault(to, 0) + 1);
            // add outgoing line:
            if (!adjacency.containsKey(from))
                adjacency.put(from, new LinkedList<>());
            adjacency.get(from).add(to);
        }
        // find starting node (pick any first, then check if we have one that out > in):
        int node = adjacency.keySet().iterator().next();
        for (int adj: adjacency.keySet()) {
            if (adjacency.get(adj).size() > incoming.getOrDefault(adj, 0)) {
                node = adj;
                break;
            }
        }
        // initialize resulting traverse order:
        LinkedList<Integer> order = new LinkedList<>();
        visit(node, adjacency, order);
        // now contruct the result pair list from the traverse order
        int[][] result = new int[order.size() - 1][];
        int index = 0;
        int last = -1;
        for (int next: order) {
            if (last >= 0) {
                result[index++] = new int[]{last, next};
            }
            last = next;
        }
        return result;
    }

    private static void visit(int node, Map<Integer, LinkedList<Integer>> adjacency, LinkedList<Integer> order) {
        // do we have where to go?
        while (adjacency.getOrDefault(node, new LinkedList<>()).size() > 0) {
            // if so, pick/remove target node and visit it:
            int newNode = adjacency.get(node).pop();
            visit(newNode, adjacency, order);
        }
        // if not, add the node to the list and backtrack
        order.addFirst(node);
    }
}
