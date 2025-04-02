package cz.braza.leetcode;

/*
Daily 16.3.2025 - https://leetcode.com/problems/minimum-time-to-repair-cars/

2594. Minimum Time to Repair Cars
Medium
Topics
Companies
Hint
You are given an integer array ranks representing the ranks of some mechanics. ranksi is the rank of the ith mechanic. A mechanic with a rank r can repair n cars in r * n2 minutes.

You are also given an integer cars representing the total number of cars waiting in the garage to be repaired.

Return the minimum time taken to repair all the cars.

Note: All the mechanics can repair the cars simultaneously.



Example 1:

Input: ranks = [4,2,3,1], cars = 10
Output: 16
Explanation:
- The first mechanic will repair two cars. The time required is 4 * 2 * 2 = 16 minutes.
- The second mechanic will repair two cars. The time required is 2 * 2 * 2 = 8 minutes.
- The third mechanic will repair two cars. The time required is 3 * 2 * 2 = 12 minutes.
- The fourth mechanic will repair four cars. The time required is 1 * 4 * 4 = 16 minutes.
It can be proved that the cars cannot be repaired in less than 16 minutes.​​​​​
Example 2:

Input: ranks = [5,1,8], cars = 6
Output: 16
Explanation:
- The first mechanic will repair one car. The time required is 5 * 1 * 1 = 5 minutes.
- The second mechanic will repair four cars. The time required is 1 * 4 * 4 = 16 minutes.
- The third mechanic will repair one car. The time required is 8 * 1 * 1 = 8 minutes.
It can be proved that the cars cannot be repaired in less than 16 minutes.​​​​​


Constraints:

1 <= ranks.length <= 10^5
1 <= ranks[i] <= 100
1 <= cars <= 10^6
 */
public class L2594MinimumTimeToRepairCars {
    /*
    For a given time we can tell how many cars we repair - sqrt(time/rank-i)
    Thus binary search for the lowest time possible.
    Check for int constraints, it can overflow in many testcases.

    Runs 53ms, beats 53.2%, 1072 testcases
     */
    public long repairCars(int[] ranks, int cars) {
        int lowestRank = Integer.MAX_VALUE;
        for (int rank : ranks)
            if (rank < lowestRank) lowestRank = rank;
        long left = 1;
        long right = (long)cars * cars * lowestRank; // the fastest one repairs all the cars

        while (left < right) {
            long mid = (right + left) / 2;
            long carsRepaired = 0;
            for (int rank : ranks)
                carsRepaired += (long) Math.sqrt(mid / rank);
            if (carsRepaired >= cars)
                right = mid;
            else
                left = mid + 1;
        }
        return left;
    }
}
