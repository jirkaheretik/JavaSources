package cz.braza.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
Daily 27.1.2025

1462. Course Schedule IV
Medium
Topics
Companies
Hint
There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1. You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course ai first if you want to take course bi.

For example, the pair [0, 1] indicates that you have to take course 0 before you can take course 1.
Prerequisites can also be indirect. If course a is a prerequisite of course b, and course b is a prerequisite of course c, then course a is a prerequisite of course c.

You are also given an array queries where queries[j] = [uj, vj]. For the jth query, you should answer whether course uj is a prerequisite of course vj or not.

Return a boolean array answer, where answer[j] is the answer to the jth query.



Example 1:


Input: numCourses = 2, prerequisites = [[1,0]], queries = [[0,1],[1,0]]
Output: [false,true]
Explanation: The pair [1, 0] indicates that you have to take course 1 before you can take course 0.
Course 0 is not a prerequisite of course 1, but the opposite is true.
Example 2:

Input: numCourses = 2, prerequisites = [], queries = [[1,0],[0,1]]
Output: [false,false]
Explanation: There are no prerequisites, and each course is independent.
Example 3:


Input: numCourses = 3, prerequisites = [[1,2],[1,0],[2,0]], queries = [[1,0],[1,2]]
Output: [true,true]


Constraints:

2 <= numCourses <= 100
0 <= prerequisites.length <= (numCourses * (numCourses - 1) / 2)
prerequisites[i].length == 2
0 <= ai, bi <= numCourses - 1
ai != bi
All the pairs [ai, bi] are unique.
The prerequisites graph has no cycles.
1 <= queries.length <= 10^4
0 <= ui, vi <= numCourses - 1
ui != vi
 */
public class L1462CourseSchedule {
    /*
    First we build an adjacency matrix (array of arraylists) from the prerequisities array
    Then we fill in reachable matrix (2D boolean array of size numCourses x numCourses) from adj list
    Next just process queries and fill in resulting _list_ (sic!) (should be a boolean array, since we know the size beforehand)

    Runs 20ms, beats 86.6%, 69 testcases
     */
    public List<Boolean> checkIfPrerequisite(int numCourses, int[][] prerequisites, int[][] queries) {
        // build adjacency matrix:
        ArrayList<Integer>[] adj = new ArrayList[numCourses];
        for (int i = 0; i < numCourses; i++)
            adj[i] = new ArrayList<>();
        for (int[] pre: prerequisites)
            adj[pre[0]].add(pre[1]);
        // reachable matrix:
        boolean[][] reachable = new boolean[numCourses][numCourses];
        // fill in reachable array:
        for (int course = 0; course < numCourses; course++) {
            Queue<Integer> q = new LinkedList<>();
            q.offer(course);
            while (!q.isEmpty()) {
                int node = q.poll();
                for (int next: adj[node])
                    // if already marked we've been there (kinda like visited array)
                    if (!reachable[course][next]) {
                        reachable[course][next] = true;
                        q.offer(next);
                    }
            }
        }
        // process queries with answers from the matrix:
        List<Boolean> result = new ArrayList<>();
        for (int[] q: queries)
            result.add(reachable[q[0]][q[1]]);
        return result;
    }
}
