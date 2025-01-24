package cz.braza.leetcode;

/*
6. Zigzag Conversion
Medium
Topics
Companies
The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may want to display this pattern in a fixed font for better legibility)

P   A   H   N
A P L S I I G
Y   I   R
And then read line by line: "PAHNAPLSIIGYIR"

Write the code that will take a string and make this conversion given a number of rows:

string convert(string s, int numRows);


Example 1:

Input: s = "PAYPALISHIRING", numRows = 3
Output: "PAHNAPLSIIGYIR"
Example 2:

Input: s = "PAYPALISHIRING", numRows = 4
Output: "PINALSIGYAHRPI"
Explanation:
P     I    N
A   L S  I G
Y A   H R
P     I
Example 3:

Input: s = "A", numRows = 1
Output: "A"


Constraints:

1 <= s.length <= 1000
s consists of English letters (lower-case and upper-case), ',' and '.'.
1 <= numRows <= 1000
 */
public class L6ZigzagConversion {
    /*
    The only trick is to get the indices of characters to use.
    In the top row and bottom row, we always go by (2n-2) positions.
    In the middle rows, we alternate two spans/skips, that sum to (2n-2) as well.

    Runs 3ms, beats 90.1%, 1157 testcases
     */
    public static String convert(String s, int numRows) {
        if (numRows < 2) return s;
        int max = s.length();
        StringBuilder sb = new StringBuilder();
        // process individual rows:
        for (int row = 0; row < numRows; row++) {
            int first = row;
            int span1 = 2 * (numRows - 1 - row);
            int span2 = 2 * (numRows - 1) - span1;
            if (span2 == 0) span2 = span1;
            if (span1 == 0) span1 = span2;
            boolean useSpan1 = true;
            while (first < max) {
                sb.append(s.charAt(first));
                first += useSpan1 ? span1 : span2;
                useSpan1 = !useSpan1;
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(convert("ABECEDAJENASEVEDA", 2));
        System.out.println(convert("ABECEDAJENASEVEDA", 3));
        System.out.println(convert("ABECEDAJENASEVEDA", 4));
    }
}
