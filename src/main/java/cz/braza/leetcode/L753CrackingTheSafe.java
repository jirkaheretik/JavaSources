package cz.braza.leetcode;

import java.util.HashMap;
import java.util.Map;

/*
753. Cracking the Safe
Hard
Topics
Companies
Hint
There is a safe protected by a password. The password is a sequence of n digits where each digit can be in the range [0, k - 1].

The safe has a peculiar way of checking the password. When you enter in a sequence, it checks the most recent n digits that were entered each time you type a digit.

For example, the correct password is "345" and you enter in "012345":
After typing 0, the most recent 3 digits is "0", which is incorrect.
After typing 1, the most recent 3 digits is "01", which is incorrect.
After typing 2, the most recent 3 digits is "012", which is incorrect.
After typing 3, the most recent 3 digits is "123", which is incorrect.
After typing 4, the most recent 3 digits is "234", which is incorrect.
After typing 5, the most recent 3 digits is "345", which is correct and the safe unlocks.
Return any string of minimum length that will unlock the safe at some point of entering it.



Example 1:

Input: n = 1, k = 2
Output: "10"
Explanation: The password is a single digit, so enter each digit. "01" would also unlock the safe.
Example 2:

Input: n = 2, k = 2
Output: "01100"
Explanation: For each possible password:
- "00" is typed in starting from the 4th digit.
- "01" is typed in starting from the 1st digit.
- "10" is typed in starting from the 3rd digit.
- "11" is typed in starting from the 2nd digit.
Thus "01100" will unlock the safe. "10011", and "11001" would also unlock the safe.


Constraints:

1 <= n <= 4
1 <= k <= 10
1 <= kn <= 4096
 */
public class L753CrackingTheSafe {
    /*
    From solutions, to have a notion how it works:
    Runs 5ms, beats 87.1%
     */
    public static String crackSafe(int n, int k) {
        //only 1 digit password so we try all possible values from 0 to k -1;
        if (n == 1) return "0123456789".substring(0, k);
        //password can only be 0s
        if (k == 1) return "0000".substring(4-n);

        Map<String, Integer> suffixMap = new HashMap<>();
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < n - 1;  i++)
            result.append("0");

        for (int i = 0; i < Math.pow(k, n); i++) {
            //get the last suffix in result string
            String suffix = result.substring(result.length() - n + 1);
            //the map record the next digit to append once the current suffix showed later as prefix
            //for each prefix the value start from 'k-1' and decreased to '0')
            suffixMap.put(suffix, suffixMap.getOrDefault(suffix, k) - 1);
            result.append(suffixMap.get(suffix));
        }

        return result.toString();
    }

    public static void main(String[] args) {
        System.out.println(crackSafe(4, 2));
    }
}
