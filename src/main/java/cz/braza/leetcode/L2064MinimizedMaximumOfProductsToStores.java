package cz.braza.leetcode;

import java.util.Arrays;

/*
2064. Minimized Maximum of Products Distributed to Any Store
Medium
Topics
Companies
Hint
You are given an integer n indicating there are n specialty retail stores. There are m product types of varying amounts, which are given as a 0-indexed integer array quantities, where quantities[i] represents the number of products of the ith product type.

You need to distribute all products to the retail stores following these rules:

A store can only be given at most one product type but can be given any amount of it.
After distribution, each store will have been given some number of products (possibly 0). Let x represent the maximum number of products given to any store. You want x to be as small as possible, i.e., you want to minimize the maximum number of products that are given to any store.
Return the minimum possible x.



Example 1:

Input: n = 6, quantities = [11,6]
Output: 3
Explanation: One optimal way is:
- The 11 products of type 0 are distributed to the first four stores in these amounts: 2, 3, 3, 3
- The 6 products of type 1 are distributed to the other two stores in these amounts: 3, 3
The maximum number of products given to any store is max(2, 3, 3, 3, 3, 3) = 3.
Example 2:

Input: n = 7, quantities = [15,10,10]
Output: 5
Explanation: One optimal way is:
- The 15 products of type 0 are distributed to the first three stores in these amounts: 5, 5, 5
- The 10 products of type 1 are distributed to the next two stores in these amounts: 5, 5
- The 10 products of type 2 are distributed to the last two stores in these amounts: 5, 5
The maximum number of products given to any store is max(5, 5, 5, 5, 5, 5, 5) = 5.
Example 3:

Input: n = 1, quantities = [100000]
Output: 100000
Explanation: The only optimal way is:
- The 100000 products of type 0 are distributed to the only store.
The maximum number of products given to any store is max(100000) = 100000.


Constraints:

m == quantities.length
1 <= m <= n <= 105
1 <= quantities[i] <= 105
 */
public class L2064MinimizedMaximumOfProductsToStores {
    /*
    From hints: for a given K, we can decide if it can be distributed (no store gets more than K) or not
    Find minimum K by binary search.
    Sort quantities, find total average, that is minimum for K, and max item, that is maximum for K

    Runs 83ms, beats 45% (half of submissions does that in 33ms)
     */
    public static int minimizedMaximum(int n, int[] quantities) {
        Arrays.sort(quantities);
        // find average and max:
        long sum = 0;
        for (int q: quantities)
            sum += q;
        int minK = (int) Math.ceil((double)sum / n);
        int maxK = quantities[quantities.length - 1];

        while (minK + 1 < maxK) {
            int k = (maxK + minK) / 2;
            if (k == minK) k++;
            if (canDistribute(k, n, quantities))
                maxK = k;
            else
                minK = k;
        }
        // now maxK we can surely distribute, not sure about minK:
        return canDistribute(minK, n, quantities) ? minK : maxK;
    }

    public static boolean canDistribute(int k, int n, int[] quantities) {
        int minNumber = 0;
        for (int q: quantities)
            minNumber += q / k + (q % k > 0 ? 1 : 0);
        return minNumber <= n;
    }

    /*
    Update: actually, the sorting does us no good, we can skip that:
    Runs 46ms, beats 56.8%
     */
    public static int minimizedMaximumNoSort(int n, int[] quantities) {
        // find average and max:
        long sum = 0;
        int maxK = 0;
        for (int q : quantities) {
            sum += q;
            if (q > maxK) maxK = q;
        }
        int minK = (int) Math.ceil((double) sum / n);

        // rest is the same:
        return 0;
    }

    /*
    Update: unless minK can be distributed right from the start, at no point we update it with distributable number:
    so we can check it first, and otherwise always return maxK
    Runs 44ms, beats 77.9%
     */
    public static int minimizedMaximumOpt(int n, int[] quantities) {
        long sum = 0;
        int maxK = 0;
        for (int q : quantities) {
            sum += q;
            if (q > maxK) maxK = q;
        }
        int minK = (int) Math.ceil((double) sum / n);
        if (canDistribute(minK, n, quantities)) return minK;

        while (minK + 1 < maxK) {
            int k = (maxK + minK) / 2;
            if (k == minK) k++;
            if (canDistribute(k, n, quantities))
                maxK = k;
            else
                minK = k;
        }
        // now maxK we can surely distribute:
        return maxK;
    }

    /*
    Another update: make minK undistributable, so we do not need to check it
    Runs 43ms, beats 79.6%
     */
    public static int minimizedMaximumOpt2(int n, int[] quantities) {
        long sum = 0;
        int maxK = 0;
        for (int q : quantities) {
            sum += q;
            if (q > maxK) maxK = q;
        }
        int minK = (int) Math.ceil((double) sum / n) - 1;

        while (minK + 1 < maxK) {
            int k = (maxK + minK) / 2;
            if (k == minK) k++;
            if (canDistribute(k, n, quantities))
                maxK = k;
            else
                minK = k;
        }
        // now maxK we can surely distribute:
        return maxK;
    }

    public static void main(String[] args) {
        int[] t = {3,6,7,11};
        System.out.println(minimizedMaximum(8, t));
    }
}
