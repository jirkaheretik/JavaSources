package cz.braza.leetcode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/*
Daily 25.3.2025 - https://leetcode.com/problems/check-if-grid-can-be-cut-into-sections/

3394. Check if Grid can be Cut into Sections
Medium
Topics
Companies
Hint
You are given an integer n representing the dimensions of an n x n grid, with the origin at the bottom-left corner of the grid. You are also given a 2D array of coordinates rectangles, where rectangles[i] is in the form [startx, starty, endx, endy], representing a rectangle on the grid. Each rectangle is defined as follows:

(startx, starty): The bottom-left corner of the rectangle.
(endx, endy): The top-right corner of the rectangle.
Note that the rectangles do not overlap. Your task is to determine if it is possible to make either two horizontal or two vertical cuts on the grid such that:

Each of the three resulting sections formed by the cuts contains at least one rectangle.
Every rectangle belongs to exactly one section.
Return true if such cuts can be made; otherwise, return false.



Example 1:

Input: n = 5, rectangles = [[1,0,5,2],[0,2,2,4],[3,2,5,3],[0,4,4,5]]

Output: true

Explanation:



The grid is shown in the diagram. We can make horizontal cuts at y = 2 and y = 4. Hence, output is true.

Example 2:

Input: n = 4, rectangles = [[0,0,1,1],[2,0,3,4],[0,2,2,3],[3,0,4,3]]

Output: true

Explanation:



We can make vertical cuts at x = 2 and x = 3. Hence, output is true.

Example 3:

Input: n = 4, rectangles = [[0,2,2,4],[1,0,3,2],[2,2,3,4],[3,0,4,2],[3,2,4,4]]

Output: false

Explanation:

We cannot make two horizontal or two vertical cuts that satisfy the conditions. Hence, output is false.



Constraints:

3 <= n <= 10^9
3 <= rectangles.length <= 10^5
0 <= rectangles[i][0] < rectangles[i][2] <= n
0 <= rectangles[i][1] < rectangles[i][3] <= n
No two rectangles overlap.
 */
public class L3394CanGridBeCutIntoSections {
    /*
    Due to constraints we cannot do it in O(m+n), where m is rectanles.length, since n can be 4+ orders of magnitude higher
    Lets "sort" rectangles in both directions by using a priority queue, whenever we pick a new rectangle that starts after current end,
    we can make a cut, otherwise just move the end and pick next rectangle. If two cuts can be made, we are done,
    otherwise process the other direction too.
    Runs 144ms, beats 15.5%, 694 testcases
     */
    public boolean checkValidCuts(int n, int[][] rectangles) {
        PriorityQueue<int[]> pqCols = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        PriorityQueue<int[]> pqRows = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        for (int[] rectangle : rectangles) {
            pqCols.offer(new int[]{rectangle[1], rectangle[3]});
            pqRows.offer(new int[]{rectangle[0], rectangle[2]});
        }
        // process columns:
        int[] first = pqCols.poll();
        int end = first[1];
        boolean firstCut = false;
        while (!pqCols.isEmpty()) {
            int[] cut = pqCols.poll();
            if (cut[0] < end) { if (cut[1] > end) end = cut[1]; }
            else {
                if (!firstCut) firstCut = true; else return true;
                end = cut[1];
            }
        }
        // process rows:
        first = pqRows.poll();
        end = first[1];
        firstCut = false;
        while (!pqRows.isEmpty()) {
            int[] cut = pqRows.poll();
            if (cut[0] < end) { if (cut[1] > end) end = cut[1]; }
            else {
                if (!firstCut) firstCut = true; else return true;
                end = cut[1];
            }
        }
        return false;
    }

    /*
    Same logic, but instead of PQ just sort the rectangles array
    Runs 123ms, beats 25.2%, 694 testcases
     */
    public boolean checkValidCutsArr(int n, int[][] rectangles) {
        // process columns:
        Arrays.sort(rectangles, Comparator.comparingInt(a -> a[0]));
        boolean firstCut = false;
        int end = rectangles[0][2];
        for (int[] rectangle : rectangles) {
            if (rectangle[0] >= end)
                if (!firstCut) { firstCut = true; end = rectangle[2]; }
                else return true;
            else if (rectangle[2] > end) end = rectangle[2];
        }
        // process rows:
        Arrays.sort(rectangles, Comparator.comparingInt(a -> a[1]));
        firstCut = false;
        end = rectangles[0][3];
        for (int[] rectangle : rectangles) {
            if (rectangle[1] >= end)
                if (!firstCut) { firstCut = true; end = rectangle[3]; }
                else return true;
            else if (rectangle[3] > end) end = rectangle[3];
        }
        return false;
    }
}
