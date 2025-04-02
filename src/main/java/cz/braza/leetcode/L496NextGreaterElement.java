package cz.braza.leetcode;

import java.util.HashMap;
import java.util.Stack;

/*
496. Next Greater Element I
Easy
Topics
Companies
The next greater element of some element x in an array is the first greater element that is to the right of x in the same array.

You are given two distinct 0-indexed integer arrays nums1 and nums2, where nums1 is a subset of nums2.

For each 0 <= i < nums1.length, find the index j such that nums1[i] == nums2[j] and determine the next greater element of nums2[j] in nums2. If there is no next greater element, then the answer for this query is -1.

Return an array ans of length nums1.length such that ans[i] is the next greater element as described above.



Example 1:

Input: nums1 = [4,1,2], nums2 = [1,3,4,2]
Output: [-1,3,-1]
Explanation: The next greater element for each value of nums1 is as follows:
- 4 is underlined in nums2 = [1,3,4,2]. There is no next greater element, so the answer is -1.
- 1 is underlined in nums2 = [1,3,4,2]. The next greater element is 3.
- 2 is underlined in nums2 = [1,3,4,2]. There is no next greater element, so the answer is -1.
Example 2:

Input: nums1 = [2,4], nums2 = [1,2,3,4]
Output: [3,-1]
Explanation: The next greater element for each value of nums1 is as follows:
- 2 is underlined in nums2 = [1,2,3,4]. The next greater element is 3.
- 4 is underlined in nums2 = [1,2,3,4]. There is no next greater element, so the answer is -1.


Constraints:

1 <= nums1.length <= nums2.length <= 1000
0 <= nums1[i], nums2[i] <= 10^4
All integers in nums1 and nums2 are unique.
All the integers of nums1 also appear in nums2.


Follow up: Could you find an O(nums1.length + nums2.length) solution?
 */
public class L496NextGreaterElement {
    /*
    First preprocess nums2 through stack to find next greater element for each (unique) value, set that into a hash map.
    Then process nums1, find the greater element (if any) and put it back, return new nums1 as answer.
    Runs 3ms, beats 90.1%, 16 testcases
     */
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        HashMap<Integer, Integer> map = new HashMap<>();
        Stack<Integer> stack = new Stack<>();
        for (int num: nums2) {
            while (!stack.isEmpty() && stack.peek() < num) {
                map.put(stack.pop(), num);
            }
            stack.push(num);
        }
        for (int idx = 0; idx < nums1.length; idx++)
            nums1[idx] = map.getOrDefault(nums1[idx], -1);
        return nums1;
    }

    /*
    Naive solution in O(m * n) instead of O(m + n), though for small constraints it works the same:
    Runs 3ms, beats 90.1%
     */
    public int[] nextGreaterElementNaive(int[] nums1, int[] nums2) {
        for (int idx = 0; idx < nums1.length; idx++) {
            // find the number in nums2:
            int idx2 = 0;
            while (nums1[idx] != nums2[idx2]) idx2++;
            // find the next greater number:
            int nextGreater = -1;
            for (int j = idx2 + 1; j < nums2.length; j++) {
                if (nums2[j] > nums1[idx]) {
                    nextGreater = nums2[j];
                    break;
                }
            }
            nums1[idx] = nextGreater;
        }
        return nums1;
    }
}
