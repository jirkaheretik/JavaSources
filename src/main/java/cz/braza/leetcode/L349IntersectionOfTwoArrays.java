package cz.braza.leetcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/*
349. Intersection of Two Arrays
Easy
Topics
Companies
Given two integer arrays nums1 and nums2, return an array of their
intersection
. Each element in the result must be unique and you may return the result in any order.



Example 1:

Input: nums1 = [1,2,2,1], nums2 = [2,2]
Output: [2]
Example 2:

Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
Output: [9,4]
Explanation: [4,9] is also accepted.


Constraints:

1 <= nums1.length, nums2.length <= 1000
0 <= nums1[i], nums2[i] <= 1000
 */
public class L349IntersectionOfTwoArrays {
    /*
    Basic idea: create a set for the result. Take one array one by one, and look for
    the same item (number) in the other array, adding to the set if it is there.
    In order to speed up the search, we sort the other array first and use binarySearch.
    Runs 4ms, beats 32.7% (majority does 2ms)
     */
    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> result = new HashSet<>();
        Arrays.sort(nums2);
        for (int item: nums1)
            if (Arrays.binarySearch(nums2, item) >= 0)
                result.add(item);
        int[] res = new int[result.size()];
        int index = 0;
        for (int i: result)
            res[index++] = i;
        return res;
    }

    /*
    Another try with sets, a bit clumsy, maybe not the best way to do it
    Runs 6ms, beats 14.2%
     */
    public int[] intersectionSets(int[] nums1, int[] nums2) {
        Set<Integer> first = new HashSet<>(Arrays.stream(nums1).boxed().toList());
        Set<Integer> second = new HashSet<>(Arrays.stream(nums2).boxed().toList());
        Set<Integer> result =  first.stream().filter(second::contains).collect(Collectors.toSet());
        int[] iResult = new int[result.size()];
        int index = 0;
        for (int val: result)
            iResult[index++] = val;
        return iResult;
    }


}
