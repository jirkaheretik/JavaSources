package cz.braza.leetcode;

/*
https://leetcode.com/problems/corporate-flight-bookings/

1109. Corporate Flight Bookings
Medium
Topics
Companies
There are n flights that are labeled from 1 to n.

You are given an array of flight bookings bookings, where bookings[i] = [firsti, lasti, seatsi] represents a booking for flights firsti through lasti (inclusive) with seatsi seats reserved for each flight in the range.

Return an array answer of length n, where answer[i] is the total number of seats reserved for flight i.



Example 1:

Input: bookings = [[1,2,10],[2,3,20],[2,5,25]], n = 5
Output: [10,55,45,25,25]
Explanation:
Flight labels:        1   2   3   4   5
Booking 1 reserved:  10  10
Booking 2 reserved:      20  20
Booking 3 reserved:      25  25  25  25
Total seats:         10  55  45  25  25
Hence, answer = [10,55,45,25,25]
Example 2:

Input: bookings = [[1,2,10],[2,2,15]], n = 2
Output: [10,25]
Explanation:
Flight labels:        1   2
Booking 1 reserved:  10  10
Booking 2 reserved:      15
Total seats:         10  25
Hence, answer = [10,25]



Constraints:

1 <= n <= 2 * 10^4
1 <= bookings.length <= 2 * 10^4
bookings[i].length == 3
1 <= firsti <= lasti <= n
1 <= seatsi <= 10^4
 */
public class L1109CorporateFlightBookings {
    /*
    Easy line sweep - for each booking, mark +val at left-1 and -val at right (if available)
    Then go through the array and set prefix sum to result value.
    Runs 3ms, beats 97.2%, 63 testcases
     */
    public int[] corpFlightBookings(int[][] bookings, int n) {
        int[] result = new int[n];
        // process bookings first:
        for (int[] book: bookings) {
            result[book[0] - 1] += book[2];
            if (book[1] < n) result[book[1]] -= book[2];
        }
        int sum = 0;
        for (int idx = 0; idx < result.length; idx++) {
            sum += result[idx];
            result[idx] = sum;
        }
        return result;
    }
}
