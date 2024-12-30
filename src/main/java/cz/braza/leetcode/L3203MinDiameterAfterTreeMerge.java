package cz.braza.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
3203. Find Minimum Diameter After Merging Two Trees
Hard
Topics
Companies
Hint
There exist two undirected trees with n and m nodes, numbered from 0 to n - 1 and from 0 to m - 1, respectively. You are given two 2D integer arrays edges1 and edges2 of lengths n - 1 and m - 1, respectively, where edges1[i] = [ai, bi] indicates that there is an edge between nodes ai and bi in the first tree and edges2[i] = [ui, vi] indicates that there is an edge between nodes ui and vi in the second tree.

You must connect one node from the first tree with another node from the second tree with an edge.

Return the minimum possible diameter of the resulting tree.

The diameter of a tree is the length of the longest path between any two nodes in the tree.



Example 1:

Input: edges1 = [[0,1],[0,2],[0,3]], edges2 = [[0,1]]

Output: 3

Explanation:

We can obtain a tree of diameter 3 by connecting node 0 from the first tree with any node from the second tree.

Example 2:


Input: edges1 = [[0,1],[0,2],[0,3],[2,4],[2,5],[3,6],[2,7]], edges2 = [[0,1],[0,2],[0,3],[2,4],[2,5],[3,6],[2,7]]

Output: 5

Explanation:

We can obtain a tree of diameter 5 by connecting node 0 from the first tree with node 0 from the second tree.



Constraints:

1 <= n, m <= 105
edges1.length == n - 1
edges2.length == m - 1
edges1[i].length == edges2[i].length == 2
edges1[i] = [ai, bi]
0 <= ai, bi < n
edges2[i] = [ui, vi]
0 <= ui, vi < m
The input is generated such that edges1 and edges2 represent valid trees.
 */
public class L3203MinDiameterAfterTreeMerge {
    /*
    Based on editorial and BFS - main issue is to find the diameter of a graph,
    we use adjacency list down the road, built from edges array just once.
    Added DFS as well to compare running time.
    BFS:
    Runs 151ms, beats 37.8%
    DFS:
    Runs: 116ms, beats 59.1%
     */
    public int minimumDiameterAfterMerge(int[][] edges1, int[][] edges2) {
        // number of nodes is edges + 1, as each edge adds one new node, otherwise there is a cycle (not a tree)
        int s1 = edges1.length + 1;
        int s2 = edges2.length + 1;
        // build adjacency lists
        List<List<Integer>> adj1 = buildAdjacency(s1, edges1);
        List<List<Integer>> adj2 = buildAdjacency(s2, edges2);
        /* use BFS:
        int left = findDiameter(s1, adj1);
        int right = findDiameter(s2, adj2);
        */
        // use DFS:
        int left = findDFSDiameter(adj1, 0, -1)[0];
        int right = findDFSDiameter(adj2, 0, -1)[0];

        int together = (left + 1) / 2 + (right + 1) / 2 + 1;
        return Math.max(Math.max(left, right), together);
    }

    /*
    For a graph given by array of edges, get the longest path = diameter
     */
    public int getDiameter(int[][] edges) {
        return 0;
    }

    public static int findDiameter(int size, List<List<Integer>> adj) {
        // pick any arbitrary node and find farthest node (one end of diameter)
        int[] temp = findFarthestNode(size, adj, 0);
        // then go from there and find farthest
        int[] second = findFarthestNode(size, adj, temp[0]);
        // return distance only
        return second[1];
    }

    public static int[] findFarthestNode(int size, List<List<Integer>> adj, int source) {
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[size];
        queue.add(source);
        visited[source] = true;
        int maxDistance = 0;
        int farthest = source;
        while (!queue.isEmpty()) {
            int qsize = queue.size();
            for (int i = 0; i < qsize; i++) {
                int current = queue.poll();
                farthest = current;
                // find neighbors and process
                for (int neighbor: adj.get(current))
                    if (!visited[neighbor]) {
                        visited[neighbor] = true;
                        queue.add(neighbor);
                    }
            }
            if (!queue.isEmpty()) maxDistance++;
        }
        return new int[]{farthest, maxDistance};
    }

    private List<List<Integer>> buildAdjacency(int size, int[][] edges) {
        List<List<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i < size; i++)
            adjList.add(new ArrayList<>());
        for (int[] edge : edges) {
            adjList.get(edge[0]).add(edge[1]);
            adjList.get(edge[1]).add(edge[0]);
        }
        return adjList;
    }

    /* we go dfs and track TWO largest depths disregarding parent tree (if any)
    Function returns two values - diameter and depth of subtree
    * */
    public static int[] findDFSDiameter(List<List<Integer>> adj, int node, int parent) {
        int maxDepth1 = 0, maxDepth2 = 0;
        int diameter = 0;
        for (int neighbor: adj.get(node)) {
            if (neighbor == parent) continue;
            int[] res = findDFSDiameter(adj, neighbor, node);
            // res[0] diameter, res[1]+1 is depth
            if (res[0] > diameter) diameter = res[0];
            int depth = res[1] + 1; // to include path to neighbor
            if (depth > maxDepth1) {
                maxDepth2 = maxDepth1;
                maxDepth1 = depth;
            } else if (depth > maxDepth2)
                maxDepth2 = depth;
        }
        // update diameter, if it goes through current:
        if (maxDepth1 + maxDepth2 > diameter)
            diameter = maxDepth1 + maxDepth2;
        return new int[]{diameter, maxDepth1};
    }
}
