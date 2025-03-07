package cz.braza.leetcode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/*
Daily 26.1.2025

2127. Maximum Employees to Be Invited to a Meeting
Hard
Topics
Companies
Hint
A company is organizing a meeting and has a list of n employees, waiting to be invited. They have arranged for a large circular table, capable of seating any number of employees.

The employees are numbered from 0 to n - 1. Each employee has a favorite person and they will attend the meeting only if they can sit next to their favorite person at the table. The favorite person of an employee is not themself.

Given a 0-indexed integer array favorite, where favorite[i] denotes the favorite person of the ith employee, return the maximum number of employees that can be invited to the meeting.



Example 1:


Input: favorite = [2,2,1,2]
Output: 3
Explanation:
The above figure shows how the company can invite employees 0, 1, and 2, and seat them at the round table.
All employees cannot be invited because employee 2 cannot sit beside employees 0, 1, and 3, simultaneously.
Note that the company can also invite employees 1, 2, and 3, and give them their desired seats.
The maximum number of employees that can be invited to the meeting is 3.
Example 2:

Input: favorite = [1,2,0]
Output: 3
Explanation:
Each employee is the favorite person of at least one other employee, and the only way the company can invite them is if they invite every employee.
The seating arrangement will be the same as that in the figure given in example 1:
- Employee 0 will sit between employees 2 and 1.
- Employee 1 will sit between employees 0 and 2.
- Employee 2 will sit between employees 1 and 0.
The maximum number of employees that can be invited to the meeting is 3.
Example 3:


Input: favorite = [3,0,1,4,1]
Output: 4
Explanation:
The above figure shows how the company will invite employees 0, 1, 3, and 4, and seat them at the round table.
Employee 2 cannot be invited because the two spots next to their favorite employee 1 are taken.
So the company leaves them out of the meeting.
The maximum number of employees that can be invited to the meeting is 4.


Constraints:

n == favorite.length
2 <= n <= 10^5
0 <= favorite[i] <= n - 1
favorite[i] != i
 */
public class L2127MaxEmployeesAtTable {
    /*
    From Editorial, removed Math.xxx function calls and some curly braces

    Process:
    Find all nodes/persons (if any) with no indegree, that is nobody wants to sit next to them
    From all those, find "depth" of any node - that is longest chain that leads to them.
    Then detect cycles like a->b, b->a or longer: a->b, b->c, c->d, d->a
    Any two person cycle (they mutually like each other) we can extend it by depth of those two nodes
    Otherwise, just find longest cycle (we can use this, unless we find more of those extended two cycles.

    Runs 21ms, beats 80.1%, 98 testcases
     */
    public static int maximumInvitations(int[] favorite) {
        int n = favorite.length;
        int[] inDegree = new int[n];

        // Calculate in-degree for each node
        for (int i : favorite) inDegree[i]++;

        // Topological sorting to remove non-cycle nodes
        Queue<Integer> q = new LinkedList<>();
        for (int person = 0; person < n; ++person)
            if (inDegree[person] == 0)
                q.offer(person);

        int[] depth = new int[n]; // Depth of each node
        Arrays.fill(depth, 1);

        while (!q.isEmpty()) {
            int currentNode = q.poll();
            int nextNode = favorite[currentNode];
            if (depth[currentNode] + 1 > depth[nextNode]) depth[nextNode] = depth[currentNode] + 1;
            if (--inDegree[nextNode] == 0)
                q.offer(nextNode);
        }

        int longestCycle = 0;
        int twoCycleInvitations = 0;

        // Detect cycles
        for (int person = 0; person < n; ++person) {
            if (inDegree[person] == 0) continue; // Already processed

            int cycleLength = 0;
            int current = person;
            while (inDegree[current] != 0) {
                inDegree[current] = 0; // Mark as visited
                cycleLength++;
                current = favorite[current];
            }

            if (cycleLength == 2)
                // For 2-cycles, add the depth of both nodes
                twoCycleInvitations += depth[person] + depth[favorite[person]];
            else if (longestCycle < cycleLength)
                longestCycle = cycleLength;
        }

        return longestCycle > twoCycleInvitations ? longestCycle : twoCycleInvitations;
    }

    public static void main(String[] args) {
        // 4x two cycles, then one full 5 cycle:
        int[] test1 = {1, 0, 3, 2, 5, 4, 7, 6, 9, 10, 11, 12, 8};
        System.out.println(maximumInvitations(test1));
        // 3x 2 cycles, extend one by 3 from one side and 3 from the other side,
        // then one full 10 cycle:
        int[] test2 = {1, 2, 3, 4, 3, 4, 5, 6, 9, 8, 11, 10, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 12};
        System.out.println(maximumInvitations(test2));
    }
}
