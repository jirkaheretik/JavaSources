package cz.braza.leetcode;

/*
307. Range Sum Query - Mutable - https://leetcode.com/problems/range-sum-query-mutable/
Medium
Topics
Companies
Given an integer array nums, handle multiple queries of the following types:

Update the value of an element in nums.
Calculate the sum of the elements of nums between indices left and right inclusive where left <= right.
Implement the NumArray class:

NumArray(int[] nums) Initializes the object with the integer array nums.
void update(int index, int val) Updates the value of nums[index] to be val.
int sumRange(int left, int right) Returns the sum of the elements of nums between indices left and right inclusive (i.e. nums[left] + nums[left + 1] + ... + nums[right]).


Example 1:

Input
["NumArray", "sumRange", "update", "sumRange"]
[[[1, 3, 5]], [0, 2], [1, 2], [0, 2]]
Output
[null, 9, null, 8]

Explanation
NumArray numArray = new NumArray([1, 3, 5]);
numArray.sumRange(0, 2); // return 1 + 3 + 5 = 9
numArray.update(1, 2);   // nums = [1, 2, 5]
numArray.sumRange(0, 2); // return 1 + 2 + 5 = 8


Constraints:

1 <= nums.length <= 3 * 10^4
-100 <= nums[i] <= 100
0 <= index < nums.length
-100 <= val <= 100
0 <= left <= right < nums.length
At most 3 * 10^4 calls will be made to update and sumRange.
 */
public class L307RangeSumQuery {
}

/*
303. Range Sum Query Immutable - https://leetcode.com/problems/range-sum-query-immutable/
Just make the sum while instantiated, then only return the difference.
Runs 7ms, beats 100%, 15 testcases
 */
class NumArrayImmutable {
    int[] vals;

    public NumArrayImmutable(int[] nums) {
        vals = nums;
        int sum = 0;
        for (int idx = 0; idx < vals.length; idx++) {
            sum += vals[idx];
            vals[idx] = sum;
        }
    }

    public int sumRange(int left, int right) {
        return left > 0 ? vals[right] - vals[left - 1] : vals[right];
    }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * int param_1 = obj.sumRange(left,right);
 */


/*
307 - Mutable variant
First lets create one array with sums, let the other with values,
when a mutable call is made, count the difference and update sums from right onwards.
This leads to TLE in last testcase, though that one runs just 17ms alone.
*/
class NumArrayMutable {
    int[] sums;
    int[] vals;

    public NumArrayMutable(int[] nums) {
        vals = nums;
        sums = new int[nums.length];
        int sum = 0;
        for (int idx = 0; idx < sums.length; idx++) {
            sum += vals[idx];
            sums[idx] = sum;
        }
    }

    public void update(int index, int val) {
        int diff = val - vals[index];
        if (diff == 0) return; // no need to update
        vals[index] = val;
        for (int idx = index; idx < sums.length; idx++)
            sums[idx] += diff;
    }

    public int sumRange(int left, int right) {
        return left > 0 ? sums[right] - sums[left - 1] : sums[right];
    }
}

class NumArray {
    private int[] segmentTree; // Array to store segment tree
    private int arrayLength; // Length of the input array

    // Constructor to initialize the segment tree with the given array
    public NumArray(int[] nums) {
        this.arrayLength = nums.length; // Set the length of the input array
        if (arrayLength > 0) { // If array is not empty
            int height = (int) (Math.ceil(Math.log(arrayLength) / Math.log(2))); // Calculate height of the segment tree
            int maxSize = 2 * (int) Math.pow(2, height) - 1; // Maximum size of the segment tree
            segmentTree = new int[maxSize]; // Initialize the segment tree array
            buildSegmentTree(nums, 0, arrayLength - 1, 0); // Build the segment tree
        }
    }

    // Utility function to get the middle index of a range
    private int getMiddle(int start, int end) {
        return start + (end - start) / 2;
    }

    // Recursive function to build the segment tree
    private void buildSegmentTree(int[] arr, int segmentStart, int segmentEnd, int currentIndex) {
        // If the segment start is equal to the segment end, it's a leaf node
        if (segmentStart == segmentEnd) {
            segmentTree[currentIndex] = arr[segmentStart]; // Set the leaf node value
            return;
        }
        int mid = getMiddle(segmentStart, segmentEnd); // Get the middle index
        buildSegmentTree(arr, segmentStart, mid, currentIndex * 2 + 1); // Recursively build the left subtree
        buildSegmentTree(arr, mid + 1, segmentEnd, currentIndex * 2 + 2); // Recursively build the right subtree
        // Set the current node value as the sum of left and right children
        segmentTree[currentIndex] = segmentTree[currentIndex * 2 + 1] + segmentTree[currentIndex * 2 + 2];
    }

    // Function to update an element in the segment tree
    public void update(int index, int newValue) {
        if (arrayLength == 0) return; // If the array is empty, do nothing
        int difference = newValue - getElement(0, arrayLength - 1, index, 0); // Calculate the difference
        updateSegmentTree(0, arrayLength - 1, index, difference, 0); // Update the segment tree
    }

    // Utility function to get the value of an element in the segment tree
    private int getElement(int segmentStart, int segmentEnd, int queryIndex, int currentIndex) {
        // If the segment start is equal to the segment end, it's a leaf node
        if (segmentStart == segmentEnd)
            return segmentTree[currentIndex]; // Return the leaf node value
        int mid = getMiddle(segmentStart, segmentEnd); // Get the middle index
        // Recursively get the element value from the left or right subtree
        if (queryIndex <= mid)
            return getElement(segmentStart, mid, queryIndex, 2 * currentIndex + 1);
        return getElement(mid + 1, segmentEnd, queryIndex, 2 * currentIndex + 2);
    }

    // Recursive function to update the segment tree
    private void updateSegmentTree(int segmentStart, int segmentEnd, int index, int difference, int currentIndex) {
        // If the index is outside the segment range, return
        if (index < segmentStart || index > segmentEnd) return;
        segmentTree[currentIndex] += difference; // Update the current node value
        // If the segment start is not equal to the segment end, update the children
        if (segmentStart != segmentEnd) {
            int mid = getMiddle(segmentStart, segmentEnd); // Get the middle index
            updateSegmentTree(segmentStart, mid, index, difference, 2 * currentIndex + 1); // Update the left subtree
            updateSegmentTree(mid + 1, segmentEnd, index, difference, 2 * currentIndex + 2); // Update the right subtree
        }
    }

    // Function to get the sum of elements in the given range
    public int sumRange(int left, int right) {
        if (arrayLength == 0) return 0; // If the array is empty, return 0
        return getSum(0, arrayLength - 1, left, right, 0); // Get the sum from the segment tree
    }

    // Recursive function to get the sum of elements in the given range
    private int getSum(int segmentStart, int segmentEnd, int queryStart, int queryEnd, int currentIndex) {
        // If the query range is within the segment range, return the current node value
        if (queryStart <= segmentStart && queryEnd >= segmentEnd)
            return segmentTree[currentIndex];
        // If the segment range is outside the query range, return 0
        if (segmentEnd < queryStart || segmentStart > queryEnd)
            return 0;
        int mid = getMiddle(segmentStart, segmentEnd); // Get the middle index
        // Recursively get the sum from the left and right subtrees
        return getSum(segmentStart, mid, queryStart, queryEnd, 2 * currentIndex + 1) +
                getSum(mid + 1, segmentEnd, queryStart, queryEnd, 2 * currentIndex + 2);
    }
}