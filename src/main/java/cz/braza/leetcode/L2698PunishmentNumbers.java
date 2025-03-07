package cz.braza.leetcode;

/*
Daily 15.2.2025

2698. Find the Punishment Number of an Integer
Medium
Topics
Companies
Hint
Given a positive integer n, return the punishment number of n.

The punishment number of n is defined as the sum of the squares of all integers i such that:

1 <= i <= n
The decimal representation of i * i can be partitioned into contiguous substrings such that the sum of the integer values of these substrings equals i.


Example 1:

Input: n = 10
Output: 182
Explanation: There are exactly 3 integers i in the range [1, 10] that satisfy the conditions in the statement:
- 1 since 1 * 1 = 1
- 9 since 9 * 9 = 81 and 81 can be partitioned into 8 and 1 with a sum equal to 8 + 1 == 9.
- 10 since 10 * 10 = 100 and 100 can be partitioned into 10 and 0 with a sum equal to 10 + 0 == 10.
Hence, the punishment number of 10 is 1 + 81 + 100 = 182
Example 2:

Input: n = 37
Output: 1478
Explanation: There are exactly 4 integers i in the range [1, 37] that satisfy the conditions in the statement:
- 1 since 1 * 1 = 1.
- 9 since 9 * 9 = 81 and 81 can be partitioned into 8 + 1.
- 10 since 10 * 10 = 100 and 100 can be partitioned into 10 + 0.
- 36 since 36 * 36 = 1296 and 1296 can be partitioned into 1 + 29 + 6.
Hence, the punishment number of 37 is 1 + 81 + 100 + 1296 = 1478


Constraints:

1 <= n <= 1000
 */
public class L2698PunishmentNumbers {

    /*
    First array that only contains numbers that can be partitioned by ONE split, but the requirement allows any number of splits
     */
    public static final int[] DOUBLES = {1, 9, 10, 45, 55, 99, 100, 297, 703, 999, 1000};
    /*
    So, an isPartitiable() function does find (and print) all those that can be partitioned:
     */
    public static final int[] PARTS = {1, 9, 10, 36, 45, 55, 82, 91, 99, 100, 235, 297, 369, 370, 379, 414, 657, 675, 703, 756, 792, 909, 918, 945, 964, 990, 991, 999, 1000};

    /*
    Then we create a 2D array with numbers and its squares as well
     */
    public static final int[][] NUMS = {{1, 1}, {9, 81}, {10, 100}, {36, 1296}, {45, 2025}, {55, 3025}, {82, 6724}, {91, 8281}, {99, 9801}, {100, 10000}, {235, 55225}, {297, 88209}, {369, 136161}, {370, 136900}, {379, 143641}, {414, 171396}, {657, 431649}, {675, 455625}, {703, 494209}, {756, 571536}, {792, 627264}, {909, 826281}, {918, 842724}, {945, 893025}, {964, 929296}, {990, 980100}, {991, 982081}, {999, 998001}, {1000, 1000000}};

    /*
    With all this above, we can just go through the array adding squares, and if we are above n, we return the result.
    As expected, runs 0ms, beats 100% (since no more computing), 216 testcases
     */
    public int punishmentNumber(int n) {
        int sum = 0;
        for (int[] item: NUMS)
            if (item[0] <= n) sum += item[1];
            else return sum;
        return sum;
    }

    public static void main(String[] args) {
        // print out punishment numbers:
        System.out.print("{1");
        /*
        for (int n = 5; n <= 1000; n++) {
            int squared = n * n;
            int digits = (int)Math.log10(squared) + 1;
            for (int split = 1; split < digits; split++) {
                int divide = (int)Math.pow(10, split);
                if (squared / divide + squared % divide == n)
                    System.out.print(", " + n);
            }
        }
        */
        for (int n = 5; n <= 1000; n++)
            if (isPartitiable((n * n), n))
                System.out.print(", " + n);
        System.out.println("}\n");

        // now create the squared 2D array print:
        System.out.print("{");
        for (int num: PARTS)
            System.out.printf("{%d, %d}, ", num, num * num);
        System.out.println("}");
    }

    /* base taken from editorial */
    public static boolean isPartitiable(String stringNum, int target) {
        // Valid Partition Found
        if (stringNum.isEmpty() && target == 0) return true;

        // Invalid Partition Found
        if (target < 0) return false;

        // Recursively check all partitions for a valid partition
        for (int index = 0; index < stringNum.length(); index++) {
            String left = stringNum.substring(0, index + 1);
            String right = stringNum.substring(index + 1);
            int leftNum = Integer.parseInt(left);

            if (isPartitiable(right, target - leftNum)) return true;
        }
        return false;
    }

    /*
    Converted the isPartitiable(String, int) to work solely with ints.
     */
    public static boolean isPartitiable(int source, int target) {
        // Valid Partition Found
        if (source == target) return true;
        // Invalid Partition Found
        if (target < 0) return false;

        // length and splits
        int length = (int)Math.log10(source) + 1;
        // Recursively check all partitions for a valid partition
        for (int index = 1; index < length; index++) {
            int pow10 = (int)Math.pow(10, index);
            int leftNum = source / pow10;
            if (isPartitiable(source % pow10, target - leftNum)) return true;
        }
        return false;
    }
}
