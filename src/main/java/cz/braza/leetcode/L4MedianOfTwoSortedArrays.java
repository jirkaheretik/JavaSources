package cz.braza.leetcode;

import java.util.Arrays;

public class L4MedianOfTwoSortedArrays {
    public static void main(String[] args) {
        int[] nums1 = {0, 1, 2, 3};
        int[] nums2 = {5, 8, 9};
        System.out.println(findMedianSortedArraysLinear(nums1, nums2));
    }

    public static double findMedianSortedArraysQuicksort(int[] nums1, int[] nums2) {
        int[] result = new int[nums1.length + nums2.length];
        System.arraycopy(nums1, 0, result, 0, nums1.length);
        System.arraycopy(nums2, 0, result, nums1.length, nums2.length);
        Arrays.sort(result);
        int idx = result.length / 2;
        return result.length % 2 == 0 ? (result[idx] + result[idx - 1]) / 2.0 : result[idx];
    }

    private static int getNextItem(int[] nums1, int index) {
        return index < nums1.length ? nums1[index] : Integer.MAX_VALUE;
    }

    public static double findMedianSortedArraysLinear(int[] nums1, int[] nums2) {
        int count = 0;
        int idx1 = 0;
        int idx2 = 0;
        int previous = Integer.MIN_VALUE;
        int totalCount = nums1.length + nums2.length;
        int target = totalCount / 2;
        int val1 = getNextItem(nums1, idx1++);
        int val2 = getNextItem(nums2, idx2++);
        while (count < target) {
            if (val1 < val2) {
                previous = val1;
                val1 = getNextItem(nums1, idx1++);
            } else {
                previous = val2;
                val2 = getNextItem(nums2, idx2++);
            }
            count++;
        }
        int last = val1 < val2 ? val1 : val2;
        return totalCount % 2 == 0 ? (last + previous) / 2.0 : last;
    }

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        return 0;
    }
}
