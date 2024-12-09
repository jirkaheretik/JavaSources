package cz.braza.leetcode;

import java.util.Arrays;
import java.util.Comparator;

/*
2070. Most Beautiful Item for Each Query
Medium
Topics
Companies
Hint
You are given a 2D integer array items where items[i] = [pricei, beautyi] denotes the price and beauty of an item respectively.

You are also given a 0-indexed integer array queries. For each queries[j], you want to determine the maximum beauty of an item whose price is less than or equal to queries[j]. If no such item exists, then the answer to this query is 0.

Return an array answer of the same length as queries where answer[j] is the answer to the jth query.



Example 1:

Input: items = [[1,2],[3,2],[2,4],[5,6],[3,5]], queries = [1,2,3,4,5,6]
Output: [2,4,5,5,6,6]
Explanation:
- For queries[0]=1, [1,2] is the only item which has price <= 1. Hence, the answer for this query is 2.
- For queries[1]=2, the items which can be considered are [1,2] and [2,4].
  The maximum beauty among them is 4.
- For queries[2]=3 and queries[3]=4, the items which can be considered are [1,2], [3,2], [2,4], and [3,5].
  The maximum beauty among them is 5.
- For queries[4]=5 and queries[5]=6, all items can be considered.
  Hence, the answer for them is the maximum beauty of all items, i.e., 6.
Example 2:

Input: items = [[1,2],[1,2],[1,3],[1,4]], queries = [1]
Output: [4]
Explanation:
The price of every item is equal to 1, so we choose the item with the maximum beauty 4.
Note that multiple items can have the same price and/or beauty.
Example 3:

Input: items = [[10,1000]], queries = [5]
Output: [0]
Explanation:
No item has a price less than or equal to 5, so no item can be chosen.
Hence, the answer to the query is 0.


Constraints:

1 <= items.length, queries.length <= 105
items[i].length == 2
1 <= pricei, beautyi, queries[j] <= 109
 */
public class L2070MostBeautifulItems {

    /*
    First attempt in O(MxN). For each query, go through the item array
    and find the highest item that fits the price constraint, store back
    in the queries array and return.
    Not suprisingly TLE on testcase 34/35 (2MB array, 1MB another array)
     */
    public static int[] maximumBeauty(int[][] items, int[] queries) {
        for (int index = 0; index < queries.length; index++)
            queries[index] = findMostBeautifulItemThatFits(items, queries[index]);
        return queries;
    }

    public static int findMostBeautifulItemThatFits(int[][] items, int priceLimit) {
        int maximum = 0;
        for (int[] item: items)
            if (item[0] <= priceLimit && item[1] > maximum)
                maximum = item[1];
        return maximum;
    }

    /*
    Sort items so we can use binarySearch (own implementation)
    Runs 50ms, beats 32%
     */
    public static int[] maximumBeautySort(int[][] items, int[] queries) {
        // sort by [0] and then [1] for same price values
        // but probably not needed because of the next step, simplify with comparingInt
        // Arrays.sort(items, (a, b) -> a[0] == b[0] ? Integer.compare(a[1], b[1]) : Integer.compare(a[0], b[0]));
        Arrays.sort(items, Comparator.comparingInt(o -> o[0]));
        // now lower priced item can have higher beauty, but we wouldn't be able to find them
        // go up the array and notice current max, and if last beauty for a new price value
        // is lower than current max, replace:
        int max = items[0][1]; // first beauty
        int lastVal = items[0][0]; // first price
        for (int index = 1; index < items.length; index++) {
            if (items[index][0] != lastVal && items[index - 1][1] < max)
                items[index - 1][1] = max;
            if (items[index][1] > max)
                max = items[index][1];
        }
        // and do not forget last item:
        if (items[items.length - 1][1] < max)
            items[items.length - 1][1] = max;
        // debug print:
        for (int[] x: items)
            System.out.println(Arrays.toString(x));
        for (int index = 0; index < queries.length; index++)
            queries[index] = findMostBeautifulItemThatFitsSorted(items, queries[index]);
        return queries;
    }

    public static int findMostBeautifulItemThatFitsSorted(int[][] items, int priceLimit) {
        int minIndex = 0;
        int maxIndex = items.length;
        while (maxIndex - minIndex > 1) {
            int half = (maxIndex - minIndex) / 2;
            System.out.println(half);
            half += minIndex + (half == 0 ? 1 : 0);
            System.out.println(half);
            if (half < items.length && items[half][0] > priceLimit)
                maxIndex = half;
            else
                minIndex = half;
            System.out.printf("Index %d <%d-%d> beauty %d%n", half, minIndex, maxIndex, items[minIndex][1]);
        }
        // now items[maxIndex] should be too high price, items[minIndex] should be ok (if any)
        return (items[minIndex][0] <= priceLimit) ? items[minIndex][1] : 0;
    }

    public static void main(String[] args) {
        int[][] it = {{1,2},{3,2},{2,4},{5,6},{3,5}};
        int[] q = {1,2,3,4,5,6};
        //System.out.println(Arrays.toString(maximumBeauty(it, q)));
        System.out.println(Arrays.toString(maximumBeautySort(it, q)));
        //System.out.println(Arrays.toString(maximumBeauty(it, q)));
    }
}
