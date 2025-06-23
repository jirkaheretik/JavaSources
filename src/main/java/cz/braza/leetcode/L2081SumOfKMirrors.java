package cz.braza.leetcode;

import cz.braza.Timing;

import java.util.Arrays;

/*
Daily 23.6.2025 - https://leetcode.com/problems/sum-of-k-mirror-numbers/
2081. Sum of k-Mirror Numbers
Solved
Hard
Topics
premium lock icon
Companies
Hint
A k-mirror number is a positive integer without leading zeros that reads the same both forward and backward in base-10 as well as in base-k.

For example, 9 is a 2-mirror number. The representation of 9 in base-10 and base-2 are 9 and 1001 respectively, which read the same both forward and backward.
On the contrary, 4 is not a 2-mirror number. The representation of 4 in base-2 is 100, which does not read the same both forward and backward.
Given the base k and the number n, return the sum of the n smallest k-mirror numbers.



Example 1:

Input: k = 2, n = 5
Output: 25
Explanation:
The 5 smallest 2-mirror numbers and their representations in base-2 are listed as follows:
  base-10    base-2
    1          1
    3          11
    5          101
    7          111
    9          1001
Their sum = 1 + 3 + 5 + 7 + 9 = 25.
Example 2:

Input: k = 3, n = 7
Output: 499
Explanation:
The 7 smallest 3-mirror numbers are and their representations in base-3 are listed as follows:
  base-10    base-3
    1          1
    2          2
    4          11
    8          22
    121        11111
    151        12121
    212        21212
Their sum = 1 + 2 + 4 + 8 + 121 + 151 + 212 = 499.
Example 3:

Input: k = 7, n = 17
Output: 20379000
Explanation: The 17 smallest 7-mirror numbers are:
1, 2, 3, 4, 5, 6, 8, 121, 171, 242, 292, 16561, 65656, 2137312, 4602064, 6597956, 6958596


Constraints:

2 <= k <= 9
1 <= n <= 30
 */
public class L2081SumOfKMirrors {
    static long[][] nums = {{1L, 3L, 5L, 7L, 9L, 33L, 99L, 313L, 585L, 717L, 7447L, 9009L, 15351L, 32223L, 39993L, 53235L, 53835L, 73737L, 585585L, 1758571L, 1934391L, 1979791L, 3129213L, 5071705L, 5259525L, 5841485L, 13500531L, 719848917L, 910373019L, 939474939L},
            {1L, 2L, 4L, 8L, 121L, 151L, 212L, 242L, 484L, 656L, 757L, 29092L, 48884L, 74647L, 75457L, 76267L, 92929L, 93739L, 848848L, 1521251L, 2985892L, 4022204L, 4219124L, 4251524L, 4287824L, 5737375L, 7875787L, 7949497L, 27711772L, 83155138L},
            {1L, 2L, 3L, 5L, 55L, 373L, 393L, 666L, 787L, 939L, 7997L, 53235L, 55255L, 55655L, 57675L, 506605L, 1801081L, 2215122L, 3826283L, 3866683L, 5051505L, 5226225L, 5259525L, 5297925L, 5614165L, 5679765L, 53822835L, 623010326L, 954656459L, 51717171715L},
            {1L, 2L, 3L, 4L, 6L, 88L, 252L, 282L, 626L, 676L, 1221L, 15751L, 18881L, 10088001L, 10400401L, 27711772L, 30322303L, 47633674L, 65977956L, 808656808L, 831333138L, 831868138L, 836131638L, 836181638L, 2512882152L, 2596886952L, 2893553982L, 6761551676L, 12114741121L, 12185058121L},
            {1L, 2L, 3L, 4L, 5L, 7L, 55L, 111L, 141L, 191L, 343L, 434L, 777L, 868L, 1441L, 7667L, 7777L, 22022L, 39893L, 74647L, 168861L, 808808L, 909909L, 1867681L, 3097903L, 4232324L, 4265624L, 4298924L, 4516154L, 4565654L},
            {1L, 2L, 3L, 4L, 5L, 6L, 8L, 121L, 171L, 242L, 292L, 16561L, 65656L, 2137312L, 4602064L, 6597956L, 6958596L, 9470749L, 61255216L, 230474032L, 466828664L, 485494584L, 638828836L, 657494756L, 858474858L, 25699499652L, 40130703104L, 45862226854L, 61454945416L, 64454545446L},
            {1L, 2L, 3L, 4L, 5L, 6L, 7L, 9L, 121L, 292L, 333L, 373L, 414L, 585L, 3663L, 8778L, 13131L, 13331L, 26462L, 26662L, 30103L, 30303L, 207702L, 628826L, 660066L, 1496941L, 1935391L, 1970791L, 4198914L, 55366355L},
            {1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L, 191L, 282L, 373L, 464L, 555L, 646L, 656L, 6886L, 25752L, 27472L, 42324L, 50605L, 626626L, 1540451L, 1713171L, 1721271L, 1828281L, 1877781L, 1885881L, 2401042L, 2434342L, 2442442L},
    };
    public static final long[][] RESULT = {{1, 4, 9, 16, 25, 58, 157, 470, 1055, 1772, 9219, 18228, 33579, 65802, 105795, 159030, 212865, 286602, 872187, 2630758, 4565149, 6544940, 9674153, 14745858, 20005383, 25846868, 39347399, 759196316, 1669569335, 2609044274L},
            {1, 3, 7, 15, 136, 287, 499, 741, 1225, 1881, 2638, 31730, 80614, 155261, 230718, 306985, 399914, 493653, 1342501, 2863752, 5849644, 9871848, 14090972, 18342496, 22630320, 28367695, 36243482, 44192979, 71904751, 155059889},
            {1, 3, 6, 11, 66, 439, 832, 1498, 2285, 3224, 11221, 64456, 119711, 175366, 233041, 739646, 2540727, 4755849, 8582132, 12448815, 17500320, 22726545, 27986070, 33283995, 38898160, 44577925, 98400760, 721411086, 1676067545, 53393239260L},
            {1, 3, 6, 10, 16, 104, 356, 638, 1264, 1940, 3161, 18912, 37793, 10125794, 20526195, 48237967, 78560270, 126193944, 192171900, 1000828708, 1832161846, 2664029984L, 3500161622L, 4336343260L, 6849225412L, 9446112364L, 12339666346L, 19101218022L, 31215959143L, 43401017264L},
            {1, 3, 6, 10, 15, 22, 77, 188, 329, 520, 863, 1297, 2074, 2942, 4383, 12050, 19827, 41849, 81742, 156389, 325250, 1134058, 2043967, 3911648, 7009551, 11241875, 15507499, 19806423, 24322577, 28888231},
            {1, 3, 6, 10, 15, 21, 29, 150, 321, 563, 855, 17416, 83072, 2220384, 6822448, 13420404, 20379000, 29849749, 91104965, 321578997, 788407661, 1273902245, 1912731081, 2570225837L, 3428700695L, 29128200347L, 69258903451L, 115121130305L, 176576075721L, 241030621167L},
            {1, 3, 6, 10, 15, 21, 28, 37, 158, 450, 783, 1156, 1570, 2155, 5818, 14596, 27727, 41058, 67520, 94182, 124285, 154588, 362290, 991116, 1651182, 3148123, 5083514, 7054305, 11253219, 66619574},
            {1, 3, 6, 10, 15, 21, 28, 36, 227, 509, 882, 1346, 1901, 2547, 3203, 10089, 35841, 63313, 105637, 156242, 782868, 2323319, 4036490, 5757761, 7586042, 9463823, 11349704, 13750746, 16185088, 18627530}};

