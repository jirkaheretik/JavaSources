package cz.braza.leetcode;

/*
2259. Remove Digit From Number to Maximize Result
Easy
Topics
Companies
Hint
You are given a string number representing a positive integer and a character digit.

Return the resulting string after removing exactly one occurrence of digit from number such that the value of the resulting string in decimal form is maximized. The test cases are generated such that digit occurs at least once in number.



Example 1:

Input: number = "123", digit = "3"
Output: "12"
Explanation: There is only one '3' in "123". After removing '3', the result is "12".
Example 2:

Input: number = "1231", digit = "1"
Output: "231"
Explanation: We can remove the first '1' to get "231" or remove the second '1' to get "123".
Since 231 > 123, we return "231".
Example 3:

Input: number = "551", digit = "5"
Output: "51"
Explanation: We can remove either the first or second '5' from "551".
Both result in the string "51".


Constraints:

2 <= number.length <= 100
number consists of digits from '1' to '9'.
digit is a digit from '1' to '9'.
digit occurs at least once in number.
 */
public class L2259RemoveDigitToMaximizeNumber {
    // just try first and last position of a given digit, compare the results and return greater
    // actually can give WRONG result for long numbers, we need to:
    // "remove the first target digit followed by a higher digit, or last digit if none such is found
    public String removeDigitIncorrect(String number, char digit) {
        String digitStr = "" + digit;
        int firstIdx = number.indexOf(digitStr);
        String first = number.substring(0, firstIdx) + number.substring(firstIdx + 1);
        int lastIdx = number.lastIndexOf(digitStr);
        if (lastIdx != firstIdx) {
            String second = number.substring(0, lastIdx) + number.substring(lastIdx + 1);
            if (second.compareTo(first) > 0)
                first = second;
        }
        return first;
    }

    // see discussion above. Lets find first occurrence of digit followed by a higher
    // digit, or use last digit position if no such occurrence found.
    // runs 1ms, beats 80.5%
    public String removeDigit(String number, char digit) {
        String digitStr = "" + digit;
        int lastPos = -1;
        for (int index = 0; index < number.length(); index++) {
            char currDigit = number.charAt(index);
            if (currDigit == digit) {
                lastPos = index;
                if (lastPos + 1 < number.length() && currDigit < number.charAt(index + 1))
                    break; // search no longer
            }
        }
        return number.substring(0, lastPos) + number.substring(lastPos + 1);
    }

}
