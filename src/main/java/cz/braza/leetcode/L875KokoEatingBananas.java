package cz.braza.leetcode;

/*
Same algorithm and results as L2064 (minimized maximum of products), copy&paste
Runs 11ms, beats 80.5%
 */
public class L875KokoEatingBananas {
    public int minEatingSpeed(int[] piles, int h) {
        long sum = 0;
        int maxK = 0;
        for (int q : piles) {
            sum += q;
            if (q > maxK) maxK = q;
        }
        int minK = (int) Math.ceil((double) sum / h) - 1;

        while (minK + 1 < maxK) {
            int k = (maxK + minK) / 2;
            if (k == minK) k++;
            if (canDistribute(k, h, piles))
                maxK = k;
            else
                minK = k;
        }
        // now maxK we can surely distribute:
        return maxK;
    }

    public static boolean canDistribute(int k, int n, int[] quantities) {
        int minNumber = 0;
        for (int q: quantities)
            minNumber += q / k + (q % k > 0 ? 1 : 0);
        return minNumber <= n;
    }
}
