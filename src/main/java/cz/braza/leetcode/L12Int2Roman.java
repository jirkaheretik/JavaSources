package cz.braza.leetcode;

/*
12. Integer to Roman
Medium
Topics
Companies
Seven different symbols represent Roman numerals with the following values:

Symbol	Value
I	1
V	5
X	10
L	50
C	100
D	500
M	1000
Roman numerals are formed by appending the conversions of decimal place values from highest to lowest. Converting a decimal place value into a Roman numeral has the following rules:

If the value does not start with 4 or 9, select the symbol of the maximal value that can be subtracted from the input, append that symbol to the result, subtract its value, and convert the remainder to a Roman numeral.
If the value starts with 4 or 9 use the subtractive form representing one symbol subtracted from the following symbol, for example, 4 is 1 (I) less than 5 (V): IV and 9 is 1 (I) less than 10 (X): IX. Only the following subtractive forms are used: 4 (IV), 9 (IX), 40 (XL), 90 (XC), 400 (CD) and 900 (CM).
Only powers of 10 (I, X, C, M) can be appended consecutively at most 3 times to represent multiples of 10. You cannot append 5 (V), 50 (L), or 500 (D) multiple times. If you need to append a symbol 4 times use the subtractive form.
Given an integer, convert it to a Roman numeral.



Example 1:

Input: num = 3749

Output: "MMMDCCXLIX"

Explanation:

3000 = MMM as 1000 (M) + 1000 (M) + 1000 (M)
 700 = DCC as 500 (D) + 100 (C) + 100 (C)
  40 = XL as 10 (X) less of 50 (L)
   9 = IX as 1 (I) less of 10 (X)
Note: 49 is not 1 (I) less of 50 (L) because the conversion is based on decimal places
Example 2:

Input: num = 58

Output: "LVIII"

Explanation:

50 = L
 8 = VIII
Example 3:

Input: num = 1994

Output: "MCMXCIV"

Explanation:

1000 = M
 900 = CM
  90 = XC
   4 = IV


Constraints:

1 <= num <= 3999
 */
public class L12Int2Roman {
    /*
    Process 4xx and 8xx first, otherwise just strip one value/one letter and move on
    Runs 9ms, beats 13.7% :-( 3999 testcases :-D
     */
    public static final String LETTERS = "MDCLXVI";
    public static final int[] VALUES = {1000, 500, 100, 50, 10, 5, 1};
    public static final String[] MAPPING = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
    public static final int[] VALUES2 = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};

    public static final String[] M = {"", "M", "MM", "MMM", "MMMM", "MMMMM"};
    public static final String[] C = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
    public static final String[] X = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
    public static final String[] I = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};

    public String intToRoman(int num) {
        StringBuilder result = new StringBuilder();
        while (num > 0) {
            for (int idx = 0; idx < VALUES.length; idx++)
                if (num >= VALUES[idx]) {
                    System.out.printf("DBG num %d res %s idx %d with val %d%n", num, result.toString(), idx, VALUES[idx]);
                    if (idx % 2 == 0 && num >= 4 * VALUES[idx]) {
                        result.append("" + LETTERS.charAt(idx) + LETTERS.charAt(idx - 1));
                        num -= 4 * VALUES[idx];
                        break; // end of for
                    }
                    if (idx % 2 == 1 && num >= 9 * VALUES[idx + 1]) {
                        result.append("" + LETTERS.charAt(idx + 1) + LETTERS.charAt(idx - 1));
                        num -= 9 * VALUES[idx + 1];
                        break;
                    }
                    result.append(LETTERS.charAt(idx));
                    num -= VALUES[idx];
                    break;
                }
        }
        return result.toString();
    }

    /*
    Handling of 4 and 9 is cumbersome in previous attempt, lets use those values as well,
    this time we cannot use LETTERS, as we have more than one char per value,
    we use MAPPING instead.
    Also, just use repeat() for how many times we need, and no need for break (and start again)
    since we always deal with the largest value completely.
    Runs 3ms, beats 98.6%
     */
    public String intToRomanStr(int num) {
        StringBuilder result = new StringBuilder();
        while (num > 0) {
            for (int idx = 0; idx < VALUES2.length; idx++)
                if (num >= VALUES2[idx]) {
                    int count = num / VALUES2[idx];
                    result.append(MAPPING[idx].repeat(count));
                    num -= count * VALUES2[idx];
                }
        }
        return result.toString();
    }

    /*
    Feels a bit like cheating, surprisingly does not work well:
    Runs 11ms, beats 11.5%
     */
    public String intToRomanCheat(int num) {
        return M[num / 1000] + C[(num % 1000) / 100] + X[(num % 100) / 10] + I[num % 10];
    }
}