    // runs too slow (hours, possibly), got TLE
    // Generate values instead (preprocessing)
    public long kMirrorOG(int k, int n) {
        int count = 0;
        long sum = 0;
        int num = 1;
        while (count < n) {
            if (isPalindrome10(num) && isPalindromeK(num, k)) {
                sum += num;
                count++;
                //System.out.println("Found " + num);
            }
            num++;
        }
        return sum;
    }

    public static boolean isPalindrome10(long x) {
        return isPalindromeK(x, 10);
    }

    public static boolean isPalindromeK(long x, int k) {
        int[] digits = new int[64];
        int didx = 0;
        while (x > 0) {
            digits[didx++] = (int) (x % k);
            x /= k;
        }
        didx -= 1;
        for (int i = 0; i <= didx / 2; i++)
            if (digits[i] != digits[didx - i]) return false;
        return true;
    }

    // slow but steady, will print out Java definition of array with k-palindromes
    // we can then make a presum out of it (see constructResult())
    public static void constructArray() {
        System.out.print("long[][] nums = {");
        for (int k = 2; k < 10; k++) {
            int count = 0;
            long num = 1;
            System.out.print("{");
            while (count < 30) {
                if (isPalindrome10(num) && isPalindromeK(num, k)) {
                    //sum += num;
                    count++;
                    System.out.print(num + "L, ");
                }
                num++;

            }
            System.out.println("},");
        }
        System.out.println("};");
    }

    public static void constructResult() {
        System.out.print("public static final long[][] RESULT = {");
        for (long[] row : nums) {
            for (int i = 1; i < row.length; i++) row[i] += row[i - 1];
            System.out.println(Arrays.toString(row) + ",");
        }
        System.out.println("};");
    }

    public long kMirror(int k, int n) {
        return RESULT[k - 2][n - 1];
    }

    public static void main(String[] args) {
        //Timing.time(L2081SumOfKMirrors::constructArray, 1);
        Timing.time(L2081SumOfKMirrors::constructResult, 1);
    }
}
