package cz.braza.leetcode;

import java.util.ArrayList;
import java.util.List;

/*
2872. Maximum Number of K-Divisible Components
Hard
Topics
Companies
Hint
There is an undirected tree with n nodes labeled from 0 to n - 1. You are given the integer n and a 2D integer array edges of length n - 1, where edges[i] = [ai, bi] indicates that there is an edge between nodes ai and bi in the tree.

You are also given a 0-indexed integer array values of length n, where values[i] is the value associated with the ith node, and an integer k.

A valid split of the tree is obtained by removing any set of edges, possibly empty, from the tree such that the resulting components all have values that are divisible by k, where the value of a connected component is the sum of the values of its nodes.

Return the maximum number of components in any valid split.



Example 1:


Input: n = 5, edges = [[0,2],[1,2],[1,3],[2,4]], values = [1,8,1,4,4], k = 6
Output: 2
Explanation: We remove the edge connecting node 1 with 2. The resulting split is valid because:
- The value of the component containing nodes 1 and 3 is values[1] + values[3] = 12.
- The value of the component containing nodes 0, 2, and 4 is values[0] + values[2] + values[4] = 6.
It can be shown that no other valid split has more than 2 connected components.
Example 2:


Input: n = 7, edges = [[0,1],[0,2],[1,3],[1,4],[2,5],[2,6]], values = [3,0,6,1,5,2,1], k = 3
Output: 3
Explanation: We remove the edge connecting node 0 with 2, and the edge connecting node 0 with 1. The resulting split is valid because:
- The value of the component containing node 0 is values[0] = 3.
- The value of the component containing nodes 2, 5, and 6 is values[2] + values[5] + values[6] = 9.
- The value of the component containing nodes 1, 3, and 4 is values[1] + values[3] + values[4] = 6.
It can be shown that no other valid split has more than 3 connected components.


Constraints:

1 <= n <= 3 * 10^4
edges.length == n - 1
edges[i].length == 2
0 <= ai, bi < n
values.length == n
0 <= values[i] <= 10^9
1 <= k <= 10^9
Sum of values is divisible by k.
The input is generated such that edges represents a valid tree.
 */
public class L2872MaxNumberOfKDivisibleComponents {
    /*
    Basically from editorial - first populate adjacency list, start at node 0
    as root (therefore parent == -1), since 0 has to be there and is the node that
    surely does not break any sums/components.
    Then dfs the tree, and whenever I get k-divisible sum from leafs, I can
    create new component (increase count).
    Runs 22ms, beats 93.5%
     */
    public int maxKDivisibleComponents(int n, int[][] edges, int[] values, int k) {
        List<Integer>[] adjacency = new ArrayList[n];
        // initalize to empty lists:
        for (int i = 0; i < n; i++)
            adjacency[i] = new ArrayList<>();
        // process edges:
        for (int[] edge: edges) {
            adjacency[edge[0]].add(edge[1]);
            adjacency[edge[1]].add(edge[0]);
        }
        for (int i = 0; i < n; i++)
            System.out.println("" + i + ". " + adjacency[i]);
        // pass by reference, as we need two values to return - remaining sum and this (number of components)
        int[] componentCount = new int[1];
        dfs(0, -1, adjacency, values, k, componentCount);
        return componentCount[0];
    }

    private int dfs(int current, int parent, List<Integer>[] connections, int[] nodeValues, int k, int[] components) {
        int sum = 0; // k-modulo of result sum
        for (int neighbor: connections[current])
            if (neighbor != parent) {
                sum += dfs(neighbor, current, connections, nodeValues, k, components);
                sum %= k;
            }
        // add my node
        sum += nodeValues[current];
        sum %= k;
        if (sum == 0) components[0]++; // this can be detached, new component is created
        return sum;
    }
}
