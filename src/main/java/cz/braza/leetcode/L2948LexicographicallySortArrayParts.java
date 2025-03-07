package cz.braza.leetcode;

import java.util.Arrays;
import java.util.Comparator;

/*
Daily 25.1.2025

2948. Make Lexicographically Smallest Array by Swapping Elements
Medium
Topics
Companies
Hint
You are given a 0-indexed array of positive integers nums and a positive integer limit.

In one operation, you can choose any two indices i and j and swap nums[i] and nums[j] if |nums[i] - nums[j]| <= limit.

Return the lexicographically smallest array that can be obtained by performing the operation any number of times.

An array a is lexicographically smaller than an array b if in the first position where a and b differ, array a has an element that is less than the corresponding element in b. For example, the array [2,10,3] is lexicographically smaller than the array [10,2,3] because they differ at index 0 and 2 < 10.



Example 1:

Input: nums = [1,5,3,9,8], limit = 2
Output: [1,3,5,8,9]
Explanation: Apply the operation 2 times:
- Swap nums[1] with nums[2]. The array becomes [1,3,5,9,8]
- Swap nums[3] with nums[4]. The array becomes [1,3,5,8,9]
We cannot obtain a lexicographically smaller array by applying any more operations.
Note that it may be possible to get the same result by doing different operations.
Example 2:

Input: nums = [1,7,6,18,2,1], limit = 3
Output: [1,6,7,18,1,2]
Explanation: Apply the operation 3 times:
- Swap nums[1] with nums[2]. The array becomes [1,6,7,18,2,1]
- Swap nums[0] with nums[4]. The array becomes [2,6,7,18,1,1]
- Swap nums[0] with nums[5]. The array becomes [1,6,7,18,1,2]
We cannot obtain a lexicographically smaller array by applying any more operations.
Example 3:

Input: nums = [1,7,28,19,10], limit = 3
Output: [1,7,28,19,10]
Explanation: [1,7,28,19,10] is the lexicographically smallest array we can obtain because we cannot apply the operation on any two indices.


Constraints:

1 <= nums.length <= 10^5
1 <= nums[i] <= 10^9
1 <= limit <= 10^9
 */
public class L2948LexicographicallySortArrayParts {
    /*
    Try this approach not mentioned in solutions/editorial:
    - we can only swap elements that are at most `limit` away,
    - which basically allows us to sort array with similar values (diff between any two adjacent is no more than limit)
    Thus:
    - create another 2D array with pairs value-index, sort this array by VALUE
    - then start processing it, and find subarray that satisfies the condition (adjacent values are no more than limit off each other)
    - create a copy of this subarray, this time sort it by INDEX
    - process it, and assing lowest value into the result array to the indices in order
    This solution does not need any dynamic sized structure (List, Set, PriorityQueue and such)

    Works quite well:
    Runs 76ms, beats 84.9%, 523 testcases
     */
    public int[] lexicographicallySmallestArray(int[] nums, int limit) {
        int[][] work = new int[nums.length][2];
        for (int idx = 0; idx < nums.length; idx++) {
            work[idx][0] = nums[idx];
            work[idx][1] = idx;
        }
        Arrays.sort(work, Comparator.comparingInt(a -> a[0]));
        // now, find how far can we go:
        int startIndex = 0;
        while (startIndex < nums.length) {
            // find end index:
            int endIndex = startIndex;
            while (endIndex < nums.length - 1 && work[endIndex + 1][0] - work[endIndex][0] <= limit) endIndex++;
            // now we want to sort out a subarray starting at startIndex, ending at endIndex (included):
            int[] indices = new int[endIndex - startIndex + 1];
            for (int i = startIndex; i <= endIndex; i++)
                indices[i - startIndex] = work[i][1];
            Arrays.sort(indices);
            // fill in the result array:
            for (int index: indices)
                nums[index] = work[startIndex++][0];
            // now startIndex should be endIndex + 1, go again, if necessary
        }
        return nums;
    }
}
