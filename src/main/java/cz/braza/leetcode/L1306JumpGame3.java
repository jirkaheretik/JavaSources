package cz.braza.leetcode;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/*
1306. Jump Game III
Medium
Topics
Companies
Hint
Given an array of non-negative integers arr, you are initially positioned at start index of the array. When you are at index i, you can jump to i + arr[i] or i - arr[i], check if you can reach any index with value 0.

Notice that you can not jump outside of the array at any time.



Example 1:

Input: arr = [4,2,3,0,3,1,2], start = 5
Output: true
Explanation:
All possible ways to reach at index 3 with value 0 are:
index 5 -> index 4 -> index 1 -> index 3
index 5 -> index 6 -> index 4 -> index 1 -> index 3
Example 2:

Input: arr = [4,2,3,0,3,1,2], start = 0
Output: true
Explanation:
One possible way to reach at index 3 with value 0 is:
index 0 -> index 4 -> index 1 -> index 3
Example 3:

Input: arr = [3,0,2,1,2], start = 2
Output: false
Explanation: There is no way to reach at index 1 with value 0.


Constraints:

1 <= arr.length <= 5 * 104
0 <= arr[i] < arr.length
0 <= start < arr.length
 */
public class L1306JumpGame3 {
    /*
    First attempt. Make set of processed nodes (indices) and Stack or Queue of to process indices.
    Start at given index, get all nodes I can reach (zero, one, two) into toProcess. Pop from stack,
    add it to processed. If value is zero, return true. add target nodes/indices into toProcess,
    unless they are already processed. Return false once we empty toProcess
    (if we did not return true earlier).
    Runs: 17 ms, beats 8.5 %
     */
    public static boolean canReach(int[] arr, int start) {
        Stack<Integer> toProcess = new Stack<>();
        Set<Integer> processed = new HashSet<>();
        toProcess.push(start);
        do {
            int index = toProcess.pop();
            if (arr[index] == 0) return true;
            processed.add(index);
            int low = index - arr[index];
            if (low >= 0 && !processed.contains(low))
                toProcess.push(low);
            int high = index + arr[index];
            if (high < arr.length && !processed.contains(high))
                toProcess.push(high);
        } while (!toProcess.isEmpty());
        return false;
    }

    /*
    2nd try, same system, but checking arr values before inserting into toProcess
    Runs 15 ms, beats 13.7 %
     */
    public static boolean canReachCheckFirst(int[] arr, int start) {
        Stack<Integer> toProcess = new Stack<>();
        Set<Integer> processed = new HashSet<>();
        toProcess.push(start);
        do {
            int index = toProcess.pop();
            int low = index - arr[index];
            if (low >= 0 && !processed.contains(low))
                if (arr[low] == 0) return true;
                else toProcess.push(low);
            int high = index + arr[index];
            if (high < arr.length && !processed.contains(high))
                if (arr[high] == 0) return true;
                else toProcess.push(high);
            processed.add(index);
        } while (!toProcess.isEmpty());
        return false;
    }

    public static void main(String[] args) {
        int[] nums = {4,2,3,0,3,1,2};
        System.out.println(canReach(nums, 0));
    }
}
