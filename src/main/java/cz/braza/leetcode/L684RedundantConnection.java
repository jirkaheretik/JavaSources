package cz.braza.leetcode;

import java.util.Arrays;

/*
Daily 29.1.2025

684. Redundant Connection
Medium
Topics
Companies
In this problem, a tree is an undirected graph that is connected and has no cycles.

You are given a graph that started as a tree with n nodes labeled from 1 to n, with one additional edge added. The added edge has two different vertices chosen from 1 to n, and was not an edge that already existed. The graph is represented as an array edges of length n where edges[i] = [ai, bi] indicates that there is an edge between nodes ai and bi in the graph.

Return an edge that can be removed so that the resulting graph is a tree of n nodes. If there are multiple answers, return the answer that occurs last in the input.



Example 1:


Input: edges = [[1,2],[1,3],[2,3]]
Output: [2,3]
Example 2:


Input: edges = [[1,2],[2,3],[3,4],[1,4],[1,5]]
Output: [1,4]


Constraints:

n == edges.length
3 <= n <= 1000
edges[i].length == 2
1 <= ai < bi <= edges.length
ai != bi
There are no repeated edges.
The given graph is connected.
 */
public class L684RedundantConnection {
    /*
    First intuition (and wrong):
    make a boolean array of nodes that are already connected, mark both nodes true
    when an edge connects them, and if (before that) they are both true,
    this is the edge that makes a cycle, return it.
    Works well on examples, but fails otherwise (testcase 10/39), like: [[3,4],[1,2],[2,4],[3,5],[2,5]]
     */
    public int[] findRedundantConnectionFail(int[][] edges) {
        boolean[] connected = new boolean[edges.length + 1];
        for (int[] edge: edges)
            if (connected[edge[0]] && connected[edge[1]])
                return edge;
            else
                connected[edge[0]] = connected[edge[1]] = true;
        return new int[]{0, 0}; // just to be there for compiler to be happy
    }

    /*
    Mark a "group" for each node - and once an edge connects two nodes from the same group,
    it forms a cycle. Since there should only be one such edge, we return it.
    Easy, static (no dynamic structures), but O(n^2)

    Runs 4ms, beats 25.6%, 39 testcases
     */
    public static int[] findRedundantConnection(int[][] edges) {
        int[] groupNo = new int[edges.length + 1]; // to which group each node belongs
        for (int idx = 1; idx < groupNo.length; idx++) groupNo[idx] = idx;

        for (int[] edge: edges) {
            if (groupNo[edge[0]] == groupNo[edge[1]]) return edge;
                // if not, we need to group them together:
            else {
                int oldVal = groupNo[edge[1]]; // in order not to rewrite it too soon and do not finish cycle
                for (int node = 1; node < groupNo.length; node++)
                    if (groupNo[node] == oldVal) groupNo[node] = groupNo[edge[0]];
            }
        }
        return new int[]{0, 0}; // just to be there for compiler to be happy
    }

    public static void main(String[] args) {
        int[][] edges = {{3, 7}, {1, 4}, {2, 8}, {1, 6}, {7, 9}, {6, 10}, {1, 7}, {2, 3}, {8, 9},{5, 9}};
        System.out.println(Arrays.toString(findRedundantConnection(edges)));
    }


    /*
    From Editorial - using DSU (Disjoint Set Union)
     */
    class DSU {

        private int N; // size of DSU
        private int[] size;
        private int[] representative;

        // Initialize DSU class, size of each component will be one and each node
        // will be representative of it's own.
        public DSU(int N) {
            this.N = N;
            size = new int[N];
            representative = new int[N];

            for (int node = 0; node < N; node++) {
                size[node] = 1;
                representative[node] = node;
            }
        }

        // Returns the ultimate representative of the node.
        public int find(int node) {
            if (representative[node] == node)
                return node;

            return representative[node] = find(representative[node]);
        }

        // Returns true if node nodeOne and nodeTwo belong to different component and update the
        // representatives accordingly, otherwise returns false.
        public boolean doUnion(int nodeOne, int nodeTwo) {
            nodeOne = find(nodeOne);
            nodeTwo = find(nodeTwo);

            if (nodeOne == nodeTwo)
                return false;
            else
                if (size[nodeOne] > size[nodeTwo]) {
                    representative[nodeTwo] = nodeOne;
                    size[nodeOne] += size[nodeTwo];
                } else {
                    representative[nodeOne] = nodeTwo;
                    size[nodeTwo] += size[nodeOne];
                }
            return true;
        }
    }

    /*
    Runs 0ms, beats 100%, 39 testcases
     */
    public int[] findRedundantConnectionDSU(int[][] edges) {
        DSU dsu = new DSU(edges.length + 1);

        for (int[] edge: edges)
            // If union returns false, we know the nodes are already connected
            // and hence we can return this edge.
            if (!dsu.doUnion(edge[0], edge[1]))
                return edge;
        return new int[]{0, 0}; // just to be there for compiler to be happy
    }
}
